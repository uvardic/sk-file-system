package specification;

import java.io.File;
import java.util.Objects;

import static util.Preconditions.checkNotNull;

public class FileMetaData {

    private final File file;

    public static class FileMetaDataBuilder {

    }

    private FileMetaData(final File file) {
        this.file = checkNotNull(file);
    }

    @Override
    public boolean equals(final Object reference) {
        if (reference instanceof FileMetaData) {
            final FileMetaData other = (FileMetaData) reference;

            return Objects.equals(this.file, other.file);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(file);
    }
}
