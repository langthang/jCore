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
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ArrayUtils utility class.
 *
 * @author Scot P. Floess
 */
public class ArrayUtilsTest {

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = ArrayUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test an array for a minimum size, a failure message and the array is null.
     */
    @Test
    public void test_ensureArray_minSize_msg_null() {

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(null, 1, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, a failure message and the array is too small.
     */
    @Test
    public void test_ensureArray_minSize_msg_arrayTooSmall() {

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(new String[10], 11, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, a failure message and the array is empty.
     */
    @Test
    public void test_ensureArray_minSize_msg_empty() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(new String[0], 2, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, a failure message and a null element in the array.
     */
    @Test
    public void test_ensureArray_minSize_msg_nullElement() {
        final String[] str = new String[3];

        str[0] = TestUtils.generateUniqueStr();
        str[2] = TestUtils.generateUniqueStr();

        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(str, str.length, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, and a failure.
     */
    @Test
    public void test_ensureArray_minSize_msg() {
        final String[] str = new String[2];

        str[0] = TestUtils.generateUniqueStr();
        str[1] = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be same array", str, ArrayUtils.ensureArray(str, str.length, "Blah"));
    }

    /**
     * Test an array for a minimum size, and it is null.
     */
    @Test
    public void test_ensureArray_minSize_null() {
        try {
            final String[] str = null;
            ArrayUtils.ensureArray(str, 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, and it is too small.
     */
    @Test
    public void test_ensureArray_minSize_arrayTooSmall() {
        try {
            ArrayUtils.ensureArray(new String[10], 11);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, and it is empty.
     */
    @Test
    public void test_ensureArray_minSize_empty() {
        try {
            ArrayUtils.ensureArray(new String[0], 1);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size, and it has a null element.
     */
    @Test
    public void test_ensureArray_minSize_nullElement() {
        final String[] str = new String[3];

        str[0] = TestUtils.generateUniqueStr();
        str[2] = TestUtils.generateUniqueStr();

        try {
            ArrayUtils.ensureArray(str, str.length);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array for a minimum size.
     */
    @Test
    public void test_ensureArray_minSize() {
        final String[] str = new String[2];

        str[0] = TestUtils.generateUniqueStr();
        str[1] = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be same array", str, ArrayUtils.ensureArray(str, str.length));
    }

    /**
     * Test a null array with a failure message.
     */
    @Test
    public void test_ensureArray_msg_null() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(null, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an empty array and a failure message.
     */
    @Test
    public void test_ensureArray_msg_empty() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        try {
            ArrayUtils.ensureArray(new String[0], msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array with a null element and a failure message.
     */
    @Test
    public void test_ensureArray_msg_nullElement() {
        final String msg = TestUtils.generateUniqueStr("You", "fail");

        final String[] str = new String[3];

        str[0] = TestUtils.generateUniqueStr();
        str[2] = TestUtils.generateUniqueStr();

        try {
            ArrayUtils.ensureArray(str, msg);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", msg, e.getMessage());
        }
    }

    /**
     * Test an array and a failure message.
     */
    @Test
    public void test_ensureArray_msg() {
        final String[] str = new String[2];

        str[0] = TestUtils.generateUniqueStr();
        str[1] = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be same array", str, ArrayUtils.ensureArray(str, "Blah"));
    }

    /**
     * Test a null array.
     */
    @Test
    public void test_ensureArray_null() {
        try {
            final String[] str = null;
            ArrayUtils.ensureArray(str);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array that is too small.
     */
    @Test
    public void test_ensureArray_arrayTooSmall() {
        try {
            ArrayUtils.ensureArray(new String[0]);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an empty array.
     */
    @Test
    public void test_ensureArray_empty() {
        try {
            ArrayUtils.ensureArray(new String[0]);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array with a null element.
     */
    @Test
    public void test_ensureArray_nullElement() {
        final String[] str = new String[3];

        str[0] = TestUtils.generateUniqueStr();
        str[2] = TestUtils.generateUniqueStr();

        try {
            ArrayUtils.ensureArray(str);
        } catch (final IllegalArgumentException e) {
            Assert.assertEquals("Should have gotten the error msg", ObjectUtils.DEFAULT_ERROR_MSG, e.getMessage());
        }
    }

    /**
     * Test an array.
     */
    @Test
    public void test_ensureArray() {
        final String[] str = new String[2];

        str[0] = TestUtils.generateUniqueStr();
        str[1] = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be same list", str, ArrayUtils.ensureArray(str));
    }

}
