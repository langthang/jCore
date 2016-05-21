/*
 * Copyright (C) 2014 Scot P. Floess
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
package org.flossware.jcore.collections.map;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.flossware.jcore.AbstractCommonBase;

/**
 * Abstract base class for functional maps.
 *
 * @author Scot P. Floess
 *
 * @param <K> the key.
 * @param <V> the type to cache.
 */
public abstract class AbstractFunctionalMap<K, V> extends AbstractCommonBase implements FunctionalMap<K, V> {

    /**
     * Default constructor.
     */
    protected AbstractFunctionalMap() {
    }

    /**
     * Return the map we will use.
     *
     * @return the map to be used.
     */
    protected abstract Map<K, V> getMap();

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> putF(final K key, final V value) {
        log(Level.FINEST, "Putting [{0}] -> [{1}]", key, value);

        put(key, value);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> putAllF(final Map<? extends K, ? extends V> map) {
        log(Level.FINEST, "Putting whole map {0}", map);

        putAll(map);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    public FunctionalMap<K, V> removeF(final K key) {
        log(Level.FINEST, "Removing key [{0}]", key);

        remove(key);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return logF(Level.FINEST, "Size [{0}]", getMap().size());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return logF(Level.FINEST, "Map empty [{0}]", getMap().isEmpty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(final Object o) {
        return logF(Level.FINEST, "Map contains key result [{0}] for key [{1}]", getMap().containsKey(o), o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(final Object o) {
        return logF(Level.FINEST, "Map contains value result [{0}] for value [{1}]", getMap().containsValue(o), 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(final Object o) {
        return logF(Level.FINEST, "Retrieved value [{0}] for key [{1}]", getMap().get(o), o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(final K k, final V v) {
        return logF(Level.FINEST, "Put yielded [{0}] for [{1}] -> [{2}]", getMap().put(k, v), k, v);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(Object o) {
        return logF(Level.FINEST, "Removed [{0}] for key [{1}]", getMap().remove(o), o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        log(Level.FINEST, "Putting all {0}", map);

        getMap().putAll(map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        log(Level.FINEST, "Clearing");

        getMap().clear();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<K> keySet() {
        return logF(Level.FINEST, "Returning key set {0}", getMap().keySet());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<V> values() {
        return logF(Level.FINEST, "Returning values {0}", getMap().values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return logF(Level.FINEST, "Returning entry set {0}", getMap().entrySet());
    }
}
