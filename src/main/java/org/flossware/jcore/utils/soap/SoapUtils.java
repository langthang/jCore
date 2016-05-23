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

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.utils.LoggerUtils;

/**
 * Soap utility class.
 *
 * @author sfloess
 */
public class SoapUtils {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ServiceUtils.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Return false if val is null otherwise the boolean representation of val.
     *
     * @param val the value to examine.
     *
     * @return false if val is null or the boolean representation of val.
     */
    static boolean isRequest(final Object val) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Is request [{0}] for val [{1}]", (val == null ? false : (Boolean) val), val);
    }

    /**
     * If the msgContext is a request return true otherwise false.
     *
     * @param msgContext to examine if its a request.
     *
     * @return true if msgContext is a request or false if not.
     */
    public static boolean isRequest(final SOAPMessageContext msgContext) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Is request [{0}] for SOAPMessageContext [{1}]", isRequest(msgContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY)), msgContext);
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
            return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Soap header is [{0}] for SOAPMessageContext [{1}]", msgContext.getMessage().getSOAPPart().getEnvelope().getHeader(), msgContext);
        }

        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Soap header is [{0}] for SOAPMessageContext [{1}]", msgContext.getMessage().getSOAPPart().getEnvelope().addHeader(), msgContext);
    }

    private SoapUtils() {
    }

}
