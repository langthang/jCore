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
 * Tests the ClassUtils utility class.
 *
 * @author Scot P. Floess
 */
public class ClassUtilsTest {

    /**
     * Tests retrieve a package name on a null class.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getPackageName_null() {
        ClassUtils.getPackageName(null);
    }

    /**
     * Tests retrieving our package name.
     */
    @Test
    public void test_getPackageName() {
        Assert.assertEquals("Should be the same package name", ClassUtilsTest.class.getPackage().getName(), ClassUtils.getPackageName(ClassUtilsTest.class));
    }

    /**
     * Tests getting the class of a null object.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getClass_null() {
        ClassUtils.getClass(null);
    }

    /**
     * Tests retrieving our class.
     */
    @Test
    public void test_getClass() {
        Assert.assertSame("Should be the same class", ClassUtilsTest.class, ClassUtils.getClass(new ClassUtilsTest()));
    }
}
