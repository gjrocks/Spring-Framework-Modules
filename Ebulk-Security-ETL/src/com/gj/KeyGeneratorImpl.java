package com.gj;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;




public class KeyGeneratorImpl implements IKeyGenerator {

    public static final String AES_ALGORITHM = "AES";

 

    private String          algorithm   = AES_ALGORITHM;
    private KeyGenerator    keyGenerator;
    private int             keySize     = 256;

    public KeyGeneratorImpl() throws Exception {
        init();
    }

    public KeyGeneratorImpl(int keySize) throws Exception {
        this.keySize = keySize;
        
        init();
    }

    public KeyGeneratorImpl(String algorithm, int keySize) throws Exception {
        this.algorithm = algorithm;
        this.keySize = keySize;
        
        init();
    }

    public byte[] execute() {
     
        SecretKey secretKey = this.keyGenerator.generateKey();
      
        return secretKey.getEncoded();
    }
    
    private void init() throws Exception {
      
        KeyGenerator keyGenerator = KeyGenerator.getInstance(this.algorithm);
        
     
        keyGenerator.init(this.keySize, new SecureRandom());
 
        this.keyGenerator = keyGenerator;    
    }
}
