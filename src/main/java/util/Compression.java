package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static util.Preconditions.checkFile;

public class Compression {

    private Compression() {}

    public static void toZip(final File input, final String destinationPath) throws IOException {
        checkFile(input, "Input file is invalid!");

        toZipWorker(input, new File(destinationPath));
    }

    // overrides destination file
    public static void toZip(final File input, final File destination) throws IOException {
        checkFile(input, "Input file is invalid!");
        checkFile(destination, "Destination file is invalid!");

        toZipWorker(input, destination);
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
