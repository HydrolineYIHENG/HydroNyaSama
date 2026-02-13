/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.service;

import java.util.Arrays;

import cn.hydcraft.hydronyasama.service.create.CreateGetNetworkActionHandler;
import cn.hydcraft.hydronyasama.service.create.CreateGetRealtimeActionHandler;
import cn.hydcraft.hydronyasama.service.mtr.MtrGetAllStationSchedulesActionHandler;
import cn.hydcraft.hydronyasama.service.mtr.MtrGetDepotTrainsActionHandler;
import cn.hydcraft.hydronyasama.service.mtr.MtrGetRailwaySnapshotActionHandler;
import cn.hydcraft.hydronyasama.service.mtr.MtrGetRouteTrainsActionHandler;
import cn.hydcraft.hydronyasama.service.mtr.MtrGetStationScheduleActionHandler;

/**
 * Factory helpers to keep loader entrypoints concise.
 */
public final class BeaconServiceFactory {
    private BeaconServiceFactory() {
    }

    public static DefaultBeaconProviderService createDefault() {
        return new DefaultBeaconProviderService(Arrays.asList(
            new PingActionHandler(),
            new MtrGetRailwaySnapshotActionHandler(),
            new MtrGetRouteTrainsActionHandler(),
            new MtrGetStationScheduleActionHandler(),
            new MtrGetAllStationSchedulesActionHandler(),
            new MtrGetDepotTrainsActionHandler(),
            new CreateGetNetworkActionHandler(),
            new CreateGetRealtimeActionHandler()
        ));
    }
}
