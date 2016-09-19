/*
 * Copyright (C) 2014 Scot P. Floess
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
package org.flossware.jcore;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.flossware.jcore.utils.LoggerUtils;

/**
 * An abstract base class one can extend to get common functionality such as a logger. There are a family of methods that allow one
 * to log values and return a value. This allows one to perform simple logging and return of values in one line.
 *
 * @author Scot P. Floess
 */
public abstract class AbstractCommonBase {

    /**
     * Our logger.
     */
    private final Logger logger;

    /**
     * Default constructor.
     */
    protected AbstractCommonBase() {
        this.logger = Logger.getLogger(getClass().getName());
    }

    /**
     * Return the logger.
     *
     * @return the logger.
     */
    protected Logger getLogger() {
        return logger;
    }

    /**
     * Performs a log using the var args <code>objs</code> as an array that can be presented to the logger.
     *
     * @param level the level of the log.
     * @param str the format string.
     * @param objs a var arg thats converted to an object array for logging.
     */
    protected void log(final Level level, final String str, final Object... objs) {
        LoggerUtils.log(getLogger(), level, str, objs);
    }

    /**
     * Log and return the value.
     *
     * @param <V> the type of data to return.
     *
     * @param level the level to log at.
     * @param str the log string.
     * @param retVal the value to return.
     *
     * @return the object logged.
     */
    protected <V> V logAndReturn(final Level level, final String str, final V retVal) {
        return LoggerUtils.logAndReturn(getLogger(), level, str, retVal);
    }

    /**
     * Log and return the value thats found at <code>index</code> in the var arg <code>objs</code>. The "i" in LogFi stands for
     * integer position. Without a unique name on this method, there is conflict in calling the logAndReturn() counterpart.
     *
     * @param <V> the type to return.
     *
     * @param level the level of the log.
     * @param str the format string.
     * @param index the index into <code>objs</code> that is the return value.
     * @param objs a var arg thats converted to an object array for logging.
     *
     * @return the value found at index <code>index</code> in the var args <code>objs</code>.
     */
    protected <V> V logAndReturnByIndex(final Level level, final String str, final int index, final Object... objs) {
        return LoggerUtils.logAndReturnByIndex(getLogger(), level, str, index, objs);
    }

    /**
     * Log and return the value at found as the 0th index in the var arg <code>objs</code>.
     *
     * @param <V> the type to return.
     *
     * @param level the level of the log.
     * @param str the format string.
     * @param objs a var arg thats converted to an object array for logging.
     *
     * @return the value found at 0th index in the var args <code>objs</code>.
     */
    protected <V> V logAndReturn(final Level level, final String str, final Object... objs) {
        return LoggerUtils.logAndReturn(getLogger(), level, str, objs);
    }

}
