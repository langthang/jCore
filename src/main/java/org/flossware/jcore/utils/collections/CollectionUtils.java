/*
 * Copyright (C) 2016 Scot P. Floess
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.Filter;
import org.flossware.jcore.utils.ObjectUtils;

/**
 *
 * Utility class for collections.
 *
 * @author sfloess
 *
 */
public class CollectionUtils {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(CollectionUtils.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Default minimum size for a collection.
     */
    public static final int DEFAULT_MIN_COLLECTION_SIZE = 1;

    /**
     * Error message when a collection is bad.
     */
    public static final String COLLECTION_ERROR_MSG = "Must have a collection";

    /**
     * Error message when a comparator is null.
     */
    public static final String COMPARATOR_ERROR_MSG = "Must have a comparator";

    /**
     * Error message when a filter is null.
     */
    public static final String FILTER_ERROR_MSG = "Must have a filter";

    /**
     * Ensures collection is not empty or null.
     *
     * @param <C> the collection to examine for a size.
     * @param collection to check for null/empty.
     * @param minSize the minimum size of the collection.
     * @param errorMsg the message within the IllegalArgumentException if empty or null.
     *
     * @return collection if it is not null and of minimum size minSize.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or null.
     */
    public static <C extends Collection<?>> C ensureCollection(final C collection, final int minSize, final String errorMsg) throws IllegalArgumentException {
        ObjectUtils.ensureObject((Object) collection, errorMsg);

        if (collection.size() < minSize) {
            throw new IllegalArgumentException(errorMsg);
        }

        for (final Object value : collection) {
            ObjectUtils.ensureObject(value, errorMsg);
        }

        return collection;
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C> the collection to examine for a size.
     *
     * @param collection to check for null/empty.
     * @param errorMsg the message within the IllegalArgumentException if empty or null.
     *
     * @return collection if it is not null and contains DEFAULT_MIN_LIST_SIZE elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or null.
     */
    public static <C extends Collection<?>> C ensureCollection(final C collection, final String errorMsg) throws IllegalArgumentException {
        return ensureCollection(collection, DEFAULT_MIN_COLLECTION_SIZE, errorMsg);
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C> the collection to examine for a size.
     * @param collection to check for null/empty.
     * @param minSize the minimum size of the list.
     *
     * @return collection if it is not null and contains minSize elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or null.
     */
    public static <C extends Collection<?>> C ensureCollection(final C collection, final int minSize) throws IllegalArgumentException {
        return ensureCollection(collection, minSize, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * Ensures list is not empty or null.
     *
     * @param <C> the collection to examine for a size.
     * @param collection to check for null/empty.
     *
     * @return collection if it is not null and contains DEFAULT_MIN_LIST_SIZE elements.
     *
     * @throws IllegalArgumentException if <code>collection</code> is empty or null.
     */
    public static <C extends Collection<?>> C ensureCollection(final C collection) throws IllegalArgumentException {
        return ensureCollection(collection, DEFAULT_MIN_COLLECTION_SIZE);
    }

    /**
     * Only add a value if <code>isToAdd</code> is true.
     *
     * @param <V> the type contained in <code>collection</code>.
     *
     * @param collection the collection to be added to.
     * @param value the value to add to collection.
     * @param isToAdd if true, <code>value</code> will be added to <code>collection</code>, false it will not be added.
     *
     * @return the collection added to.
     */
    public static <V> Collection<V> addValue(final Collection<V> collection, final V value, final boolean isToAdd) {
        if (!isToAdd) {
            return collection;
        }

        ensureCollection(collection, 0, COLLECTION_ERROR_MSG).add(value);

        return collection;
    }

    /**
     * Turns toCopy into a list.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
//    public static <V> V[] asArray(final Collection<V> toCopy) {
//        ensureCollection(toCopy, 0, "Must have a collection");
//
//        final V[] retVal = (V[]) Array.newInstance(toCopy, toCopy.size());
//
//        getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);
//
//        retVal.addAll(toCopy);
//
//        return retVal;
//    }
    /**
     * Turns toCopy into a list.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
    public static <V> List<V> asList(final Collection<V> toCopy) {
        ensureCollection(toCopy, 0, COLLECTION_ERROR_MSG);

        final List<V> retVal = new LinkedList<>();

        getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);

        retVal.addAll(toCopy);

        return retVal;
    }

    /**
     * Turns toCopy into a set.
     *
     * @param <V> the type of data to copy.
     * @param toCopy the collection to copy.
     *
     * @return a new list.
     */
    public static <V> Set<V> asSet(final Collection<V> toCopy) {
        ensureCollection(toCopy, 0, COLLECTION_ERROR_MSG);

        final Set<V> retVal = new TreeSet<>();

        getLogger().log(Level.FINE, "Creating a copy of {0}", toCopy);

        retVal.addAll(toCopy);

        return retVal;
    }

    /**
     * Take a collection and sort it, returning a new collection of the sort.
     *
     * @param <V> the type to sort.
     *
     * @param toSort the collection to sort - will not be affected.
     * @param comparator will perform comparisons for sort order.
     *
     * @return a newly sorted collection.
     */
    public static <V> Collection<V> sort(final Collection<V> toSort, final Comparator<V> comparator) {
        ensureCollection(toSort, 0, COLLECTION_ERROR_MSG);
        ObjectUtils.ensureObject(comparator, "Must have a comparator");

        final List<V> retVal = asList(toSort);

        getLogger().log(Level.FINE, "Sorting {0}", toSort);

        Collections.sort(retVal, comparator);

        return retVal;
    }

    /**
     * Filter a collection.
     *
     * @param <T> the type to filter upon.
     * @param <V> the value to find.
     *
     * @param collection is the collection to filter.
     * @param filter is the filter to apply.
     * @param value the value to search for.
     *
     * @return a collection of those items in the collection that satisfy the filter.
     *
     */
    public static <T, V> Collection<T> filter(final Collection<T> collection, final Filter<T, V> filter, final V value) {
        ensureCollection(collection, 0, COLLECTION_ERROR_MSG);
        ObjectUtils.ensureObject(filter, FILTER_ERROR_MSG);

        final List<T> retVal = new ArrayList(collection.size());

        for (final T toFilter : collection) {
            addValue(retVal, toFilter, filter.accept(toFilter, value));
        }

        getLogger().log(Level.FINE, "Filtered {0}", retVal);

        return retVal;
    }
}
