/*
 * HydroNyaSama - fabric-1.18.2
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.fabric;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.core.content.ModContent;
import cn.hydcraft.hydronyasama.fabric.content.FabricContentRegistrar;
import cn.hydcraft.hydronyasama.fabric.network.FabricBeaconNetwork;
import net.fabricmc.api.ModInitializer;

/**
 * Fabric entrypoint delegating into the shared bootstrap.
 */
public final class BeaconProviderFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BeaconProviderMod.init();
        ModContent.bootstrap(new FabricContentRegistrar());
        new FabricBeaconNetwork();
    }
}
