package org.flossware.jcore.utils;

/**
 *
 * Utility class ensuring the integrity of objects/params/values are of a correct value (for example not null).
 *
 * @author sfloess
 *
 */
public class ObjectUtils {

    /**
     * Default error message when a parameter is bad.
     */
    public static final String DEFAULT_ERROR_MSG = "Invalid value";

    /**
     * Checks <code>object</code> to ensureObject it is not null.
     *
     * @param <V> the type of object we are ensuring is not null.
     *
     * @param object The object to inspect to ensureObject its not null.
     * @param errorMsg The error message within the raised exception if <code>object</code> is null.
     *
     * @return object if it is not null.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static <V> V ensureObject(final V object, final String errorMsg) throws IllegalArgumentException {
        if (null == object) {
            throw new IllegalArgumentException(errorMsg);
        }

        return object;
    }

    /**
     * Checks <code>object</code> to ensureObject it is not null.
     *
     * @param <V> the type of object we are ensuring is not null.
     *
     * @param object The object to inspect to ensureObject its not null.
     *
     * @return object if it is not null.
     *
     * @throws IllegalArgumentException if <code>object</code> is null.
     */
    public static <V> V ensureObject(final V object) throws IllegalArgumentException {
        return ObjectUtils.ensureObject(object, DEFAULT_ERROR_MSG);
    }

    /**
     * Compute the package for object.
     *
     * @param object the object for whom we desire a package.
     *
     * @return the package for object.
     */
    public static String getPackage(final Object object) {
        return ClassUtils.getPackageName(ObjectUtils.ensureObject(object, "Must have an object!").getClass());
    }

    /**
     * Default constructor not allowed.
     */
    private ObjectUtils() {
    }
}
