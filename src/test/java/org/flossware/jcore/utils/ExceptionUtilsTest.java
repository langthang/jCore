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

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ExceptionUtils utility class.
 *
 * @author Scot P. Floess
 */
public class ExceptionUtilsTest {

    class TestException extends Exception {

        public TestException() {

        }

        public TestException(final Throwable causedBy) {
            super(causedBy);
        }
    }

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = ExceptionUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Tests of contains is processable.
     */
    @Test
    public void test_isContainsProcessable() {
        Assert.assertFalse("Should not be processable", ExceptionUtils.isContainsProcessable(null, null));
        Assert.assertFalse("Should not be processable", ExceptionUtils.isContainsProcessable(null, ExceptionUtilsTest.class));
        Assert.assertFalse("Should not be processable", ExceptionUtils.isContainsProcessable(new RuntimeException(), null));
        Assert.assertTrue("Should be processable", ExceptionUtils.isContainsProcessable(new NullPointerException(), RuntimeException.class));
    }

    /**
     * Tests containing a throwable via its class.
     */
    @Test
    public void tests_contains_class() {
        Assert.assertFalse("Should not contain the exception", ExceptionUtils.contains(new RuntimeException(), NullPointerException.class));
        Assert.assertFalse("Should not contain the exception", ExceptionUtils.contains(new RuntimeException(new IllegalArgumentException()), NullPointerException.class));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new RuntimeException(), RuntimeException.class));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new NullPointerException(), RuntimeException.class));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new TestException(new IOException()), TestException.class));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new TestException(new TestException(new IOException())), IOException.class));
    }

    /**
     * Tests containing a throwable via instance.
     */
    @Test(expected = IllegalArgumentException.class)
    public void tests_contains_throwable_null() {
        ExceptionUtils.contains(new RuntimeException(), (Throwable) null);
    }

    /**
     * Tests containing a throwable via instance.
     */
    @Test
    public void tests_contains_throwable() {
        Assert.assertFalse("Should not contain the exception", ExceptionUtils.contains(new RuntimeException(), new NullPointerException()));
        Assert.assertFalse("Should not contain the exception", ExceptionUtils.contains(new RuntimeException(new IllegalArgumentException()), new NullPointerException()));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new RuntimeException(), new RuntimeException()));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new NullPointerException(), new RuntimeException()));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new TestException(new IOException()), new TestException()));
        Assert.assertTrue("Should contain the exception", ExceptionUtils.contains(new TestException(new TestException(new IOException())), new IOException()));

    }
}
