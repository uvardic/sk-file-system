package manager;

import specification.FileSystem;

import java.util.Objects;

// file system wrapper
class FileSystemInfo {

    private final FileSystem fileSystem;

    FileSystemInfo(final FileSystem fileSystem) {
        this.fileSystem = fileSystem;
    }

    FileSystem getFileSystem() {
        return fileSystem;
    }

    @Override
    public String toString() {
        return "FileSystemInfo:[fileSystem=" + fileSystem + "]";
    }

    @Override
    public boolean equals(final Object reference) {
        if (reference instanceof FileSystemInfo) {
            final FileSystemInfo other = (FileSystemInfo) reference;

            return Objects.equals(this.fileSystem, other.fileSystem);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fileSystem);
    }
}
