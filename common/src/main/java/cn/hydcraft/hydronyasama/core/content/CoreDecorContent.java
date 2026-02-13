/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.core.content;

import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;
import cn.hydcraft.hydronyasama.core.registry.DataDrivenContentBuilder;

/**
 * Core decoration content registration using data-driven approach for better performance.
 */
public final class CoreDecorContent {

    private static final String GROUP = "core";
    private static final String NAMESPACE = "hydronyasama";

    private CoreDecorContent() {
    }

    /**
     * Registers all core decoration content.
     * Uses data-driven builder for optimized bulk registration.
     */
    public static void register(ContentRegistrar registrar) {
        DataDrivenContentBuilder builder = DataDrivenContentBuilder.create(GROUP, NAMESPACE);

        // Logo blocks (solid, no variants)
        builder.addSimpleBlocks("rock",
                "nsb_logo",
                "nsc_logo",
                "nsdn_logo",
                "nse_logo",
                "nso_logo",
                "nsr_logo",
                "nst_logo"
        );

        // Sign blocks (glass with light, no variants)
        builder.addSimpleGlassBlocks("glass",
                "nsb_sign",
                "nse_sign",
                "nso_sign",
                "nsr_sign",
                "nst_sign"
        );

        // Register all content using batch registration for optimal performance
        builder.register(registrar);
    }
}
