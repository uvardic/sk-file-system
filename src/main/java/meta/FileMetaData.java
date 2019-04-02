package meta;

import java.io.File;
import java.util.Date;
import java.util.Objects;

import static util.Preconditions.checkNotNull;

public class FileMetaData {

    private final File file;

    private final String fileName;

    private final String authorName;

    private final String programName;

    private final String category;

    private final String description;

    private final Date saveDate;

    private FileMetaData(final FileMetaDataBuilder builder) {
        this.file        = builder.file;
        this.fileName    = builder.fileName;
        this.authorName  = builder.authorName;
        this.programName = builder.programName;
        this.category    = builder.category;
        this.description = builder.description;
        this.saveDate    = builder.saveDate;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return fileName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getProgramName() {
        return programName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public Date getSaveDate() {
        return saveDate;
    }

    @Override
    public String toString() {
        return "FileMetaData:[" +
                formatVariable("file", file) +
                formatVariable("fileName", fileName) +
                formatVariable("authorName", authorName) +
                formatVariable("programName", programName) +
                formatVariable("category", category) +
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

        private String authorName;

        private String programName;

        private String category;

        private String description;

        private Date saveDate;

        public FileMetaDataBuilder(final File file) {
            this.file = checkNotNull(file);
        }

        public FileMetaDataBuilder fileName(final String fileName) {
            this.fileName = checkNotNull(fileName);

            return this;
        }

        public FileMetaDataBuilder authorName(final String authorName) {
            this.authorName = checkNotNull(authorName);

            return this;
        }

        public FileMetaDataBuilder programName(final String programName) {
            this.programName = checkNotNull(programName);

            return this;
        }

        public FileMetaDataBuilder category(final String category) {
            this.category = category;

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
