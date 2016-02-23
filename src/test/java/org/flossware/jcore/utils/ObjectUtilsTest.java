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
 * Tests the ObjectUtils utility class.
 *
 * @author Scot P. Floess
 */
public class ObjectUtilsTest {

    /**
     * Tests a null object a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureObject_msg_fail() {
        ObjectUtils.ensureObject(null, "No null!");
    }

    /**
     * Tests a non-null object.
     */
    @Test
    public void test_ensureObject_msg() {
        final Object obj = new Object();
        Assert.assertSame("Should be correct value", obj, ObjectUtils.ensureObject(obj, "No null"));
    }

    /**
     * Tests a null object a failure message.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_ensureObject_fail() {
        ObjectUtils.ensureObject(null);
    }

    /**
     * Tests a non-null object.
     */
    @Test
    public void test_ensureObject() {
        final Object obj = new Object();
        Assert.assertSame("Should be correct value", obj, ObjectUtils.ensureObject(obj));
    }
    
    /**
     * Tests a null object for a package.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPackage_fail() {
        ObjectUtils.getPackage(null);
    }
        
    /**
     * Tests an object for a package.
     */
    @Test
    public void test_getPackage() {
        Assert.assertEquals("Should be correct package", getClass().getPackage().getName(), ObjectUtils.getPackage(new ObjectUtilsTest()));
    }
}
