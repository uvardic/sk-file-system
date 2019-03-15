package meta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static util.Preconditions.checkFile;
import static util.Preconditions.checkNotNull;

public class FileMetaDataWriter {

    private static final Gson JSONParser = new GsonBuilder().setPrettyPrinting().create();

    private FileMetaDataWriter() {}

    public static void writeFileMetaData(final FileMetaData metaData, final String destinationPath) {
        checkNotNull(metaData);
        checkNotNull(destinationPath);

        writeFileMetaDataWorker(metaData, destinationPath);
    }

    // override destination method
    public static void writeFileMetaData(final FileMetaData metaData, final File destination) {
        checkNotNull(metaData);
        checkFile(destination, "Invalid destination file!");

        writeFileMetaDataWorker(metaData, destination.getAbsolutePath());
    }

    private static void writeFileMetaDataWorker(final FileMetaData metaData, final String destinationPath) {
        final String fileMetaDataJSON = JSONParser.toJson(metaData);

        try (final PrintWriter writer = new PrintWriter(new FileWriter(new File(destinationPath)))) {
            writer.write(fileMetaDataJSON);
        } catch (final IOException ex) {
            ex.printStackTrace();
        }
    }

}
