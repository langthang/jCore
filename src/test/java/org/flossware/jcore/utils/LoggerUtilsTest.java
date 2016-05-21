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

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the LoggerUtils utility class.
 *
 * @author Scot P. Floess
 */
public class LoggerUtilsTest {

    static final Logger LOGGER = Logger.getLogger(LoggerUtilsTest.class.getName());

    /**
     * Doesn't really test anything - just ensures we can log.
     */
    @Test
    public void test_log() {
        LoggerUtils.log(LOGGER, Level.SEVERE, "This is a log {0} for {1}", TestUtils.generateUniqueStr("0"), TestUtils.generateUniqueStr("1"));
    }

    /**
     * Tests we log and return the correct value.
     */
    @Test
    public void test_logF_V() {
        final String toCompare = TestUtils.generateUniqueStr("0");

        Assert.assertSame("Should have gotten correct return value", toCompare, LoggerUtils.logF(LOGGER, Level.SEVERE, "This is it {0}", toCompare));
    }

    /**
     * Tests we log and return the correct value from the var args.
     */
    @Test
    public void test_logFi_varArgs() {
        final String str0 = TestUtils.generateUniqueStr("0");
        final String str1 = TestUtils.generateUniqueStr("1");
        final String str2 = TestUtils.generateUniqueStr("2");
        final String str3 = TestUtils.generateUniqueStr("3");

        Assert.assertSame("Should have gotten correct return value", str2, LoggerUtils.logFi(LOGGER, Level.SEVERE, "This is it {0} {1} {2} {3}", 2, str0, str1, str2, str3));
    }

    /**
     * Tests we log and return the correct value from the var args.
     */
    @Test
    public void test_logF_varArgs() {
        final String str0 = TestUtils.generateUniqueStr("0");
        final String str1 = TestUtils.generateUniqueStr("1");
        final String str2 = TestUtils.generateUniqueStr("2");
        final String str3 = TestUtils.generateUniqueStr("3");

        Assert.assertSame("Should have gotten correct return value", str0, LoggerUtils.logF(LOGGER, Level.SEVERE, "This is it {0} {1} {2} {3}", str0, str1, str2, str3));
    }
}
