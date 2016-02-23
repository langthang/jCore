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
import java.util.List;
import java.util.TreeSet;
import org.flossware.jcore.Filter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the IterableUtils utility class.
 *
 * @author Scot P. Floess
 */
public class IterableUtilsTest {

    /**
     * Simple implementation for filtering.
     */
    static final class ContainsFilter implements Filter<String, String> {

        @Override
        public boolean accept(String toFilter, String value) {
            return toFilter.contains(value);
        }
    }

    /**
     * Simple implementation for filtering.
     */
    static final class NotContainsFilter implements Filter<String, String> {

        @Override
        public boolean accept(String toFilter, String value) {
            return !toFilter.contains(value);
        }
    }

    /**
     * Tests contains using null.
     */
    @Test
    public void test_contains_null() {
        try {
            IterableUtils.contains(null, new ContainsFilter(), "1");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IterableUtils.ITERABLE_ERROR_MSG, e.getMessage());
        }

        try {
            CollectionUtils.filter(new TreeSet(), null, "1");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", IterableUtils.FILTER_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test contains.
     */
    @Test
    public void test_contains() {
        final List list = new ArrayList<>();
        list.add("3hello3");
        list.add("222222");
        list.add("hello1");

        Assert.assertTrue("Should contain values", IterableUtils.contains(list, new ContainsFilter(), "3"));
        Assert.assertTrue("Should contain values", IterableUtils.contains(list, new ContainsFilter(), "hello"));

        Assert.assertTrue("Should not contain values", IterableUtils.contains(list, new NotContainsFilter(), "BLAH"));
    }
}
