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
import org.flossware.jcore.AbstractCommonBase;
import org.flossware.jcore.utils.collections.ArrayUtils;

/**
 * Converts an array into an iteratable.
 *
 * @author Scot P. Floess
 * 
 * @param <T> the type of value in the array.
 */
public class ArrayIterable<T> extends AbstractCommonBase implements Iterable<T> {
    
    private final ArrayIterator arrayIterator; 

    /**
     * Sets an array to iterate upon.
     *
     * @param values the array elements.
     */
    public ArrayIterable(final T[] values) {
        this.arrayIterator = new ArrayIterator(ArrayUtils.ensureArray(values, 0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<T> iterator() {
        return arrayIterator;
    }
}
