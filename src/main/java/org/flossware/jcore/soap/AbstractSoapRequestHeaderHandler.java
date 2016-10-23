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
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.utils.soap.SoapUtils;

/**
 * Abstract base class that is a request handler placing items into a SOAP header.
 *
 * @author sfloess
 */
public abstract class AbstractSoapRequestHeaderHandler extends AbstractSoapRequestHandler {

    /**
     * Default constructor.
     */
    protected AbstractSoapRequestHeaderHandler() {
    }

    /**
     * Return the SOAP Header QName.
     *
     * @return the SOAP header QName.
     */
    protected abstract QName getHeaderName();

    /**
     * Return the name of the child element in the SOAP header.
     *
     * @return the name of the child element in the SOAP header.
     */
    protected abstract String getName();

    /**
     * Return the value of the child element in the SOAP header.
     *
     * @return the value of the child element in the SOAP header.
     */
    protected abstract String getValue();

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean processRequest(final SOAPMessageContext msgContext) throws SOAPException {
        SoapUtils.addSoapHeaderValue(msgContext, getHeaderName(), getName(), getValue());
        return true;
    }

}
