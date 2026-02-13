/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.core.content;

import cn.hydcraft.hydronyasama.building.content.BuildingContent;
import cn.hydcraft.hydronyasama.core.registry.ContentRegistrar;

public final class ModContent {

    public static final String MOD_GROUP_ID = "hydronyasama";

    private ModContent() {
    }

    public static void bootstrap(ContentRegistrar registrar) {
        CoreDecorContent.register(registrar);
        BuildingContent.register(registrar);
    }
}
