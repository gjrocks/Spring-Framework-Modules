package com.gj;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;



import com.gj.KeyLoader;


public class SymmetricCipherImpl extends BaseCipher {

   

    private String      algorithm   = "AES";
    private SecretKey   key         = null;
    
    
    public SymmetricCipherImpl(byte[] cryptographyKey,String algorithm) {
        this.algorithm = algorithm;
        init(cryptographyKey);
    }

    public SymmetricCipherImpl(byte[] cryptographyKey) {
      init(cryptographyKey);
    }
    
    
    public void init(byte[] cryptographyKey) {
    
     
      
      try {
      
          key = new SecretKeySpec(cryptographyKey, algorithm);
          setEcipher(getEncryptionCipher());
          setDcipher(getDecryptionCipher());
          
             
               
      } catch (Exception e) {
         e.printStackTrace();
      }    
      
    } 
    
    // this needs to be 8 bytes
    //final static byte[] iv = new byte[] {0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

    private Cipher getEncryptionCipher() throws Exception {
     
      //IvParameterSpec ivSpec = new IvParameterSpec(iv);       
      final Cipher ec = Cipher.getInstance("AES/ECB/PKCS5Padding");
      ec.init(Cipher.ENCRYPT_MODE, key);
       
      
      return ec;    
    }
    
    private Cipher getDecryptionCipher() throws Exception {
      
      //IvParameterSpec ivSpec = new IvParameterSpec(iv);         
      final Cipher dc = Cipher.getInstance("AES/ECB/PKCS5Padding");
      dc.init(Cipher.DECRYPT_MODE, key); 
      return dc;
    }


    public String getAlgorithm() {
        return algorithm;
    }


    public static void main(String[] args) throws Exception {
        final SymmetricCipherImpl c = new SymmetricCipherImpl(KeyLoader.loadKey("y:/tmp/174543.key"));
        
        final String enVal = c.encrypt("jaspyt");
       
            
        String deVal = c.decrypt(enVal);
       
                  
    }

}
