package io.github.sidneyroberto9.rotom.encoding;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Utility class for Base64 encoding and decoding of strings and integers.
 */
public class EncodingUtils {

    /**
     * Encodes the given text to Base64 (UTF-8).
     *
     * @param input text to encode
     * @return Base64-encoded string
     * @throws NullPointerException if {@code input} is null
     */
    public String toBase64(String input) {
        return Base64.getEncoder().encodeToString(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Decodes a Base64 string back to its original text (UTF-8).
     *
     * @param base64 Base64-encoded string
     * @return decoded text
     * @throws IllegalArgumentException if {@code base64} is not a valid Base64 string
     * @throws NullPointerException     if {@code base64} is null
     */
    public String fromBase64(String base64) {
        byte[] decoded = Base64.getDecoder().decode(base64);
        return new String(decoded, StandardCharsets.UTF_8);
    }

    /**
     * Encodes an integer as a Base64 string, using its 4-byte big-endian representation.
     * Useful for obfuscating sequential IDs.
     *
     * @param value integer to encode
     * @return Base64-encoded representation of the integer
     */
    public String encodeInt(int value) {
        byte[] bytes = ByteBuffer.allocate(Integer.BYTES).putInt(value).array();
        return Base64.getEncoder().encodeToString(bytes);
    }

    /**
     * Decodes a Base64 string produced by {@link #encodeInt(int)} back to an integer.
     *
     * @param encoded Base64-encoded integer
     * @return decoded integer
     * @throws IllegalArgumentException if {@code encoded} is not a valid Base64 string or does not decode to exactly 4 bytes
     * @throws NullPointerException     if {@code encoded} is null
     */
    public int decodeInt(String encoded) {
        byte[] decoded = Base64.getDecoder().decode(encoded);

        if (decoded.length != Integer.BYTES) {
            throw new IllegalArgumentException("Valor codificado inválido para inteiro: " + encoded);
        }

        return ByteBuffer.wrap(decoded).getInt();
    }
}
