package system;

import meta.FileMetaData;

import java.io.File;
import java.util.Collection;
import java.util.Map;

public interface FileSystem {

    void initialize();

    void save(File file);

    void upload(File file, String destinationPath);

    void upload(File file, String destinationPath, FileMetaData fileMetaData);

    void uploadCollection(Collection<File> files, String destinationPath);

    void uploadCollection(Map<File, FileMetaData> filesWithMetaData, String destinationPath);

    void uploadCompressed(File file, String destinationPath);

    void uploadCompressed(File file, String destinationPath, FileMetaData fileMetaData);

    void uploadCompressedCollection(Collection<File> files, String destinationPath);

    void uploadCompressedCollection(Map<File, FileMetaData> filesWithMetaData, String destinationPath);

    void createDirectory(String directoryPath);

    void disallowExtension(String extension);

    void allowExtension(String extension);

    Collection<?> getDisallowedExtensions();

    File findByName(String fileName);

    File findByExtension(String fileExtension);

}
