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
 * Lightweight status enum mirrored on Bukkit side for quick parsing.
 */
public enum ResultCode {
    OK,
    BUSY,
    INVALID_ACTION,
    INVALID_PAYLOAD,
    NOT_READY,
    ERROR
}
