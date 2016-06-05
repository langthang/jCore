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
package org.flossware.jcore.utils.soap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the SoapUtils utility class.
 *
 * @author Scot P. Floess
 */
@RunWith(MockitoJUnitRunner.class)
public class SoapUtilsTest {

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
    SOAPHeader soapHeaderAdd;

    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        final Constructor constructor = SoapUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance(new Object[0]);
    }

    /**
     * Test isRequest.
     */
    @Test
    public void test_isRequest_Object() {
        Assert.assertTrue("Should be true", SoapUtils.isRequest(Boolean.TRUE));
        Assert.assertFalse("Should be false", SoapUtils.isRequest(Boolean.FALSE));
        Assert.assertFalse("Should be false", SoapUtils.isRequest((Object) null));
    }

    /**
     * Test a request
     */
    @Test
    public void test_isRequest_true() {
        Mockito.when(soapMessageContext.get(Mockito.anyString())).thenReturn(true);

        Assert.assertTrue("Should be a request", SoapUtils.isRequest(soapMessageContext));
    }

    /**
     * Test a request
     */
    @Test
    public void test_isRequest_false() {
        Mockito.when(soapMessageContext.get(Mockito.anyString())).thenReturn(false);

        Assert.assertFalse("Should not be a request", SoapUtils.isRequest(soapMessageContext));
    }

    /**
     * Test a request
     */
    @Test
    public void test_isRequest_null() {
        Assert.assertFalse("Should not be a request", SoapUtils.isRequest(soapMessageContext));
    }

    /**
     * Test retrieving a soap header where the soap header exists.
     */
    @Test
    public void test_getSoapHeader_set() throws SOAPException {
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);
        Mockito.when(soapMessage.getSOAPPart()).thenReturn(soapPart);
        Mockito.when(soapPart.getEnvelope()).thenReturn(soapEnvelope);
        Mockito.when(soapEnvelope.getHeader()).thenReturn(soapHeader);

        Assert.assertSame("Should be the same header", soapHeader, SoapUtils.getSoapHeader(soapMessageContext));
    }

    /**
     * Test retrieving a soap header where the soap header does not exist.
     */
    @Test
    public void test_getSoapHeader_unset() throws SOAPException {
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);
        Mockito.when(soapMessage.getSOAPPart()).thenReturn(soapPart);
        Mockito.when(soapPart.getEnvelope()).thenReturn(soapEnvelope);
        Mockito.when(soapEnvelope.addHeader()).thenReturn(soapHeaderAdd);

        Assert.assertSame("Should be the same header", soapHeaderAdd, SoapUtils.getSoapHeader(soapMessageContext));
    }
}
