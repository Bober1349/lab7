package org.example;

public class UsernameValidator {
    // Regex: only letters, digits, period, and underscore
    private static final String USERNAME_PATTERN = "^[a-zA-Z0-9._]+$";

    /**
     * Validates a username based on allowed characters.
     *
     * @param username the input string to validate
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        return username.matches(USERNAME_PATTERN);
    }
}