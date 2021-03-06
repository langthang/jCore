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
package org.flossware.jcore.utils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Exception utility class.
 *
 * @author Scot P. Floess
 */
public class ExceptionUtils {

    /**
     * Our logger.
     */
    private static final Logger logger = Logger.getLogger(ExceptionUtils.class.getName());

    /**
     * Return the logger.
     */
    private static Logger getLogger() {
        return logger;
    }

    /**
     * Return true if both throwable and containedThrowable are not null (nice short hand method for testing).
     *
     * @param throwable must be non null to be processable.
     * @param containedThrowable must be non null to be processable.
     *
     * @return true if both throwable and containedThrowable are not null.
     */
    static boolean isContainsProcessable(final Throwable throwable, final Class containedThrowable) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST, "Contains processable [{0}] for throwable {1} and contained {2}", null != throwable && null != containedThrowable, throwable, containedThrowable);
    }

    /**
     * Return true if throwable or any of its root causes is a derived class of containedThrowable.
     *
     * @param <T> the type of throwable to see if contained.
     *
     * @param throwable to examine if it or any root causes is a subclass of containedThrowable.
     * @param containedThrowable used to see if is contained within all the causes.
     *
     * @return true if throwable or any of its root causes is a derived class of containedThrowable.
     */
    public static <T extends Throwable> boolean contains(final Throwable throwable, final Class<T> containedThrowable) {
        if (!isContainsProcessable(throwable, containedThrowable)) {
            LoggerUtils.log(getLogger(), Level.FINEST, "The throwable {0} is NOT contained in {1}", containedThrowable, throwable);

            return false;
        }

        if (containedThrowable.isAssignableFrom(throwable.getClass())) {
            LoggerUtils.log(getLogger(), Level.FINEST, "The throwable {0} IS contained in {1}", containedThrowable, throwable);

            return true;
        }

        LoggerUtils.log(getLogger(), Level.FINEST, "Examinging the cause {0} for containment of {1}", throwable.getCause(), containedThrowable);

        return contains(throwable.getCause(), containedThrowable);
    }

    /**
     * Return true if throwable or any of its root causes is containedThrowable.
     *
     * @param throwable to examine if it or any root causes is a subclass of containedThrowable.
     * @param containedThrowable used to see if is contained within all the causes.
     *
     * @return if throwable or its root causes is an contained, or false if not.
     */
    public static boolean contains(final Throwable throwable, final Throwable containedThrowable) {
        return LoggerUtils.logAndReturn(getLogger(), Level.FINEST,
                "Contains [{0}] for the containment of {1} in {2}",
                contains(throwable, ObjectUtils.ensureObject(containedThrowable, "Must have a throwable to check").getClass()),
                containedThrowable, throwable);
    }

    /**
     * Not allowed.
     */
    private ExceptionUtils() {
    }
}
