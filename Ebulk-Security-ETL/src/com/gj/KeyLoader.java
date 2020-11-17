package com.gj;

import java.io.File;
import java.io.IOException;

import org.springframework.util.FileCopyUtils;


public class KeyLoader {
    public KeyLoader() {
    }
    
    public static byte[] loadKey(String file) throws IOException {
        // load the key and return it as byte[]
        final File integrityKeyFile = new File(file); 
        final byte[] key = FileCopyUtils.copyToByteArray(integrityKeyFile);
        return key;
    }        
       
    
}
