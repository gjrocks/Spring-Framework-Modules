/*
 * Copyright (c) 2008 Nick Allen.  All rights reserved.
 * Distribution and use of this source code (or the application compiled from this source code) 
 * and associated documentation ("Software"), with or without modification is prohibited.
 */
package com.gj;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;



public class Base64Util {

    public static final String UTF_CHAR_ENCODING = "UTF8";
    
   

    public Base64Util() {
    }
    
    
    public static String base64encode(byte[] bytes) {
        try {
            
            Base64 base64 = new Base64();
            byte[] encodedBytes = base64.encode(bytes);        
            return new String(encodedBytes, UTF_CHAR_ENCODING);
            
        } catch (UnsupportedEncodingException uee) {
            
            return null;
        }        
    }
    
    public static byte[] base64decode(String str) {
        try {
        
            Base64 base64 = new Base64();
            byte[] decodedBytes = base64.decode(str.getBytes(UTF_CHAR_ENCODING));        
            return decodedBytes;
        
        } catch (UnsupportedEncodingException uee) {
           
            return null;
        }
        
    }    
    
    
}
