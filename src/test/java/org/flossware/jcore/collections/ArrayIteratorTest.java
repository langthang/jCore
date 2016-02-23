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

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the SrrayIterator class.
 *
 * @author Scot P. Floess
 */
public class ArrayIteratorTest {

    /**
     * Tests ensureNext having no more elements.
     */
    @Test
    public void test_ensureNext_noMoreElements() {
        try {
            new ArrayIterator(new Object[0]).ensureNext();
            Assert.fail("Test should have failed!");
        } catch (final NoSuchElementException noSuchElementException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.NEXT_ERROR_MSG, noSuchElementException.getMessage());
        }
    }

    /**
     * Tests ensureNext.
     */
    @Test
    public void test_ensureNext() {
        // This shouldn't fail.
        new ArrayIterator(new Object[]{"Hello", "Goodbye"}).ensureNext();
    }

    /**
     * Test using a null value for the constructor.
     */
    @Test
    public void test_constructor_null() {
        try {
            new ArrayIterator(null);
            Assert.fail("The test should have failed");
        } catch (final IllegalArgumentException illegalArgumentException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.NULL_ERROR_MSG, illegalArgumentException.getMessage());
        }
    }

    /**
     * Test the hasNext() method.
     */
    @Test
    public void test_hasNext() {
        Assert.assertFalse("Should not have next", new ArrayIterator(new Object[0]).hasNext());
        Assert.assertTrue("Should have next", new ArrayIterator(new Object[]{"Hello"}).hasNext());
    }

    /**
     * Tests next going beyond the available entries.
     */
    @Test
    public void test_next_tooFarInArray() {
        try {
            new ArrayIterator(new Object[0]).next();
            Assert.fail("Test should have failed!");
        } catch (final NoSuchElementException noSuchElementException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.NEXT_ERROR_MSG, noSuchElementException.getMessage());
        }

        String[] values = new String[]{"zero", "one"};
        final ArrayIterator itr = new ArrayIterator(values);

        Assert.assertSame("Should be same value", values[0], itr.next());
        Assert.assertSame("Should be same value", values[1], itr.next());

        try {
            itr.next();

            Assert.fail("Test should have failed!");
        } catch (final NoSuchElementException noSuchElementException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.NEXT_ERROR_MSG, noSuchElementException.getMessage());
        }
    }

    /**
     * Tests next() method.
     */
    @Test
    public void test_next() {
        String[] values = new String[]{"zero", "one"};
        final ArrayIterator itr = new ArrayIterator(values);

        Assert.assertSame("Should be same value", values[0], itr.next());
        Assert.assertSame("Should be same value", values[1], itr.next());
        
        Assert.assertFalse("Should be no more elements", itr.hasNext());
    }
    
    /**
     * Tests remove() method.
     */
    @Test
    public void test_remove() {
        try {
            new ArrayIterator(new Object[0]).remove();
            
            Assert.fail("Test should have failed!");
        } catch (final UnsupportedOperationException unsupportedOperationException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.REMOVE_ERROR_MSG, unsupportedOperationException.getMessage());
        }   
        
        try {
            new ArrayIterator(new Object[]{"one", "two", "three"}).remove();
            
            Assert.fail("Test should have failed!");
        } catch (final UnsupportedOperationException unsupportedOperationException) {
            Assert.assertEquals("Should be correct failure message", ArrayIterator.REMOVE_ERROR_MSG, unsupportedOperationException.getMessage());
        }  
    }
    
    /**
     * Tests iterating across the whole collection.
     */
    @Test
    public void test_iterate() {
        final Set<String> set = new TreeSet<>();
        
        set.add("one");
        set.add("two");
        set.add("three");
        
        int removed = 0;
        int count = set.size();
        
        final ArrayIterator<String> itr = new ArrayIterator(set.toArray());
        while (itr.hasNext()) {
            Assert.assertTrue("Should have removed!", set.remove(itr.next()));
            removed++;
        }
        
        Assert.assertTrue("Should be no more elements", set.isEmpty());
        Assert.assertEquals("Shouldbe the same number removed as in set", count, removed);
    }
}
