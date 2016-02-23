package org.flossware.jcore.utils;

/**
 *
 * Utility class ensuring the integrity of integers are of a correct value.
 *
 * @author sfloess
 *
 */
public class IntUtils {

    /**
     * Ensure a value is no greater than a maximum.
     *
     * @param value is the value to compare.
     * @param maxValue is the maximum that value can be.
     * @param errorMsg is the message within the IllegalArgumentException if value greater than maxValue.
     *
     * @return value if value is less than maxValue.
     *
     * @throws IllegalArgumentException if value is greater than maxValue.
     */
    public static int ensureMaxInt(final int value, final int maxValue, final String errorMsg) throws IllegalArgumentException {
        if (value > maxValue) {
            throw new IllegalArgumentException(errorMsg);
        }

        return value;
    }

    /**
     * Ensure a value is no less than a maximum.
     *
     * @param value is the value to compare.
     * @param maxValue is the maximum that value can be.
     *
     * @return value if value is less than maxValue.
     *
     * @throws IllegalArgumentException if value is greater than maxValue.
     */
    public static int ensureMaxInt(final int value, final int maxValue) throws IllegalArgumentException {
        return ensureMaxInt(value, maxValue, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value is the value to compare.
     * @param minValue is the minimum that value can be.
     * @param errorMsg is the message within the IllegalArgumentException if value greater than minValue.
     *
     * @return value if value is greater than minValue.
     *
     * @throws IllegalArgumentException if value is greater than minValue.
     */
    public static int ensureMinInt(final int value, final int minValue, final String errorMsg) throws IllegalArgumentException {
        if (value < minValue) {
            throw new IllegalArgumentException(errorMsg);
        }

        return value;
    }

    /**
     * Ensure a value is no less than a minimum.
     *
     * @param value is the value to compare.
     * @param minValue is the minimum that value can be.
     *
     * @return value if value is greater than minValue.
     *
     * @throws IllegalArgumentException if value is greater than minValue.
     */
    public static int ensureMinInt(final int value, final int minValue) throws IllegalArgumentException {
        return ensureMinInt(value, minValue, ObjectUtils.DEFAULT_ERROR_MSG);
    }

    /**
     * Default constructor not allowed.
     */
    private IntUtils() {
    }
}
