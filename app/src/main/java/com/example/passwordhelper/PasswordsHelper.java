package com.example.passwordhelper;

import java.util.HashMap;

public class PasswordsHelper {

    private String[] mRussians;
    private String[] mLatin;
    private HashMap<String, String> alphabet;

    public PasswordsHelper(String[] mRussians, String[] mLatin) {
//        if (mRussians.length != mLatin.length) {
//            throw new IllegalArgumentException();
//        }
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
        this.mRussians = mRussians;
        this.mLatin = mLatin;

    }

    public String convert(CharSequence source) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String key = String.valueOf(Character.toLowerCase(c));

            for (int j = 0; j < mRussians.length; j++) {
                if (key.equals(mRussians[j])) {
                    result.append(Character.isUpperCase(c)? mLatin[i].toUpperCase() : mLatin[i]);
                }
            }
            if (result.length() <= i) {
                result.append(c);
            }
        }
        return result.toString();
    }

    public String convertWithHashMap(CharSequence source) {
//        if (alphabet.keySet().size() != alphabet.values().size()) {
//            throw new IllegalArgumentException();
//        }

    //Оставить такую же логику?
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            String key = String.valueOf(Character.toLowerCase(c));


            for (String s : alphabet.keySet()) {
                for (int j = 0; j < s.length(); j++) {
                    if (key.equals(s)) {
                        for (String value : alphabet.values()) {
                            result.append(Character.isUpperCase(c) ? alphabet.get(value).toUpperCase(): alphabet.get(j));

                        }
                        }
                }
                if (result.length() <= i) {
                    result.append(c);
                }

            }
        }
        return result.toString();
    }



    public int getQuality(CharSequence password) {
        Math.min();
    }
}


//Drawable
