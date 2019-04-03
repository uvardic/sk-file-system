package system;

import exceptions.FileSystemClosedException;
import meta.FileMetaData;

import java.io.File;
import java.util.Collection;
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
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileNotSupportedException if the specified file extension
     * was excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if the specified {@code file}
     * wasn't found
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
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileNotSupportedException if the specified file extension
     * was excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if the specified {@code file}
     * wasn't found
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
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileNotSupportedException if one of the specified file
     * extensions were excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if one of the specified
     * {@code files} wasn't found
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
     * @exception NullPointerException if one of the specified parameters is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception IllegalArgumentException if the specified {@code path} is not
     * a directory
     * @exception exceptions.FileNotSupportedException if one of the specified file
     * extensions were excluded with {@link #excludeFileExtension(String)} method
     * @exception exceptions.FileNotFoundException if one of the specified
     * {@code files} wasn't found
     */
    void uploadCollection(final Map<File, FileMetaData> files, final String path);

    /**
     * Copies {@code file} data from the file system to the local machine.
     *
     * @param file to be downloaded
     *
     * @exception NullPointerException if the specified {@code file} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotFoundException if the specified
     * {@code file} was not found
     */
    void download(final File file);

    /**
     * Copies a collection of file data from the file system to the
     * local machine.
     *
     * @param files collection of files to be downloaded
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     * @exception exceptions.FileNotFoundException if one of the specified
     * {@code files} was not found
     */
    void downloadCollection(final Collection<File> files);

    /**
     * Creates a directory on the specified {@code dirPath}.
     *
     * @param dirPath desired path of the new directory
     *
     * @exception NullPointerException if the specified {@code dirPath} is null
     * @exception IllegalArgumentException if the specified {@code dirPath} is
     * an invalid file path
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    void createDir(final String dirPath);

    /**
     * Finds all files on the file system and returns them as a collection.
     * If there are no files on the file system an empty collection
     * will be returned.
     *
     * @return a collection of all found files found on the file system
     *
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    Collection<File> findAll();

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
    Collection<File> findByName(final String name);

    /**
     * Attempts to find all file with the specified {@code extension}.
     * if there are no files on the file system with the specified
     * {@code extension} an empty collection will be returned.
     *
     * @param extension of the desired file
     *
     * @return a collection of all found files on the file system
     *
     * @exception NullPointerException if the specifed {@code name} is null
     * @exception FileSystemClosedException if the file system was closed
     * by calling the {@link #terminate()} method
     */
    Collection<File> findByExtension(final String extension);

}
