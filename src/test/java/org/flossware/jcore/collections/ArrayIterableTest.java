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

import java.util.Set;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the SrrayIterable class.
 *
 * @author Scot P. Floess
 */
public class ArrayIterableTest {
    
    /**
     * Tests a null array - should fail.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_null() {
        new ArrayIterable(null);
    }
    
    /**
     * Tests iterating across the whole collection.
     */
    @Test
    public void test_iterator() {
        final Set<String> set = new TreeSet<>();
        
        set.add("one");
        set.add("two");
        set.add("three");
        
        int removed = 0;
        int count = set.size();
        
        final ArrayIterable<String> itr = new ArrayIterable(set.toArray());
        
        for (final String str : itr) {
            Assert.assertTrue("Should have removed!", set.remove(str));
            removed++;      
        }
        
        Assert.assertTrue("Should be no more elements", set.isEmpty());
        Assert.assertEquals("Shouldbe the same number removed as in set", count, removed);
    }
}
