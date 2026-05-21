package br.com.sidneyroberto9.utils.strings;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.Set;
import java.util.regex.Pattern;

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

    // ── Null-safe checks ─────────────────────────────────────────────────────

    public boolean isBlank(String value) {
        return value == null || value.isBlank();
    }

    public boolean isNotBlank(String value) {
        return !this.isBlank(value);
    }

    // ── Trim / require ───────────────────────────────────────────────────────

    public String trimOrNull(String value) {
        if (value == null || value.isBlank()) return null;
        return value.trim();
    }

    public String requireNonBlank(String value, String message) {
        if (this.isBlank(value)) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }

    // ── Capitalization ───────────────────────────────────────────────────────

    public String capitalize(String word) {
        if (word == null || word.isEmpty()) return word;
        return Character.toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
    }

    public String capitalizeWords(String fullName) {
        if (fullName == null) return "";

        String[] parts = WHITESPACE.split(fullName.trim());

        for (int i = 0; i < parts.length; i++) {
            String lower = parts[i].toLowerCase();
            parts[i] = (i > 0 && PT_CONNECTIVES.contains(lower))
                    ? lower
                    : this.capitalize(parts[i]);
        }

        return String.join(" ", parts);
    }

    public String firstTwoNames(String fullName) {
        if (fullName == null) return "";

        String[] parts = WHITESPACE.split(fullName.trim());

        if (parts.length == 0) return "";

        String first = this.capitalize(parts[0]);

        if (parts.length == 1) return first;

        return first + " " + this.capitalize(parts[1]);
    }

    // ── Digits / chars ───────────────────────────────────────────────────────

    public String digitsOnly(String input) {
        if (input == null || input.isEmpty()) return "";
        return NON_DIGIT.matcher(input).replaceAll("");
    }

    public String alphanumericOnly(String input) {
        if (input == null || input.isEmpty()) return "";
        return NON_ALPHANUMERIC.matcher(input).replaceAll("");
    }

    public String removeAccents(String input) {
        if (input == null) return null;
        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        return DIACRITICAL.matcher(normalized).replaceAll("");
    }

    public String slugify(String input) {
        if (input == null) return "";
        String step = this.removeAccents(input.toLowerCase());
        step = WHITESPACE.matcher(step).replaceAll("-");
        step = SLUG_INVALID.matcher(step).replaceAll("");
        step = MULTI_HYPHEN.matcher(step).replaceAll("-");
        return step.replaceAll("^-|-$", "");
    }

    public String truncate(String value, int maxLength) {
        if (value == null) return null;
        if (maxLength < 0) throw new IllegalArgumentException("maxLength deve ser >= 0");
        return value.length() <= maxLength ? value : value.substring(0, maxLength);
    }

    public boolean containsIgnoreCase(String text, String search) {
        if (text == null || search == null) return false;
        return text.toLowerCase().contains(search.toLowerCase());
    }

    // ── Email ────────────────────────────────────────────────────────────────

    public String normalizeEmail(String email) {
        if (email == null) return null;
        return email.trim().toLowerCase();
    }

    public String emailDomain(String email) {
        if (this.isBlank(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        String afterAt = email.trim().split("@")[1];
        return afterAt.split("\\.")[0].toUpperCase();
    }

    public String maskEmail(String email) {
        if (this.isBlank(email) || !email.contains("@")) {
            throw new IllegalArgumentException("Email inválido: " + email);
        }
        String[] parts = email.trim().split("@");
        String local = parts[0];
        String visible = local.length() > 1 ? local.substring(0, 1) : local;
        return visible + "***@" + parts[1];
    }

    // ── URL ──────────────────────────────────────────────────────────────────

    public String encodeUrl(String baseUrl, String text) {
        return baseUrl + URLEncoder.encode(text, StandardCharsets.UTF_8);
    }
}
