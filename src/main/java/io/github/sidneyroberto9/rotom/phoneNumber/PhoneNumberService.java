package io.github.sidneyroberto9.rotom.phoneNumber;

import java.util.Set;
import java.util.regex.Pattern;

/**
 * Service for formatting and validation of Brazilian phone numbers.
 * Supports mobile phones (9 local digits) and landlines (8 local digits),
 * with or without area code (DDD) and country code (+55).
 */
public class PhoneNumberService {

    private static final Pattern MOBILE_FORMATTED = Pattern.compile("\\(\\d{2}\\) 9\\d{4}-\\d{4}");
    private static final Pattern LANDLINE_FORMATTED = Pattern.compile("\\(\\d{2}\\) \\d{4}-\\d{4}");

    private static final Set<Integer> VALID_DDDS = Set.of(
            11, 12, 13, 14, 15, 16, 17, 18, 19,
            21, 22, 24,
            27, 28,
            31, 32, 33, 34, 35, 37, 38,
            41, 42, 43, 44, 45, 46,
            47, 48, 49,
            51, 53, 54, 55,
            61, 62, 63, 64, 65, 66, 67, 68, 69,
            71, 73, 74, 75, 77, 79,
            81, 82, 83, 84, 85, 86, 87, 88, 89,
            91, 92, 93, 94, 95, 96, 97, 98, 99
    );

    /**
     * Removes all non-numeric characters from the phone number.
     *
     * @param phone phone number in any format
     * @return digits only, or {@code null} if the input is null
     */
    public String strip(String phone) {
        if (phone == null) {
            return null;
        }

        return phone.replaceAll("\\D", "");
    }

    /**
     * Extracts the 2-digit area code (DDD) from the phone number.
     * Requires at least 10 digits (DDD + local number).
     *
     * @param phone phone number with area code
     * @return DDD as a string, or {@code null} if the number has fewer than 10 digits
     */
    public String getDDD(String phone) {
        String digits = this.strip(phone);
        if (digits == null || digits.length() < 10) {
            return null;
        }

        return digits.substring(0, 2);
    }

    /**
     * Checks whether the phone number is a Brazilian mobile number.
     * Mobile phones have 9 local digits and the first local digit is {@code 9}.
     *
     * @param phone phone number with or without area code
     * @return {@code true} if mobile, {@code false} otherwise
     */
    public boolean isMobile(String phone) {
        String digits = this.strip(phone);
        if (digits == null) {
            return false;
        }

        int len = digits.length();
        if (len == 11) {
            return digits.charAt(2) == '9';
        }

        if (len == 9) {
            return digits.charAt(0) == '9';
        }

        return false;
    }

    /**
     * Checks whether the phone number is a Brazilian landline.
     * Landlines have 8 local digits.
     *
     * @param phone phone number with or without area code
     * @return {@code true} if landline, {@code false} otherwise
     */
    public boolean isLandline(String phone) {
        String digits = this.strip(phone);
        if (digits == null) {
            return false;
        }

        int len = digits.length();
        return len == 10 || len == 8;
    }

    /**
     * Checks whether the phone number is already formatted with area code and hyphen,
     * either as mobile {@code (XX) XXXXX-XXXX} or landline {@code (XX) XXXX-XXXX}.
     *
     * @param phone phone number to check
     * @return {@code true} if already formatted, {@code false} otherwise
     */
    public boolean isFormatted(String phone) {
        if (phone == null) {
            return false;
        }

        return MOBILE_FORMATTED.matcher(phone).matches() || LANDLINE_FORMATTED.matcher(phone).matches();
    }

    /**
     * Validates a Brazilian phone number by checking its length and area code (DDD).
     * Numbers without area code (8 or 9 digits) are accepted without DDD validation.
     *
     * @param phone phone number in any format
     * @return {@code true} if the number is valid, {@code false} otherwise
     */
    public boolean isValid(String phone) {
        String digits = this.strip(phone);
        if (digits == null) {
            return false;
        }

        int len = digits.length();
        if (len == 8 || len == 9) {
            return true;
        }

        if (len == 10 || len == 11) {
            int ddd = Integer.parseInt(digits.substring(0, 2));
            if (!VALID_DDDS.contains(ddd)) {
                return false;
            }

            return len != 11 || digits.charAt(2) == '9';
        }

        return false;
    }

    /**
     * Formats the phone number by automatically detecting mobile or landline.
     * Strips the country code prefix {@code +55} or {@code 55} when present.
     * Output examples: {@code (83) 98663-5812} for mobile, {@code (83) 3222-1234} for landline.
     *
     * @param phone number in any format (with or without DDD, country code, or mask)
     * @return formatted phone number
     * @throws IllegalArgumentException if the number cannot be interpreted
     */
    public String format(String phone) {
        String digits = this.strip(phone);
        if (digits == null) {
            throw new IllegalArgumentException("Telefone inválido: " + phone);
        }

        if (digits.startsWith("55") && (digits.length() == 12 || digits.length() == 13)) {
            digits = digits.substring(2);
        }

        if (digits.length() == 11) {
            return String.format("(%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 7),
                    digits.substring(7));
        }

        if (digits.length() == 10) {
            return String.format("(%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 6),
                    digits.substring(6));
        }

        if (digits.length() == 9) {
            return String.format("%s-%s", digits.substring(0, 5), digits.substring(5));
        }

        if (digits.length() == 8) {
            return String.format("%s-%s", digits.substring(0, 4), digits.substring(4));
        }

        throw new IllegalArgumentException("Telefone inválido: " + phone);
    }

    /**
     * Formats the phone number including the Brazilian country code {@code +55}.
     * Strips the country code prefix when already present in the input.
     * Output example: {@code +55 (83) 98663-5812}.
     *
     * @param phone number with area code in any format
     * @return phone number formatted with country code {@code +55}
     * @throws IllegalArgumentException if the number has no area code or cannot be interpreted
     */
    public String formatWithCountryCode(String phone) {
        String digits = this.strip(phone);
        if (digits == null) {
            throw new IllegalArgumentException("Telefone inválido: " + phone);
        }

        if (digits.startsWith("55") && (digits.length() == 12 || digits.length() == 13)) {
            digits = digits.substring(2);
        }

        if (digits.length() == 11) {
            return String.format("+55 (%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 7),
                    digits.substring(7));
        }

        if (digits.length() == 10) {
            return String.format("+55 (%s) %s-%s",
                    digits.substring(0, 2),
                    digits.substring(2, 6),
                    digits.substring(6));
        }

        throw new IllegalArgumentException("Telefone inválido para código de país: " + phone);
    }
}
