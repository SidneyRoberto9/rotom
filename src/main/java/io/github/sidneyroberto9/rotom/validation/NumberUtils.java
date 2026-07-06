package io.github.sidneyroberto9.rotom.validation;

/**
 * Utility class for checking whether a string represents a valid number.
 */
public class NumberUtils {

    /**
     * Checks whether the given string can be parsed as an {@code int}.
     *
     * @param value string to check
     * @return {@code true} if it is a valid integer, {@code false} otherwise (including {@code null}/empty)
     */
    public boolean isInteger(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string can be parsed as a {@code float}.
     *
     * @param value string to check
     * @return {@code true} if it is a valid float, {@code false} otherwise (including {@code null}/empty)
     */
    public boolean isFloat(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string can be parsed as a {@code double}.
     *
     * @param value string to check
     * @return {@code true} if it is a valid double, {@code false} otherwise (including {@code null}/empty)
     */
    public boolean isDouble(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Checks whether the given string is a plain numeric value (optional leading sign, digits,
     * optional decimal part) via pattern matching rather than parsing.
     *
     * @param value string to check
     * @return {@code true} if it matches a numeric pattern, {@code false} otherwise (including {@code null}/empty)
     */
    public boolean isNumeric(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }

        return value.matches("-?\\d+(\\.\\d+)?");
    }
}
