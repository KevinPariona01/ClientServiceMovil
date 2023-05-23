package com.candwi.solinspeccion.libreria;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encriptamiento {
    public static String md5_1(String input){
        String result = input;
        if(input != null) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(input.getBytes());
                BigInteger hash = new BigInteger(1, md.digest());
                result = hash.toString(16);
                if ((result.length() % 2) != 0) {
                    result = "0" + result;
                }
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }
}
