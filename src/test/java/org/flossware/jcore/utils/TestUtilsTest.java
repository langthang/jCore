/*
 * Copyright (C) 2016 flossware
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
 * Tests the TestUtils utility class.
 *
 * @author flossware
 */
public class TestUtilsTest {

    /**
     * Test prefix and suffix.
     */
    @Test
    public void test_generateUniqueStr_prefix_suffix() {
        final String prefix = "prefix1";
        final String suffix = "1suffix";

        final String str = TestUtils.generateUniqueStr(prefix, suffix);

        Assert.assertTrue("Should start with correct string", str.startsWith(prefix));
        Assert.assertTrue("Should end with correct string", str.endsWith(suffix));
    }

    /**
     * Test prefix.
     */
    @Test
    public void test_generateUniqueStr_prefix() {
        final String prefix = "prefix2";

        final String str = TestUtils.generateUniqueStr(prefix);

        Assert.assertTrue("Should start with correct string", str.startsWith(prefix));
    }

    /**
     * Test generating a unique string.
     */
    @Test
    public void test_generateUniqueStr() {
        final String str = TestUtils.generateUniqueStr();

        Assert.assertFalse("Should be a string", str.isEmpty());
    }
}
