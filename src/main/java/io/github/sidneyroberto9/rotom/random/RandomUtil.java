package io.github.sidneyroberto9.rotom.random;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * Utility class for generating random numbers, strings, tokens, and API keys.
 * Backed by {@link SecureRandom} for cryptographically strong randomness.
 */
public class RandomUtil {

    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final String NUMERIC = "0123456789";
    private static final String DEFAULT_API_KEY_PREFIX = "sk_live_";

    private final SecureRandom random = new SecureRandom();

    /**
     * Generates a random integer between {@code min} and {@code max}, inclusive.
     *
     * @param min lower bound, inclusive
     * @param max upper bound, inclusive
     * @return random integer in the range {@code [min, max]}
     * @throws IllegalArgumentException if {@code min} is greater than {@code max}
     */
    public int randomInt(int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("min deve ser menor ou igual a max");
        }

        return this.random.nextInt((max - min) + 1) + min;
    }

    /**
     * Generates a random alphanumeric string (uppercase letters and digits) of the given size.
     *
     * @param size desired length
     * @return random alphanumeric string
     * @throws IllegalArgumentException if {@code size} is negative
     */
    public String randomAlphanumeric(int size) {
        return this.randomFrom(ALPHANUMERIC, size);
    }

    /**
     * Generates a random string containing only digits, of the given size.
     *
     * @param size desired length
     * @return random numeric string
     * @throws IllegalArgumentException if {@code size} is negative
     */
    public String randomNumeric(int size) {
        return this.randomFrom(NUMERIC, size);
    }

    /**
     * Generates a random alphanumeric code of the given size. Alias of {@link #randomAlphanumeric(int)},
     * intended for use cases like verification codes.
     *
     * @param size desired length
     * @return random code
     * @throws IllegalArgumentException if {@code size} is negative
     */
    public String randomCode(int size) {
        return this.randomAlphanumeric(size);
    }

    /**
     * Generates a random token by truncating a random UUID (without hyphens) to the given length.
     *
     * @param limit desired token length (must be between 1 and 32, inclusive)
     * @return random token
     * @throws IllegalArgumentException if {@code limit} is not between 1 and 32
     */
    public String token(int limit) {
        String uuid = UUID.randomUUID().toString().replace("-", "");

        if (limit < 1 || limit > uuid.length()) {
            throw new IllegalArgumentException("limit deve estar entre 1 e " + uuid.length());
        }

        return uuid.substring(0, limit);
    }

    /**
     * Generates an API key with the default prefix {@code sk_live_} followed by 12 hexadecimal characters.
     *
     * @return generated API key, e.g. {@code sk_live_a3f2b7c8d901}
     */
    public String apiKey() {
        return this.apiKey(DEFAULT_API_KEY_PREFIX);
    }

    /**
     * Generates an API key with the given prefix followed by 12 hexadecimal characters.
     *
     * @param prefix prefix to prepend to the generated key
     * @return generated API key
     */
    public String apiKey(String prefix) {
        String uuid = UUID.randomUUID().toString().replace("-", "").substring(0, 12);
        return prefix + uuid;
    }

    private String randomFrom(String alphabet, int size) {
        if (size < 0) {
            throw new IllegalArgumentException("size deve ser >= 0");
        }

        StringBuilder result = new StringBuilder(size);

        for (int i = 0; i < size; i++) {
            result.append(alphabet.charAt(this.random.nextInt(alphabet.length())));
        }

        return result.toString();
    }
}
