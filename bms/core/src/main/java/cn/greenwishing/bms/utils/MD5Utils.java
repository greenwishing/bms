package cn.greenwishing.bms.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Frank wu
 * @date 13-6-3
 */
public class MD5Utils {
    public static final String MD5 = "MD5";

    public static String md5(String value) {
        return md5(value.getBytes());
    }

    public static String md5(byte[] value) {
        if (value == null) {
            return "";
        }
        try {
            MessageDigest md5 = MessageDigest.getInstance(MD5);
            md5.update(value);
            byte[] bytes = md5.digest();

            int i;
            StringBuilder buf = new StringBuilder("");
            for (byte aByte : bytes) {
                i = aByte;
                if (i < 0) {
                    i += 256;
                }
                if (i < 16) {
                    buf.append("0");
                }
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new UnsupportedOperationException("No md5 algorithm!");
        }
    }
}
