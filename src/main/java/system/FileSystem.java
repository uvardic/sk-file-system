package system;

import exceptions.FileSystemClosedException;
import meta.FileMetaData;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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
 * public class FileSystemImplementation implements FileSystem {
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
public interface FileSystem {

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
     * Uploads a single file to the specified {@code path} in this file system.
     *
     * <p>
     * Specified {@code path} must be a path to a directory where the file
     * will be uploaded.
     * </p>
     *
     * @param file to upload to this file system
     * @param path desired path for specified {@code file}
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileAlreadyExistsException if a file with the same name
     * as the specified file already exists on the specified {@code path}
     */
    void upload(final File file, final String path);

    /**
     * Uploads a single file, with its meta data, to the specified
     * {@code path} in this file system.
     *
     * <p>
     * Specified {@code path} must be a path to a directory where the file
     * will be uploaded.
     * </p>
     *
     * @param file to upload to this file system
     * @param path desired path for specified {@code file}
     * @param fileMetaData meta data for the specified {@code file}
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileAlreadyExistsException if a file with the same name
     * as the specified file already exists on the specified {@code path}
     */
    void upload(final File file, final String path, final FileMetaData fileMetaData);

    /**
     * Uploads a collection of files to the specified {@code path} in this file system.
     *
     * <p>
     * Specified {@code path} must be a path to a directory where the files
     * will be uploaded.
     * </p>
     *
     * @param files collection of files
     * @param path desired path for specified {@code files}
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileAlreadyExistsException if a file with the same name
     * as the specified file already exists on the specified {@code path}
     */
    void uploadCollection(final Collection<File> files, final String path);

    /**
     * Uploads a collection of files, with their meta data, to the specified
     * {@code path} in this file system.
     *
     * <p>
     * Specified {@code path} must be a path to a directory where the files
     * will be uploaded.
     * </p>
     *
     * @param files collection of files and their meta data
     * @param path desired path for specified {@code files}
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileAlreadyExistsException if one of the specified files
     * already exists on the specified {@code path}
     */
    void uploadCollection(final Map<File, FileMetaData> files, final String path);

    void download(final File file);

    void downloadCollection(final List<File> files);

    void createDir(final String dirName, final String dirPath);

    void excludeFileExtension(final String fileExtension);

    List<File> findAll();

    List<File> findByName(final String name);

    List<File> findByExtension(final String extension);

}
