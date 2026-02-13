/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.building.content;

import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;
import cn.hydcraft.hydronyasama.core.registry.DataDrivenContentBuilder;

/**
 * Building content registration using data-driven approach for better performance.
 */
public final class BuildingContent {

    private static final String GROUP = "building";
    private static final String NAMESPACE = "hydronyasama";

    private BuildingContent() {
    }

    /**
     * Registers all building content.
     * Uses data-driven builder for optimized bulk registration.
     */
    public static void register(ContentRegistrar registrar) {
        DataDrivenContentBuilder builder = DataDrivenContentBuilder.create(GROUP, NAMESPACE);

        // Legacy blocks with _block suffix
        builder.addBlock("anti_static_floor_block", "rock", "anti_static_floor");
        builder.addBlock("asphalt_block", "rock", "asphalt");
        builder.addBlock("black_marble_block", "rock", "black_marble");

        // New building blocks (using texture name as ID)
        builder.addBlocks("rock",
                "big_concrete_brick",
                "brushed_aluminum",
                "cinder_brick_wall",
                "coarse_concrete_floor",
                "concrete_brick_wall",
                "concrete_rail_tunnel_down",
                "concrete_rail_tunnel_top",
                "concrete_rail_tunnel_up",
                "concrete_wall",
                "dark_grey_cell_tile",
                "dark_latex_painted_wall",
                "dense_concrete_wall",
                "dense_mesh_wire",
                "exq_marble",
                "grey_brick",
                "latex_painted_wall",
                "light_grey_cell_tile",
                "marble",
                "mesh_wire",
                "mineral_wool_celling_plate",
                "oblique_paving_brick",
                "old_brick",
                "plazza_floor",
                "red_ko_mak",
                "road_bed_stone",
                "rusty_tread_steel_plate",
                "sand_brick",
                "square_iron_mesh",
                "tatami",
                "white_brick_wall",
                "zry_bathroom_cell_tile",
                "zry_tvbg_wall"
        );

        // Corrugated blocks (color variants)
        builder.addBlocks("rock",
                "corrugated_blue",
                "corrugated_cyan",
                "corrugated_green",
                "corrugated_magenta",
                "corrugated_orange",
                "corrugated_purple",
                "corrugated_red",
                "corrugated_white",
                "corrugated_yellow"
        );

        // Road tiles
        builder.addBlocks("rock",
                "road_cyan_tile",
                "road_dark_green_tile",
                "road_dark_grey_tile",
                "road_dark_orange_tile",
                "road_dark_pink_tile",
                "road_dark_yellow_tile",
                "road_dust_yellow_tile",
                "road_grey_tile",
                "road_light_blue_tile",
                "road_light_green_tile",
                "road_light_grey_tile",
                "road_light_orange_tile",
                "road_light_pink_tile",
                "road_light_yellow_tile",
                "road_purple_tile",
                "road_red_tile",
                "road_shine_blue_tile",
                "road_shine_orange_tile",
                "road_shine_yellow_tile",
                "road_sky_blue_tile",
                "road_stone_yellow_tile",
                "road_white_tile",
                "road_yellow_tile"
        );

        // Tarps (color variants)
        builder.addBlocks("rock",
                "tarp_blue",
                "tarp_green",
                "tarp_magenta",
                "tarp_orange",
                "tarp_purple",
                "tarp_red",
                "tarp_yellow"
        );

        // Tread steel blocks (color variants)
        builder.addBlocks("rock",
                "tread_steel_black",
                "tread_steel_blue",
                "tread_steel_brown",
                "tread_steel_cyan",
                "tread_steel_green",
                "tread_steel_grey",
                "tread_steel_light_blue",
                "tread_steel_light_grey",
                "tread_steel_lime",
                "tread_steel_magenta",
                "tread_steel_orange",
                "tread_steel_pink",
                "tread_steel_purple",
                "tread_steel_red",
                "tread_steel_white",
                "tread_steel_yellow"
        );

        // Register all content using batch registration for optimal performance
        builder.register(registrar);
    }
}
