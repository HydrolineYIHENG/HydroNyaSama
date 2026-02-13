/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.core.registry;

import java.util.ArrayList;
import java.util.List;

/**
 * Builder for data-driven content registration.
 * Allows defining blocks/items in a declarative manner and registering them efficiently.
 */
public final class DataDrivenContentBuilder {

    private final String group;
    private final String namespace;
    private final List<ContentData> contentData;

    private DataDrivenContentBuilder(String group, String namespace) {
        this.group = group;
        this.namespace = namespace;
        this.contentData = new ArrayList<>();
    }

    /**
     * Creates a new builder for specified group and namespace.
     */
    public static DataDrivenContentBuilder create(String group, String namespace) {
        return new DataDrivenContentBuilder(group, namespace);
    }

    /**
     * Adds a simple block with variants.
     */
    public DataDrivenContentBuilder addBlock(String idPath, String material) {
        return addBlock(idPath, material, idPath);
    }

    /**
     * Adds a simple block with variants using custom texture.
     */
    public DataDrivenContentBuilder addBlock(String idPath, String material, String texture) {
        return addBlock(idPath, material, texture, 0);
    }

    /**
     * Adds a simple block with variants using custom texture and light level.
     */
    public DataDrivenContentBuilder addBlock(String idPath, String material, String texture, int lightLevel) {
        return addBlock(idPath, material, texture, lightLevel, null);
    }

    /**
     * Adds a simple block with variants.
     */
    public DataDrivenContentBuilder addBlock(String idPath, String material, String texture, int lightLevel, String kind) {
        String effectiveKind = (kind != null) ? kind : "cube";
        contentData.add(new ContentData(ContentId.of(namespace, idPath), group, effectiveKind, material, texture, lightLevel, null, true));
        return this;
    }

    /**
     * Adds a block without variants.
     */
    public DataDrivenContentBuilder addSimpleBlock(String idPath, String material) {
        return addSimpleBlock(idPath, material, idPath);
    }

    /**
     * Adds a simple block without variants using custom texture.
     */
    public DataDrivenContentBuilder addSimpleBlock(String idPath, String material, String texture) {
        return addSimpleBlock(idPath, material, texture, 0);
    }

    /**
     * Adds a simple block without variants using custom texture and light level.
     */
    public DataDrivenContentBuilder addSimpleBlock(String idPath, String material, String texture, int lightLevel) {
        return addSimpleBlock(idPath, material, texture, lightLevel, null);
    }

    /**
     * Adds a simple block without variants.
     */
    public DataDrivenContentBuilder addSimpleBlock(String idPath, String material, String texture, int lightLevel, String kind) {
        String effectiveKind = (kind != null) ? kind : "simple_block";
        contentData.add(new ContentData(ContentId.of(namespace, idPath), group, effectiveKind, material, texture, lightLevel, null, false));
        return this;
    }

    /**
     * Adds a glass block with variants.
     */
    public DataDrivenContentBuilder addGlassBlock(String idPath, String material) {
        return addGlassBlock(idPath, material, idPath);
    }

    /**
     * Adds a glass block with variants using custom texture.
     */
    public DataDrivenContentBuilder addGlassBlock(String idPath, String material, String texture) {
        return addGlassBlock(idPath, material, texture, 15);
    }

    /**
     * Adds a glass block with variants using custom texture and light level.
     */
    public DataDrivenContentBuilder addGlassBlock(String idPath, String material, String texture, int lightLevel) {
        String kind = lightLevel > 0 ? "simple_glass_block" : "simple_block";
        contentData.add(new ContentData(ContentId.of(namespace, idPath), group, kind, material, texture, lightLevel, null, true));
        return this;
    }

    /**
     * Adds a glass block without variants.
     */
    public DataDrivenContentBuilder addSimpleGlassBlock(String idPath, String material) {
        return addSimpleGlassBlock(idPath, material, idPath);
    }

    /**
     * Adds a glass block without variants using custom texture.
     */
    public DataDrivenContentBuilder addSimpleGlassBlock(String idPath, String material, String texture) {
        return addSimpleGlassBlock(idPath, material, texture, 15);
    }

