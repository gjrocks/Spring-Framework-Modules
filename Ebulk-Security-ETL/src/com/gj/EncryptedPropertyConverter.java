/*
 * Copyright (c) 2008 Nick Allen.  All rights reserved.
 * Distribution and use of this source code (or the application compiled from this source code) 
 * and associated documentation ("Software"), with or without modification is prohibited.
 */
package com.gj;

import org.springframework.util.StringUtils;

import com.gj.ICipher;
import com.gj.PasswordCipherImpl;
/**
 * This is used to decrypt a string value
 */
public class EncryptedPropertyConverter {

    
    private String value;
    private boolean isEncrypted = false;    
    private ICipher passwordCipher = null;
    
    public EncryptedPropertyConverter(String value,boolean isEncrypted) {
      this(new PasswordCipherImpl(),value,isEncrypted);
    }
    
    public EncryptedPropertyConverter(ICipher passwordCipher,String value,boolean isEncrypted) {
      this.passwordCipher = passwordCipher;
      this.value = value;
      this.isEncrypted = isEncrypted;      
    }

    
    public String getValue() {
      if (isEncrypted) {
        return passwordCipher.decrypt(value);      
      } else {
          return value;
      }      
    }
    public String getEncryptedValue(String newkey) {
        if (StringUtils.hasText(newkey)) {
          return passwordCipher.encrypt(newkey);      
        } else {
            return null;
        }      
      }
}
