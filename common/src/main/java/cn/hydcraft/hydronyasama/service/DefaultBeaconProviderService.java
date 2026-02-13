/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.service;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import cn.hydcraft.hydronyasama.protocol.BeaconMessage;
import cn.hydcraft.hydronyasama.protocol.BeaconResponse;
import cn.hydcraft.hydronyasama.protocol.ChannelConstants;
import cn.hydcraft.hydronyasama.protocol.ResultCode;
import cn.hydcraft.hydronyasama.transport.TransportContext;

/**
 * Thread-safe registry of beacon actions with sensible fallbacks.
 */
public final class DefaultBeaconProviderService implements BeaconProviderService {
    private final Map<String, BeaconActionHandler> handlers = new ConcurrentHashMap<>();

    public DefaultBeaconProviderService(Collection<BeaconActionHandler> initialHandlers) {
        if (initialHandlers != null) {
            initialHandlers.forEach(this::register);
        }
    }

    public DefaultBeaconProviderService register(BeaconActionHandler handler) {
        Objects.requireNonNull(handler, "handler");
        String action = handler.action();
        if (action == null || action.isEmpty()) {
            throw new IllegalArgumentException("action cannot be empty");
        }
        handlers.put(action, handler);
        return this;
    }

    @Override
    public BeaconResponse handle(BeaconMessage request, TransportContext context) {
        if (request.getProtocolVersion() != ChannelConstants.PROTOCOL_VERSION) {
            return BeaconResponse.builder(request.getRequestId())
                .result(ResultCode.INVALID_PAYLOAD)
                .message("Unsupported protocol version: " + request.getProtocolVersion())
                .build();
        }

        BeaconActionHandler handler = handlers.getOrDefault(request.getAction(), handlers.get(ChannelConstants.DEFAULT_ACTION));
        if (handler == null) {
            return BeaconResponse.builder(request.getRequestId())
                .result(ResultCode.INVALID_ACTION)
                .message("Unknown action: " + request.getAction())
                .build();
        }

        try {
            return handler.handle(request, context);
        } catch (Exception ex) {
            return BeaconResponse.builder(request.getRequestId())
                .result(ResultCode.ERROR)
                .message("Handler error: " + ex.getMessage())
                .build();
        }
    }
}
