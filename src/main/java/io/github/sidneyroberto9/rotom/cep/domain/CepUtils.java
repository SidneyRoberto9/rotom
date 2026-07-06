package io.github.sidneyroberto9.rotom.cep.domain;

import java.util.regex.Pattern;

/**
 * Utilities for CEP (Brazilian postal code) normalization, validation, and formatting
 * without any external HTTP calls. For address lookup, use {@link CepService}.
 */
public class CepUtils {

    private static final Pattern NON_DIGIT = Pattern.compile("\\D");
    private static final Pattern EIGHT_DIGITS = Pattern.compile("\\d{8}");

    /**
     * Removes all non-numeric characters from the CEP.
     *
     * @param raw CEP in any format (e.g. {@code 58038-000} or {@code 58038000})
     * @return digits only, or {@code null} if the input is null
     */
    public String normalize(String raw) {
        if (raw == null) {
            return null;
        }

        return NON_DIGIT.matcher(raw).replaceAll("");
    }

    /**
     * Checks whether the CEP has exactly 8 numeric digits.
     * The input should be normalized (no mask) before calling this method.
     *
     * @param cep CEP with digits only
     * @return {@code true} if it has exactly 8 digits, {@code false} otherwise
     */
    public boolean isValid(String cep) {
        if (cep == null) {
            return false;
        }

        return EIGHT_DIGITS.matcher(cep).matches();
    }

    /**
     * Formats the CEP to the standard pattern {@code XXXXX-XXX}.
     * Normalizes the input before formatting.
     *
     * @param cep CEP with or without mask
     * @return CEP formatted as {@code XXXXX-XXX}
     * @throws IllegalArgumentException if the CEP does not have exactly 8 valid digits
     */
    public String format(String cep) {
        String normalized = this.normalize(cep);

        if (!this.isValid(normalized)) {
            throw new IllegalArgumentException("CEP inválido: " + cep);
        }

        return normalized.substring(0, 5) + "-" + normalized.substring(5);
    }
}
