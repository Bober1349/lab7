package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordValidatorTest {
    @Test
    void validPassword_shouldPass() {
        PasswordValidator validator = new PasswordValidator();
        PasswordValidator.Result result = validator.validate("S3cure!Pass");
        assertTrue(result.isValid(), () -> "Expected valid but got: " + result.getErrors());
    }

    @Test
    void tooShort_shouldFail() {
        PasswordValidator validator = new PasswordValidator();
        var result = validator.validate("S3!a");
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.contains("at least")));
    }

    @Test
    void missingUppercase_shouldFail() {
        PasswordValidator.Rules rules = new PasswordValidator.Rules();
        rules.requireUppercase = true;
        PasswordValidator validator = new PasswordValidator(rules);

        var result = validator.validate("lowercase3!");
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Must contain at least one uppercase letter."));
    }

    @Test
    void whitespace_forbidden_shouldFail() {
        PasswordValidator validator = new PasswordValidator();
        var result = validator.validate("Has Space1!");
        assertFalse(result.isValid());
        assertTrue(result.getErrors().contains("Must not contain whitespace."));
    }
}
