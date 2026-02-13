/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.transport;

import java.util.UUID;

import cn.hydcraft.hydronyasama.protocol.BeaconResponse;

/**
 * Abstraction for sending responses back through the plugin channel.
 */
public interface ChannelMessenger {
    void reply(UUID playerUuid, BeaconResponse response);
}
