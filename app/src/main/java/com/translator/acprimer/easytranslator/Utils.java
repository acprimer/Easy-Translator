package com.translator.acprimer.easytranslator;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yaodh on 2016/6/2.
 */
public class Utils {

    public static String md5(String source) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(source.getBytes());
            byte[] resultBytes = digest.digest();
            StringBuilder builder = new StringBuilder();
            for(byte b : resultBytes) {
                builder.append(String.format("%02x", b & 0xff));
            }
            return builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
