/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.gateway;

import java.nio.file.Path;
import java.util.Objects;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.service.BeaconProviderService;
import cn.hydcraft.hydronyasama.transport.BeaconRequestDispatcher;

public final class BeaconGatewayManager {
    private final BeaconProviderService service;
    private final GatewayConfigLoader configLoader = new GatewayConfigLoader();
    private GatewayServer server;

    public BeaconGatewayManager(BeaconProviderService service) {
        this.service = Objects.requireNonNull(service, "service");
    }

    public synchronized void start(Path configDir) {
        Objects.requireNonNull(configDir, "configDir");
        if (server != null) {
            return;
        }
        GatewayConfig config = configLoader.load(configDir);
        if (!config.enabled()) {
            BeaconProviderMod.LOGGER.info("Beacon gateway is disabled in config");
            return;
        }
        if (config.listenPort() <= 0) {
            BeaconProviderMod.LOGGER.warn("Beacon gateway port is invalid (<= 0), skipping startup");
            return;
        }
        GatewayServer instance = new GatewayServer(config, new BeaconRequestDispatcher(service));
        instance.start();
        this.server = instance;
    }

    public synchronized void stop() {
        if (server != null) {
            try {
                server.close();
            } finally {
                server = null;
            }
        }
    }
}
