package system;

import exceptions.FileSystemNotFoundException;

import java.util.ArrayList;
import java.util.List;

import static util.Preconditions.*;

/**
 * The basic service for managing a list of {@link FileSystem}s.
 * <p>
 * Used for managing a list of registered {@code FileSystem}s and
 * retrieving their instances. Every {@code FileSystem} implementation
 * should invoke the {@link #registerSystem(FileSystem)} method to
 * make itself visible to this class. For example
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
 * This way when the {@link #getFileSystem(String)} method is called
 * {@code FileSystemImplementation} will be loaded and registered
 * with the {@code FileSystemManager}.
 * </p>
 *
 * @see FileSystem
 * @see #registerSystem(FileSystem)
 * @see #getFileSystem(String)
 *
 * @author Uros Vardic
 */
public class FileSystemManager {

    private static final List<FileSystem> registeredSystems = new ArrayList<>();

    private FileSystemManager() {}

    /**
     * Attempts to select an appropriate file system from the list of registered file systems
     * based on the specified {@code caller}.
     * <p>
     * This method attempts to load the {@code caller} and find it in the
     * registered file systems list. A newly-loaded file system should call
     * {@link #registerSystem(FileSystem)} method to make itself known to the
     * {@code FileSystemManager}.
     * <p>
     * <p>
     * Invoking this method is equivalent to {@link #getFileSystem(String)}
     * where the {@code callerClassName} is the name of the specified {@code caller}.
     * </p>
     *
     * @param caller class of the desired file system implementation
     *
     * @return file system based on the {@code caller}
     *
     * @exception NullPointerException if the specified {@code caller} is null
     * @exception exceptions.FileSystemNotFoundException if a suitable file system was not found
     *
     * @see #registerSystem(FileSystem)
     * @see #getFileSystem(String)
     */
    public static FileSystem getFileSystem(final Class<? extends FileSystem> caller) {
        return getFileSystemWorker(checkNotNull(caller).getName());
    }

    /**
     * Attempts to select an appropriate file system from the list of registered file systems
     * based on the specified {@code callerClassName}.
     * <p>
     * This method attempts to load the class for the {@code callerClassName}
     * and find it in the registered file systems list. A newly-loaded file system should call
     * {@link #registerSystem(FileSystem)} method to make itself known to the
     * {@code FileSystemManager}
     * </p>
     *
     * @param callerClassName class name of the desired file system
     *
     * @return file system based on the {@code callerClassName}
     *
     * @exception NullPointerException if the specified {@code callerClassName} is null
     * @exception exceptions.FileSystemNotFoundException if the specified {@code callerClassName}
     * is invalid and the class could not be found or if a suitable file system was not found
     *
     * @see #registerSystem(FileSystem)
     */
    public static FileSystem getFileSystem(final String callerClassName) {
        return getFileSystemWorker(checkNotNull(callerClassName));
    }

    private static FileSystem getFileSystemWorker(final String callerClassName) {
        final Class<?> callerClass = initializeCallerClass(callerClassName);

        // Finds the registered system that matches the specified caller,
        // by comparing their classes,
        // else if no systems were found throws an exception.
        return registeredSystems.stream()
                .filter(fileSystem -> fileSystem.getClass().equals(callerClass))
                .findFirst()
                .orElseThrow(() -> new FileSystemNotFoundException("Suitable file system wasn't found!"));
    }

    private static Class<?> initializeCallerClass(final String callerClassName) {
        try {
            return Class.forName(callerClassName);
        } catch (final ClassNotFoundException ex) {
            throw new FileSystemNotFoundException("Specified caller class could not be found!");
        }
    }

    /**
     * Registers the specified file system with the {@code FileSystemManager}.
     * A newly-loaded file system class should call this method to make itself known
     * to the {@code FileSystemManager}.
     *
     * @param fileSystem the new file system to be registered with the {@code FileSystemManager}
     *
     * @exception NullPointerException if the given {@code fileSystem} is null
     * @exception IllegalArgumentException if the given file system is already registered
     */
    public static void registerSystem(final FileSystem fileSystem) {
        checkNotNull(fileSystem, "Null value can't be registered or unregistered!");
        checkArgument(!registeredSystems.contains(fileSystem), "Specified system is already registered!");

        registeredSystems.add(fileSystem);
    }

    /**
     * Removes the specified file system from the {@code FileSystemManager}'s list of
     * registered systems.
     *
     * @param fileSystem file system to be removed
     *
     * @exception NullPointerException if the given {@code fileSystem} is null
     * @exception IllegalArgumentException if the given {@code fileSystem} wasn't found in the list
     * of registered systems
     */
    public static void unregisterSystem(final FileSystem fileSystem) {
        checkNotNull(fileSystem, "Null value can't be registered or unregistered!");
        checkArgument(registeredSystems.contains(fileSystem), "Specified system could't be found!");

        registeredSystems.remove(fileSystem);
    }

}
