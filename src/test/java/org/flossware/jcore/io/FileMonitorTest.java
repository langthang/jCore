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
import org.flossware.jcore.utils.StringUtils;
import org.flossware.jcore.utils.TestUtils;
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
        tempFile = File.createTempFile("P" + System.currentTimeMillis() + "_", null);

        tempFile.deleteOnExit();
        tempFile.delete();
    }

    /**
     * Remove temp file when test done.
     */
    @After
    public void testCleanup() {
        tempFile.delete();
    }

    /**
     * Test the last modified observed date.
     */
    @Test
    public void test_getLastModifiedObserved() {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);

        Assert.assertNotNull("Should have a last modified observed", fileMonitor.getLastModifiedObserved());

        Assert.assertEquals("Should be the same modified observed", tempFile.lastModified(), fileMonitor.getLastModifiedObserved().longValue());
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
     * Test with a real file name.
     */
    @Test
    public void test_contructor() {
        final FileMonitor fileMonitor = new FileMonitor(tempFile.getPath());

        Assert.assertNotNull("Should have file", fileMonitor.getFile());
        Assert.assertEquals("Should not have a last modified date", 0, fileMonitor.getLastModifiedObserved().longValue());
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
    }

    /**
     * Test a non existent file from changing.
     */
    @Test
    public void test_isChanged_NoFile() {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);
        tempFile.delete();

        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());
    }

    /**
     * Test a file created but not changed.
     */
    @Test
    public void test_isChanged_NoChange() {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);

        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());
    }

    /**
     * Test a file changing.
     */
    @Test
    public void test_isChanged() throws FileNotFoundException, IOException {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);

        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());

        final FileOutputStream fos = new FileOutputStream(tempFile);
        final byte[] bytes = new byte[1024];
        fos.write(bytes);
        fos.close();

        Assert.assertTrue("File should have changed", fileMonitor.isChanged());
        Assert.assertFalse("File should not have changed", fileMonitor.isChanged());
    }

    /**
     * Test using a string builder and a prefix.
     */
    @Test
    public void test_toStringBuilder() {
        final FileMonitor fileMonitor = new FileMonitor(tempFile);
        final String prefix = TestUtils.generateUniqueStr("Foo", "Bar");

        final StringBuilder stringBuilder = fileMonitor.toStringBuilder(new StringBuilder(), prefix);

        Assert.assertFalse("Should have a string representation", StringUtils.isBlank(stringBuilder.toString()));
        Assert.assertTrue("String representation should start with correct prefix", stringBuilder.toString().startsWith(prefix));
    }
}
