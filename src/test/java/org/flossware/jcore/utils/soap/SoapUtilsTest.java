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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.io.OutputStreamFactory;
import org.flossware.jcore.soap.SoapException;
import org.flossware.jcore.utils.TestUtils;
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
@RunWith( MockitoJUnitRunner.class )
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

    @Mock
    QName headerName;

    @Mock
    Logger logger;

    @Mock
    OutputStreamFactory outputStreamFactory;

    @Mock
    ByteArrayOutputStream byteArrayOutputStream;

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

    /**
     * Test adding a SOAP header value with a null SOAP header.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_null_SOAPHeader() throws SOAPException {
        SoapUtils.addSoapHeaderValue((SOAPHeader) null, headerName, TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());
    }

    /**
     * Test adding a SOAP header value with a null SOAP header.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_null_QName() throws SOAPException {
        SoapUtils.addSoapHeaderValue(soapHeader, null, TestUtils.generateUniqueStr(), TestUtils.generateUniqueStr());
    }

    /**
     * Test adding a SOAP header value with a null name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_null_name() throws SOAPException {
        SoapUtils.addSoapHeaderValue(soapHeader, headerName, null, TestUtils.generateUniqueStr());
    }

    /**
     * Test adding a SOAP header value with an empty name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_empty_name() throws SOAPException {
        SoapUtils.addSoapHeaderValue(soapHeader, headerName, "", TestUtils.generateUniqueStr());
    }

    /**
     * Test adding a SOAP header value with a null name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_null_value() throws SOAPException {
        SoapUtils.addSoapHeaderValue(soapHeader, headerName, TestUtils.generateUniqueStr(), null);
    }

    /**
     * Test adding a SOAP header value with an empty name.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_addSoapHeaderValue_SOAPHeader_empty_value() throws SOAPException {
        SoapUtils.addSoapHeaderValue(soapHeader, headerName, TestUtils.generateUniqueStr(), "");
    }

    /**
     * Test using a null port.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_setHandler_null_port() {
        SoapUtils.setHandler(null, Mockito.mock(Handler.class));
    }

    /**
     * Test using a null handler.
     */
    @Test( expected = IllegalArgumentException.class )
    public void test_setHandler_null_handler() {
        SoapUtils.setHandler("foo", null);
    }

    /**
     * Test setting the handler.
     */
    @Test
    public void test_setHandler() {
        final BindingProvider port = Mockito.mock(BindingProvider.class);
        final Handler handler = Mockito.mock(Handler.class);
        final Binding binding = Mockito.mock(Binding.class);

        Mockito.when(port.getBinding()).thenReturn(binding);

        final BindingProvider toCompare = SoapUtils.setHandler(port, handler);

        Assert.assertSame("Should be same binding provider", toCompare, port);

        Mockito.verify(binding, Mockito.times(1)).setHandlerChain(Mockito.anyList());
    }

    /**
     * Test convertToString() with an OutputStreamFactory and SOAPMessage where a SOAPException is raised.
     */
    @Test( expected = SoapException.class )
    public void test_convertToString_OutputStreamFactory_SOAPException() throws SOAPException, IOException {
        Mockito.doThrow(new SOAPException()).when(soapMessage).writeTo(Mockito.any(OutputStream.class));

        SoapUtils.convertToString(outputStreamFactory, soapMessage);
    }

    /**
     * Test convertToString() with an OutputStreamFactory SOAPMessage where an IOException is raised.
     */
    @Test( expected = SoapException.class )
    public void test_convertToString_OutputStreamFactory_IOException() throws SOAPException, IOException {
        Mockito.doThrow(new IOException()).when(soapMessage).writeTo(Mockito.any(OutputStream.class));

        SoapUtils.convertToString(outputStreamFactory, soapMessage);
    }

    /**
     * Test convertToString() with a OutputStreamFactory and SOAPMessage.
     */
    @Test
    public void test_convertToString_OutputStreamFactory() throws SOAPException, IOException {
        Mockito.when(outputStreamFactory.createOutputStream()).thenReturn(byteArrayOutputStream);
        Assert.assertNotNull("Should have computed a string", SoapUtils.convertToString(outputStreamFactory, soapMessage));
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(ByteArrayOutputStream.class));
    }

    /**
     * Test convertToString() with a SOAPMessage and a SOAPException is raised.
     */
    @Test( expected = SoapException.class )
    public void test_convertToString_SoapMessage_SOAPException() throws SOAPException, IOException {
        Mockito.doThrow(new SOAPException()).when(soapMessage).writeTo(Mockito.any(OutputStream.class));

        SoapUtils.convertToString(soapMessage);
    }

    /**
     * Test convertToString() with a SOAPMessage and an IOException is raised.
     */
    @Test( expected = SoapException.class )
    public void test_convertToString_SoapMessage_IOException() throws SOAPException, IOException {
        Mockito.doThrow(new IOException()).when(soapMessage).writeTo(Mockito.any(OutputStream.class));

        SoapUtils.convertToString(soapMessage);
    }

    /**
     * Test convertToString() with a SOAPMessage.
     */
    @Test
    public void test_convertToString_SoapMessage() throws SOAPException, IOException {
        Assert.assertNotNull("Should have computed a string", SoapUtils.convertToString(soapMessage));
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(ByteArrayOutputStream.class));
    }

    /**
     * Test convertToString() with a SOAPMessage.
     */
    @Test
    public void test_convertToString_SOAPMessageContext() throws SOAPException, IOException {
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);
        Assert.assertNotNull("Should have computed a string", SoapUtils.convertToString(soapMessageContext));
        Mockito.verify(soapMessageContext, Mockito.times(1)).getMessage();
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage.
     */
    @Test
    public void test_logSoapMessage() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.SEVERE);

        SoapUtils.logSoapMessage(logger, Level.SEVERE, TestUtils.generateUniqueStr(), soapMessage);

        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage.
     */
    @Test
    public void test_logSoapMessageContext() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.FINE);
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);

        SoapUtils.logSoapMessageContext(logger, Level.FINE, TestUtils.generateUniqueStr(), soapMessageContext);

        Mockito.verify(soapMessageContext, Mockito.times(1)).getMessage();
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage - wrong level.
     */
    @Test
    public void test_logSoapMessageIfLevel_wrongLevel() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.SEVERE);

        SoapUtils.logSoapMessageIfLevel(logger, Level.FINE, TestUtils.generateUniqueStr(), soapMessage);

        Mockito.verify(soapMessage, Mockito.times(0)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage - wrong level.
     */
    @Test
    public void test_logSoapMessageIfLevel() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.FINE);

        SoapUtils.logSoapMessageIfLevel(logger, Level.FINE, TestUtils.generateUniqueStr(), soapMessage);

        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage - wrong level.
     */
    @Test
    public void test_logSoapMessageContextIfLevel_wrongLevel() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.SEVERE);
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);

        SoapUtils.logSoapMessageContextIfLevel(logger, Level.FINE, TestUtils.generateUniqueStr(), soapMessageContext);

        Mockito.verify(soapMessageContext, Mockito.times(1)).getMessage();
        Mockito.verify(soapMessage, Mockito.times(0)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test logging a SOAPMessage - wrong level.
     */
    @Test
    public void test_logSoapMessageContextIfLevel() throws SOAPException, IOException {
        Mockito.when(logger.getLevel()).thenReturn(Level.SEVERE);
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);

        SoapUtils.logSoapMessageContextIfLevel(logger, Level.SEVERE, TestUtils.generateUniqueStr(), soapMessageContext);

        Mockito.verify(soapMessageContext, Mockito.times(1)).getMessage();
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test appendSoapMessage().
     */
    @Test
    public void test_appendSoapMessage() throws SOAPException, IOException {
        final StringBuilder sb = new StringBuilder();

        final StringBuilder toCompare = SoapUtils.appendSoapMessage(sb, soapMessage);

        Assert.assertSame("Should be the same StringBuilder", toCompare, sb);

        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }

    /**
     * Test appendSoapMsgContext().
     */
    @Test
    public void test_appendSoapMsgContext() throws SOAPException, IOException {
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);

        final StringBuilder sb = new StringBuilder();

        final StringBuilder toCompare = SoapUtils.appendSoapMsgContext(sb, soapMessageContext);

        Assert.assertSame("Should be the same StringBuilder", toCompare, sb);

        Mockito.verify(soapMessageContext, Mockito.times(1)).getMessage();
        Mockito.verify(soapMessage, Mockito.times(1)).writeTo(Mockito.any(OutputStream.class));
    }
}
