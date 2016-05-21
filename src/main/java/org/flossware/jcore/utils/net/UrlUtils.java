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
package org.flossware.jcore.utils.net;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.io.UrlException;
import org.flossware.jcore.utils.LoggerUtils;
import org.flossware.jcore.utils.StringUtils;

/**
 *
 * URL utility class.
 *
 * @author sfloess
 *
 */
public class UrlUtils {

    /**
     * Protocol separator.
     */
    public static final String PROTOCOL_SEPARATOR = "://";

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(UrlUtils.class.getName());

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Converts <code>rawURL</code> to a URL.
     *
     * @param rawUrl the complete raw URL (including any additional paths).
     *
     * @return a URL.
     *
     * @throws UrlException if computing the URL fails.
     */
    public static URL createUrl(final String rawUrl) {
        try {
            return LoggerUtils.logF(getLogger(), Level.FINEST, "URL [{0}] for string [{1}]", new URL(rawUrl), rawUrl);
        } catch (final MalformedURLException malformedUrlException) {
            getLogger().log(Level.SEVERE, "Trouble getting protocol and host [{0}]", malformedUrlException.getMessage());

            throw new UrlException(malformedUrlException);
        }
    }

    /**
     * Taking the <code>rawURL</code>, will return the URL for just the server.
     *
     * @param rawUrl The complete raw URL (including any additional paths).
     *
     * @return the server's URL including protocol.
     *
     * @throws IllegalArgumentException if computing the URL fails.
     */
    public static String computeHostUrlAsString(final String rawUrl) {
        final URL url = createUrl(rawUrl);

        return LoggerUtils.logF(getLogger(), Level.FINEST, "String URL [{0}] for raw string [{1}]", StringUtils.concat(url.getProtocol(), PROTOCOL_SEPARATOR, url.getHost()), rawUrl);
    }

    /**
     * Using <code>rawUrl</code>, convert to protocol and host version.
     *
     * @param rawUrl the raw URL to convert.
     *
     * @return a URL representation of only protocol and host.
     */
    public static URL computeHostUrl(final String rawUrl) {
        try {
            return LoggerUtils.logF(getLogger(), Level.FINEST, "Host URL [{0}] for raw string [{1}]", new URL(computeHostUrlAsString(rawUrl)), rawUrl);
        } catch (final MalformedURLException malformedUrlException) {
            throw new UrlException(malformedUrlException);
        }
    }
}
