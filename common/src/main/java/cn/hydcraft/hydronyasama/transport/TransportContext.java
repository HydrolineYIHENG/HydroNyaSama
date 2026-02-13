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
import java.util.UUID;

/**
 * Transport-layer metadata describing the origin of an incoming request.
 */
public final class TransportContext {
    private final UUID originId;
    private final TransportKind kind;
    private final Instant receivedAt;

    public TransportContext(UUID originId, TransportKind kind, Instant receivedAt) {
        this.originId = originId;
        this.kind = kind;
        this.receivedAt = receivedAt;
    }

    public UUID getOriginId() {
        return originId;
    }

    public TransportKind getKind() {
        return kind;
    }

    public Instant getReceivedAt() {
        return receivedAt;
    }
}
