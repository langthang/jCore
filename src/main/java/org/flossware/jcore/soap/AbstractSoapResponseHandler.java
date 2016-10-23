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

import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 * Abstract base class that assumes no special processing for requests, returning true solely for processRequest().
 *
 * @author sfloess
 */
public abstract class AbstractSoapResponseHandler extends AbstractSoapHandler {

    /**
     * Default constructor.
     */
    protected AbstractSoapResponseHandler() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final boolean processRequest(final SOAPMessageContext msgContext) {
        return true;
    }
}
