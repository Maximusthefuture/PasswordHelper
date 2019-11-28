package com.example.passwordhelper;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class PasswordsHelper {

    private HashMap<String, String> alphabet;

    public PasswordsHelper() {
        alphabet = new HashMap<>();
        alphabet.put("й","q");
        alphabet.put("ц","w");
        alphabet.put("у","e");
        alphabet.put("к","r");
        alphabet.put("е","t");
        alphabet.put("н","y");
        alphabet.put("г","u");
        alphabet.put("ш","i");
        alphabet.put("щ","o");
        alphabet.put("з","p");
        alphabet.put("ф","a");
        alphabet.put("ы","s");
        alphabet.put("в","d");
        alphabet.put("а","f");
        alphabet.put("п","g");
        alphabet.put("р","h");
        alphabet.put("о","j");
        alphabet.put("л","k");
        alphabet.put("д","l");
        alphabet.put("я","z");
        alphabet.put("ч","x");
        alphabet.put("с","c");
        alphabet.put("м","v");
        alphabet.put("и","b");
        alphabet.put("т","n");
        alphabet.put("ь","m");

    }

    public String convertWithHashMap(CharSequence source) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String key = String.valueOf(Character.toLowerCase(c));
            for (Map.Entry<String, String> entry : alphabet.entrySet()) {
                if (key.equals(entry.getKey())) {
                    result.append(Character.isUpperCase(c) ? entry.getValue().toUpperCase() : entry.getValue());
                }
            }
            if (result.length() <= i) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public int getQuality(CharSequence password) {
        return Math.min(password.length(), 10);
    }

    public String generatePassword(int n, boolean isUpperCase, boolean isNumber, boolean iSpecial) {   //, boolean isNumber, boolean isSpecial
        String randomPassword = "abcdefghijklmnopqrstuvxyz";
        String numbers = "0123456789";
        String specialSymbols = "[]{}()<>*+-=!?^$|";
        int random;

//        String randomUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789";
        StringBuilder builder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            int index = (int) (randomPassword.length() * Math.random());

            if (isUpperCase) {
                String upper = randomPassword.toUpperCase();
                builder.append(upper.charAt(index));
            } else if (isNumber) {
                random = (int) (10 * Math.random());
                builder.append(numbers.charAt(random));
                builder.append(randomPassword.charAt(index));
//            } else if (isNumber && isUpperCase){
//                int random = (int) (10 * Math.random());
//                builder.append(numbers.charAt(random));
//                builder.append(randomPassword.charAt(index)).toString().toUpperCase();
            } else if (iSpecial) {
                random = (int) (10 * Math.random());
                builder.append(specialSymbols.charAt(random));
                builder.append(randomPassword.charAt(index));
            } else {
                builder.append(randomPassword.charAt(index));
                Log.d("PasswordHelper", isUpperCase + "");
            }
        }

        return builder.toString();
    }


    public void generateSpecialSymbols(StringBuilder builder, boolean isSpecial) {

    }
}

