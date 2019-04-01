package system;

import meta.FileMetaData;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface FileSystem {

    void initialize();

    void terminate();

    void upload(final File file, final String path);

    void upload(final File file, final String path, final FileMetaData fileMetaData);

    void uploadCollection(final Collection<File> files, final String path);

    void uploadCollection(final Map<File, FileMetaData> files, final String path);

    void download(final File file);

    void downloadCollection(final List<File> files);

    void createDir(final String dirName, final String dirPath);

    void excludeFileExtension(final String fileExtension);

    List<File> findAll();

    List<File> findByName(final String name);

    List<File> findByExtension(final String extension);

}
