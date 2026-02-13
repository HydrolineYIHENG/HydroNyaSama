/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.service.create;

import com.google.gson.JsonObject;

import cn.hydcraft.hydronyasama.create.CreateJsonWriter;
import cn.hydcraft.hydronyasama.create.CreateQueryGateway;
import cn.hydcraft.hydronyasama.create.CreateRealtimeSnapshot;
import cn.hydcraft.hydronyasama.protocol.BeaconMessage;
import cn.hydcraft.hydronyasama.protocol.BeaconResponse;
import cn.hydcraft.hydronyasama.transport.TransportContext;

public final class CreateGetRealtimeActionHandler extends AbstractCreateActionHandler {
    public static final String ACTION = "create:get_realtime";

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
        CreateRealtimeSnapshot snapshot = gateway.fetchRealtimeSnapshot();
        JsonObject responsePayload = CreateJsonWriter.writeRealtimeSnapshot(snapshot);
        return ok(message.getRequestId(), responsePayload);
    }
}
