package com.lqf.wxdoctor.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptUtil {
    public static String sha1(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest sha1 = MessageDigest.getInstance("SHA1");
        sha1.update(s.getBytes("UTF-8"));
        byte a[] = sha1.digest();
        StringBuffer hexString = new StringBuffer();
        // 字节数组转换为 十六进制 数
        for (int i = 0; i < a.length; i++) {
            String shaHex = Integer.toHexString(a[i] & 0xFF);
            if (shaHex.length() < 2) {
                hexString.append(0);
            }
            hexString.append(shaHex);
        }
        String c = hexString.toString();
        return c;
    }
}
