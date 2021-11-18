package com.example.karama.helper;

public class UHelper {
    public static String getNullorEmptyV2(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                return str;
            }
        }
        return "";
    }
}
