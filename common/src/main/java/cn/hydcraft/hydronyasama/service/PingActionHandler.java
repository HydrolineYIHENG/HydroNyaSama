/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.service;

import java.time.Duration;
import java.time.Instant;

import com.google.gson.JsonObject;

import cn.hydcraft.hydronyasama.protocol.BeaconMessage;
import cn.hydcraft.hydronyasama.protocol.BeaconResponse;
import cn.hydcraft.hydronyasama.protocol.ResultCode;
import cn.hydcraft.hydronyasama.transport.TransportContext;

/**
 * Simple built-in action that helps Bukkit check connectivity and latency.
 */
public final class PingActionHandler implements BeaconActionHandler {
    public static final String ACTION = "beacon:ping";

    @Override
    public String action() {
        return ACTION;
    }

    @Override
    public BeaconResponse handle(BeaconMessage message, TransportContext context) {
        JsonObject payload = new JsonObject();
        payload.addProperty("echo", message.getPayload().has("echo") ? message.getPayload().get("echo").getAsString() : "pong");
        payload.addProperty("receivedAt", context.getReceivedAt().toEpochMilli());
        payload.addProperty("latencyMs", Duration.between(context.getReceivedAt(), Instant.now()).abs().toMillis());
        return BeaconResponse.builder(message.getRequestId())
            .result(ResultCode.OK)
            .payload(payload)
            .build();
    }
}
