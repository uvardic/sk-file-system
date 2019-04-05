package system;

import exceptions.FileSystemClosedException;
import meta.FileMetaData;

import java.util.List;

/**
 * Contains methods that define a file system.
 *
 * <p>
 * File systems vary greatly so the {@link #initialize()} method
 * should setup all necessary parameters needed for this specific
 * system to function and open it for use.
 * </p>
 *
 * <p>
 * File system can be closed with {@link #terminate()} method.
 * Once closed, any further attempts to access objects from this system
 * should throw an {@link FileSystemClosedException}.
 * </p>
 *
 * <p>
 * Every file system implementation should be registered
 * and initialized with {@link FileSystemManager} class.
 * By registering, this system makes itself visible to the manager
 * class. For example
 *
 * <pre>{@code
 * public class FileSystemImplementation implements FileSystem<java.io.File> {
 *
 *     static {
 *         FileSystemManager.registerFileSystem(new FileSystemImplementation());
 *     }
 *
 *     public FileSystemImplementation() {}
 *
 *     // Implemented methods
 *
 * }
 * }</pre>
 *
 * This way only a name of a file system can be passed
 * to the {@link FileSystemManager} and the correct instance
 * will be provided.
 * </p>
 *
 * @see FileSystemClosedException
 * @see FileSystemManager
 *
 * @author Uros Vardic
 */
public interface FileSystem<T> {

    /**
     * Sets up all necessary parameters needed for the file system
     * to function and opens it for use.
     *
     * <p>
     * When a file system is opened for use all objects,
     * that it provides, can be accessed with the methods of this
     * interface.
     * </p>
     */
    void initialize();

    /**
     * Closes this file system.
     *
     * <p>
     * When a file system is closed any further attempts to access
     * objects from this system should throw an {@link FileSystemClosedException}.
     * </p>
     */
    void terminate();

    /**
     * Excludes all files with the specified {@code fileExtension} from
     * the file system.
     *
     * <p>
     * Excluded files can't be uploaded to the file system. An attempt to upload
     * an excluded file will throw an {@link exceptions.FileNotSupportedException}.
     * </p>
     *
     * @param fileExtension extension to be excluded from the file system
     *
     * @exception NullPointerException if the specified {@code fileExtension} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code fileExtension} is
     * already excluded
     */
    void excludeFileExtension(final String fileExtension);

    /**
     * Uploads a single file, on the given {@code filePath}, to the specified
     * {@code destinationPath} on this file system.
     *
     * <p>
     * If the {@code path} wasn't found on the file system or it does not point
     * to a directory, the specified {@code file} will be uploaded to the root
     * directory of the file system.
     * </p>
     *
     * @param filePath to upload to this file system
     * @param destinationPath desired path for specified {@code file}
     *
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotSupportedException if the specified file extension
     * was excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if the specified {@code file}
     * wasn't found
     */
    void upload(final String filePath, final String destinationPath);

    /**
     * Uploads a single file with its meta data to the specified {@code path}
     * in this file system.
     *
     * <p>
     * If the {@code path} wasn't found on the file system or it does not point
     * to a directory, the specified {@code file} will be uploaded to the root
     * directory of the file system.
     * </p>
     *
     * @param fileMetaData file meta data
     * @param path desired path for specified {@code file}
     *
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotSupportedException if the specified file extension
     * was excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if the specified {@code file}
     * wasn't found
     *
     * @see FileMetaData
     */
    void upload(final FileMetaData fileMetaData, final String path);

    /**
     * Uploads a collection of files, on the given {@code filePaths} to the
     * specified {@code destinationPath} on this file system.
     *
     * <p>
     * If the {@code path} wasn't found on the file system or it does not point
     * to a directory, the specified {@code files} will be uploaded to the root
     * directory of the file system.
     * </p>
     *
     * @param filePaths collection of file paths
     * @param destinationPath desired path for specified {@code files}
     *
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotSupportedException if one of the specified file
     * extensions were excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if one of the specified
     * {@code files} wasn't found
     */
    void uploadCollection(final List<String> filePaths, final String destinationPath);

    /**
     * Copies file data from the {@code path} on the file system to the local machine.
     * Specified {@code file} can also be a directory.
     *
     * @param path of the file to be downloaded
     *
     * @exception NullPointerException if the specified {@code file} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotFoundException if the specified
     * {@code file} was not found
     */
    void download(final String path);

    /**
     * Copies a collection of file data on the specified {@code paths}
     * from the file system to the local machine.
     *
     * @param paths collection of file paths to be downloaded
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotFoundException if one of the specified
     * {@code files} was not found
     */
    void downloadMultiple(final List<String> paths);

    /**
     * Creates a directory with the specified {@code dirPath}. Directory name
     * is represented by the last component of the specified {@code dirPath}.
     *
     * <p>
     * If the specified {@code dirPath} is invalid a new directory a new
     * directory will be created in the root of the file system. Name of
     * that new directory will be the last component of the {@code dirPath}.
     * </p>
     *
     * @param dirPath desired path of the new directory
     *
     * @exception NullPointerException if the specified {@code dirPath} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    void createDir(final String dirPath);

    /**
     * Attempts to find all files with the specified {@code name}.
     * If there are no files on the file system with the specified
     * {@code name} an empty collection will be returned.
     *
     * @param name of the desired file
     *
     * @return a collection of all found files on the file system
     *
     * @exception NullPointerException if the specified {@code name} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    List<T> findFileByName(final String name);

    /**
     * Attempts to find all file with the specified {@code extension}.
     * If there are no files on the file system with the specified
     * {@code extension} an empty collection will be returned.
     *
     * @param extension of the desired file
     *
     * @return a collection of all found files on the file system
     *
     * @exception NullPointerException if the specified {@code extension} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    List<T> findFileByExtension(final String extension);

    /**
     * Attempts to find all directories with the specified {@code name}.
     * If there are no directories on the file system with the specified
     * {@code name} an empty collection will be returned.
     *
     * @param name of the desired directory
     *
     * @return a collection of all found directories on the file system
     *
     * @exception NullPointerException if the specified {@code name} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    List<T> findDirectory(final String name);
}
