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

import java.io.File;
import java.io.IOException;
import org.flossware.jcore.io.FileException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the FileUtils utility class.
 *
 * @author Scot P. Floess
 */
public class FileUtilsTest {

    /**
     * Tests getting a file input stream for a null file.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getFileInputStream__file_null() {
        FileUtils.getFileInputStream((File) null);
    }

    /**
     * Tests getting a file input stream for a file not found.
     *
     * @throws java.io.IOException
     */
    @Test(expected = FileException.class)
    public void test_getFileInputStream_file_fileNotFound() throws IOException {
        final File tempFile = File.createTempFile("temp-file-name", ".tmp");
        tempFile.delete();

        FileUtils.getFileInputStream(tempFile);
    }

    /**
     * Tests getting a file input stream
     *
     * @throws java.io.IOException
     */
    @Test
    public void test_getFileInputStream_file() throws IOException {
        final File tempFile = File.createTempFile("temp-file-name", ".tmp");

        Assert.assertNotNull("Should have gotten an input stream", FileUtils.getFileInputStream(tempFile));

        tempFile.delete();
    }

    /**
     * Tests getting a file input stream for a null file name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_getFileInputStream__string_null() {
        FileUtils.getFileInputStream((String) null);
    }

    /**
     * Tests getting a file input stream for a file not found.
     *
     * @throws java.io.IOException
     */
    @Test(expected = FileException.class)
    public void test_getFileInputStream_string_fileNotFound() throws IOException {
        final File tempFile = File.createTempFile("temp-file-name", ".tmp");
        tempFile.delete();

        FileUtils.getFileInputStream(tempFile.getPath());
    }

    /**
     * Tests getting a file input stream
     *
     * @throws java.io.IOException
     */
    @Test
    public void test_getFileInputStream_string() throws IOException {
        final File tempFile = File.createTempFile("temp-file-name", ".tmp");

        Assert.assertNotNull("Should have gotten an input stream", FileUtils.getFileInputStream(tempFile.getPath()));

        tempFile.delete();
    }
}
