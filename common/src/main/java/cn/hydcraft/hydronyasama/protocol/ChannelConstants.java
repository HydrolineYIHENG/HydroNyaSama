/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.protocol;

/**
 * Shared constants for the lightweight JSON protocol used by Bukkit plugins and the mod.
 */
public final class ChannelConstants {
    public static final String CHANNEL_NAMESPACE = "hydroline";
    public static final String CHANNEL_NAME = CHANNEL_NAMESPACE + ":beacon_provider";
    public static final int PROTOCOL_VERSION = 1;
    public static final String DEFAULT_ACTION = "beacon:invoke";
    public static final int MAX_PAYLOAD_BYTES = 2 * 1024; // Plugin channel limit is 32KB, keep JSON small.

    private ChannelConstants() {
    }
}
