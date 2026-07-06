package io.github.sidneyroberto9.rotom.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

/**
 * Utility class for common file operations: Base64 encoding, human-readable size formatting,
 * and moving files into the current working directory.
 */
public class FileUtils {

    private static final String SIZE_UNITS = " KMGTPE";

    /**
     * Reads the given file and encodes its content to Base64.
     *
     * @param file file to encode
     * @return Base64-encoded content of the file
     * @throws IOException if the file cannot be read
     */
    public String toBase64(File file) throws IOException {
        return Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
    }

    /**
     * Reads the file at the given path and encodes its content to Base64.
     *
     * @param path path to the file to encode
     * @return Base64-encoded content of the file
     * @throws IOException if the file cannot be read
     */
    public String toBase64(String path) throws IOException {
        return this.toBase64(new File(path));
    }

    /**
     * Formats a byte count as a human-readable size (e.g. {@code 1.5 KB}, {@code 3.2 MB}).
     *
     * @param bytes size in bytes (must be zero or greater)
     * @return human-readable size
     * @throws IllegalArgumentException if {@code bytes} is negative
     */
    public String formatSize(long bytes) {
        if (bytes < 0) {
            throw new IllegalArgumentException("bytes deve ser >= 0");
        }

        if (bytes < 1024) {
            return bytes + " B";
        }

        int exp = (63 - Long.numberOfLeadingZeros(bytes)) / 10;
        double value = (double) bytes / (1L << (exp * 10));
        return String.format("%.1f %sB", value, SIZE_UNITS.charAt(exp));
    }

    /**
     * Moves the given file into the current working directory ({@code user.dir}), keeping its
     * original file name.
     *
     * @param file file to move
     * @return the moved file, pointing at its new location
     * @throws IOException if the move fails
     */
    public File moveToWorkspace(File file) throws IOException {
        return this.moveToWorkspace(file, null);
    }

    /**
     * Moves the given file into the current working directory ({@code user.dir}), optionally
     * renaming it. When a name is provided, the file's original extension is preserved.
     *
     * @param file file to move
     * @param name new base name for the file (without extension), or {@code null}/blank to keep the original name
     * @return the moved file, pointing at its new location
     * @throws IOException if the move fails
     */
    public File moveToWorkspace(File file, String name) throws IOException {
        String originalName = file.getName();
        String targetName = originalName;

        if (name != null && !name.isBlank()) {
            int dot = originalName.lastIndexOf('.');
            String extension = dot >= 0 ? originalName.substring(dot) : "";
            targetName = name + extension;
        }

        Path target = Paths.get(this.workspaceDir(), targetName);
        Files.move(file.toPath(), target, StandardCopyOption.REPLACE_EXISTING);
        return target.toFile();
    }

    private String workspaceDir() {
        return System.getProperty("user.dir");
    }
}
