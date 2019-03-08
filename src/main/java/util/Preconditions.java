package util;

/**
 * Static convenience methods that help a method or constructor check whether it was invoked
 * correctly (that is, whether its <i>preconditions</i> were met).
 *
 * <p>If the precondition is not met, the {@code Preconditions} method throws an unchecked exception
 * of a specified type, which helps the method in which the exception was thrown communicate that
 * its caller has made a mistake. This allows constructs such as
 *
 * <pre>{@code
 * public static double sqrt(double value) {
 *   if (value < 0)
 *     throw new IllegalArgumentException("input is negative: " + value);
 *   // Calculate square root
 * }
 * }</pre>
 *
 * <p>to be replaced with the more compact
 *
 * <pre>{@code
 * public static double sqrt(double value) {
 *   checkArgument(value >= 0, "input is negative: %s", value);
 *   // Calculate square root
 * }
 * }</pre>
 *
 * <p>so that a hypothetical bad caller of this method, such as:
 *
 * <pre>{@code
 * void exampleBadCaller() {
 *     double d = sqrt(-1.0);
 * }
 * }</pre>
 *
 * <p>would be flagged as having called {@code sqrt()} with an illegal argument.
 *
 * @author Uros Vardic
 */
public class Preconditions {

    private Preconditions() {}

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     *
     * @return the non-null reference that was validated
     *
     * @exception NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference) {
        if (reference == null)
            throw new NullPointerException();

        return reference;
    }

    /**
     * Ensures that an object reference passed as a parameter to the calling method is not null.
     *
     * @param reference an object reference
     * @param errorMessage the exception message to us if the check fails
     *
     * @return the non-null reference that was validated
     *
     * @exception NullPointerException if {@code reference} is null
     */
    public static <T> T checkNotNull(final T reference, final String errorMessage) {
        if (reference == null)
            throw new NullPointerException(errorMessage);

        return reference;
    }

    /**
     * Ensures the truth of an expression.
     *
     * @param expression a boolean expression
     *
     * @exception IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(final boolean expression) {
        if (!expression)
            throw new IllegalArgumentException();
    }

    /**
     * Ensures the truth of an expression.
     *
     * @param expression a boolean expression
     * @param errorMessage the exception message to use if the check fails
     *
     * @exception IllegalArgumentException if {@code expression} is false
     */
    public static void checkArgument(final boolean expression, final String errorMessage) {
        if (!expression)
            throw new IllegalArgumentException(errorMessage);
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance.
     *
     * @param expression a boolean expression
     *
     * @exception IllegalStateException if {@code expression} is false
     */
    public static void checkState(final boolean expression) {
        if (!expression)
            throw new IllegalStateException();
    }

    /**
     * Ensures the truth of an expression involving the state of the calling instance.
     *
     * @param expression a boolean expression
     * @param errorMessage the exception message to use if the check fails
     *
     * @exception IllegalStateException if {@code expression} is false
     */
    public static void checkState(final boolean expression, final String errorMessage) {
        if (!expression)
            throw new IllegalStateException(errorMessage);
    }
}
