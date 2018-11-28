package cn.xstar.site.util;


import org.apache.commons.codec.digest.DigestUtils;

public class CipherUtil {
    public static String MD5(String text) {
        return DigestUtils.md5Hex(text);
    }

    public static boolean verify(String text, String md5) {
        return MD5(text).equalsIgnoreCase(md5);
    }
}
