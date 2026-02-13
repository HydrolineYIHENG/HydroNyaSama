/*
 * HydroNyaSama - forge-1.18.2
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.forge;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.core.content.ModContent;
import cn.hydcraft.hydronyasama.forge.content.ForgeContentRegistrar;
import cn.hydcraft.hydronyasama.forge.network.ForgeBeaconNetwork;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * Forge bootstrap that wires shared logic and keeps room for Forge-only hooks.
 */
@Mod(BeaconProviderMod.MOD_ID)
public final class BeaconProviderForge {
    public BeaconProviderForge() {
        BeaconProviderMod.init();
        new ForgeBeaconNetwork();
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModContent.bootstrap(new ForgeContentRegistrar(modBus));
        modBus.addListener(this::onCommonSetup);
    }

    private void onCommonSetup(final FMLCommonSetupEvent event) {
        // Register Forge specifics here when needed.
    }
}
