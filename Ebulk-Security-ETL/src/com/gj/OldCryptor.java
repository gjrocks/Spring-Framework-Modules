package com.gj;

import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.util.StringUtils;

public class OldCryptor {

	static OldCryptor old = null;
	StandardPBEStringEncryptor cryptor = null;
	Properties props = new Properties();

	private OldCryptor() {

		cryptor = new StandardPBEStringEncryptor();
		cryptor.setProviderName(SpringPropertiesUtil.getProperty("source.provider.name"));
		cryptor.setAlgorithm(SpringPropertiesUtil.getProperty("source.algo.name")); 
		cryptor.setKeyObtentionIterations(1000);
		
		PasswordCipherImpl cipher=new PasswordCipherImpl();
		String encryptionPassword=SpringPropertiesUtil.getProperty("encryptionPassword");
	  	String encryptionPasswordIsEncrypted=SpringPropertiesUtil.getProperty("encryptionPassword.isEncrypted");
	  	boolean isEncrypted=(StringUtils.hasText(encryptionPasswordIsEncrypted) && encryptionPasswordIsEncrypted.equalsIgnoreCase("true"))?true:false;
	  	EncryptedPropertyConverter encryptedPropertyConverter=new EncryptedPropertyConverter(cipher,encryptionPassword,isEncrypted);
	  	cryptor.setPassword(encryptedPropertyConverter.getValue());
	}

	public static OldCryptor getInstance() {
		if(old==null){
			old=new OldCryptor();
		}
	return old;
	}

	public String encrypt(String plainText) {
		return null;
	}

	public String decrypt(String text) {
		if (StringUtils.hasText(text)) {

			try {
				String retText = cryptor.decrypt(text);
				return retText;
			} catch (Exception e) {
				if (e.getClass().equals(org.jasypt.exceptions.EncryptionOperationNotPossibleException.class)) {
					return text;
				}
				throw e;
			}
		}
		//
		return null;
	}

}
