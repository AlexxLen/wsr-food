package com.example.wsrfood.utilities;

import java.util.regex.Pattern;

public class Validation {
    public static boolean isEmailValid(String email) {
        Pattern pattern = Pattern.compile("^([a-z\\d_.-]+)@([a-z\\d_.-]+)\\.([a-z.]{2,3})$",
                Pattern.CASE_INSENSITIVE);
        return pattern.matcher(email).matches();
    }

    public static boolean isPhoneNumberValid(String phone) {
        Pattern pattern = Pattern.compile("^\\+7\\s[(]\\d{3}[)]\\s\\d{3}-\\d{2}-\\d{2}$");
        return pattern.matcher(phone).matches();
    }
}
