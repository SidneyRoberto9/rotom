package br.com.sidneyroberto9.utils.cnpj;

import java.util.regex.Pattern;

/**
 * Service for validation and formatting of CNPJ (Brazilian company taxpayer registry).
 * All methods accept CNPJ with or without a mask.
 */
public class CNPJService {

    private static final Pattern FORMATTED = Pattern.compile("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}");

    /**
     * Formats the CNPJ to the standard pattern {@code XX.XXX.XXX/XXXX-XX}.
     *
     * @param cnpj CNPJ with or without mask
     * @return CNPJ formatted as {@code XX.XXX.XXX/XXXX-XX}
     */
    public String format(String cnpj) {
        cnpj = this.strip(cnpj);
        return cnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    /**
     * Removes the mask from the CNPJ, returning only the 14 numeric digits.
     *
     * @param cnpj CNPJ with or without mask
     * @return digits only, or {@code null} if the input is null
     */
    public String strip(String cnpj) {
        if (cnpj == null) {
            return null;
        }

        return cnpj.replaceAll("\\D", "");
    }

    /**
     * Checks whether the CNPJ is already in the formatted pattern {@code XX.XXX.XXX/XXXX-XX}.
     *
     * @param cnpj CNPJ to check
     * @return {@code true} if already formatted, {@code false} otherwise
     */
    public boolean isFormatted(String cnpj) {
        if (cnpj == null) {
            return false;
        }

        return FORMATTED.matcher(cnpj).matches();
    }

    /**
     * Validates the CNPJ using the official two-check-digit algorithm.
     * Rejects null inputs, incorrect length, and repeated-digit sequences (e.g. {@code 11.111.111/1111-11}).
     *
     * @param cnpj CNPJ with or without mask
     * @return {@code true} if the CNPJ is mathematically valid, {@code false} otherwise
     */
    public boolean isValid(String cnpj) {
        if (cnpj == null) {
            return false;
        }

        cnpj = this.strip(cnpj);

        if (cnpj.length() != 14) {
            return false;
        }

        if (cnpj.matches("(\\d)\\1{13}")) {
            return false;
        }

        int[] weights1 = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        int sum = 0;
        for (int i = 0; i < 12; i++) {
            sum += (cnpj.charAt(i) - '0') * weights1[i];
        }
        int digit1 = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        int[] weights2 = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
        sum = 0;
        for (int i = 0; i < 13; i++) {
            sum += (cnpj.charAt(i) - '0') * weights2[i];
        }
        int digit2 = sum % 11 < 2 ? 0 : 11 - (sum % 11);

        return digit1 == (cnpj.charAt(12) - '0') && digit2 == (cnpj.charAt(13) - '0');
    }
}
