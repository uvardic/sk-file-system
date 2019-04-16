package meta;

import java.io.Serializable;
import java.util.Date;

import static util.Preconditions.checkNotNull;

public class FileMetaData implements Serializable {

    private final String fileName;

    private final String description;

    private final String mimeType;

    private final String extension;

    private final Long version;

    private final Date saveDate;

    private FileMetaData(final FileMetaDataBuilder builder) {
        this.fileName    = builder.fileName;
        this.mimeType    = builder.mimeType;
        this.extension   = builder.extension;
        this.version     = builder.version;
        this.description = builder.description;
        this.saveDate    = builder.saveDate;
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

    public static class FileMetaDataBuilder {

        private String fileName;

        private String description;

        private String mimeType;

        private String extension;

        private Long version;

        private Date saveDate;

        public FileMetaDataBuilder() {}

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
