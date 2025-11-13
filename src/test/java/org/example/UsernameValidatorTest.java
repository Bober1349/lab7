package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsernameValidatorTest {

    @Test
    void isValid_alphabetsAndNumbers_true() {
        String username = "validUser123";
        boolean expected = true;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_dots_true() {
        String username = "user.name";
        boolean expected = true;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_underscores_true() {
        String username = "user_name";
        boolean expected = true;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_illegalCharacters_false() {
        String username = "invalid@user!";
        boolean expected = false;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_space_false() {
        String username = "invalid user";
        boolean expected = false;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_empty_false() {
        String username = "";
        boolean expected = false;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }

    @Test
    void isValid_null_false() {
        String username = null;
        boolean expected = false;

        boolean actual = UsernameValidator.isValid(username);

        assertEquals(expected, actual);
    }
}