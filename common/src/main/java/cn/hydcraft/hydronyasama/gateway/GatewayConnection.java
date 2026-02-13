/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.gateway;

import java.net.SocketAddress;
import java.util.UUID;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.google.gson.JsonObject;

import io.netty.channel.Channel;

final class GatewayConnection {
    private final Channel channel;
    private final UUID tempId = UUID.randomUUID();
    private UUID connectionId;
    private boolean handshakeComplete;
    private ScheduledFuture<?> handshakeTimeout;

    GatewayConnection(Channel channel) {
        this.channel = channel;
    }

    UUID temporaryId() {
        return tempId;
    }

    UUID connectionId() {
        return connectionId;
    }

    void markHandshakeComplete(UUID connectionId) {
        this.connectionId = connectionId;
        this.handshakeComplete = true;
    }

    boolean handshakeComplete() {
        return handshakeComplete;
    }

    void send(GatewayMessageType type, JsonObject body) {
        channel.writeAndFlush(channel.alloc().buffer().writeBytes(GatewayCodec.encode(type, connectionId, body)));
    }

    void scheduleHandshakeTimeout(Runnable action, long delay, TimeUnit unit) {
        this.handshakeTimeout = channel.eventLoop().schedule(action, delay, unit);
    }

    void cancelHandshakeTimeout() {
        if (handshakeTimeout != null) {
            handshakeTimeout.cancel(false);
            handshakeTimeout = null;
        }
    }

    void close() {
        cancelHandshakeTimeout();
        channel.close();
    }

    SocketAddress remoteAddress() {
        return channel.remoteAddress();
    }

    Channel channel() {
        return channel;
    }
}
