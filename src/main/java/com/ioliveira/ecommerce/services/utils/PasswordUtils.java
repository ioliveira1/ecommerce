package com.ioliveira.ecommerce.services.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {
    private static String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static int positions = 6;

    public static String newPassword() {
        return RandomStringUtils.random(positions, chars);
    }

}
