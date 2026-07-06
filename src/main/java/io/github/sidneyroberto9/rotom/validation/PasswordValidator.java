package io.github.sidneyroberto9.rotom.validation;

import java.util.regex.Pattern;

/**
 * Utility class for validating password strength.
 */
public class PasswordValidator {

    private static final Pattern STRONG_PASSWORD = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#_$%^&<>?,/:;|'\"+!.}(){%~^$])(?=\\S+$).{8,16}$"
    );

    /**
     * Checks whether the given password is strong: 8 to 16 characters, with at least one digit,
     * one lowercase letter, one uppercase letter, one special character
     * ({@code @#_$%^&<>?,/:;|'"+!.}(){%~^$}), and no whitespace.
     *
     * @param password password to validate
     * @return {@code true} if the password meets all strength requirements, {@code false} otherwise (including {@code null})
     */
    public boolean isStrong(String password) {
        if (password == null) {
            return false;
        }

        return STRONG_PASSWORD.matcher(password).matches();
    }
}
