package com.gj;


/**
 * Interface for cipher implementations
 * @author Nick Allen
 */
public interface ICipher {
    
    String SERVICE_NAME = "cipher";

    String encrypt(String str);
    String decrypt(String str);
    
    public byte[] encrypt(byte[] value);
    public byte[] decrypt(byte[] value);    
          
}
