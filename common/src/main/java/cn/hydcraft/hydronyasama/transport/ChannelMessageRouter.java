/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.transport;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import cn.hydcraft.hydronyasama.protocol.BeaconResponse;
import cn.hydcraft.hydronyasama.service.BeaconProviderService;

/**
 * Glue code used by loader-specific entrypoints to wire channel events into the shared service.
 */
public final class ChannelMessageRouter {
    private final BeaconRequestDispatcher dispatcher;
    private final ChannelMessenger messenger;

    public ChannelMessageRouter(BeaconProviderService service, ChannelMessenger messenger) {
        this.dispatcher = new BeaconRequestDispatcher(Objects.requireNonNull(service, "service"));
        this.messenger = Objects.requireNonNull(messenger, "messenger");
    }

    public void handleIncoming(UUID playerUuid, byte[] payload) {
        TransportContext context = new TransportContext(playerUuid, TransportKind.PLUGIN_MESSAGE, Instant.now());
        BeaconResponse response = dispatcher.dispatch(payload, context);
        messenger.reply(playerUuid, response);
    }
}
