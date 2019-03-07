package manager;

import com.sun.istack.internal.NotNull;
import specification.FileSystem;
import sun.reflect.CallerSensitive;

import java.util.ArrayList;
import java.util.List;

import static util.Preconditions.checkArgument;
import static util.Preconditions.checkNotNull;

public class FileSystemManager {

    private static final List<FileSystem> registeredSystems = new ArrayList<>();

    private FileSystemManager() {}

    @NotNull
    @CallerSensitive
    public static FileSystem getFileSystem(@NotNull final Class<? extends FileSystem> caller) {
        return getFileSystemWorker(checkNotNull(caller).getName());
    }

    @NotNull
    @CallerSensitive
    public static FileSystem getFileSystem(@NotNull final String callerClassName) {
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
                .orElseThrow(() -> new IllegalArgumentException("Suitable file system wasn't found!"));
    }

    private static Class<?> initializeCallerClass(final String callerClassName) {
        try {
            return Class.forName(callerClassName);
        } catch (final ClassNotFoundException ex) {
            throw new IllegalArgumentException("Specified caller class could not be found!");
        }
    }

    public static void registerSystem(@NotNull final FileSystem fileSystem) {
        checkNotNull(fileSystem, "Null value can't be registered or unregistered!");
        checkArgument(!registeredSystems.contains(fileSystem), "Specified system is already registered!");

        registeredSystems.add(fileSystem);
    }

    public static void unregisterSystem(@NotNull final FileSystem fileSystem) {
        checkNotNull(fileSystem, "Null value can't be registered or unregistered!");
        checkArgument(registeredSystems.contains(fileSystem), "Specified system could't be found!");

        registeredSystems.remove(fileSystem);
    }

}
