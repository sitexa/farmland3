package com.sitexa.framework.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * Created by IntelliJ IDEA.
 * User: xnpeng
 * Date: 2009-3-14
 * Time: 23:37:50
 */
public class StringUtils {

    public static String encode(String s) {
        if (s == null) return null;
        if (s.trim().length() == 0) return s;
        try {
            return new String(s.getBytes("iso8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return s;
        }
    }

    public static String encrypt(String message) {
        try {
            return encrypt(message, "MD5");
        } catch (Exception e) {
            return message;
        }
    }

    /**
     * encrypt a message(algorithm: MD5 or SHA-1)
     *
     * @param message   String
     * @param algorithm String
     * @return String length: 32 if algorithm = MD5, or 40 if algorithm = SHA-1
     * @throws Exception exceptions.
     */
    public static String encrypt(String message, String algorithm) throws Exception {
        if (message == null) {
            throw new Exception("message is null.");
        }
        if (!"MD5".equals(algorithm) && !"SHA-1".equals(algorithm)) {
            throw new Exception("algorithm must be MD5 or SHA-1.");
        }
        byte[] buffer = message.getBytes();

        // The SHA algorithm results in a 20-byte digest, while MD5 is 16 bytes long.
        MessageDigest md = MessageDigest.getInstance(algorithm);

        // Ensure the digest's buffer is empty. This isn't necessary the first time used.
        // However, it is good practice to always empty the buffer out in case you later reuse it.
        md.reset();

        // Fill the digest's buffer with data to compute a message digest from.
        md.update(buffer);

        // Generate the digest. This does any necessary padding required by the algorithm.
        byte[] digest = md.digest();

        // Save or print digest bytes. Integer.toHexString() doesn't print leading zeros.
        StringBuffer hexString = new StringBuffer();
        String sHexBit;
        for (byte aDigest : digest) {
            sHexBit = Integer.toHexString(0xFF & aDigest);
            if (sHexBit.length() == 1) {
                sHexBit = "0" + sHexBit;
            }
            hexString.append(sHexBit);
        }
        return hexString.toString();
    }

    public static String encodeByteString(String name) {
        String encoding = "utf-8";
        String bs = "";
        try {
            byte[] b = name.getBytes(encoding);
            for (int i = 0; i < b.length; i++) {
                byte b1 = b[i];
                if (i == 0) bs += b1;
                else bs += "sitexa" + b1;
            }
        } catch (Exception ignored) {
        }
        return bs;
    }

    @SuppressWarnings("deprecation")
    public static String decodeByteString(String name) {
        String encoding = "utf-8";
        String sb = "";
        String[] bs = name.split("sitexa");
        int l = bs.length;
        byte[] ba = new byte[l];
        for (int i = 0; i < bs.length; i++) {
            String b = bs[i];
            int x = Integer.parseInt(b);
            ba[i] = (byte) x;
        }
        try {
            sun.io.ByteToCharConverter converter = sun.io.ByteToCharConverter.getConverter(encoding);
            char[] c = converter.convertAll(ba);
            for (char c1 : c) sb += c1;
        } catch (Exception ignored) {
        }
        return sb;
    }

    public static void main(String[] args) throws Exception {
        String plainText = "汉字-1234567890-abcdefghijklmnopqrstuvwxyz-everythingelse";
        System.out.println("plain text: " + plainText);
        String cryptograph = encrypt(plainText);
        System.out.println("cryptograph: " + cryptograph);
        System.out.println(encrypt("eBao123"));//c5718d5d761ef62b8e76c90864e93063

        String s = "pop007";
        String t = new BASE64Encoder().encode(s.getBytes());
        System.out.println("t = " + t);
        String p = new String(new BASE64Decoder().decodeBuffer(t));
        System.out.println("p = " + p);
    }

}
