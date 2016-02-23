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

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the IntUtils utility class.
 *
 * @author Scot P. Floess
 */
public class IntUtilsTest {

    /**
     * Tests a max value for an int with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMaxInt_msg_fail() {
        IntUtils.ensureMaxInt(2, 1, "Too big");
    }

    /**
     * Tests a max value for an int.
     */
    @Test
    public void test_ensureMaxInt_msg() {
        Assert.assertEquals("Should be correct value", 1, IntUtils.ensureMaxInt(1, 2, "Too big"));
    }

    /**
     * Tests a max value for an int with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMaxInt_fail() {
        IntUtils.ensureMaxInt(2, 1);
    }

    /**
     * Tests a max value for an int.
     */
    @Test
    public void test_ensureMaxInt() {
        Assert.assertEquals("Should be correct value", 1, IntUtils.ensureMaxInt(1, 2));
    }

    /**
     * Tests a min value for an int with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMinInt_msg_fail() {
        IntUtils.ensureMinInt(0, 1, "Too small");
    }

    /**
     * Tests a min value for an int.
     */
    @Test
    public void test_ensureMinInt_msg() {
        Assert.assertEquals("Should be correct value", 2, IntUtils.ensureMinInt(2, 1, "Too small"));
    }

    /**
     * Tests a min value for an int with a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureMinInt_fail() {
        IntUtils.ensureMinInt(1, 2);
    }

    /**
     * Tests a min value for an int.
     */
    @Test
    public void test_ensureMinInt() {
        Assert.assertEquals("Should be correct value", 2, IntUtils.ensureMinInt(2, 1));
    }
}
