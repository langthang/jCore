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
package org.flossware.jcore.operation;

import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test the NullPrepare class. Really does very little.
 *
 * @author Scot P. Floess
 */
public class NullPrepareTest {

    /**
     * Test the whole class.
     */
    @Test
    public void test() {
        final NullPrepare<String> nullPrepare = new NullPrepare();
        final String str = TestUtils.generateUniqueStr();

        Assert.assertSame("Should be the same string", str, nullPrepare.prepare(str));
    }
}
