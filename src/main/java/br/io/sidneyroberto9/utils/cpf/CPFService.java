package br.io.sidneyroberto9.utils.cpf;

import java.util.regex.Pattern;

/**
 * Service for validation and formatting of CPF (Brazilian individual taxpayer registry).
 * All methods accept CPF with or without a mask.
 */
public class CPFService {

    private static final Pattern FORMATTED = Pattern.compile("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");

    /**
     * Formats the CPF to the standard pattern {@code XXX.XXX.XXX-XX}.
     *
     * @param cpf CPF with or without mask
     * @return CPF formatted as {@code XXX.XXX.XXX-XX}
     */
    public String format(String cpf) {
        cpf = this.strip(cpf);
        return cpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }

    /**
     * Removes the mask from the CPF, returning only the 11 numeric digits.
     *
     * @param cpf CPF with or without mask
     * @return digits only, or {@code null} if the input is null
     */
    public String strip(String cpf) {
        if (cpf == null) {
            return null;
        }

        return cpf.replaceAll("\\D", "");
    }

    /**
     * Checks whether the CPF is already in the formatted pattern {@code XXX.XXX.XXX-XX}.
     *
     * @param cpf CPF to check
     * @return {@code true} if already formatted, {@code false} otherwise
     */
    public boolean isFormatted(String cpf) {
        if (cpf == null) {
            return false;
        }

        return FORMATTED.matcher(cpf).matches();
    }

    /**
     * Validates the CPF using the official check-digit algorithm.
     * Rejects null inputs, incorrect length, and repeated-digit sequences (e.g. {@code 111.111.111-11}).
     *
     * @param cpf CPF with or without mask
     * @return {@code true} if the CPF is mathematically valid, {@code false} otherwise
     */
    public boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        cpf = this.strip(cpf);

        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;
        int weight = 10;
        for (int i = 0; i < 9; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }
        int digit1 = 11 - (sum % 11);
        if (digit1 > 9) {
            digit1 = 0;
        }

        sum = 0;
        weight = 11;
        for (int i = 0; i < 10; i++) {
            sum += (cpf.charAt(i) - '0') * weight--;
        }
        int digit2 = 11 - (sum % 11);
        if (digit2 > 9) {
            digit2 = 0;
        }

        return digit1 == (cpf.charAt(9) - '0') && digit2 == (cpf.charAt(10) - '0');
    }
}
