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
package org.flossware.jcore.utils.net;

import java.io.IOException;
import java.net.URL;
import org.flossware.jcore.io.UrlException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the FileUtils utility class.
 *
 * @author Scot P. Floess
 */
public class UrlUtilsTest {

    /**
     * Tests creating a URL with a null.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_null() {
        UrlUtils.createUrl(null);
    }

    /**
     * Tests creating a URL with an empty string.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_emptyString() {
        UrlUtils.createUrl("");
    }

    /**
     * Tests creating a URL with a bad string.
     */
    @Test(expected = UrlException.class)
    public void test_createUrl_badString() {
        UrlUtils.createUrl("ht://foo.com");
    }

    /**
     * Test creating a URL.
     *
     * @throws IOException
     */
    @Test
    public void test_createUrl() throws IOException {
        Assert.assertNotNull("Should have created a url", UrlUtils.createUrl("http://foo.com"));
    }

    /**
     * Tests computing the host URL with a null.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrlAsString_null() {
        UrlUtils.computeHostUrlAsString(null);
    }

    /**
     * Tests computing the host URL URL with an empty string.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrlAsString_emptyString() {
        UrlUtils.computeHostUrlAsString("");
    }

    /**
     * Tests computing the host URL with a bad string.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrlAsString_badString() {
        UrlUtils.computeHostUrlAsString("ht://foo .com");
    }

    /**
     * Test computing the host URL.
     *
     * @throws IOException
     */
    @Test
    public void test_computeHostUrlAsString() throws IOException {
        Assert.assertEquals("Should have created a url", "http://foo.com", UrlUtils.computeHostUrlAsString("http://foo.com/bar/alpha"));
    }

    /**
     * Tests computing the host URL with a null.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrl_null() {
        UrlUtils.computeHostUrl(null);
    }

    /**
     * Tests computing the host URL with an empty string.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrl_emptyString() {
        UrlUtils.computeHostUrl("");
    }

    /**
     * Tests computing the host URL with a bad string.
     */
    @Test(expected = UrlException.class)
    public void test_computeHostUrl_badString() {
        UrlUtils.computeHostUrl("ht://foo!.com");
    }

    /**
     * Test computing the host URL.
     *
     * @throws IOException
     */
    @Test
    public void test_computeHostUrl() throws IOException {
        final URL hostUrl = UrlUtils.computeHostUrl("http://foo.com/bar/alpha");

        Assert.assertNotNull("Should have created a url", hostUrl);
        Assert.assertEquals("Should be the correct protocol", "http", hostUrl.getProtocol());
        Assert.assertEquals("Should be the correct protocol", "foo.com", hostUrl.getHost());
    }
}
