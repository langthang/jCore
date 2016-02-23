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
package org.flossware.jcore.utils.soap;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Soap utility class.
 *
 * @author sfloess
 */
public class SoapUtils {

    /**
     * Return false if val is null otherwise the boolean representation of val.
     *
     * @param val the value to examine.
     *
     * @return false if val is null or the boolean representation of val.
     */
    static boolean isRequest(final Object val) {
        return val == null ? false : (Boolean) val;
    }

    /**
     * If the msgContext is a request return true otherwise false.
     *
     * @param msgContext to examine if its a request.
     *
     * @return true if msgContext is a request or false if not.
     */
    public static boolean isRequest(final SOAPMessageContext msgContext) {
        return isRequest(msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY));
    }

    /**
     * Return our SOAP header or add one if its not found.
     *
     * @param msgContext contains the SOAP header desired.
     *
     * @return the SOAP header if it exists or add one if not found.
     *
     * @throws SOAPException any problems arise adding the SOAP header if missing.
     */
    public static SOAPHeader getSoapHeader(final SOAPMessageContext msgContext) throws SOAPException {
        if (null != msgContext.getMessage().getSOAPPart().getEnvelope().getHeader()) {
            return msgContext.getMessage().getSOAPPart().getEnvelope().getHeader();
        }

        return msgContext.getMessage().getSOAPPart().getEnvelope().addHeader();
    }

    private SoapUtils() {
    }

}
