package ua.itea.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;

public class Utils {
    public static String hashString (String hash) {
        MessageDigest md5 = null;
        final String salt = "dbitea";
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Add salt here hask + salt
        md5.update(StandardCharsets.UTF_8.encode(hash + salt));
        return String.format("%032x", new BigInteger(md5.digest()));
    }

    public static String repeatStr (String str, int times) {
        return String.join("", Collections.nCopies(times,str));
    }

    public static String alignCenter (String str, int max_length) {
        StringBuffer res = new StringBuffer();

        if (str.length() > max_length) {
            return str.substring(0,max_length);
        }

        int spaces_num = (max_length - str.length()) / 2;
        res.append(repeatStr(" ",spaces_num));
        res.append(str);
        return res.toString();
    }

    public static String alignRight (String str, int max_length) {
        StringBuffer res = new StringBuffer();

        if (str.length() > max_length) {
            return str.substring(0,max_length);
        }

        int spaces_num = (max_length - str.length());
        res.append(repeatStr(" ",spaces_num));
        res.append(str);
        return res.toString();
    }

    public static String alignSides (String str1, String str2, int max_length) {
        StringBuffer res = new StringBuffer();

        if (str1.length() > max_length) {
            return str1.substring(0,max_length);
        }

        if (str2.length() > max_length) {
            return res.append(str1)
                    .append(str2.substring(0,max_length-str1.length()))
                    .toString();
        }

        int spaces_num = (max_length - (str1.length() + str2.length()));
        res.append(str1);
        res.append(repeatStr(" ",spaces_num < 0 ? 0 : spaces_num));
        res.append(spaces_num < 0 ? str2.substring(0,max_length-str1.length()) : str2);
        return res.toString();
    }
}
