package exceptions;

/**
 * Throws an indicator that a file with the same name was found
 * on the specified path.
 *
 * @author Uros Vardic
 */
public class FileAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new file already exists exception with
     * null as its detail message.
     *
     * <p>
     * The cause is not initialized, and may subsequently be initialized by
     * a call to {@link Throwable#initCause(Throwable)}.
     * </p>
     */
    public FileAlreadyExistsException() {}

    /**
     * Constructs a new file already exists exception with
     * the specified detail message.
     *
     * <p>
     * The cause is not initialized, and may subsequently be initialized by
     * a call to {@link Throwable#initCause(Throwable)}.
     * </p>
     *
     * @param message the detail message. The detail message is saved
     * for later retrieval by the {@link Throwable#getMessage()} method
     */
    public FileAlreadyExistsException(final String message) {
        super(message);
    }

    /**
     * Constructs a new file already exists exception with
     * the specified detail message and cause.
     *
     * <p>
     * Note that the detail message associated with {@code cause} is not automatically
     * incorporated in this runtime exception's detail message.
     * </p>
     *
     * @param message the detail message. The detail message is saved
     * for later retrieval by the {@link Throwable#getMessage()} method
     * @param cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown)
     */
    public FileAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new file already exists exception with
     * the specified cause and a detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of cause).
     *
     * <p>
     * This constructor is useful for runtime exceptions that are little
     * more than wrappers for other throwables.
     * </p>
     *
     * @param cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown)
     */
    public FileAlreadyExistsException(final Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a file already exists exception with
     * the specified detail message, cause, suppression enabled or disabled
     * and writable stack trace enabled or disabled.
     *
     * @param message the detail message. The detail message is saved
     * for later retrieval by the {@link Throwable#getMessage()} method
     * @param cause the cause (which is saved for later retrieval by the
     * {@link Throwable#getCause()} method). (A null value is permitted, and indicates
     * that the cause is nonexistent or unknown)
     * @param enableSuppression whether or not suppression is enabled or disabled
     * @param writableStackTrace whether or not the stack trace should be writable
     */
    public FileAlreadyExistsException(final String message, final Throwable cause,
                                       final boolean enableSuppression,
                                       final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}
