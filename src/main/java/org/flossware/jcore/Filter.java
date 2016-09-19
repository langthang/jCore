package org.flossware.jcore;

/**
 * API to perform filtering.
 *
 * @author sfloess
 *
 * @param <T> the type being filtered. Inspired by file filters such as FileFilter and FilenameFilter.
 * @param <V> the value to filter against <code>T</code>.
 */
public interface Filter<T, V> {

    /**
     * Return true if <code>toFilter</code> meets filter.
     *
     * @param toFilter the thing being filtered.
     * @param value the value to apply filter against <code>toFilter</code>.
     *
     * @return true if filter applies or false if not.
     */
    boolean accept(T toFilter, V value);
}
