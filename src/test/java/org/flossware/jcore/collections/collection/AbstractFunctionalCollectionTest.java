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
import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AbstractFunctionalCollection base class.
 *
 * @author sfloess
 */
public class AbstractFunctionalCollectionTest {

    class StubFunctionalCollection extends AbstractFunctionalCollection<String> {

        Set<String> set = new TreeSet();

        @Override
        protected Collection<String> getCollection() {
            return set;
        }
    }

    private StubFunctionalCollection functionalCollection;

    @Before
    public void init() {
        functionalCollection = new StubFunctionalCollection();
    }

    @Test
    public void test_addF() {
        functionalCollection.addF("9").addF("7").addF("6");

        Assert.assertEquals("Should have three elements", 3, functionalCollection.size());
        Assert.assertTrue("Should have found the element", functionalCollection.remove("7"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("9"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("6"));
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_removeF() {
        functionalCollection.addF("9").addF("7").addF("6");
        functionalCollection.removeF("1").removeF("7").removeF("6").removeF("9");

        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_addAllF() {
        TreeSet set1 = new TreeSet();
        set1.add("1");
        set1.add("2");

        TreeSet set2 = new TreeSet();
        set2.add("3");
        set2.add("4");

        functionalCollection.addAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set1.size() + set2.size(), functionalCollection.size());

        Assert.assertTrue("Should have found the element", functionalCollection.remove("2"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("4"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("3"));
        Assert.assertTrue("Should have found the element", functionalCollection.remove("1"));
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_removeAllF() {
        TreeSet set1 = new TreeSet();
        set1.add("1");
        set1.add("2");

        TreeSet set2 = new TreeSet();
        set2.add("3");
        set2.add("4");

        functionalCollection.addAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set1.size() + set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set1).addAllF(set2);
        Assert.assertEquals("Should be elements", set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set1);
        Assert.assertEquals("Should be elements", set2.size(), functionalCollection.size());

        functionalCollection.removeAllF(set2);
        Assert.assertTrue("Should be no elements", functionalCollection.isEmpty());
    }

    @Test
    public void test_size() {
        functionalCollection.add("1");

        Assert.assertEquals("Should be correct size", 1, functionalCollection.size());

        functionalCollection.add("2");

        Assert.assertEquals("Should be correct size", 2, functionalCollection.size());
    }

    @Test
    public void test_isEmpty() {

        Assert.assertTrue("Should be empty", functionalCollection.isEmpty());

        functionalCollection.add("1");

        Assert.assertFalse("Should not be empty", functionalCollection.isEmpty());

        functionalCollection.add("2");

        Assert.assertFalse("Should not be empty", functionalCollection.isEmpty());
    }

    @Test
    public void test_contains() {

        Assert.assertFalse("Should not contain", functionalCollection.contains("1"));

        functionalCollection.add("1");

        Assert.assertTrue("Should contain", functionalCollection.contains("1"));

        functionalCollection.add("3");

        Assert.assertTrue("Should contain", functionalCollection.contains("1"));

        Assert.assertTrue("Should not contain", functionalCollection.contains("3"));
    }

    @Test
    public void test_iterator() {
        functionalCollection.add("1");
        functionalCollection.add("2");
        functionalCollection.add("3");

        int totalOneFound = 0;
        int totalTwoFound = 0;
        int totalThreeFound = 0;
        int totalOtherFound = 0;

        for (final String str : functionalCollection) {
            if ("1".equals(str)) {
                totalOneFound++;
            } else if ("2".equals(str)) {
                totalTwoFound++;
            } else if ("3".equals(str)) {
                totalThreeFound++;
            } else {
                totalOtherFound++;
            }
        }

        Assert.assertEquals("Should have found only one 1", 1, totalOneFound);
        Assert.assertEquals("Should have found only one 2", 1, totalTwoFound);
        Assert.assertEquals("Should have found only one 3", 1, totalThreeFound);
        Assert.assertEquals("Should have found no others", 0, totalOtherFound);
    }

    @Test
    public void test_toArray_void() {
        functionalCollection.add("1");
        functionalCollection.add("2");
        functionalCollection.add("3");

        final Object[] array = functionalCollection.toArray();

        Assert.assertEquals("Should be correct number of elements", functionalCollection.size(), array.length);

        Assert.assertTrue("Should have found the element", "1".equals(array[0]));
        Assert.assertTrue("Should have found the element", "2".equals(array[1]));
        Assert.assertTrue("Should have found the element", "3".equals(array[2]));
    }

    @Test
    public void test_toArray_array() {
        functionalCollection.add("1");
        functionalCollection.add("2");
        functionalCollection.add("3");

        final Object[] array1 = functionalCollection.toArray(new Object[1]);
        Assert.assertEquals("Should be correct number of elements", 3, array1.length);
        Assert.assertTrue("Should have found the element", "1".equals(array1[0]));
        Assert.assertTrue("Should have found the element", "2".equals(array1[1]));
        Assert.assertTrue("Should have found the element", "3".equals(array1[2]));

        final Object[] array2 = functionalCollection.toArray(new Object[2]);
        Assert.assertEquals("Should be correct number of elements", 3, array2.length);
        Assert.assertTrue("Should have found the element", "1".equals(array2[0]));
        Assert.assertTrue("Should have found the element", "2".equals(array2[1]));
        Assert.assertTrue("Should have found the element", "3".equals(array2[2]));

        final Object[] array3 = functionalCollection.toArray(new Object[3]);
        Assert.assertEquals("Should be correct number of elements", 3, array3.length);
        Assert.assertTrue("Should have found the element", "1".equals(array3[0]));
        Assert.assertTrue("Should have found the element", "2".equals(array3[1]));
        Assert.assertTrue("Should have found the element", "3".equals(array3[2]));
    }

    @Test
    public void test_add() {
        Assert.assertTrue("Should have added", functionalCollection.add("1"));
        Assert.assertTrue("Should have added", functionalCollection.add("2"));
    }

    @Test
    public void test_remove() {
        functionalCollection.add("1");
        functionalCollection.add("2");

        Assert.assertTrue("Should have removed", functionalCollection.remove("2"));
        Assert.assertTrue("Should have removed", functionalCollection.remove("1"));

        Assert.assertFalse("Should not have removed", functionalCollection.remove("3"));
        Assert.assertFalse("Should not have removed", functionalCollection.remove("2"));
        Assert.assertFalse("Should not have removed", functionalCollection.remove("1"));
    }

    @Test
    public void test_containsAll() {
        final Set<String> set1 = new TreeSet<>();

        set1.add("1");
        set1.add("2");

        functionalCollection.add("1");
        functionalCollection.add("2");

        Assert.assertTrue("Should contain all", functionalCollection.containsAll(set1));

        final Set<String> set2 = new TreeSet<>();

        set2.add("3");
        set2.add("4");

        Assert.assertFalse("Should not contain all", functionalCollection.containsAll(set2));
    }

    @Test
    public void test_addAll() {
        final Set<String> set = new TreeSet<>();

        set.add("1");
        set.add("2");
        set.add("3");

        Assert.assertTrue("Should have added all values", functionalCollection.addAll(set));
        Assert.assertEquals("Should have correct number of elements", set.size(), functionalCollection.size());
        Assert.assertTrue("Should have value", functionalCollection.contains("1"));
        Assert.assertTrue("Should have value", functionalCollection.contains("2"));
        Assert.assertTrue("Should have value", functionalCollection.contains("3"));
    }

    @Test
    public void test_retainAll() {
        final Set<String> set1 = new TreeSet<>();

        set1.add("1");
        set1.add("2");
        set1.add("3");

        functionalCollection.addF("5").addF("4").addF("6").addF("7").addF("1").addF("3").addF("2");

        Assert.assertTrue("Should have retained values", functionalCollection.retainAll(set1));
        Assert.assertEquals("Should have correct total", 3, functionalCollection.size());

    }

    @Test
    public void test_removeAll() {
        final Set<String> set1 = new TreeSet<>();

        set1.add("1");
        set1.add("2");
        set1.add("3");

        functionalCollection.addF("5").addF("4").addF("6").addF("7").addF("1").addF("3").addF("2");

        Assert.assertTrue("Should have retained values", functionalCollection.removeAll(set1));
        Assert.assertEquals("Should have correct total", 4, functionalCollection.size());
    }

    @Test
    public void test_clear() {
        Assert.assertTrue("Should be empty", functionalCollection.isEmpty());

        functionalCollection.addF("5").addF("4").addF("6").addF("7").addF("1").addF("3").addF("2");

        Assert.assertFalse("Should not be empty", functionalCollection.isEmpty());

        functionalCollection.clear();

        Assert.assertTrue("Should be empty", functionalCollection.isEmpty());
    }
}
