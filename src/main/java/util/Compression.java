package util;

import com.sun.istack.internal.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static util.Preconditions.checkFile;

public class Compression {

    private Compression() {}

    public static void toZip(@NotNull final String inputPath, @NotNull final String destinationPath) throws IOException {
        checkFile(inputPath, "Input path is invalid!");

        toZipWorker(new File(inputPath), new File(destinationPath));
    }

    public static void toZip(@NotNull final String inputPath, @NotNull final File destination) throws IOException {
        checkFile(inputPath, "Input path is invalid!");
        checkFile(destination, "Destination file is invalid!");

        toZipWorker(new File(inputPath), destination);
    }

    public static void toZip(@NotNull final String inputPath, @NotNull final URI destinationURI) throws IOException {
        checkFile(inputPath, "Input path is invalid!");

        toZipWorker(new File(inputPath), new File(destinationURI));
    }

    public static void toZip(@NotNull final File input, @NotNull final String destinationPath) throws IOException {
        checkFile(input, "Input file is invalid!");

        toZipWorker(input, new File(destinationPath));
    }

    public static void toZip(@NotNull final File input, @NotNull final File destination) throws IOException {
        checkFile(input, "Input file is invalid!");
        checkFile(destination, "Destination file is invalid!");

        toZipWorker(input, destination);
    }

    public static void toZip(@NotNull final File input, @NotNull final URI destinationURI) throws IOException {
        checkFile(input, "Input file is invalid!");

        toZipWorker(input, new File(destinationURI));
    }

    public static void toZip(@NotNull final URI inputURI, @NotNull final String destinationPath) throws IOException {
        checkFile(inputURI, "Input URI is invalid!");

        toZipWorker(new File(inputURI), new File(destinationPath));
    }

    public static void toZip(@NotNull final URI inputURI, @NotNull final File destination) throws IOException {
        checkFile(inputURI, "Input UIR is invalid!");
        checkFile(destination, "Destination file is invalid!");

        toZipWorker(new File(inputURI), destination);
    }

    public static void toZip(@NotNull final URI inputURI, @NotNull final URI destinationURI) throws IOException {
        checkFile(inputURI, "Input URI is invalid!");

        toZipWorker(new File(inputURI), new File(destinationURI));
    }

    private static void toZipWorker(final File input, final File destination) throws IOException {
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
}
