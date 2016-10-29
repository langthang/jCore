/*
 * Copyright (C) 2015 Scot P. Floess
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

import java.util.Set;
import java.util.logging.Level;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.AbstractCommonBase;
import org.flossware.jcore.utils.soap.SoapUtils;

/**
 * Abstract base class and partial implementation for SOAPHandler. Acts as a template to allow subclasses to implement the
 * request or response on handleMessage().
 *
 * @author sfloess
 */
public abstract class AbstractSoapHandler extends AbstractCommonBase implements SOAPHandler<SOAPMessageContext> {

    /**
     * Default constructor.
     */
    protected AbstractSoapHandler() {
    }

    /**
     * Subclasses should implement this method when processing a request via <code>msgContext</code>.
     *
     * @param msgContext the request message context.
     *
     * @return the result of processing the msgContext request.
     *
     * @throws SOAPException if any problems arise processing the request.
     */
    protected abstract boolean processRequest(SOAPMessageContext msgContext) throws SOAPException;

    /**
     * Subclasses should implement this method when processing a response via <code>msgContext</code>.
     *
     * @param msgContext the response message context.
     *
     * @return the result of processing the msgContext response.
     *
     * @throws SOAPException if any problems arise processing the response.
     */
    protected abstract boolean processResponse(SOAPMessageContext msgContext) throws SOAPException;

    /**
     * Override this method if better failure processing is desired.
     *
     * @param soapException the failure that arose when handling our message.
     */
    protected void processFailure(final SOAPException soapException) {
        getLogger().log(Level.SEVERE, "Problem handling msg", soapException);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set getHeaders() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleMessage(final SOAPMessageContext msgContext) {
        SoapUtils.logSoapMessageContextIfLevel(getLogger(), Level.FINE, "Attempting to process", msgContext);

        try {
            if (!SoapUtils.isRequest(msgContext)) {
                return processResponse(msgContext);
            }

            final boolean retVal = processRequest(msgContext);

            msgContext.getMessage().saveChanges();

            return retVal;
        } catch (final SOAPException soapException) {
            SoapUtils.logSoapMessageContext(getLogger(), Level.SEVERE, "Failed to process", msgContext);
            processFailure(soapException);
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean handleFault(final SOAPMessageContext msgContext) {
        SoapUtils.logSoapMessageContext(getLogger(), Level.SEVERE, "Received a SOAP fault for SOAPMessageContext", msgContext);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close(final MessageContext msgContext) {
        log(Level.FINEST, "Request to close MessageContext:  {0}", msgContext);
    }
}
