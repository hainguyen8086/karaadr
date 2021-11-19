package com.example.karama.helper;

import android.widget.EditText;

public class UHelper {
    public static String getNullorEmptyV2(String str) {
        if (str != null) {
            if (!str.isEmpty()) {
                return str;
            }
        }
        return "";
    }
    public static String onTextStr(EditText editText){
        String str = editText.getText().toString();
        return str;
    }
    public static boolean onValidate(String str){
        if(str.equals("")){
            return true;
        }
        return false;
    }
}