    /**
     * Adds a glass block without variants using custom texture and light level.
     */
    public DataDrivenContentBuilder addSimpleGlassBlock(String idPath, String material, String texture, int lightLevel) {
        String kind = lightLevel > 0 ? "simple_glass_block" : "simple_block";
        contentData.add(new ContentData(ContentId.of(namespace, idPath), group, kind, material, texture, lightLevel, null, false));
        return this;
    }

    /**
     * Adds multiple blocks with same material.
     */
    public DataDrivenContentBuilder addBlocks(String material, String... idPaths) {
        for (String idPath : idPaths) {
            addBlock(idPath, material);
        }
        return this;
    }

    /**
     * Adds multiple simple blocks without variants.
     */
    public DataDrivenContentBuilder addSimpleBlocks(String material, String... idPaths) {
        for (String idPath : idPaths) {
            addSimpleBlock(idPath, material);
        }
        return this;
    }

    /**
     * Adds multiple glass blocks without variants.
     */
    public DataDrivenContentBuilder addSimpleGlassBlocks(String material, String... idPaths) {
        for (String idPath : idPaths) {
            addSimpleGlassBlock(idPath, material);
        }
        return this;
    }

    /**
     * Registers all content using provided registrar.
     */
    public void register(ContentRegistrar registrar) {
        ContentRegistrar efficientRegistrar = new BatchContentRegistrar(registrar);
        register(efficientRegistrar);
        ((BatchContentRegistrar) efficientRegistrar).flush();
    }

    /**
     * Registers all content using provided registrar.
     */
    public void register(ContentRegistrar registrar, boolean useBatch) {
        if (useBatch) {
            ContentRegistrar batchRegistrar = new BatchContentRegistrar(registrar);
            register(batchRegistrar);
            ((BatchContentRegistrar) batchRegistrar).flush();
        } else {
            register(registrar);
        }
    }

    private void register(ContentRegistrar registrar) {
        for (ContentData data : contentData) {
            // Register main block
            registrar.registerBlock(new ContentRegistrar.BlockDefinition(data.id, data.group, data.kind, data.material, data.texture, data.lightLevel, data.baseBlockId));

            // Register item
            registrar.registerItem(new ContentRegistrar.ItemDefinition(data.id, data.group, "block_item", data.id));

            // Register variants if enabled
            if (data.hasVariants) {
                registerVariants(registrar, data);
            }
        }
    }

    private void registerVariants(ContentRegistrar registrar, ContentData data) {
        String basePath = data.id.path();
        if (basePath.endsWith("_block")) {
            basePath = basePath.substring(0, basePath.length() - 6);
        }

        for (VariantType variant : VariantType.values()) {
            String suffix = variant.getSuffix();
            String kind = variant.getKind();

            ContentId variantId = ContentId.of(data.id.namespace(), basePath + "_" + suffix);
            registrar.registerBlock(new ContentRegistrar.BlockDefinition(variantId, data.group, kind, data.material, data.texture, 0, data.id));
            registrar.registerItem(new ContentRegistrar.ItemDefinition(variantId, data.group, "block_item", variantId));
        }
    }

    private enum VariantType {
        CARPET("carpet", "carpet"),
        EDGE("edge", "edge"),
        RAILING("railing", "railing"),
        ROOF("roof", "roof"),
        FENCE("fence", "fence"),
        FENCE_GATE("fence_gate", "fence_gate"),
        PANE("pane", "pane"),
        SLAB("slab", "slab"),
        STAIRS("stairs", "stairs"),
        STRIP("strip", "strip"),
        VSLAB("v_slab", "v_slab"),
        VSTRIP("v_strip", "v_strip"),
        WALL("wall", "wall");

        private final String suffix;
        private final String kind;

        VariantType(String suffix, String kind) {
            this.suffix = suffix;
            this.kind = kind;
        }

        public String getSuffix() {
            return suffix;
        }

        public String getKind() {
            return kind;
        }
    }

    private static final class ContentData {

        final ContentId id;
        final String group;
        final String kind;
        final String material;
        final String texture;
        final int lightLevel;
        final ContentId baseBlockId;
        final boolean hasVariants;

        ContentData(ContentId id, String group, String kind, String material, String texture, int lightLevel, ContentId baseBlockId, boolean hasVariants) {
            this.id = id;
            this.group = group;
            this.kind = kind;
            this.material = material;
            this.texture = texture;
            this.lightLevel = lightLevel;
            this.baseBlockId = baseBlockId;
            this.hasVariants = hasVariants;
        }
    }
}
