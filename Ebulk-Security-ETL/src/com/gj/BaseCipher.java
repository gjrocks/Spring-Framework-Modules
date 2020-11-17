package com.gj;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;



import com.gj.Base64Util;


/**
 * The parent class for encryption implementations
 * @author Nick Allen
 */
public abstract class BaseCipher implements ICipher {
  
    private static final String UTF_CHAR_ENCODING = "UTF8";
  
   
  
    private Cipher ecipher;
    private Cipher dcipher;
  
    /**
     * 
     * @return encrypted value
     * @param str
     */
    public String encrypt(String str) {
        String encryptedValue = null;
    
        try {
            // Encode the string into bytes using utf-8
            final byte[] utf8 = str.getBytes(UTF_CHAR_ENCODING);

            // Encrypt
            final byte[] enc = ecipher.doFinal(utf8);

            // Encode bytes to base64 to get a string
            encryptedValue = new String(Base64Util.base64encode(enc));
            
        } catch (UnsupportedEncodingException uee) {
           uee.printStackTrace();
        } catch (IllegalBlockSizeException ibse) {
           ibse.printStackTrace();
        } catch (BadPaddingException bpe) {
           bpe.printStackTrace();
        }
        
        return encryptedValue;
    }

    /**
     * 
     * @return decrypted value 
     * @param str
     */
    public String decrypt(String str) {
        String decryptedValue = null;
    
        try {
            // Decode base64 to get bytes
            final byte[] dec = Base64Util.base64decode(str);

            // Decrypt
            final byte[] utf8 = dcipher.doFinal(dec);

            // Decode using utf-8
            decryptedValue = new String(utf8, UTF_CHAR_ENCODING);
            
        } catch (IOException ioe) {
           ioe.printStackTrace();
        } catch (IllegalBlockSizeException ibse) {
           ibse.printStackTrace();
        } catch (BadPaddingException bpe) {
            bpe.printStackTrace();
        } 
      
        return decryptedValue;
    }


    /**
     * 
     * @return 
     * @param val
     */
    public byte[] encrypt(byte[] val) {
        try {
            // Encrypt
            final byte[] encryptedVal = ecipher.doFinal(val);
            return encryptedVal;
        } catch (Exception e) {
         e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @return 
     * @param encryptedVal
     */
    public byte[] decrypt(byte[] encryptedVal) {
        
        try {
            // Decrypt
            final byte[] decrypted = dcipher.doFinal(encryptedVal);
            return decrypted;
        } catch (Exception e) {
         e.printStackTrace();
        } 
        return null;
    }  


    /*
    public String createMessageDigest(String value) {
        
        if (value==null) return null;
        
        try {
            final MessageDigest md5 = MessageDigest.getInstance("MD5");
            
            final byte[] utf8 = value.getBytes(UTF_CHAR_ENCODING);
            
            final byte[] digestValue = md5.digest(utf8);
            
            logger.debug("digestValue: " + digestValue);
            final String result = new String(Base64Coder.encode(digestValue));
            return result;
            
        } catch (Exception e) {
            logger.error("createMessageDigest - Error:" + e.getMessage());
        }
        
        return null;
    }
    */
    
    public abstract String getAlgorithm();




    /**
     * 
     * @param ecipher
     */
    protected void setEcipher(Cipher ecipher) {
        this.ecipher = ecipher;
    }

    /**
     * 
     * @return
     */
    protected Cipher getEcipher() {
        return this.ecipher;
    }

    /**
     * 
     * @param dcipher
     */
    protected void setDcipher(Cipher dcipher) {
        this.dcipher = dcipher;
    }

    /**
     * 
     * @return
     */
    protected Cipher getDcipher() {
        return this.dcipher;
    }
}
