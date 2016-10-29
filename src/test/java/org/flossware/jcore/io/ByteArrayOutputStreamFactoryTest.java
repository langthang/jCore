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
package org.flossware.jcore.io;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the ByteArrayOutputStreamFactory class.
 *
 * @author Scot P. Floess
 */
public class ByteArrayOutputStreamFactoryTest {

    /**
     * Tests the constructor.
     */
    @Test
    public void test_constructor() {
        new ByteArrayOutputStreamFactory();
    }

    /**
     * Tests creating an OutputStream.
     */
    @Test
    public void test_createOutputStream() {
        final OutputStream toCompare = new ByteArrayOutputStreamFactory().createOutputStream();

        Assert.assertNotNull("Should have created an outputStream", toCompare);
        Assert.assertTrue("Should be a ByteArrayOutputStream", toCompare instanceof ByteArrayOutputStream);
    }

}
