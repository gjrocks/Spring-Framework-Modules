package com.gj;


public class CryptoUtils {

    private static final String DIGITS = "0123456789abcdef";

    public static String toHex(byte data[], int length) {
        StringBuffer sb = new StringBuffer();
        
        for (int i = 0; i < length; i++) {
            int v = data[i] & 0xff;
            
            sb.append(DIGITS.charAt(v >> 4));
            sb.append(DIGITS.charAt(v & 0xf));
        }
        
        return sb.toString();
    }

    public static String toHex(byte[] data) {
        return toHex(data, data.length);
    }
}
