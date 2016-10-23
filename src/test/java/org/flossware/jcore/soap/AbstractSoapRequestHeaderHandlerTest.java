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

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.utils.TestUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the AbstractSoapResponseHeaderHandler.
 *
 * @author Scot P. Floess
 */
@RunWith( MockitoJUnitRunner.class )
public class AbstractSoapRequestHeaderHandlerTest {

    static class StubSoapRequestHeaderHandler extends AbstractSoapRequestHeaderHandler {

        final QName headerName;
        final String name;
        final String value;

        StubSoapRequestHeaderHandler(final QName headerName, final String name, final String value) {
            this.headerName = headerName;
            this.name = name;
            this.value = value;
        }

        @Override
        protected QName getHeaderName() {
            return headerName;
        }

        @Override
        protected String getName() {
            return name;
        }

        @Override
        protected String getValue() {
            return value;
        }
    }

    @Mock
    SOAPMessageContext soapMessageContext;

    @Mock
    SOAPMessage soapMessage;

    @Mock
    SOAPPart soapPart;

    @Mock
    SOAPEnvelope soapEnvelope;

    @Mock
    SOAPHeader soapHeader;

    @Mock
    SOAPElement soapHeaderNameElement;

    @Mock
    SOAPElement soapNameElement;

    @Mock
    QName headerName;

    String name;
    String value;

    @Before
    public void init() throws SOAPException {
        name = TestUtils.generateUniqueStr("name");
        value = TestUtils.generateUniqueStr("value");

        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);
        Mockito.when(soapMessage.getSOAPPart()).thenReturn(soapPart);
        Mockito.when(soapPart.getEnvelope()).thenReturn(soapEnvelope);
        Mockito.when(soapEnvelope.getHeader()).thenReturn(soapHeader);
        Mockito.when(soapHeader.addChildElement(headerName)).thenReturn(soapHeaderNameElement);
        Mockito.when(soapHeaderNameElement.addChildElement(name)).thenReturn(soapNameElement);
    }

    /**
     * Test constructor.
     */
    @Test
    public void test_constructor() {
        new StubSoapRequestHeaderHandler(headerName, name, value);
    }

    /**
     * Test the processRequest() with a null SOAPMessageContext.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_null_SOAPMessageContext() throws SOAPException {
        new StubSoapRequestHeaderHandler(headerName, name, value).processRequest(null);
    }

    /**
     * Test the processRequest() with a null name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_null_headerName() throws SOAPException {
        new StubSoapRequestHeaderHandler(null, name, value).processRequest(soapMessageContext);
    }

    /**
     * Test the processRequest() with an empty name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_null_name() throws SOAPException {
        new StubSoapRequestHeaderHandler(headerName, null, value).processRequest(soapMessageContext);
    }

    /**
     * Test the processRequest() with an empty name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_empty_name() throws SOAPException {
        new StubSoapRequestHeaderHandler(headerName, "", value).processRequest(soapMessageContext);
    }

    /**
     * Test the processRequest() with a null value.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_null_value() throws SOAPException {
        new StubSoapRequestHeaderHandler(headerName, name, null).processRequest(soapMessageContext);
    }

    /**
     * Test the processRequest() with an empty value.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_processRequest_empty_value() throws SOAPException {
        new StubSoapRequestHeaderHandler(headerName, name, "").processRequest(soapMessageContext);
    }

    /**
     * Test the processRequest() method.
     */
    @Test
    public void test_processRequest() throws SOAPException {
        Assert.assertTrue("Should be true", new StubSoapRequestHeaderHandler(headerName, name, value).processRequest(soapMessageContext));
    }
}
