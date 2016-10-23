/*
 * Copyright (C) 2015 sfloess
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
package org.flossware.jcore.collections.collection;

import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import org.flossware.jcore.AbstractCommonBase;

/**
 * Abstract base class for functional sets.
 *
 * @author sfloess
 */
public abstract class AbstractFunctionalCollection<V> extends AbstractCommonBase implements FunctionalCollection<V> {

    /**
     * Default constructor.
     */
    protected AbstractFunctionalCollection() {
    }

    /**
     * Return the set we will use.
     *
     * @param <T> the type of collection to return.
     *
     * @return the collection to be used.
     */
    protected abstract <T extends Collection<V>> T getCollection();

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> addF(final V value) {
        log(Level.FINEST, "Adding [{0}]", value);

        add(value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> removeF(final V value) {
        log(Level.FINEST, "Removing {0}", value);

        remove(value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> addAllF(final Collection<? extends V> collection) {
        log(Level.FINEST, "Adding all {0}", collection);

        addAll(collection);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FunctionalCollection<V> removeAllF(final Collection<?> collection) {
        log(Level.FINEST, "Removing all {0}", collection);

        removeAll(collection);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return logAndReturn(Level.FINEST, "Size [{0}]", getCollection().size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return logAndReturn(Level.FINEST, "Is empty [{0}]", getCollection().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean contains(final Object o) {
        return logAndReturnByIndex(Level.FINEST, "Object [{0}] contained [{1}]", 1, o, getCollection().contains(o));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<V> iterator() {
        return getCollection().iterator();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] toArray() {
        return getCollection().toArray();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> T[] toArray(final T[] ts) {
        return getCollection().toArray(ts);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean add(final V e) {
        return logAndReturn(Level.FINEST, "Attempting add result [{0}] for [{1}]", getCollection().add(e), e);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(final Object o) {
        return logAndReturn(Level.FINEST, "Attempting remove result [{0}] for [{1}]", getCollection().remove(o), o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsAll(final Collection<?> clctn) {
        return logAndReturn(Level.FINEST, "Contains all result [{0}] in {1}", getCollection().containsAll(clctn), clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addAll(final Collection<? extends V> clctn) {
        return logAndReturn(Level.FINEST, "Add all result [{0}] in {1}", getCollection().addAll(clctn), clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean retainAll(final Collection<?> clctn) {
        return logAndReturn(Level.FINEST, "Retaining all result [{0}] in {1}", getCollection().retainAll(clctn), clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeAll(final Collection<?> clctn) {
        return logAndReturn(Level.FINEST, "Removal result [{0}] from {1}", getCollection().removeAll(clctn), clctn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        log(Level.FINEST, "Clearing collection");

        getCollection().clear();
    }
}
