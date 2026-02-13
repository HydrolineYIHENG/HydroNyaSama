/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.service.create;

import java.util.Optional;

import com.google.gson.JsonObject;

import cn.hydcraft.hydronyasama.create.CreateJsonWriter;
import cn.hydcraft.hydronyasama.create.CreateNetworkSnapshot;
import cn.hydcraft.hydronyasama.create.CreateQueryGateway;
import cn.hydcraft.hydronyasama.protocol.BeaconMessage;
import cn.hydcraft.hydronyasama.protocol.BeaconResponse;
import cn.hydcraft.hydronyasama.transport.TransportContext;

public final class CreateGetNetworkActionHandler extends AbstractCreateActionHandler {
    public static final String ACTION = "create:get_network";

    @Override
    public String action() {
        return ACTION;
    }

    @Override
    public BeaconResponse handle(BeaconMessage message, TransportContext context) {
        CreateQueryGateway gateway = gateway();
        if (!gateway.isReady()) {
            return notReady(message.getRequestId());
        }
        JsonObject payload = message.getPayload();
        String graphId = payload != null && payload.has("graphId") ? payload.get("graphId").getAsString() : null;
        boolean includePolylines = payload == null || !payload.has("includePolylines") || payload.get("includePolylines").getAsBoolean();
        Optional<CreateNetworkSnapshot> snapshot = gateway.fetchNetworkSnapshot(graphId, includePolylines);
        if (graphId != null && !snapshot.isPresent()) {
            return invalidPayload(message.getRequestId(), "unknown graphId");
        }
        CreateNetworkSnapshot networkSnapshot = snapshot.orElseGet(() -> new CreateNetworkSnapshot(null, null, null, null, null, null, null));
        JsonObject responsePayload = CreateJsonWriter.writeNetworkSnapshot(networkSnapshot, includePolylines);
        return ok(message.getRequestId(), responsePayload);
    }
}
