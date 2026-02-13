package cn.hydcraft.hydronyasama.building.content;

import cn.hydcraft.hydronyasama.core.registry.ContentId;
import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;

public final class BuildingContent {

    private static final String GROUP = "building";

    private BuildingContent() {
    }

    public static void register(ContentRegistrar registrar) {
        // Legacy blocks with _block suffix
        registerCube(registrar, "anti_static_floor_block", "rock", "anti_static_floor");
        registerCube(registrar, "asphalt_block", "rock", "asphalt");
        registerCube(registrar, "black_marble_block", "rock", "black_marble");

        // New blocks (using texture name as ID)
        registerCube(registrar, "big_concrete_brick", "rock", "big_concrete_brick");
        registerCube(registrar, "brushed_aluminum", "rock", "brushed_aluminum");
        registerCube(registrar, "cinder_brick_wall", "rock", "cinder_brick_wall");
        registerCube(registrar, "coarse_concrete_floor", "rock", "coarse_concrete_floor");
        registerCube(registrar, "concrete_brick_wall", "rock", "concrete_brick_wall");
        registerCube(registrar, "concrete_rail_tunnel_down", "rock", "concrete_rail_tunnel_down");
        registerCube(registrar, "concrete_rail_tunnel_top", "rock", "concrete_rail_tunnel_top");
        registerCube(registrar, "concrete_rail_tunnel_up", "rock", "concrete_rail_tunnel_up");
        registerCube(registrar, "concrete_wall", "rock", "concrete_wall");
        registerCube(registrar, "corrugated_blue", "rock", "corrugated_blue");
        registerCube(registrar, "corrugated_cyan", "rock", "corrugated_cyan");
        registerCube(registrar, "corrugated_green", "rock", "corrugated_green");
        registerCube(registrar, "corrugated_magenta", "rock", "corrugated_magenta");
        registerCube(registrar, "corrugated_orange", "rock", "corrugated_orange");
        registerCube(registrar, "corrugated_purple", "rock", "corrugated_purple");
        registerCube(registrar, "corrugated_red", "rock", "corrugated_red");
        registerCube(registrar, "corrugated_white", "rock", "corrugated_white");
        registerCube(registrar, "corrugated_yellow", "rock", "corrugated_yellow");
        registerCube(registrar, "dark_grey_cell_tile", "rock", "dark_grey_cell_tile");
        registerCube(registrar, "dark_latex_painted_wall", "rock", "dark_latex_painted_wall");
        registerCube(registrar, "dense_concrete_wall", "rock", "dense_concrete_wall");
        registerCube(registrar, "dense_mesh_wire", "rock", "dense_mesh_wire");
        registerCube(registrar, "exq_marble", "rock", "exq_marble");
        registerCube(registrar, "grey_brick", "rock", "grey_brick");
        registerCube(registrar, "latex_painted_wall", "rock", "latex_painted_wall");
        registerCube(registrar, "light_grey_cell_tile", "rock", "light_grey_cell_tile");
        registerCube(registrar, "marble", "rock", "marble");
        registerCube(registrar, "mesh_wire", "rock", "mesh_wire");
        registerCube(registrar, "mineral_wool_celling_plate", "rock", "mineral_wool_celling_plate");
        registerCube(registrar, "oblique_paving_brick", "rock", "oblique_paving_brick");
        registerCube(registrar, "old_brick", "rock", "old_brick");
        registerCube(registrar, "plazza_floor", "rock", "plazza_floor");
        registerCube(registrar, "red_ko_mak", "rock", "red_ko_mak");
        registerCube(registrar, "road_bed_stone", "rock", "road_bed_stone");
        registerCube(registrar, "road_cyan_tile", "rock", "road_cyan_tile");
        registerCube(registrar, "road_dark_green_tile", "rock", "road_dark_green_tile");
        registerCube(registrar, "road_dark_grey_tile", "rock", "road_dark_grey_tile");
        registerCube(registrar, "road_dark_orange_tile", "rock", "road_dark_orange_tile");
        registerCube(registrar, "road_dark_pink_tile", "rock", "road_dark_pink_tile");
        registerCube(registrar, "road_dark_yellow_tile", "rock", "road_dark_yellow_tile");
        registerCube(registrar, "road_dust_yellow_tile", "rock", "road_dust_yellow_tile");
        registerCube(registrar, "road_grey_tile", "rock", "road_grey_tile");
        registerCube(registrar, "road_light_blue_tile", "rock", "road_light_blue_tile");
        registerCube(registrar, "road_light_green_tile", "rock", "road_light_green_tile");
        registerCube(registrar, "road_light_grey_tile", "rock", "road_light_grey_tile");
        registerCube(registrar, "road_light_orange_tile", "rock", "road_light_orange_tile");
        registerCube(registrar, "road_light_pink_tile", "rock", "road_light_pink_tile");
        registerCube(registrar, "road_light_yellow_tile", "rock", "road_light_yellow_tile");
        registerCube(registrar, "road_purple_tile", "rock", "road_purple_tile");
        registerCube(registrar, "road_red_tile", "rock", "road_red_tile");
        registerCube(registrar, "road_shine_blue_tile", "rock", "road_shine_blue_tile");
        registerCube(registrar, "road_shine_orange_tile", "rock", "road_shine_orange_tile");
        registerCube(registrar, "road_shine_yellow_tile", "rock", "road_shine_yellow_tile");
        registerCube(registrar, "road_sky_blue_tile", "rock", "road_sky_blue_tile");
        registerCube(registrar, "road_stone_yellow_tile", "rock", "road_stone_yellow_tile");
        registerCube(registrar, "road_white_tile", "rock", "road_white_tile");
        registerCube(registrar, "road_yellow_tile", "rock", "road_yellow_tile");
        registerCube(registrar, "rusty_tread_steel_plate", "rock", "rusty_tread_steel_plate");
        registerCube(registrar, "sand_brick", "rock", "sand_brick");
        registerCube(registrar, "square_iron_mesh", "rock", "square_iron_mesh");
        registerCube(registrar, "tarp_blue", "rock", "tarp_blue");
        registerCube(registrar, "tarp_green", "rock", "tarp_green");
        registerCube(registrar, "tarp_magenta", "rock", "tarp_magenta");
        registerCube(registrar, "tarp_orange", "rock", "tarp_orange");
        registerCube(registrar, "tarp_purple", "rock", "tarp_purple");
        registerCube(registrar, "tarp_red", "rock", "tarp_red");
        registerCube(registrar, "tarp_yellow", "rock", "tarp_yellow");
        registerCube(registrar, "tatami", "rock", "tatami");
        registerCube(registrar, "tread_steel_black", "rock", "tread_steel_black");
        registerCube(registrar, "tread_steel_blue", "rock", "tread_steel_blue");
        registerCube(registrar, "tread_steel_brown", "rock", "tread_steel_brown");
        registerCube(registrar, "tread_steel_cyan", "rock", "tread_steel_cyan");
        registerCube(registrar, "tread_steel_green", "rock", "tread_steel_green");
        registerCube(registrar, "tread_steel_grey", "rock", "tread_steel_grey");
        registerCube(registrar, "tread_steel_light_blue", "rock", "tread_steel_light_blue");
        registerCube(registrar, "tread_steel_light_grey", "rock", "tread_steel_light_grey");
        registerCube(registrar, "tread_steel_lime", "rock", "tread_steel_lime");
        registerCube(registrar, "tread_steel_magenta", "rock", "tread_steel_magenta");
        registerCube(registrar, "tread_steel_orange", "rock", "tread_steel_orange");
        registerCube(registrar, "tread_steel_pink", "rock", "tread_steel_pink");
        registerCube(registrar, "tread_steel_purple", "rock", "tread_steel_purple");
        registerCube(registrar, "tread_steel_red", "rock", "tread_steel_red");
        registerCube(registrar, "tread_steel_white", "rock", "tread_steel_white");
        registerCube(registrar, "tread_steel_yellow", "rock", "tread_steel_yellow");
        registerCube(registrar, "white_brick_wall", "rock", "white_brick_wall");
        registerCube(registrar, "zry_bathroom_cell_tile", "rock", "zry_bathroom_cell_tile");
        registerCube(registrar, "zry_tvbg_wall", "rock", "zry_tvbg_wall");
    }

