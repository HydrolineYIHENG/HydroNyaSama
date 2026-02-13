/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.gateway;

public enum GatewayMessageType {
    HANDSHAKE("handshake"),
    HANDSHAKE_ACK("handshake_ack"),
    REQUEST("request"),
    RESPONSE("response"),
    PING("ping"),
    PONG("pong"),
    ERROR("error");

    private final String wireName;

    GatewayMessageType(String wireName) {
        this.wireName = wireName;
    }

    public String wireName() {
        return wireName;
    }

    public static GatewayMessageType fromWireName(String wireName) {
        for (GatewayMessageType type : values()) {
            if (type.wireName.equalsIgnoreCase(wireName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown gateway message type: " + wireName);
    }
}
