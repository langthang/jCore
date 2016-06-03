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

import org.flossware.jcore.utils.ObjectUtils;

/**
 * Array utility class.
 *
 * @author flossware
 */
public class ArrayUtils {

    /**
     * Default minimum size for an array.
     */
    public static final int DEFAULT_MIN_ARRAY_LENGTH = 1;

    /**
     * Ensure an array has something in it.
     *
     * @param <V> the type of array.
     * @param values represents the array to ensureObject there are value or has at least one element.
     * @param minLength the minimum number of elements in the array.
     * @param errorMsg is the message of the IllegalArgumentException if raised.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final int minLength, final String errorMsg) throws IllegalArgumentException {
        ObjectUtils.ensureObject((Object) values, errorMsg);

        if (values.length < minLength) {
            throw new IllegalArgumentException(errorMsg);
        }

        for (final V value : values) {
            ObjectUtils.ensureObject(value, errorMsg);
        }

        return values;
    }

    /**
     * Ensure an array has something in it.
     *
     * @param <V> the type of array.
     * @param values represents the array to ensureObject there are value or has at least one element.
     * @param minLength the minimum number of elements in the array.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final int minLength) throws IllegalArgumentException {
        return ensureArray(values, minLength, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param values represents the array to ensureObject there are value or has at least one element.
     * @param errorMsg is the message of the IllegalArgumentException if raised.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values, final String errorMsg) throws IllegalArgumentException {
        return ensureArray(values, DEFAULT_MIN_ARRAY_LENGTH, errorMsg);
    }

    /**
     * Ensure an array has something in it.
     *
     * @param values represents the array to ensureObject there are value or has at least one element.
     *
     * @return values if not null, contains at least one element and each element is not null.
     *
     * @throws IllegalArgumentException if values has any nulls or does not have at least 1 element.
     */
    public static <V> V[] ensureArray(final V[] values) throws IllegalArgumentException {
        return ensureArray(values, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * See if a collection meets a filter.
     *
     * @param <T> the type within the collection to search.
     * @param <V> the value to search for within the collection.
     *
     * @param toSearch the collection to iterate over to see if it contains a value.
     * @param filter the filter to apply to each object.
     * @param value using filter, determining if it is contained in <code>toSearch</code>.
     *
     * @return true if the collection meets the filter or false if not.
     */
//    public static <T, V> boolean contains(final T[] toSearch, final Filter<T, V> filter, final V value) {
//        getLogger().log(Level.FINE, "Determining if [{0}] is found in {1}", new Object[]{value, toSearch});
//
//        ensureArray(toSearch, 0, "Must have a collection to filter");
//        ObjectUtils.ensureObject(filter, "Must have a filter");
//
//        return contains(Arrays.asList(toSearch), filter, value);
//    }
    /**
     * Default constructor not allowed
     */
    private ArrayUtils() {
    }
}
