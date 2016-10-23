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
package org.flossware.jcore.soap;

import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Tests the AbstractSoapRequestHandler.
 *
 * @author Scot P. Floess
 */
public class AbstractSoapRequestHandlerTest {

    static class StubSoapRequestHandler extends AbstractSoapRequestHandler {

        @Override
        protected boolean processRequest(SOAPMessageContext msgContext) throws SOAPException {
            return true;
        }
    }

    /**
     * Test constructor.
     */
    @Test
    public void test_constructor() {
        new StubSoapRequestHandler();
    }

    /**
     * Test the processRequest() method.
     */
    @Test
    public void test_processResponse() throws SOAPException {
        Assert.assertTrue("Should be true", new StubSoapRequestHandler().processResponse(Mockito.mock(SOAPMessageContext.class)));
    }
}
