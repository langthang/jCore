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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import org.flossware.jcore.Filter;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the CollectionUtils utility class.
 *
 * @author Scot P. Floess
 */
public class CollectionUtilsTest {

    /**
     * Simple implementation for string comparisons.
     */
    static final class StringComparator implements Comparator<String> {

        @Override
        public int compare(String val1, String val2) {
            return val1.compareTo(val2);
        }

    }

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
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = CollectionUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test a collection for a minimum size, a failure message and the collection is null.
     */
    @Test
    public void test_ensureCollection_minSize_msg_null() {

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(null, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, a failure message and the collection is too small.
     */
    @Test
    public void test_ensureCollection_minSize_msg_collectionTooSmall() {

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(Arrays.asList("1", "2"), 11, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, a failure message and the collection is empty.
     */
    @Test
    public void test_ensureCollection_minSize_msg_empty() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(new TreeSet(), 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, a failure message and a null element in the collection.
     */
    @Test
    public void test_ensureCollection_minSize_msg_nullElement() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), null, TestUtils.generateUniqueStr());

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(strList, strList.size(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, and a failure.
     */
    @Test
    public void test_ensureCollection_minSize_msg() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());

        Assert.assertSame("Should be same collection", strList, CollectionUtils.ensureCollection(strList, strList.size(), "Blah"));
    }

    /**
     * Test a collection for a minimum size, and it is null.
     */
    @Test
    public void test_ensureCollection_minSize_null() {
        try {
            CollectionUtils.ensureCollection(null, 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, and it is too small.
     */
    @Test
    public void test_ensureCollection_minSize_collectionTooSmall() {
        try {
            CollectionUtils.ensureCollection(Arrays.asList("1", "2", "3"), 11);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, and it is empty.
     */
    @Test
    public void test_ensureCollection_minSize_empty() {
        try {
            CollectionUtils.ensureCollection(new TreeSet(), 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size, and it has a null element.
     */
    @Test
    public void test_ensureCollection_minSize_nullElement() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), null, TestUtils.generateUniqueStr());

        try {
            CollectionUtils.ensureCollection(strList, strList.size());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection for a minimum size.
     */
    @Test
    public void test_ensureCollection_minSize() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());

        Assert.assertSame("Should be same collection", strList, CollectionUtils.ensureCollection(strList, strList.size()));
    }

    /**
     * Test a null collection with a failure message.
     */
    @Test
    public void test_ensureCollection_msg_null() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(null, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an empty collection and a failure message.
     */
    @Test
    public void test_ensureCollection_msg_empty() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            CollectionUtils.ensureCollection(new TreeSet(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection with a null element and a failure message.
     */
    @Test
    public void test_ensureCollection_msg_nullElement() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), null, TestUtils.generateUniqueStr());

        try {
            CollectionUtils.ensureCollection(strList, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a collection and a failure message.
     */
    @Test
    public void test_ensureCollection_msg() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());

        Assert.assertSame("Should be same collection", strList, CollectionUtils.ensureCollection(strList, "Blah"));
    }

    /**
     * Test a null collection.
     */
    @Test
    public void test_ensureCollection_null() {
        try {
            CollectionUtils.ensureCollection(null);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection that is too small.
     */
    @Test
    public void test_ensureCollection_collectionTooSmall() {
        try {
            CollectionUtils.ensureCollection(new TreeSet());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an empty collection.
     */
    @Test
    public void test_ensureCollection_empty() {
        try {
            CollectionUtils.ensureCollection(new TreeSet());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection with a null element.
     */
    @Test
    public void test_ensureCollection_nullElement() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), null, TestUtils.generateUniqueStr());

        try {
            CollectionUtils.ensureCollection(strList);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a collection.
     */
    @Test
    public void test_ensureCollection() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());

        Assert.assertSame("Should be same list", strList, CollectionUtils.ensureCollection(strList));
    }

    /**
     * Tests adding a value where the collection is null.
     */
    @Test
    public void test_addValue_null() {
        try {
            CollectionUtils.addValue(null, "1", true);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COLLECTION_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Tests adding a value where but the boolean is not to add.
     */
    @Test
    public void test_addValue_noAdd() {
        Assert.assertTrue("Should not have added to the collection", CollectionUtils.addValue(new ArrayList<>(), "1", false).isEmpty());
    }

    /**
     * Tests adding a value where but the boolean is not to add.
     */
    @Test
    public void test_addValue() {
        final List arrayList = new ArrayList();
        final String toAdd = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be the same collection", arrayList, CollectionUtils.addValue(arrayList, toAdd, true));
        Assert.assertFalse("Should have added to the collection", arrayList.isEmpty());
        Assert.assertSame("Should have the value", toAdd, arrayList.get(0));
    }

    /**
     * Tests converting to a list using null.
     */
    @Test
    public void test_asList_null() {
        try {
            CollectionUtils.asList(null);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COLLECTION_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Tests converting to a list.
     */
    @Test
    public void test_asList() {
        final Set set = new TreeSet<>();
        set.add(TestUtils.generateUniqueStr());
        set.add(TestUtils.generateUniqueStr());

        final List list = CollectionUtils.asList(set);

        Assert.assertNotSame("Should not be the same collection", set, list);
        Assert.assertEquals("Should have same total values", set.size(), list.size());

        for (final Object value : list) {
            Assert.assertTrue("Should have found the value", set.contains(value));
        }
    }

    /**
     * Tests converting to a set using null.
     */
    @Test
    public void test_asSet_null() {
        try {
            CollectionUtils.asSet(null);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COLLECTION_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Tests converting to a set.
     */
    @Test
    public void test_asSet() {
        final List list = new ArrayList<>();
        list.add(TestUtils.generateUniqueStr("1"));
        list.add(TestUtils.generateUniqueStr("2"));

        final Set set = CollectionUtils.asSet(list);

        Assert.assertNotSame("Should not be the same collection", list, set);
        Assert.assertEquals("Should have same total values", list.size(), set.size());

        for (final Object value : list) {
            Assert.assertTrue("Should have found the value", set.contains(value));
        }
    }

    /**
     * Tests sorting using null.
     */
    @Test
    public void test_sort_null() {
        try {
            CollectionUtils.sort(null, new StringComparator());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COLLECTION_ERROR_MSG, e.getMessage());
        }

        try {
            CollectionUtils.sort(new TreeSet(), null);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COMPARATOR_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Tests sorting.
     */
    @Test
    public void test_sort() {
        final List list = new ArrayList<>();
        list.add("3");
        list.add("2");
        list.add("1");

        final Collection collection = CollectionUtils.sort(list, new StringComparator());

        Assert.assertNotSame("Should not be the same collection", list, collection);
        Assert.assertEquals("Should have same total values", list.size(), collection.size());

        final Iterator itr = collection.iterator();
        Assert.assertEquals("Should have correct value", "1", itr.next());
        Assert.assertEquals("Should have correct value", "2", itr.next());
        Assert.assertEquals("Should have correct value", "3", itr.next());
    }

    /**
     * Tests filter using null.
     */
    @Test
    public void test_filter_null() {
        try {
            CollectionUtils.filter((Collection) null, new ContainsFilter(), "1");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.COLLECTION_ERROR_MSG, e.getMessage());
        }

        try {
            CollectionUtils.filter(new TreeSet(), null, "1");
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", CollectionUtils.FILTER_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Tests filtering.
     */
    @Test
    public void test_filter() {
        final List list = new ArrayList<>();
        list.add("3hello3");
        list.add("222222");
        list.add("hello1");

        final Collection collection = CollectionUtils.filter(list, new ContainsFilter(), "hello");

        Assert.assertNotSame("Should not be the same collection", list, collection);
        Assert.assertEquals("Should have correct number of elements", 2, collection.size());

        Assert.assertTrue("Value should be found", collection.contains(list.get(0)));
        Assert.assertTrue("Value should be found", collection.contains(list.get(2)));
    }
}
