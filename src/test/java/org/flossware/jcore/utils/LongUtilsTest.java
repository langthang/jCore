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
package org.flossware.jcore.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the LongUtils utility class.
 *
 * @author Scot P. Floess
 */
public class LongUtilsTest {

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = LongUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Tests a max value for a long with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMaxLong_msg_fail() {
        LongUtils.ensureMaxLong(2, 1, "Too big");
    }

    /**
     * Tests a max value for a long.
     */
    @Test
    public void test_ensureMaxLong_msg() {
        Assert.assertEquals("Should be correct value", 1, LongUtils.ensureMaxLong(1, 2, "Too big"));
    }

    /**
     * Tests a max value for an int with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMaxLong_fail() {
        LongUtils.ensureMaxLong(2, 1);
    }

    /**
     * Tests a max value for a long.
     */
    @Test
    public void test_ensureMaxLong() {
        Assert.assertEquals("Should be correct value", 1, LongUtils.ensureMaxLong(1, 2));
    }

    /**
     * Tests a min value for a long with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMinLong_msg_fail() {
        LongUtils.ensureMinLong(0, 1, "Too small");
    }

    /**
     * Tests a min value for a long.
     */
    @Test
    public void test_ensureMinLong_msg() {
        Assert.assertEquals("Should be correct value", 2, LongUtils.ensureMinLong(2, 1, "Too small"));
    }

    /**
     * Tests a min value for a long with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMinLong_fail() {
        LongUtils.ensureMinLong(1, 2);
    }

    /**
     * Tests a min value for a long.
     */
    @Test
    public void test_ensureMinLong() {
        Assert.assertEquals("Should be correct value", 2, LongUtils.ensureMinLong(2, 1));
    }
}
