package manager;

import com.sun.istack.internal.NotNull;
import specification.FileSystem;
import sun.reflect.CallerSensitive;

import java.util.ArrayList;
import java.util.List;

import static util.Preconditions.checkArgument;
import static util.Preconditions.checkNotNull;

public class FileSystemManager {

    private static final List<FileSystemInfo> registeredSystems = new ArrayList<>();

    private FileSystemManager() {}

    @NotNull
    @CallerSensitive
    public static FileSystem getFileSystem(@NotNull final Class<? extends FileSystem> caller) {
        initializeFileSystemClass(caller);

        // Finds the first file system that matches the callers class and returns it.
        // If no such file system exists method throws an IllegalArgumentException.
        return registeredSystems.stream()
                .filter(fileSystemInfo -> fileSystemInfo.getFileSystem().getClass().equals(caller))
                .findFirst().map(FileSystemInfo::getFileSystem)
                .orElseThrow(() -> new IllegalArgumentException("Suitable file system wasn't found!"));

    }

    private static void initializeFileSystemClass(final Class<? extends FileSystem> caller) {
        try {
            Class.forName(caller.getName());
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void registerSystem(@NotNull final FileSystem fileSystem) {
        checkNotNull(fileSystem);

        final FileSystemInfo systemInfo = new FileSystemInfo(fileSystem);

        checkArgument(!registeredSystems.contains(systemInfo), "Specified system is already registered!");

        registeredSystems.add(systemInfo);
    }

    public static void unregisterSystem(@NotNull final FileSystem fileSystem) {
        checkNotNull(fileSystem);

        final FileSystemInfo systemInfo = new FileSystemInfo(fileSystem);

        checkArgument(registeredSystems.contains(systemInfo), "Specified system couldn't be found!");

        registeredSystems.remove(systemInfo);
    }
}
