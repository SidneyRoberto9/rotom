package io.github.sidneyroberto9.rotom.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Utility class for computing cryptographic hashes (checksums) of byte arrays, strings, streams,
 * and files.
 */
public class HashUtils {

    private static final int BUFFER_SIZE = 8192;

    /**
     * Computes the hash of the given bytes using the specified algorithm.
     *
     * @param data      bytes to hash
     * @param algorithm {@link MessageDigest} algorithm name (e.g. {@code MD5}, {@code SHA-256}, {@code SHA-512})
     * @return hash as a lowercase hexadecimal string
     * @throws IllegalArgumentException if {@code algorithm} is not supported by the JVM
     */
    public String hash(byte[] data, String algorithm) {
        MessageDigest digest = this.digestFor(algorithm);
        return this.toHex(digest.digest(data));
    }

    /**
     * Computes the hash of the given text (UTF-8) using the specified algorithm.
     *
     * @param text      text to hash
     * @param algorithm {@link MessageDigest} algorithm name (e.g. {@code MD5}, {@code SHA-256}, {@code SHA-512})
     * @return hash as a lowercase hexadecimal string
     * @throws IllegalArgumentException if {@code algorithm} is not supported by the JVM
     */
    public String hash(String text, String algorithm) {
        return this.hash(text.getBytes(StandardCharsets.UTF_8), algorithm);
    }

    /**
     * Computes the hash of the given stream's content using the specified algorithm.
     * The stream is read until exhaustion but is <strong>not</strong> closed by this method —
     * closing it remains the caller's responsibility.
     *
     * @param inputStream stream to hash
     * @param algorithm   {@link MessageDigest} algorithm name (e.g. {@code MD5}, {@code SHA-256}, {@code SHA-512})
     * @return hash as a lowercase hexadecimal string
     * @throws IOException              if reading the stream fails
     * @throws IllegalArgumentException if {@code algorithm} is not supported by the JVM
     */
    public String hash(InputStream inputStream, String algorithm) throws IOException {
        MessageDigest digest = this.digestFor(algorithm);
        byte[] buffer = new byte[BUFFER_SIZE];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            digest.update(buffer, 0, bytesRead);
        }

        return this.toHex(digest.digest());
    }

    /**
     * Computes the hash of the given file's content using the specified algorithm.
     *
     * @param file      file to hash
     * @param algorithm {@link MessageDigest} algorithm name (e.g. {@code MD5}, {@code SHA-256}, {@code SHA-512})
     * @return hash as a lowercase hexadecimal string
     * @throws IOException              if the file cannot be read
     * @throws IllegalArgumentException if {@code algorithm} is not supported by the JVM
     */
    public String hash(File file, String algorithm) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            return this.hash(fis, algorithm);
        }
    }

    /**
     * Computes the MD5 hash of the given bytes.
     *
     * @param data bytes to hash
     * @return hash as a lowercase hexadecimal string
     */
    public String md5(byte[] data) {
        return this.hash(data, "MD5");
    }

    /**
     * Computes the SHA-256 hash of the given bytes.
     *
     * @param data bytes to hash
     * @return hash as a lowercase hexadecimal string
     */
    public String sha256(byte[] data) {
        return this.hash(data, "SHA-256");
    }

    /**
     * Computes the SHA-512 hash of the given bytes.
     *
     * @param data bytes to hash
     * @return hash as a lowercase hexadecimal string
     */
    public String sha512(byte[] data) {
        return this.hash(data, "SHA-512");
    }

    private MessageDigest digestFor(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Algoritmo de hash inválido: " + algorithm, e);
        }
    }

    private String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);

        for (byte b : bytes) {
            sb.append(String.format("%02x", b & 0xff));
        }

        return sb.toString();
    }
}
