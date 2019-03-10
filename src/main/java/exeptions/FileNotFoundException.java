package exeptions;

import com.sun.istack.internal.Nullable;

/**
 * Throws an indicator that the file with the specified file name wasn't found.
 * Runtime version of {@link java.io.FileNotFoundException}.
 *
 * @see RuntimeException
 * @see java.io.FileNotFoundException
 *
 * @author Uros Vardic
 */
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException() {}

    public FileNotFoundException(@Nullable final String errorMessage) {
        super(errorMessage);
    }

}
