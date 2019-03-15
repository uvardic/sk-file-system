package compression;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static util.Preconditions.*;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class Decompression {

    private Decompression() {}

    public static void decompress(final File input) throws IOException {
        validateInput(input);

        final String destinationPath = input.getPath().substring(0, input.getName().lastIndexOf("."));

        final File destination = new File(destinationPath);

        if (!destination.exists())
            destination.mkdir();

        decompressWorker(input, destination);
    }

    public static void decompress(final File input, final String destinationPath) throws IOException {
        checkNotNull(destinationPath);
        validateInput(input);

        final File destination = new File(destinationPath);

        if (!destination.exists())
            destination.mkdir();

        decompressWorker(input, destination);
    }

    public static void decompress(final File input, final File destination) throws IOException {
        checkNotNull(destination);
        validateInput(input);

        if (!destination.exists())
            destination.mkdir();

        decompressWorker(input, destination);
    }

    private static void validateInput(final File input) {
        checkNotNull(input);
        checkFile(input, "Input file wasn't found!");

        final String extension = input.getPath().substring(input.getName().lastIndexOf("."));

        final boolean isSupported = Arrays.stream(SupportedExtensions.values())
                .anyMatch(supportedExtension -> supportedExtension.getExtension().equals(extension));

        checkArgument(isSupported, "Input file is not supported!");
    }

    private static void decompressWorker(final File input, final File destination) throws IOException {
        final byte[] buffer = new byte[1024];

        final ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(input));

        ZipEntry zipEntry = zipInputStream.getNextEntry();

        while (zipEntry != null) {
            final File newFile = new File(destination.getPath() + File.separator + zipEntry.getName());

            new File(newFile.getParent()).mkdirs();

            final FileOutputStream fileOutputStream = new FileOutputStream(newFile);

            int bytesRead;

            while ((bytesRead = zipInputStream.read(buffer)) > 0)
                fileOutputStream.write(buffer, 0, bytesRead);

            fileOutputStream.close();
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.closeEntry();
        zipInputStream.close();
    }

    public static void printSupportedExtensions() {
        Arrays.stream(SupportedExtensions.values())
                .forEach(supportedExtension -> System.out.println(supportedExtension.getExtension()));
    }

}
