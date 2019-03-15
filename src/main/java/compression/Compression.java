package compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static util.Preconditions.*;

public class Compression {

    private Compression() {}

    public static void compress(final File input, final String destinationPath) throws IOException {
        validateDestination(destinationPath);

        checkFile(input, "Input file is invalid!");

        compressWorker(input, new File(destinationPath));
    }

    public static void compress(final File input, final File destination) throws IOException {
        validateDestination(destination.getPath());

        checkFile(input, "Input file is invalid!");

        compressWorker(input, destination);
    }

    private static void validateDestination(final String destinationPath) {
        checkNotNull(destinationPath);

        final String extension = destinationPath.substring(destinationPath.lastIndexOf("."));

        final boolean isSupported = Arrays.stream(SupportedExtensions.values())
                .anyMatch(supportedExtension -> supportedExtension.getExtension().equals(extension));

        checkArgument(isSupported, "Destination file is not supported!");
    }

    private static void compressWorker(final File input, final File destination) throws IOException {
        final ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(destination));

        zipOutputStream.putNextEntry(new ZipEntry(input.getName()));

        final FileInputStream fileInputStream = new FileInputStream(input);

        final byte[] buffer = new byte[1024];

        int bytesRead;

        while ((bytesRead = fileInputStream.read(buffer)) > 0)
            zipOutputStream.write(buffer, 0, bytesRead);

        zipOutputStream.closeEntry();
        zipOutputStream.close();
    }

    public static void printSupportedExtensions() {
        Arrays.stream(SupportedExtensions.values())
                .forEach(supportedExtension -> System.out.println(supportedExtension.getExtension()));
    }
}
