package exceptions;

import system.FileSystem;

/**
 * Throws an indicator that an object was accessed after the file system
 * was closed.
 *
 * @see FileSystem
 * @see FileSystem#terminate()
 *
 * @author Uros Vardic
 */
public class FileSystemClosedException extends IllegalStateException {

    /**
     * Constructs a new file system closed exception with
     * null as its detail message.
     *
     * <p>
     * The cause is not initialized, and may subsequently be initialized by
     * a call to {@link Throwable#initCause(Throwable)}.
     * </p>
     */
    public FileSystemClosedException() {}

    /**
     * Constructs a new file system not found exception with
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
    public FileSystemClosedException(final String message) {
        super(message);
    }

    /**
     * Constructs a new file system closed exception with
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
    public FileSystemClosedException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new file system closed exception with
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
    public FileSystemClosedException(final Throwable cause) {
        super(cause);
    }

}

