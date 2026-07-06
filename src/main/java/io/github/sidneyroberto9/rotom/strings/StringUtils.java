package io.github.sidneyroberto9.rotom.strings;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Utility class for string manipulation with Brazilian Portuguese support.
 * Includes capitalization with connectives, slugification, accent removal, and email operations.
 */
public class StringUtils {

    private static final Pattern WHITESPACE = Pattern.compile("\\s+");
    private static final Pattern NON_DIGIT = Pattern.compile("[^\\d]");
    private static final Pattern NON_ALPHANUMERIC = Pattern.compile("[^\\p{L}\\p{Nd}]");
    private static final Pattern DIACRITICAL = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Pattern SLUG_INVALID = Pattern.compile("[^a-z0-9\\-]");
    private static final Pattern MULTI_HYPHEN = Pattern.compile("-{2,}");

    private static final Set<String> PT_CONNECTIVES = Set.of(
            "das", "des", "dos", "da", "de", "do", "e", "a", "o", "em", "na", "no", "para"
    );

    /**
     * Checks whether the string is null, empty, or contains only whitespace.
     *
     * @param value string to check
     * @return {@code true} if null, empty, or blank; {@code false} otherwise
     */
    public boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    /**
     * Checks whether the string has meaningful content (not null, empty, or blank).
     *
     * @param value string to check
     * @return {@code true} if it has content, {@code false} otherwise
     */
    public boolean isNotBlank(String value) {
        return !this.isBlank(value);
    }

    /**
     * Trims whitespace from both ends of the string.
     * Returns {@code null} if the string is null, empty, or contains only whitespace.
     *
     * @param value string to process
     * @return trimmed string, or {@code null} if blank
     */
    public String trimOrNull(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }

