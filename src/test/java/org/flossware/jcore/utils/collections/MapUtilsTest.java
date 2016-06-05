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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the CollectionUtils utility class.
 *
 * @author Scot P. Floess
 */
public class MapUtilsTest {

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = MapUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test a map for a minimum size, a failure message and the map is null.
     */
    @Test
    public void test_ensureMap_minSize_msg_null() {

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            MapUtils.ensureMap(null, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a map for a minimum size, a failure message and the map is too small.
     */
    @Test
    public void test_ensureMap_minSize_msg_mapTooSmall() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");
        final Map map = new HashMap();

        map.put("1", "11");

        try {
            MapUtils.ensureMap(map, 11, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a map for a minimum size, a failure message and the map is empty.
     */
    @Test
    public void test_ensureMap_minSize_msg_empty() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            MapUtils.ensureMap(new HashMap(), 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a map for a minimum size, a failure message and the map is empty.
     */
    @Test
    public void test_ensureMap_minSize_msg() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            MapUtils.ensureMap(new HashMap(), 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a map for a minimum size, and a failure.
     */
    @Test
    public void test_ensureMap_minSize_msg_good() {
        final List<String> strList = Arrays.asList(TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());
        final Map map = new HashMap();

        map.put("1", "11");
        map.put("2", "22");

        Assert.assertSame("Should be same map", map, MapUtils.ensureMap(map, 1, "Blah"));
        Assert.assertSame("Should be same map", map, MapUtils.ensureMap(map, 2, "Blah"));
    }

    /**
     * Test a map and it is null.
     */
    @Test
    public void test_ensureMap_msg_null() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            MapUtils.ensureMap(null, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test a map and its too small.
     */
    @Test
    public void test_ensureMap_msg_mapTooSmall() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            MapUtils.ensureMap(new HashMap(), msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test for a map being the correct size.
     */
    @Test
    public void test_ensureMap_msg() {
        final Map map = new HashMap();

        map.put("1", "11");

        Assert.assertSame("Should be the same map", map, MapUtils.ensureMap(map, "Blah"));
    }

    /**
     * Test a map and it is null.
     */
    @Test
    public void test_ensureMap_int_null() {
        try {
            MapUtils.ensureMap(null, 10);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", MapUtils.MAP_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a map and its too small.
     */
    @Test
    public void test_ensureMap_int_mapTooSmall() {
        try {
            MapUtils.ensureMap(new HashMap(), 2);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", MapUtils.MAP_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test for a map being the correct size.
     */
    @Test
    public void test_ensureMap_int() {
        final Map map = new HashMap();

        map.put("1", "11");
        map.put("2", "2");
        map.put("3", "333");

        Assert.assertSame("Should be the same map", map, MapUtils.ensureMap(map, 1));
        Assert.assertSame("Should be the same map", map, MapUtils.ensureMap(map, 2));
        Assert.assertSame("Should be the same map", map, MapUtils.ensureMap(map, 3));
    }

    /**
     * Test a map and it is null.
     */
    @Test
    public void test_ensureMap_null() {
        try {
            MapUtils.ensureMap(null);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", MapUtils.MAP_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test a map and its too small.
     */
    @Test
    public void test_ensureMap_mapTooSmall() {
        try {
            MapUtils.ensureMap(new HashMap());
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", MapUtils.MAP_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test for a map being the correct size.
     */
    @Test
    public void test_ensureMap() {
        final Map map = new HashMap();

        map.put("1", "11");
        map.put("2", "2");
        map.put("3", "333");

        Assert.assertSame("Should be the same map", map, MapUtils.ensureMap(map));
    }
}
