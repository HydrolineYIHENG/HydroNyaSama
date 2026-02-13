/*
 * HydroNyaSama - common
 * Copyright (c) 2024 HydroCraft
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package cn.hydcraft.hydronyasama.core.registry;

import java.util.Objects;

public final class ContentId {

    private final String namespace;
    private final String path;

    public ContentId(String namespace, String path) {
        this.namespace = requireNonEmpty(namespace, "namespace");
        this.path = requireNonEmpty(path, "path");
    }

    public static ContentId of(String namespace, String path) {
        return new ContentId(namespace, path);
    }

    public String namespace() {
        return namespace;
    }

    public String path() {
        return path;
    }

    @Override
    public String toString() {
        return namespace + ":" + path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContentId)) return false;
        ContentId contentId = (ContentId) o;
        return namespace.equals(contentId.namespace) && path.equals(contentId.path);
    }

    @Override
    public int hashCode() {
        return Objects.hash(namespace, path);
    }

    private static String requireNonEmpty(String value, String name) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(name + " is empty");
        }
        return value;
    }
}
