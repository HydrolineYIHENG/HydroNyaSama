/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.create;

import java.util.Optional;

public interface CreateQueryGateway {
    boolean isReady();

    Optional<CreateNetworkSnapshot> fetchNetworkSnapshot(String graphId, boolean includePolylines);

    CreateRealtimeSnapshot fetchRealtimeSnapshot();

    CreateQueryGateway UNAVAILABLE = new CreateQueryGateway() {
        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public Optional<CreateNetworkSnapshot> fetchNetworkSnapshot(String graphId, boolean includePolylines) {
            return Optional.empty();
        }

        @Override
        public CreateRealtimeSnapshot fetchRealtimeSnapshot() {
            return CreateRealtimeSnapshot.empty();
        }
    };
}
