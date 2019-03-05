package util;

public class Preconditions {

    private Preconditions() {}

    public static <T> T checkNotNull(final T reference) {
        if (reference == null)
            throw new NullPointerException();

        return reference;
    }

    public static <T> T checkNotNull(final T reference, final String errorMessage) {
        if (reference == null)
            throw new NullPointerException(errorMessage);

        return reference;
    }

    public static void checkArgument(final boolean argument) {
        if (!argument)
            throw new IllegalArgumentException();
    }

    public static void checkArgument(final boolean argument, final String errorMessage) {
        if (!argument)
            throw new IllegalArgumentException(errorMessage);
    }
}
