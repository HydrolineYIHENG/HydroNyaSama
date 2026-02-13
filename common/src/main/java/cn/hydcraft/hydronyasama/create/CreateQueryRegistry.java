/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.create;

import java.util.concurrent.atomic.AtomicReference;

public final class CreateQueryRegistry {
    private static final AtomicReference<CreateQueryGateway> GATEWAY = new AtomicReference<CreateQueryGateway>(CreateQueryGateway.UNAVAILABLE);

    private CreateQueryRegistry() {
    }

    public static CreateQueryGateway get() {
        return GATEWAY.get();
    }

    public static void register(CreateQueryGateway gateway) {
        GATEWAY.set(gateway == null ? CreateQueryGateway.UNAVAILABLE : gateway);
    }
}
