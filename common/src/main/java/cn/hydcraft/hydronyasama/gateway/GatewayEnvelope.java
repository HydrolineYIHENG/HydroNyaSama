/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.gateway;

import java.util.UUID;

import com.google.gson.JsonObject;

public final class GatewayEnvelope {
    private final GatewayMessageType type;
    private final UUID connectionId;
    private final JsonObject body;

    public GatewayEnvelope(GatewayMessageType type, UUID connectionId, JsonObject body) {
        this.type = type;
        this.connectionId = connectionId;
        this.body = body;
    }

    public GatewayMessageType type() {
        return type;
    }

    public UUID connectionId() {
        return connectionId;
    }

    public JsonObject body() {
        return body;
    }
}
