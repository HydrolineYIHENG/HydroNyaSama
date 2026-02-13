/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.protocol;

import java.util.Objects;

import com.google.gson.JsonObject;

/**
 * Response envelope mirrored on Bukkit side for matching request IDs.
 */
public final class BeaconResponse {
    private final String requestId;
    private final ResultCode result;
    private final String message;
    private final JsonObject payload;

    private BeaconResponse(Builder builder) {
        this.requestId = builder.requestId;
        this.result = builder.result;
        this.message = builder.message;
        this.payload = builder.payload;
    }

    public String getRequestId() {
        return requestId;
    }

    public ResultCode getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getPayload() {
        return payload;
    }

    public Builder toBuilder() {
        return new Builder()
            .requestId(requestId)
            .result(result)
            .message(message)
            .payload(payload);
    }

    public static Builder builder(String requestId) {
        return new Builder().requestId(requestId);
    }

    public static final class Builder {
        private String requestId;
        private ResultCode result = ResultCode.OK;
        private String message = "";
        private JsonObject payload = new JsonObject();

        public Builder requestId(String requestId) {
            this.requestId = Objects.requireNonNull(requestId, "requestId");
            return this;
        }

        public Builder result(ResultCode result) {
            this.result = Objects.requireNonNull(result, "result");
            return this;
        }

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder payload(JsonObject payload) {
            this.payload = payload == null ? new JsonObject() : payload;
            return this;
        }

        public BeaconResponse build() {
            if (requestId == null || requestId.isEmpty()) {
                throw new IllegalStateException("requestId is required");
            }
            return new BeaconResponse(this);
        }
    }
}
