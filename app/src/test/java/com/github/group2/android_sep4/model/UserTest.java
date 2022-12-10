package com.github.group2.android_sep4.model;

import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.Test;

public class UserTest extends TestCase {



    @Test
    void correctHashPassword() {

        User user = new User("testEmail@email.dk", "correctPassword");
        String hashedPassword = user.getPassword();

        boolean areTheySame = User.checkPassword("correctPassword", hashedPassword);
        assertTrue(areTheySame);
    }

    @Test
    void incorrectHashPassword() {

        User user  = new User("test@Email.dk", "correctPassword");
        String hashedPassword = user.getPassword();

        boolean areTheySame = User.checkPassword("incorrectPassword", hashedPassword);
        assertFalse(areTheySame);

    }

}