    private static void registerCube(ContentRegistrar registrar, String idPath, String material, String texture) {
        ContentId id = ContentId.of("hydronyasama", idPath);
        registrar.registerBlock(new ContentRegistrar.BlockDefinition(
                id,
                GROUP,
                "cube",
                material,
                texture,
                0,
                null
        ));
        registrar.registerItem(new ContentRegistrar.ItemDefinition(
                id,
                GROUP,
                "block_item",
                id
        ));

        // Register variants
        registerVariant(registrar, id, material, texture, "carpet", "carpet");
        registerVariant(registrar, id, material, texture, "edge", "edge");
        registerVariant(registrar, id, material, texture, "railing", "railing");
        registerVariant(registrar, id, material, texture, "roof", "roof");
        registerVariant(registrar, id, material, texture, "fence", "fence");
        registerVariant(registrar, id, material, texture, "fence_gate", "fence_gate");
        registerVariant(registrar, id, material, texture, "pane", "pane");
        registerVariant(registrar, id, material, texture, "slab", "slab");
        registerVariant(registrar, id, material, texture, "stairs", "stairs");
        registerVariant(registrar, id, material, texture, "strip", "strip");
        registerVariant(registrar, id, material, texture, "v_slab", "v_slab");
        registerVariant(registrar, id, material, texture, "v_strip", "v_strip");
        registerVariant(registrar, id, material, texture, "wall", "wall");
    }

    private static void registerVariant(ContentRegistrar registrar, ContentId baseId, String material, String texture, String suffix, String kind) {
        // Strip _block suffix from base ID path if present to form the prefix
        String basePath = baseId.path();
        if (basePath.endsWith("_block")) {
            basePath = basePath.substring(0, basePath.length() - 6);
        }
        
        ContentId id = ContentId.of(baseId.namespace(), basePath + "_" + suffix);
        registrar.registerBlock(new ContentRegistrar.BlockDefinition(
                id,
                GROUP,
                kind,
                material,
                texture,
                0,
                baseId
        ));
        registrar.registerItem(new ContentRegistrar.ItemDefinition(
                id,
                GROUP,
                "block_item",
                id
        ));
    }
}

