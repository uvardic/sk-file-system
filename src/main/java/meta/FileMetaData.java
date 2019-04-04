package meta;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import static util.Preconditions.checkNotNull;

public class FileMetaData {

    private final File file;

    private final String fileName;

    private final String description;

    private final String mimeType;

    private final String extension;

    private final Long version;

    private final Date saveDate;

    private FileMetaData(final FileMetaDataBuilder builder) {
        this.file        = builder.file;
        this.fileName    = builder.fileName;
        this.mimeType    = builder.mimeType;
        this.extension   = builder.extension;
        this.version     = builder.version;
        this.description = builder.description;
        this.saveDate    = builder.saveDate;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public String getDescription() {
        return description;
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getExtension() {
        return extension;
    }

    public Long getVersion() {
        return version;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    @Override
    public String toString() {
        return "FileMetaData:[" +
                formatVariable("file", file) +
                formatVariable("fileName", fileName) +
                formatVariable("mimeType", mimeType) +
                formatVariable("extension", extension) +
                formatVariable("version", version) +
                formatVariable("description", description) +
                formatVariable("saveData", saveDate) + "]";
    }

    private String formatVariable(final String variableName, final Object variable) {
        return variable == null ? "" : String.format("%s=%s", variableName, variable);
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

    public static class FileMetaDataBuilder {

        private final File file;

        private String fileName;

        private String description;

        private String mimeType;

        private String extension;

        private Long version;

        private Date saveDate;

        public FileMetaDataBuilder(final File file) {
            this.file = checkNotNull(file);
        }

        public FileMetaDataBuilder fileName(final String fileName) {
            this.fileName = checkNotNull(fileName);

            return this;
        }

        public FileMetaDataBuilder mimeType(final String mimeType) {
            this.mimeType = checkNotNull(mimeType);

            return this;
        }

        public FileMetaDataBuilder extension(final String extension) {
            this.extension = checkNotNull(extension);

            return this;
        }

        public FileMetaDataBuilder version(final Long version) {
            this.version = checkNotNull(version);

            return this;
        }

        public FileMetaDataBuilder description(final String description) {
            this.description = description;

            return this;
        }

        public FileMetaDataBuilder saveDate(final Date saveDate) {
            this.saveDate = saveDate;

            return this;
        }

        public FileMetaData build() {
            return new FileMetaData(this);
        }

    }
}
