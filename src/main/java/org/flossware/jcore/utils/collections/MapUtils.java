/*
 * Copyright (C) 2016 flossware
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.flossware.jcore.utils.collections;

import java.util.Map;
import org.flossware.jcore.utils.ObjectUtils;

/**
 *
 * @author flossware
 */
public class MapUtils {

    /**
     * Default minimum size for a map.
     */
    public static final int DEFAULT_MIN_MAP_SIZE = 1;

    /**
     * Error message when a map is bad.
     */
    public static final String MAP_ERROR_MSG = "Must have a map";

    /**
     * Ensures map is not empty or null.
     *
     * @param <K>      key type.
     * @param <V>      value type.
     *
     * @param map      to check for null/empty.
     * @param minSize  the minimum size of the map.
     * @param errorMsg the message within the IllegalArgumentException if empty or null.
     *
     * @return map if it is not null and contains minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static <K, V> Map<K, V> ensureMap(final Map<K, V> map, final int minSize, final String errorMsg) throws IllegalArgumentException {
        ObjectUtils.ensureObject((Object) map, errorMsg);

        if (map.size() < minSize) {
            throw new IllegalArgumentException(errorMsg);
        }

        return map;
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param <K>      key type.
     * @param <V>      value type.
     *
     * @param map      to check for null/empty.
     * @param errorMsg the message within the IllegalArgumentException if empty or null.
     *
     * @return map if it is not null and does not contain DEFAULT_MIN_MAP_SIZE elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static <K, V> Map<K, V> ensureMap(final Map<K, V> map, final String errorMsg) throws IllegalArgumentException {
        return ensureMap(map, DEFAULT_MIN_MAP_SIZE, errorMsg);
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param <K>     key type.
     * @param <V>     value type.
     *
     * @param map     to check for null/empty.
     * @param minSize the minimum size of the list.
     *
     * @return map if it is not null and does not contain minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static <K, V> Map<K, V> ensureMap(final Map<K, V> map, final int minSize) throws IllegalArgumentException {
        return ensureMap(map, minSize, MAP_ERROR_MSG);
    }

    /**
     * Ensures map is not empty or null.
     *
     * @param map to check for null/empty.
     *
     * @return map if it is not null and does not contain minSize elements.
     *
     * @throws IllegalArgumentException if <code>map</code> is empty or null.
     */
    public static <K, V> Map<K, V> ensureMap(final Map<K, V> map) throws IllegalArgumentException {
        return ensureMap(map, DEFAULT_MIN_MAP_SIZE);
    }

    /**
     * Default constructor not allowed.
     */
    private MapUtils() {
    }
}