        return value.trim();
    }

    /**
     * Ensures the string is not blank, throwing an exception with the given message if it is.
     *
     * @param value   string to check
     * @param message exception message thrown if the string is blank
     * @return trimmed string
     * @throws IllegalArgumentException if the string is null, empty, or blank
     */
    public String requireNonBlank(String value, String message) {
        if (this.isBlank(value)) {
            throw new IllegalArgumentException(message);
        }

        return value.trim();
    }

    /**
     * Capitalizes the first letter and lowercases the rest.
     *
     * @param word word to capitalize
     * @return capitalized word, or the original value if null or empty
     */
    public String capitalize(String word) {
        if (word == null || word.isEmpty()) {
            return word;
        }

        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

    /**
     * Capitalizes each word in the text. Portuguese connectives ({@code de}, {@code da}, {@code do}, {@code e}, etc.)
     * remain lowercase when they are not the first word.
     *
     * @param fullName text with one or more words
     * @return capitalized text respecting Portuguese connectives, or an empty string if null
     */
    public String capitalizeWords(String fullName) {
        if (fullName == null) {
            return "";
        }

        String[] parts = WHITESPACE.split(fullName.trim());

        for (int i = 0; i < parts.length; i++) {
            String lower = parts[i].toLowerCase();
            parts[i] = (i > 0 && PT_CONNECTIVES.contains(lower))
                    ? lower
                    : this.capitalize(parts[i]);
        }

        return String.join(" ", parts);
    }

    /**
     * Extracts and capitalizes the first two names from a full name.
     * Example: {@code "João da Silva Souza"} → {@code "João Da"}.
     *
     * @param fullName full name
     * @return first two names capitalized, or an empty string if null
     */
    public String firstTwoNames(String fullName) {
        if (fullName == null) {
            return "";
        }

        String[] parts = WHITESPACE.split(fullName.trim());

        if (parts.length == 0) {
            return "";
        }

        String first = this.capitalize(parts[0]);

        if (parts.length == 1) {
            return first;
        }

        return first + " " + this.capitalize(parts[1]);
    }

    /**
     * Removes all non-numeric characters, returning only digits 0–9.
     *
     * @param input string to process
     * @return digits only, or an empty string if null or empty
     */
    public String digitsOnly(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return NON_DIGIT.matcher(input).replaceAll("");
    }

    /**
     * Removes all characters that are not letters or digits (Unicode-aware).
     *
     * @param input string to process
     * @return letters and digits only, or an empty string if null or empty
     */
    public String alphanumericOnly(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        return NON_ALPHANUMERIC.matcher(input).replaceAll("");
    }

    /**
     * Removes accents and diacritics using Unicode NFD normalization.
     * Example: {@code "café"} → {@code "cafe"}.
     *
     * @param input string with accents
     * @return string without accents, or {@code null} if the input is null
     */
    public String removeAccents(String input) {
        if (input == null) {
            return null;
        }

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return DIACRITICAL.matcher(normalized).replaceAll("");
    }

    /**
     * Generates a URL-friendly slug from the text: lowercases, removes accents,
     * replaces spaces with hyphens, and strips special characters.
     * Example: {@code "Olá Mundo!"} → {@code "ola-mundo"}.
     *
     * @param input text to convert
     * @return generated slug, or an empty string if null
     */
    public String slugify(String input) {
        if (input == null) {
            return "";
        }

        String step = this.removeAccents(input.toLowerCase());
        step = WHITESPACE.matcher(step).replaceAll("-");
        step = SLUG_INVALID.matcher(step).replaceAll("");
        step = MULTI_HYPHEN.matcher(step).replaceAll("-");
        return step.replaceAll("^-|-$", "");
    }

    /**
     * Truncates the string to the given maximum length.
     * If the string is shorter than or equal to the limit, it is returned unchanged.
     *
     * @param value     string to truncate
     * @param maxLength maximum allowed length (must be zero or greater)
     * @return truncated string, or {@code null} if the input is null
     * @throws IllegalArgumentException if {@code maxLength} is negative
     */
    public String truncate(String value, int maxLength) {
        if (value == null) {
            return null;
        }

        if (maxLength < 0) {
            throw new IllegalArgumentException("maxLength deve ser >= 0");
        }

        return value.length() <= maxLength ? value : value.substring(0, maxLength);
    }

    /**
     * Checks whether the text contains the search string, ignoring case differences.
     *
     * @param text   text to search within
     * @param search string to look for
     * @return {@code true} if found; {@code false} if not found or either parameter is null
     */
    public boolean containsIgnoreCase(String text, String search) {
        if (text == null || search == null) {
            return false;
        }

        return text.toLowerCase().contains(search.toLowerCase());
    }

    /**
     * Normalizes an email address by trimming whitespace and converting to lowercase.
     *
     * @param email email to normalize
     * @return normalized email, or {@code null} if the input is null
     */
    public String normalizeEmail(String email) {
        if (email == null) {
            return null;
        }

        return email.trim().toLowerCase();
    }

    /**
     * Extracts the domain from an email address in uppercase.
     * Example: {@code "user@gmail.com"} → {@code "GMAIL"}.
     *
     * @param email valid email containing {@code @}
     * @return domain in uppercase
     * @throws IllegalArgumentException if the email is blank or does not contain {@code @}
     */
    public String emailDomain(String email) {
        if (this.isBlank(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }

        String afterAt = email.trim().split("@")[1];
        return afterAt.split("\\.")[0].toUpperCase();
    }

    /**
     * Masks an email address showing only the first character of the local part.
     * Example: {@code "user@gmail.com"} → {@code "u***@gmail.com"}.
     *
     * @param email email to mask
     * @return masked email
     * @throws IllegalArgumentException if the email is blank or does not contain {@code @}
     */
    public String maskEmail(String email) {
        if (this.isBlank(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }

        String[] parts = email.trim().split("@");
        String local = parts[0];
        String visible = local.length() > 1 ? local.substring(0, 1) : local;
        return visible + "***@" + parts[1];
    }

    /**
     * URL-encodes the text and appends it to the base URL.
     * Example: {@code encodeUrl("https://example.com/search?q=", "hello world")} → {@code "https://example.com/search?q=hello+world"}.
     *
     * @param baseUrl base URL without the parameter value
     * @param text    text to encode and append
     * @return complete URL with the encoded text
     */
    public String encodeUrl(String baseUrl, String text) {
        return baseUrl + URLEncoder.encode(text, StandardCharsets.UTF_8);
    }

    /**
     * Converts the text to camel case with spaces: each word is lowercased then has its first
     * letter capitalized. Example: {@code "HELLO WORLD"} → {@code "Hello World"}.
     *
     * @param input text to convert
     * @return converted text, or an empty string if null or empty
     */
    public String toCamelCaseWithSpaces(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        String[] words = WHITESPACE.split(input.toLowerCase().trim());
        StringBuilder result = new StringBuilder();

        for (String word : words) {
            if (word.isEmpty()) {
                continue;
            }

            result.append(this.capitalize(word)).append(" ");
        }

        return result.toString().trim();
    }

    /**
     * Extracts initials from a full name for avatar-style display.
     * A single-word name returns its first two characters uppercased; a multi-word name returns
     * the uppercased first letter of the first and last words.
     *
     * @param fullName full name
     * @return initials in uppercase, or {@code "?"} if null or blank
     */
    public String nameInitials(String fullName) {
        if (this.isBlank(fullName)) {
            return "?";
        }

        String[] parts = WHITESPACE.split(fullName.trim());

        if (parts.length == 1) {
            String word = parts[0];
            return word.substring(0, Math.min(2, word.length())).toUpperCase();
        }

        String first = parts[0];
        String last = parts[parts.length - 1];
        return (first.substring(0, 1) + last.substring(0, 1)).toUpperCase();
    }

    /**
     * Formats an 11-digit registration string (e.g. state tax registration) as
     * {@code SS.T.RRRRRRR-D} (state, legal type, registration, verification digit).
     *
     * @param input string containing 11 digits, with or without mask
     * @return formatted string, or the original input unchanged if it does not have exactly 11 digits, or {@code null} if the input is null
     */
    public String formatElevenDigits(String input) {
        if (input == null) {
            return null;
        }

        String digits = this.digitsOnly(input);

        if (digits.length() != 11) {
            return input;
        }

        return digits.substring(0, 2) + "." + digits.substring(2, 3) + "."
                + digits.substring(3, 10) + "-" + digits.substring(10, 11);
    }

    /**
     * Returns the trimmed value if it is not blank, or the given default otherwise.
     *
     * @param value        value to check
     * @param defaultValue value returned when {@code value} is null, empty, or blank
     * @return trimmed value, or {@code defaultValue} if blank
     */
    public String defaultIfBlank(String value, String defaultValue) {
        if (this.isBlank(value)) {
            return defaultValue;
        }

        return value.trim();
    }
}
