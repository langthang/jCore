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
 * Tests the StringUtils utility class.
 *
 * @author Scot P. Floess
 */
public class StringUtilsTest {

    /**
     * Test a blank string.
     */
    @Test
    public void test_isBlank() {
        Assert.assertTrue("Should be blank", StringUtils.isBlank(null));
        Assert.assertTrue("Should be blank", StringUtils.isBlank(""));
        Assert.assertTrue("Should be blank", StringUtils.isBlank("     "));
        Assert.assertFalse("Should not be blank", StringUtils.isBlank("" + System.currentTimeMillis()));
    }

    /**
     * Tests a string is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_msg_null() {
        StringUtils.ensureString(null, "Is null!");
    }

    /**
     * Tests a string is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_msg_blank() {
        StringUtils.ensureString("", "Is blank!");
    }

    /**
     * Tests a string is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_msg_manyBlanks() {
        StringUtils.ensureString("   ", "IS really blank!");
    }

    /**
     * Tests a string is set.
     */
    @Test
    public void tests_ensureString_msg() {
        Assert.assertEquals("Should be the correct string", "1", StringUtils.ensureString("1", "IS really blank!"));
        Assert.assertEquals("Should be the correct string", "22", StringUtils.ensureString("22", "IS really blank!"));
        Assert.assertEquals("Should be the correct string", "333", StringUtils.ensureString("333", "IS really blank!"));
    }

    /**
     * Tests a string is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_null() {
        StringUtils.ensureString(null);
    }

    /**
     * Tests a string is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_blank() {
        StringUtils.ensureString("");
    }

    /**
     * Tests a string is blank.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_ensureString_manyBlanks() {
        StringUtils.ensureString("   ");
    }

    /**
     * Tests a string is set.
     */
    @Test
    public void tests_ensureString() {
        Assert.assertEquals("Should be the correct string", "1", StringUtils.ensureString("1"));
        Assert.assertEquals("Should be the correct string", "22", StringUtils.ensureString("22"));
        Assert.assertEquals("Should be the correct string", "333", StringUtils.ensureString("333"));
    }

    /**
     * Tests if we can do a separator on append.
     */
    @Test
    public void test_isSeparatorAppendable() {
        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 0, "hello"));
        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 1, "hello"));
        Assert.assertTrue("Should need appendable separator", StringUtils.isSeparatorAppendable("/", 0, "hello", "Goodbye"));
        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 1, "hello", "Goodbye"));

        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 0, "hello/"));
        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 1, "hello"));
        Assert.assertFalse("Should need appendable separator", StringUtils.isSeparatorAppendable("/", 0, "hello/", "Goodbye"));
        Assert.assertFalse("Should not be need appendable separator", StringUtils.isSeparatorAppendable("/", 1, "hello/", "Goodbye"));
    }

    @Test
    public void test_concatWithSeparator_boolean() {
        Assert.assertEquals("Should be same string", "/", StringUtils.concatWithSeparator(true, "/", ""));
        Assert.assertEquals("Should be same string", "foo/", StringUtils.concatWithSeparator(true, "/", "foo"));
        Assert.assertEquals("Should be same string", "foo/bar/", StringUtils.concatWithSeparator(true, "/", "foo/", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar//", StringUtils.concatWithSeparator(true, "/", "foo/", "bar/"));
        Assert.assertEquals("Should be same string", "foo/bar/theta/", StringUtils.concatWithSeparator(true, "/", "foo/", "bar/", "theta"));
        Assert.assertEquals("Should be same string", "foo/bar/theta//", StringUtils.concatWithSeparator(true, "/", "foo/", "bar/", "theta/"));

        Assert.assertEquals("Should be same string", "/", StringUtils.concatWithSeparator(true, "/", ""));
        Assert.assertEquals("Should be same string", "foo/", StringUtils.concatWithSeparator(true, "/", "foo"));
        Assert.assertEquals("Should be same string", "foo/bar/", StringUtils.concatWithSeparator(true, "/", "foo", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar/theta/", StringUtils.concatWithSeparator(true, "/", "foo", "bar", "theta"));
    }

    @Test
    public void test_concatWithSeparator() {
        Assert.assertEquals("Should be same string", "", StringUtils.concatWithSeparator("/", ""));
        Assert.assertEquals("Should be same string", "foo/", StringUtils.concatWithSeparator("/", "foo/"));
        Assert.assertEquals("Should be same string", "foo/bar", StringUtils.concatWithSeparator("/", "foo/", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar", StringUtils.concatWithSeparator("/", "foo", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar/", StringUtils.concatWithSeparator("/", "foo/", "bar/"));
        Assert.assertEquals("Should be same string", "foo/bar/theta", StringUtils.concatWithSeparator("/", "foo/", "bar", "theta"));

        Assert.assertEquals("Should be same string", "", StringUtils.concatWithSeparator("/", ""));
        Assert.assertEquals("Should be same string", "foo", StringUtils.concatWithSeparator("/", "foo"));
        Assert.assertEquals("Should be same string", "foo/bar", StringUtils.concatWithSeparator("/", "foo", "bar"));
        Assert.assertEquals("Should be same string", "foo/bar/theta", StringUtils.concatWithSeparator("/", "foo", "bar", "theta"));
    }

    @Test
    public void test_concat() {
        Assert.assertEquals("Should be same string", "", StringUtils.concat(""));
        Assert.assertEquals("Should be same string", "foo", StringUtils.concat("foo"));
        Assert.assertEquals("Should be same string", "foobar", StringUtils.concat("foo", "bar"));
        Assert.assertEquals("Should be same string", "foobartheta", StringUtils.concat("foo", "bar", "theta"));
    }

}
