package com.gj;

import java.util.Properties;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.util.StringUtils;

public class NewCryptor {

	static NewCryptor newEncyptor = null;
	StandardPBEStringEncryptor cryptor = null;
	Properties props = new Properties();
	private static PasswordCipherImpl cipher=new PasswordCipherImpl();
	String encryptionPassword=null;
	boolean encryptionPasswordIsEncrypted=false;
	private NewCryptor(String password, boolean inEncrypted) {

		cryptor = new StandardPBEStringEncryptor();
		cryptor.setProviderName(SpringPropertiesUtil.getProperty("target.provider.name"));
		cryptor.setAlgorithm(SpringPropertiesUtil.getProperty("target.algo.name"));
		cryptor.setKeyObtentionIterations(1000);
		//String encryptionPassword=SpringPropertiesUtil.getProperty("encryptionPassword");
	  	//String encryptionPasswordIsEncrypted=SpringPropertiesUtil.getProperty("encryptionPassword.isEncrypted");
	  	
	  	this.encryptionPassword=password;
	  	this.encryptionPasswordIsEncrypted=inEncrypted;
	  	
	  	
	  	//boolean isEncrypted=(StringUtils.hasText(encryptionPasswordIsEncrypted) && encryptionPasswordIsEncrypted.equalsIgnoreCase("true"))?true:false;
	  	EncryptedPropertyConverter encryptedPropertyConverter=new EncryptedPropertyConverter(cipher,this.encryptionPassword,this.encryptionPasswordIsEncrypted);
	  	cryptor.setPassword(encryptedPropertyConverter.getValue());
	  	
	}

	public static NewCryptor getInstance() {
		if(newEncyptor==null){
			String encryptionPassword=SpringPropertiesUtil.getProperty("encryptionPassword");
		  	String encryptionPasswordIsEncrypted=SpringPropertiesUtil.getProperty("encryptionPassword.isEncrypted");
		  	boolean isEncrypted=(StringUtils.hasText(encryptionPasswordIsEncrypted) && encryptionPasswordIsEncrypted.equalsIgnoreCase("true"))?true:false;
		  	newEncyptor=new NewCryptor(encryptionPassword,isEncrypted);
		}
		return newEncyptor;
	}

	
	public static NewCryptor getInstance(String plain, boolean isEncrypted) {
		if (newEncyptor == null) {
			EncryptedPropertyConverter encryptedPropertyConverter=new EncryptedPropertyConverter(cipher,plain,isEncrypted);
			String newPassEncrypted=encryptedPropertyConverter.getEncryptedValue(plain);
			newEncyptor = new NewCryptor(newPassEncrypted,true);
		}
		return newEncyptor;
	}
	
	public String encrypt(String plainText) {
		if(StringUtils.hasText(plainText))
			return cryptor.encrypt(plainText);
		return null;
	}

	public String decrypt(String text) {
		if(StringUtils.hasText(text))
		return cryptor.decrypt(text);
		return null;
	}
	
	public static void main(String ... args){
	System.out.println(NewCryptor.getInstance().encrypt("TN123456A"));
	}

	public String getEncryptionPassword() {
		return encryptionPassword;
	}

	public void setEncryptionPassword(String encryptionPassword) {
		this.encryptionPassword = encryptionPassword;
	}

	public boolean isEncryptionPasswordIsEncrypted() {
		return encryptionPasswordIsEncrypted;
	}

	public void setEncryptionPasswordIsEncrypted(boolean encryptionPasswordIsEncrypted) {
		this.encryptionPasswordIsEncrypted = encryptionPasswordIsEncrypted;
	}

}
