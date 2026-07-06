package io.github.sidneyroberto9.rotom.validation;

import java.util.regex.Pattern;

/**
 * Utility class for validating the structural correctness of email addresses.
 * Validation is performed via regular expression, without any DNS or mailbox verification.
 */
public class EmailValidator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"
    );

    /**
     * Checks whether the given text is a structurally valid email address.
     *
     * @param email email to validate
     * @return {@code true} if it matches a valid email structure, {@code false} otherwise (including {@code null})
     */
    public boolean isValid(String email) {
        if (email == null) {
            return false;
        }

        return EMAIL_PATTERN.matcher(email.trim()).matches();
    }
}
