package meta;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static util.Preconditions.checkFile;

public class FileMetaDataReader {

    private static final Gson JSONParser = new GsonBuilder().setPrettyPrinting().create();

    private FileMetaDataReader() {}

    public static FileMetaData readFileMetaData(final String metaFilePath) {
        checkFile(metaFilePath, "Invalid meta file path!");

        return readFileMetaDataWorker(new File(metaFilePath));
    }

    public static FileMetaData readFileMetaData(final File metaFile) {
        checkFile(metaFile, "Invalid meta file!");

        return readFileMetaDataWorker(metaFile);
    }

    private static FileMetaData readFileMetaDataWorker(final File metaFile) {
        final String metaFileContent = readFile(metaFile);

        return JSONParser.fromJson(metaFileContent, FileMetaData.class);
    }

    private static String readFile(final File file) {
        try (final Scanner scanner = new Scanner(file)) {
            return scanner.useDelimiter("\\Z").next();
        } catch (final FileNotFoundException ex) {
            throw new exeptions.FileNotFoundException();
        }
    }

}
