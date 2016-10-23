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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.flossware.jcore.soap.SoapException;
import org.flossware.jcore.utils.LoggerUtils;
import org.flossware.jcore.utils.ObjectUtils;
import org.flossware.jcore.utils.StringUtils;

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
    public static boolean isRequest(final Object val) {
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
     * Return our SOAP header or addSoapHeaderValue one if its not found.
     *
     * @param msgContext contains the SOAP header desired.
     *
     * @return the SOAP header if it exists or addSoapHeaderValue one if not found.
     *
     * @throws SOAPException any problems arise adding the SOAP header if missing.
     */
    public static SOAPHeader getSoapHeader(final SOAPMessageContext msgContext) throws SOAPException {
        final SOAPHeader retVal = (null != msgContext.getMessage().getSOAPPart().getEnvelope().getHeader() ? msgContext.getMessage().getSOAPPart().getEnvelope().getHeader() : msgContext.getMessage().getSOAPPart().getEnvelope().addHeader());

        LoggerUtils.log(getLogger(), Level.FINEST, "Soap header is [{0}] for SOAPMessageContext [{1}]", retVal, msgContext);

        return retVal;
    }

    /**
     * Add a SOAP header value.
     *
     * @param soapHeader the SOAP header to add a value to.
     * @param headerName the QName of the SOAP header where we will add value.
     * @param name       the name of the child element for value.
     * @param value      the value to add.
     *
     * @throws SOAPException if any problems arise adding value.
     */
    public static void addSoapHeaderValue(final SOAPHeader soapHeader, final QName headerName, final String name, final String value) throws SOAPException {
        ObjectUtils.ensureObject(soapHeader, "Must provide a soap header!");
        ObjectUtils.ensureObject(headerName, "Must provide a QName!");
        StringUtils.ensureString(name, "Must provide a name");
        StringUtils.ensureString(value, "Must provide a value");

        LoggerUtils.log(getLogger(), Level.FINEST, "Adding head value [{0}] for QName [{1}] in SOAP header {2}", value, headerName, soapHeader);

        final SOAPElement soapHeaderNameElement = soapHeader.addChildElement(headerName);
        final SOAPElement soapNameElement = soapHeaderNameElement.addChildElement(name);
        final SOAPElement soapValueElement = soapNameElement.addTextNode(value);

        soapHeader.addChildElement(headerName).addChildElement(name).addTextNode(value);
    }

    /**
     * Add a SOAP header value.
     *
     * @param msgContext contains the SOAP header for whom we will add value.
     * @param headerName the QName of the SOAP header where we will add value.
     * @param name       the name of the child element for value.
     * @param value      the value to add.
     *
     * @throws SOAPException if any problems arise adding value.
     */
    public static void addSoapHeaderValue(final SOAPMessageContext msgContext, final QName headerName, final String name, final String value) throws SOAPException {
        ObjectUtils.ensureObject(msgContext, "Must provide a soap msg context!");

        LoggerUtils.log(getLogger(), Level.FINEST, "Adding header value [{0}] for QName [{1}] in SOAP message context {2}", value, headerName, msgContext);

        addSoapHeaderValue(getSoapHeader(msgContext), headerName, name, value);
    }

    /**
     * Set the handler for port.
     *
     * @param <P>     the type of port for web service calls.
     *
     * @param port    the port for web service calls.
     * @param handler the new handler to use.
     *
     * @return the port that now has <code>handler</code> in its handler chain.
     *
     * @throws IllegalArgumentException if service/port are null or if session id is null
     */
    public static <P> P setHandler(final P port, final Handler handler) {
        ObjectUtils.ensureObject(port, "Must provide a port!");
        ObjectUtils.ensureObject(handler, "Must provide a handler");

        final List<Handler> handlerChain = new ArrayList<>(1);
        handlerChain.add(handler);

        ((BindingProvider) port).getBinding().setHandlerChain(handlerChain);

        LoggerUtils.log(getLogger(), Level.FINEST, "Setting handler [{0}] for port [{1}]", handler, port);

        return port;
    }

    /**
     * Convert <code>soapMessage</code> to a string.
     *
     * @param soapMessage the SOAP message to convert.
     *
     * @return return the string representation of <code>soapMessage</code>.
     */
    public static String convertToString(final SOAPMessage soapMessage) {
        try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            soapMessage.writeTo(baos);
            return baos.toString();
        } catch (SOAPException | IOException ex) {
            getLogger().log(Level.SEVERE, "Trouble writing soapMessage", ex);

            throw new SoapException(ex);
        }
    }

    /**
     * Convert <code>soapMsgContext</code> to a string.
     *
     * @param soapMsgContext the SOAP message context to convert.
     *
     * @return the string representation of code>soapMsgContext</code>.
     */
    public static String convertToString(final SOAPMessageContext soapMsgContext) {
        return convertToString(soapMsgContext.getMessage());
    }

    /**
     * Log the <code>soapMessage</code>.
     *
     * @param logger      for logging.
     * @param level       the log level.
     * @param message     the message to include in the log.
     * @param soapMessage the SOAP message to log.
     */
    public static void logSoapMessage(final Logger logger, final Level level, final String message, final SOAPMessage soapMessage) {
        LoggerUtils.log(logger, level, message, convertToString(soapMessage));
    }

    /**
     * Log the <code>msgContext</code>.
     *
     * @param logger     for logging.
     * @param level      the log level.
     * @param message    the message to include in the log.
     * @param msgContext the SOAP message context to log.
     */
    public static void logSoapMessageContext(final Logger logger, final Level level, final String message, final SOAPMessageContext msgContext) {
        logSoapMessage(logger, level, message, msgContext.getMessage());
    }

    /**
     * Log the <code>soapMessage</code> if the logger level is <code>level</code>.
     *
     * @param logger      the logger to use for logging.
     * @param level       the log level that if <code>logger</code>'s level is equal, will log <code>message</code> and <code>soapMessage</code>
     * @param message     the message to include in the log.
     * @param soapMessage the SOAP message to log.
     */
    public static void logSoapMessageIfLevel(final Logger logger, final Level level, final String message, final SOAPMessage soapMessage) {
        if (level == logger.getLevel()) {
            logSoapMessage(logger, level, message, soapMessage);
        }
    }

    /**
     * Log the <code>soapMsgContext</code> if the logger level is <code>level</code>.
     *
     * @param logger         the logger to use for logging.
     * @param level          the log level that if <code>logger</code>'s level is equal, will log <code>message</code> and <code>soapMsgContext</code>
     * @param message        the message to include in the log.
     * @param soapMsgContext the SOAP message context to log.
     */
    public static void logSoapMessageContextIfLevel(final Logger logger, final Level level, final String message, final SOAPMessageContext soapMsgContext) {
        logSoapMessageIfLevel(logger, level, message, soapMsgContext.getMessage());
    }

    /**
     * Append <code>soapMessage</code> to <code>sb</code>.
     *
     * @param sb          the StringBuilder to appendSoapMessage the string version of <code>soapMessage</code>.
     * @param soapMessage to be appended.
     *
     * @return <code>sb</code>.
     */
    public static StringBuilder appendSoapMessage(final StringBuilder sb, final SOAPMessage soapMessage) {
        return sb.append(convertToString(soapMessage));
    }

    /**
     * Append <code>soapMessage</code> to <code>sb</code>.
     *
     * @param sb             the StringBuilder to appendSoapMessage the string version of <code>soapMsgContext</code>.
     * @param soapMsgContext to be appended.
     *
     * @return <code>sb</code>.
     */
    public static StringBuilder appendSoapMsgContext(final StringBuilder sb, final SOAPMessageContext soapMsgContext) {
        return appendSoapMessage(sb, soapMsgContext.getMessage());
    }

    /**
     * Default constructor not allowed.
     */
    private SoapUtils() {
    }
}
