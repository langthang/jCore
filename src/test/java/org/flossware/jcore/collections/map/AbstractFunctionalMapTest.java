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
package org.flossware.jcore.collections.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the AbstractFunctionalMap base class.
 *
 * @author sfloess
 */
public class AbstractFunctionalMapTest {

    class StubFunctionalMap extends AbstractFunctionalMap<String, String> {

        Map<String, String> map = new TreeMap();

        @Override
        protected Map<String, String> getMap() {
            return map;
        }
    }

    private StubFunctionalMap functionalMap;

    @Before
    public void init() {
        functionalMap = new StubFunctionalMap();
    }

    @Test
    public void test_putF() {
        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");

        Assert.assertEquals("Should have three elements", 3, functionalMap.size());
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key2"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key1"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key3"));
        Assert.assertNotNull("Should be no elements", functionalMap.isEmpty());
    }

    @Test
    public void test_putAllF() {
        TreeMap map1 = new TreeMap();
        map1.put("key1", "val1");
        map1.put("key2", "val2");

        TreeMap map2 = new TreeMap();
        map2.put("key3", "val3");
        map2.put("key4", "val4");

        functionalMap.putAllF(map1).putAllF(map2);
        Assert.assertEquals("Should be elements", map1.size() + map2.size(), functionalMap.size());

        Assert.assertNotNull("Should have found the element", functionalMap.remove("key2"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key4"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key3"));
        Assert.assertNotNull("Should have found the element", functionalMap.remove("key1"));

        Assert.assertTrue("Should be no elements", functionalMap.isEmpty());
    }

    @Test
    public void test_removeF() {
        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");
        functionalMap.removeF("key1").removeF("key3").removeF("key2").removeF("9");

        Assert.assertTrue("Should be no elements", functionalMap.isEmpty());
    }

    @Test
    public void test_size() {
        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");

        Assert.assertEquals("Should be correct size", 3, functionalMap.size());
    }

    @Test
    public void test_isEmpty() {
        Assert.assertTrue("Should be emtpty", functionalMap.isEmpty());

        functionalMap.putF("key1", "val1").putF("key2", "val2").putF("key3", "val3");

        Assert.assertFalse("Should not be empty", functionalMap.isEmpty());
    }

    @Test
    public void test_containsKey() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        Assert.assertTrue("Should contain value", functionalMap.containsKey("1"));
        Assert.assertTrue("Should contain value", functionalMap.containsKey("2"));

        Assert.assertFalse("Should not contain value", functionalMap.containsKey("3"));
    }

    @Test
    public void test_containsValue() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        Assert.assertTrue("Should contain value", functionalMap.containsValue("1 val"));
        Assert.assertTrue("Should contain value", functionalMap.containsValue("2 val"));

        Assert.assertFalse("Should not contain value", functionalMap.containsValue("3 val"));
    }

    @Test
    public void test_get() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        Assert.assertNotNull("Should contain value", functionalMap.get("1"));
        Assert.assertNotNull("Should contain value", functionalMap.get("2"));

        Assert.assertNull("Should not contain value", functionalMap.get("3"));
    }

    @Test
    public void test_put() {
        Assert.assertNull("Should not be a value", functionalMap.put("1", "1 val"));
        Assert.assertNotNull("Should be same value put", functionalMap.put("1", "1 val"));

        Assert.assertNull("Should not be a value", functionalMap.put("2", "2 val"));
        Assert.assertNotNull("Should be same value put", functionalMap.put("2", "2 val"));
    }

    @Test
    public void test_remove() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        Assert.assertNotNull("Should be removed", functionalMap.remove("1"));
        Assert.assertNotNull("Should be removed", functionalMap.remove("2"));

        Assert.assertNull("Should not be removed", functionalMap.remove("3"));
    }

    @Test
    public void test_putAll() {
        final Map<String, String> map = new HashMap<>();
        map.put("1", "1 val");
        map.put("2", "2 val");

        functionalMap.putAll(map);

        Assert.assertEquals("Should be the same number of elements", map.size(), functionalMap.size());
        Assert.assertTrue("Should contain value", functionalMap.containsKey("1"));
        Assert.assertTrue("Should contain value", functionalMap.containsKey("2"));
    }

    @Test
    public void test_clear() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        Assert.assertFalse("Should contain values", functionalMap.isEmpty());

        functionalMap.clear();

        Assert.assertTrue("Should be empty", functionalMap.isEmpty());
    }

    @Test
    public void test_keySet() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        final Set<String> keySet = functionalMap.keySet();

        Assert.assertEquals("Should contain correct number of keys", 2, keySet.size());
        Assert.assertTrue("Should contain key", keySet.contains("1"));
        Assert.assertTrue("Should contain key", keySet.contains("2"));
    }

    @Test
    public void test_values() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        final Collection<String> collection = functionalMap.values();

        Assert.assertEquals("Should contain correct number of keys", 2, collection.size());
        Assert.assertTrue("Should contain key", collection.contains("1 val"));
        Assert.assertTrue("Should contain key", collection.contains("2 val"));
    }

    @Test
    public void test_entryset() {
        functionalMap.putF("1", "1 val").putF("2", "2 val");

        final Set<Entry<String, String>> entrySet = functionalMap.entrySet();

        Assert.assertEquals("Should contain correct number of keys", 2, entrySet.size());

        int oneTotal = 0;
        int twoTotal = 0;
        int otherTotal = 0;

        for (final Entry<String, String> entry : entrySet) {
            if ("1".equals(entry.getKey()) && "1 val".equals(entry.getValue())) {
                oneTotal++;
            } else if ("2".equals(entry.getKey()) && "2 val".equals(entry.getValue())) {
                twoTotal++;
            } else {
                otherTotal++;
            }
        }

        Assert.assertEquals("Should have found one once", 1, oneTotal);
        Assert.assertEquals("Should have found two once", 1, twoTotal);
        Assert.assertEquals("Should not have found other", 0, otherTotal);
    }
}
