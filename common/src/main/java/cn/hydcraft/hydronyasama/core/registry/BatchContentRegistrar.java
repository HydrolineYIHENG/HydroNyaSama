/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to terms of Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.core.registry;

import java.util.ArrayList;
import java.util.List;

/**
 * Batch content registrar for optimized bulk registration.
 * Collects registration requests and applies them in batches for better performance.
 */
public final class BatchContentRegistrar {

    private final ContentRegistrar delegate;
    private final List<RegistrationRequest> pendingRegistrations;
    private final int batchSize;
    private int pendingCount;

    public BatchContentRegistrar(ContentRegistrar delegate) {
        this(delegate, 50);
    }

    public BatchContentRegistrar(ContentRegistrar delegate, int batchSize) {
        this.delegate = delegate;
        this.batchSize = batchSize;
        this.pendingRegistrations = new ArrayList<>(batchSize);
        this.pendingCount = 0;
    }

    public void registerBlock(ContentRegistrar.BlockDefinition definition) {
        pendingRegistrations.add(new RegistrationRequest(RegistrationType.BLOCK, definition));
        if (++pendingCount >= batchSize) {
            flush();
        }
    }

    public void registerItem(ContentRegistrar.ItemDefinition definition) {
        pendingRegistrations.add(new RegistrationRequest(RegistrationType.ITEM, definition));
        if (++pendingCount >= batchSize) {
            flush();
        }
    }

    /**
     * Flush all pending registrations.
     * */
    public void flush() {
        if (pendingRegistrations.isEmpty()) {
            return;
        }

        for (RegistrationRequest request : pendingRegistrations) {
            switch (request.type) {
                case BLOCK:
                    delegate.registerBlock((ContentRegistrar.BlockDefinition) request.definition);
                    break;
                case ITEM:
                    delegate.registerItem((ContentRegistrar.ItemDefinition) request.definition);
                    break;
            }
        }

        pendingRegistrations.clear();
        pendingCount = 0;
    }

    private enum RegistrationType {
        BLOCK,
        ITEM
    }

    private static final class RegistrationRequest {

        final RegistrationType type;
        final Object definition;

        RegistrationRequest(RegistrationType type, Object definition) {
            this.type = type;
            this.definition = definition;
        }
    }
}
