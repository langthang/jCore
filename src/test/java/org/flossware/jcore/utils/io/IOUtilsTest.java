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
package org.flossware.jcore.utils.io;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the FileUtils utility class.
 *
 * @author Scot P. Floess
 */
@RunWith(MockitoJUnitRunner.class)
public class IOUtilsTest {

    @Mock
    Closeable closeable;

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = IOUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Tests closing with a null.
     */
    @Test
    public void test_close_null() {
        IOUtils.close(null);
    }

    /**
     * Test closing when an IOException is raised.
     *
     * @throws IOException
     */
    @Test
    public void test_close_IOException() throws IOException {
        Mockito.doThrow(new IOException()).when(closeable).close();

        IOUtils.close(closeable);

        Mockito.verify(closeable).close();
    }

    /**
     * Test successful closes.
     */
    @Test
    public void test_close() throws IOException {
        IOUtils.close(closeable);

        Mockito.verify(closeable).close();
    }

    /**
     * Tests closing with a null.
     */
    @Test
    public void test_closeQuietly_null() {
        IOUtils.closeQuietly(null);
    }

    /**
     * Test closing when an IOException is raised.
     *
     * @throws IOException
     */
    @Test
    public void test_closeQuietly_IOException() throws IOException {
        Mockito.doThrow(new IOException()).when(closeable).close();

        IOUtils.closeQuietly(closeable);

        Mockito.verify(closeable).close();
    }

    /**
     * Test successful closes.
     */
    @Test
    public void test_closeQuietly() throws IOException {
        IOUtils.closeQuietly(closeable);

        Mockito.verify(closeable).close();
    }
}
