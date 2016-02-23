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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the file monitor class.
 *
 * @author Scot P. Floess
 */
public class FileMonitorTest {

    private File tempFile;

    /**
     * Create the temp file before test starts.
     *
     * @throws IOException
     */
    @Before
    public void testSetup() throws IOException {
        tempFile = File.createTempFile("P" + System.currentTimeMillis() + "_", "_S" + System.currentTimeMillis());
        tempFile.deleteOnExit();
    }

    /**
     * Remove temp file when test done.
     */
    @After
    public void testCleanup() {
        tempFile.delete();
    }

    /**
     * Test a null file.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_nullFile() {
        new FileMonitor((File) null);
    }

    /**
     * Test a null file name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_nullString() {
        new FileMonitor((String) null);
    }

    /**
     * Test an empty file name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_emptyString() {
        new FileMonitor("");
    }

    /**
     * Test a file name full of spaces.
     */
    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_spacesString() {
        new FileMonitor("    ");
    }

    /**
     * Test retrieving the file.
     */
    @Test
    public void test_getFile() {
        Assert.assertEquals("Should be same file", tempFile, new FileMonitor(tempFile).getFile());
    }

    /**
     * Test file exists.
     */
    @Test
    public void test_exists() throws IOException {
        final File tempFileNotExist = File.createTempFile("prefix1", "suffix1");
        tempFileNotExist.deleteOnExit();
        tempFileNotExist.delete();

        Assert.assertFalse("File should not exist", new FileMonitor(tempFileNotExist).exists());
        Assert.assertTrue("File should exist", new FileMonitor(tempFile).exists());
    }

    /**
     * Test a file changing.
     */
    public void test_isChanged() throws FileNotFoundException, IOException {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);

        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());

        final FileOutputStream fos = new FileOutputStream(fileMonitor.getFile());
        fos.write(0);

        Assert.assertTrue("File should have changed", fileMonitor.isChanged());
        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());
    }
}
