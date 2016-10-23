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
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Tests the AbstractSoapRequestHandler.
 *
 * @author Scot P. Floess
 */
@RunWith( MockitoJUnitRunner.class )
public class AbstractSoapHandlerTest {
    
    static class StubSoapRequestHandler extends AbstractSoapHandler {
        
        final boolean isProcessRequestExceptionRaised;
        final boolean isProcessResponseExceptionRaised;
        
        boolean wasProcessRequestExceptionRaised;
        boolean wasProcessResponseExceptionRaised;
        
        boolean wasProcessRequestCalled;
        boolean wasProcessResponseCalled;
        boolean wasProcessFailureCalled;
        
        StubSoapRequestHandler(final boolean isProcessRequestExceptionRaised, final boolean isProcessResponseExceptionRaised) {
            this.isProcessRequestExceptionRaised = isProcessRequestExceptionRaised;
            this.isProcessResponseExceptionRaised = isProcessResponseExceptionRaised;
            
            this.wasProcessRequestExceptionRaised = false;
            this.wasProcessResponseExceptionRaised = false;
            
            this.wasProcessRequestCalled = false;
            this.wasProcessResponseCalled = false;
            this.wasProcessFailureCalled = false;
        }
        
        StubSoapRequestHandler() {
            this(false, false);
        }
        
        @Override
        protected boolean processRequest(SOAPMessageContext msgContext) throws SOAPException {
            wasProcessRequestCalled = true;
            
            if (isProcessRequestExceptionRaised) {
                wasProcessRequestExceptionRaised = true;
                
                throw new SOAPException();
            }
            
            return true;
        }
        
        @Override
        protected boolean processResponse(SOAPMessageContext msgContext) throws SOAPException {
            wasProcessResponseCalled = true;
            
            if (isProcessResponseExceptionRaised) {
                wasProcessResponseExceptionRaised = true;
                
                throw new SOAPException();
            }
            
            return true;
        }
        
        @Override
        protected void processFailure(final SOAPException soapException) {
            wasProcessFailureCalled = true;
            super.processFailure(soapException);
        }
    }
    
    @Mock
    SOAPMessageContext soapMessageContext;
    
    @Mock
    SOAPMessage soapMessage;
    
    @Before
    public void init() {
        Mockito.when(soapMessageContext.getMessage()).thenReturn(soapMessage);
    }

    /**
     * Test constructor.
     */
    @Test
    public void test_constructor() {
        new StubSoapRequestHandler();
    }

    /**
     * Test the processFailure() method.
     */
    @Test
    public void test_processFailure() throws SOAPException {
        new StubSoapRequestHandler().processFailure(new SOAPException());
    }

    /**
     * Tests returning the headers.
     */
    @Test
    public void test_getHeaders() {
        Assert.assertNull("Should be null", new StubSoapRequestHandler().getHeaders());
    }

    /**
     * Tests handleMessage() when a SOAPException is raised for a processResponse().
     */
    @Test
    public void test_handleMessage_processResponse_SOAPException() throws SOAPException {
        final StubSoapRequestHandler stub = new StubSoapRequestHandler(false, true);
        
        Mockito.when(soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(false);
        
        Assert.assertFalse("Should have gotten a false", stub.handleMessage(soapMessageContext));
        Assert.assertTrue("Should have called processResponse()", stub.wasProcessResponseCalled);
        Assert.assertTrue("Should have raised a SOAPException from processResponse()", stub.wasProcessResponseExceptionRaised);
        Assert.assertFalse("Should not have called processRequest()", stub.wasProcessRequestExceptionRaised);
        Assert.assertFalse("Should not have raised a SOAPException processRequest()", stub.wasProcessRequestExceptionRaised);
        Assert.assertTrue("Should have called processFailure()", stub.wasProcessFailureCalled);
        
        Mockito.verify(soapMessage, Mockito.times(0)).saveChanges();
    }

    /**
     * Tests handleMessage() for a processResponse().
     */
    @Test
    public void test_handleMessage_processResponse() throws SOAPException {
        final StubSoapRequestHandler stub = new StubSoapRequestHandler(false, false);
        
        Mockito.when(soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(false);
        
        Assert.assertTrue("Should have gotten a true", stub.handleMessage(soapMessageContext));
        Assert.assertTrue("Should have called processResponse()", stub.wasProcessResponseCalled);
        Assert.assertFalse("Should have called processResponse()", stub.wasProcessResponseExceptionRaised);
        Assert.assertFalse("Should not have called processRequest()", stub.wasProcessRequestCalled);
        Assert.assertFalse("Should not have raised a SOAPException processRequest()", stub.wasProcessRequestExceptionRaised);
        Assert.assertFalse("Should not have called processFailure()", stub.wasProcessFailureCalled);
        
        Mockito.verify(soapMessage, Mockito.times(0)).saveChanges();
    }

    /**
     * Tests handleMessage() when a SOAPException is raised for a processRequest().
     */
    @Test
    public void test_handleMessage_processesRequest_SOAPException() throws SOAPException {
        final StubSoapRequestHandler stub = new StubSoapRequestHandler(true, false);
        
        Mockito.when(soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(true);
        
        Assert.assertFalse("Should have gotten a false", stub.handleMessage(soapMessageContext));
        Assert.assertFalse("Should not have called processResponse()", stub.wasProcessResponseCalled);
        Assert.assertFalse("Should not have raised a SOAPException from  processResponse()", stub.wasProcessResponseExceptionRaised);
        Assert.assertTrue("Should have called processRequest()", stub.wasProcessRequestCalled);
        Assert.assertTrue("Should have raised a SOAPException from processRequest()", stub.wasProcessRequestExceptionRaised);
        Assert.assertTrue("Should have called processFailure()", stub.wasProcessFailureCalled);
        
        Mockito.verify(soapMessage, Mockito.times(0)).saveChanges();
    }

    /**
     * Tests handleMessage() for a processResponse().
     */
    @Test
    public void test_handleMessage_processRequest() throws SOAPException {
        final StubSoapRequestHandler stub = new StubSoapRequestHandler(false, false);
        
        Mockito.when(soapMessageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)).thenReturn(true);
        
        Assert.assertTrue("Should have gotten a true", stub.handleMessage(soapMessageContext));
        Assert.assertFalse("Should not have called processResponse()", stub.wasProcessResponseCalled);
        Assert.assertFalse("Should not have called processResponse()", stub.wasProcessResponseExceptionRaised);
        Assert.assertTrue("Should have called processRequest()", stub.wasProcessRequestCalled);
        Assert.assertFalse("Should not have raised a SOAPException processRequest()", stub.wasProcessRequestExceptionRaised);
        Assert.assertFalse("Should not have called processFailure()", stub.wasProcessFailureCalled);
        
        Mockito.verify(soapMessage, Mockito.times(1)).saveChanges();
    }

    /**
     * Tests handleFault()
     */
    @Test
    public void test_handleFault() {
        Assert.assertTrue("Should have handled fault", new StubSoapRequestHandler(false, false).handleFault(soapMessageContext));
    }

    /**
     * Tests close()
     */
    @Test
    public void test_close() {
        new StubSoapRequestHandler(false, false).close(soapMessageContext);
    }
}
