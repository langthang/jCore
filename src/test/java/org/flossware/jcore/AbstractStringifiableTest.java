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
package org.flossware.jcore;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Silly test for the AbstractStringifiable base class.
 *
 * @author Scot P. Floess
 */
public class AbstractStringifiableTest {

    /**
     * Need to extend as base is abstract.
     */
    class Stub extends AbstractStringifiable {

        String id = "" + System.currentTimeMillis();

        /**
         * {@inheritDoc}
         */
        @Override
        public StringBuilder toStringBuilder(final StringBuilder sb, final String prefix) {
            return sb.append(prefix).append(id);
        }

    }

    /**
     * The instance to test against.
     */
    Stub stub;

    @Before
    public void setup() {
        stub = new Stub();
    }

    /**
     * Let's make sure the logger is always set.
     */
    @Test
    public void test_getLogger() {
        Assert.assertNotNull("Should have a logger", new Stub().getLogger());
    }

    /**
     * Test the toStringBuilder method with a StringBuilder.
     */
    @Test
    public void test_toStringBuilder_StringBuilder() {
        Assert.assertEquals("Should be the correct string", stub.id, stub.toStringBuilder(new StringBuilder()).toString());
        Assert.assertEquals("Should be the correct string", "****" + stub.id, stub.toStringBuilder(new StringBuilder().append("****")).toString());
    }

    /**
     * Test the toStringBuilder method with a String.
     */
    @Test
    public void test_toStringBuilder_String() {
        Assert.assertEquals("Should be the correct string", "*****" + stub.id, stub.toStringBuilder("*****").toString());
    }

    /**
     * Test the toStringBuilder method.
     */
    @Test
    public void test_toStringBuilder() {
        Assert.assertEquals("Should be the correct string", stub.id, stub.toString());
    }
}
