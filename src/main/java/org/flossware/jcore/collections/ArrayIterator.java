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
package org.flossware.jcore.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import org.flossware.jcore.AbstractCommonBase;
import org.flossware.jcore.utils.collections.ArrayUtils;

/**
 * Converts an array into an iterator.
 *
 * @author Scot P. Floess
 *
 * @param <T> the type in the array.
 */
public class ArrayIterator<T> extends AbstractCommonBase implements Iterator<T> {

    /**
     * Error message when one presents a null object to the constructor.
     */
    public static final String NULL_ERROR_MSG = "Must provide an array!";

    /**
     * Error message when calling next() beyond the end of the array.
     */
    public static final String NEXT_ERROR_MSG = "Cannot go past the end of the array!";

    /**
     * Error message when trying to call remove().
     */
    public static final String REMOVE_ERROR_MSG = "This iterator does not support remove!";

    /**
     * This contains the array that will be iterated upon.
     */
    private final T[] values;

    /**
     * Stores our index.
     */
    private final AtomicInteger index;

    /**
     * Return the values.
     *
     * @return the values.
     */
    private T[] getValues() {
        return values;
    }

    /**
     * Return the index.
     *
     * @return the index.
     */
    private AtomicInteger getIndex() {
        return index;
    }

    /**
     * Will ensure there is a next element. If not, will raise a NoSuchElementException.
     *
     * @throws NoSuchElementException if there are no more elements to pull from in the array.
     */
    void ensureNext() {
        if (getIndex().intValue() >= getValues().length) {
            log(Level.WARNING, "No next element!  Current index [{0}] Total values [{1}]", getIndex().intValue(), getValues().length);

            throw new NoSuchElementException(NEXT_ERROR_MSG);
        }
    }

    /**
     * Sets an array to iterate upon.
     *
     * @param values the array elements.
     */
    public ArrayIterator(final T[] values) {
        this.values = ArrayUtils.ensureArray(values, 0, NULL_ERROR_MSG);
        this.index = new AtomicInteger();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return logAndReturn(Level.FINEST, "Has more data [{0}]", (getIndex().intValue() < getValues().length));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T next() {
        ensureNext();

        return logAndReturn(Level.FINEST, "Next value [{0}]", getValues()[getIndex().getAndIncrement()]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        getLogger().log(Level.WARNING, REMOVE_ERROR_MSG);

        throw new UnsupportedOperationException(REMOVE_ERROR_MSG);
    }
}
