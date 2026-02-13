/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.transport;

/**
 * Indicates which low-level transport carried a Beacon request.
 */
public enum TransportKind {
    PLUGIN_MESSAGE,
    NETTY_GATEWAY
}
