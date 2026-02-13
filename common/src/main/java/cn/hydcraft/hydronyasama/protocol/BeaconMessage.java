/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.protocol;

import com.google.gson.JsonObject;

/**
 * Represents a JSON payload coming from Bukkit via the shared plugin messaging channel.
 */
public final class BeaconMessage {
    private final int protocolVersion;
    private final String requestId;
    private final String action;
    private final JsonObject payload;

    public BeaconMessage(int protocolVersion, String requestId, String action, JsonObject payload) {
        this.protocolVersion = protocolVersion;
        this.requestId = requestId;
        this.action = action;
        this.payload = payload;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public String getRequestId() {
        return requestId;
    }

    public String getAction() {
        return action;
    }

    public JsonObject getPayload() {
        return payload;
    }
}
