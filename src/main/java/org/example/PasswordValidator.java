package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class PasswordValidator {

    public static class Result {
        private final boolean valid;
        private final List<String> errors;

        private Result(boolean valid, List<String> errors) {
            this.valid = valid;
            this.errors = List.copyOf(errors);
        }

        public boolean isValid() { return valid; }
        public List<String> getErrors() { return errors; }

        @Override
        public String toString() {
            return valid ? "VALID" : "INVALID: " + errors;
        }
    }

    public static class Rules {
        public int minLength = 8;
        public int maxLength = 64;
        public boolean requireUppercase = true;
        public boolean requireLowercase = true;
        public boolean requireDigit = true;
        public boolean requireSpecial = true;
        public boolean forbidWhitespace = true;

        // Customize special characters
        public String specialCharacters = "!@#$%^&*()_+[]{}|;:',.<>/?`~-=\\\"";
    }

    private final Rules rules;

    // Precompiled patterns
    private final Pattern upper = Pattern.compile("[A-Z]");
    private final Pattern lower = Pattern.compile("[a-z]");
    private final Pattern digit = Pattern.compile("\\d");
    private Pattern special;

    public PasswordValidator() {
        this(new Rules());
    }

    public PasswordValidator(Rules rules) {
        this.rules = rules;
        this.special = Pattern.compile("[" + Pattern.quote(rules.specialCharacters) + "]");
    }

    public Result validate(String password) {
        List<String> errors = new ArrayList<>();

        if (password == null) {
            errors.add("Password must not be null.");
            return new Result(false, errors);
        }

        int len = password.length();
        if (len < rules.minLength) errors.add("Must be at least " + rules.minLength + " characters.");
        if (len > rules.maxLength) errors.add("Must be at most " + rules.maxLength + " characters.");

        if (rules.forbidWhitespace && containsWhitespace(password)) {
            errors.add("Must not contain whitespace.");
        }

        if (rules.requireUppercase && !upper.matcher(password).find()) {
            errors.add("Must contain at least one uppercase letter.");
        }
        if (rules.requireLowercase && !lower.matcher(password).find()) {
            errors.add("Must contain at least one lowercase letter.");
        }
        if (rules.requireDigit && !digit.matcher(password).find()) {
            errors.add("Must contain at least one digit.");
        }
        if (rules.requireSpecial && !special.matcher(password).find()) {
            errors.add("Must contain at least one special character.");
        }

        return new Result(errors.isEmpty(), errors);
    }

    private boolean containsWhitespace(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (Character.isWhitespace(s.charAt(i))) return true;
        }
        return false;
    }
}