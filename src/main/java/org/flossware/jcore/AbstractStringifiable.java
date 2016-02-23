package org.flossware.jcore;

/**
 *
 * Default abstract base class of stringifiables.
 *
 * @author sfloess
 *
 */
public abstract class AbstractStringifiable extends AbstractCommonBase implements Stringifiable {

    public static final String LINE_SEPARATOR_PROPERTY = "line.separator";
    public static final String LINE_SEPARATOR_STRING = System.getProperty(LINE_SEPARATOR_PROPERTY);

    /**
     * Default constructor.
     */
    protected AbstractStringifiable() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder toStringBuilder(final StringBuilder stringBuilder) {
        return toStringBuilder(stringBuilder, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StringBuilder toStringBuilder(final String prefix) {
        return toStringBuilder(new StringBuilder(), prefix);
    }

    /**
     * {@inheritDoc}
     *
     * @return the string representation of self.
     */
    @Override
    public String toString() {
        return toStringBuilder("").toString();
    }
}
