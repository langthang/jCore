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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.Filter;
import org.flossware.jcore.utils.LoggerUtils;
import org.flossware.jcore.utils.ObjectUtils;

/**
 * Iterator utilities.
 *
 * @author flossware
 */
public class IterableUtils {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(IterableUtils.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Error message when a iterable is null.
     */
    public static final String ITERABLE_ERROR_MSG = "Must have an iterable";

    /**
     * Error message when a filter is null.
     */
    public static final String FILTER_ERROR_MSG = "Must have a filter";

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
    public static <T, V> boolean contains(final Iterable<T> toSearch, final Filter<T, V> filter, final V value) {
        LoggerUtils.log(getLogger(), Level.FINE, "Determining if [{0}] is found in {1}", value, toSearch);

        ObjectUtils.ensureObject(toSearch, ITERABLE_ERROR_MSG);
        ObjectUtils.ensureObject(filter, FILTER_ERROR_MSG);

        for (final T item : toSearch) {
            if (filter.accept(item, value)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Default constructor not allowed.
     */
    private IterableUtils() {
    }
}
