package compression;

import static util.Preconditions.checkNotNull;

enum SupportedExtensions {

    ZIP(".zip"), JAR(".jar"), GZ(".gz");

    private final String extension;

    SupportedExtensions(final String extension) {
        this.extension = checkNotNull(extension);
    }

    String getExtension() {
        return extension;
    }
}
