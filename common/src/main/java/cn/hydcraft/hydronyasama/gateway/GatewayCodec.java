/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.gateway;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public final class GatewayCodec {
    private static final Gson GSON = new GsonBuilder().disableHtmlEscaping().create();

    private GatewayCodec() {
    }

    public static GatewayEnvelope decode(byte[] payload) {
        JsonObject root = GSON.fromJson(new String(payload, StandardCharsets.UTF_8), JsonObject.class);
        if (root == null || !root.has("type")) {
            throw new JsonParseException("Missing type field");
        }
        GatewayMessageType type = GatewayMessageType.fromWireName(root.get("type").getAsString());
        UUID connectionId = root.has("connectionId") ? UUID.fromString(root.get("connectionId").getAsString()) : null;
        JsonObject body = root.has("body") && root.get("body").isJsonObject() ? root.getAsJsonObject("body") : new JsonObject();
        return new GatewayEnvelope(type, connectionId, body);
    }

    public static byte[] encode(GatewayMessageType type, UUID connectionId, JsonObject body) {
        JsonObject root = new JsonObject();
        root.addProperty("type", type.wireName());
        root.addProperty("timestamp", Instant.now().toEpochMilli());
        if (connectionId != null) {
            root.addProperty("connectionId", connectionId.toString());
        }
        root.add("body", body != null ? body : new JsonObject());
        return GSON.toJson(root).getBytes(StandardCharsets.UTF_8);
    }

    public static JsonObject parseBody(byte[] payload) {
        JsonObject root = GSON.fromJson(new String(payload, StandardCharsets.UTF_8), JsonObject.class);
        if (root == null) {
            throw new JsonParseException("Invalid JSON");
        }
        return root;
    }
}
