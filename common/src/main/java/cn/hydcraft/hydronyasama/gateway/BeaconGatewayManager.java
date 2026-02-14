package cn.hydcraft.hydronyasama.gateway;

import cn.hydcraft.hydronyasama.BeaconProviderMod;
import cn.hydcraft.hydronyasama.transport.BeaconRequestDispatcher;
import cn.hydcraft.hydronyasama.service.BeaconProviderService;
import java.nio.file.Path;
import java.util.Objects;

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
