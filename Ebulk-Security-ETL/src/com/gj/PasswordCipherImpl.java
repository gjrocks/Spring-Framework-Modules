package com.gj;

import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.lang.ArrayUtils;



/**
 * password based encryption utility
 * @author Nick Allen
 */
public class PasswordCipherImpl extends BaseCipher {

   
   
   /**
    * The default password used by this implementation
    */
    // DO NOT CHANGE THIS VALUE UNDER ANY CIRCUMSTANCE AS IT IS USED FOR PROPERTY FILE VALUE DECRYPTION
    private static final char[] DEFAULT_PASSWORD = "xp9pl3JEsTasIt3Gr3at07W".toCharArray();
    
   // Iteration count
    private static final int COUNT = 854;
    
    // cipher algorithm applied
    private static final String ALGORITHM_NAME = "PBEWithMD5AndTripleDES";
    private static final byte SALT[] = { (byte)0x5C, (byte)0x2E, (byte)0x4E, (byte)0xF7
                                       , (byte)0xF9, (byte)0xC9, (byte)0x23, (byte)0xC1 };

    private final AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, COUNT);
    private SecretKey key = null;

	private static final Option PLAIN_PASSWORD_OPTION = OptionBuilder
			.withArgName("pw")
	        .hasArg()
	        .withDescription("Plain-text version of the password to be ENcrypted.")
	        .create("plainpw");

	private static final Option ENCRYPTED_PASSWORD_OPTION = OptionBuilder
			.withArgName("pw")
	        .hasArg()
	        .withDescription("Encrypted version of the password to be DEcrypted.")
	        .create("cryptpw");

	private static final Options OPTIONS = new Options();

	private static final CommandLineParser PARSER = new GnuParser();
	
	static {
		OPTIONS.addOption(PLAIN_PASSWORD_OPTION);
		OPTIONS.addOption(ENCRYPTED_PASSWORD_OPTION);
	}
    
    /**
     * constructor initiates class encryption using suppied password
     * @param password
     */
    public PasswordCipherImpl(String password) {
        if (password != null && password.length()>0) {
            init(password.toCharArray());
        } else {
            throw new IllegalArgumentException("Password cannot be blank");
        }
    }    

    /**
     * default constructor uses the DEFAULT_PASSWORD
     */
    public PasswordCipherImpl() {
        init(DEFAULT_PASSWORD);
    }      
   
    public String getAlgorithm() {
        return ALGORITHM_NAME;
    }
   
    /**
     * initialise the ciphers for encryption and decryption
     * @param passPhrase
     */
    private void init(char[] passPhrase) {

        //logger.debug("passPhrase='"+passPhrase+"'");
        
        try {
            // Create the key
            final KeySpec keySpec = new PBEKeySpec(passPhrase, SALT, COUNT);
            
            key = SecretKeyFactory.getInstance(ALGORITHM_NAME).generateSecret(keySpec);
            
            /*
             * This is where the JDK bug manifests in all versions of Java prior to Java 1.6
             * This should return PBEWithMD5AndTripleDES but actually returns PBEWithMD5AndDES
             * which is a massive problem for encryption capability!! - NSA 28.02.07
             */
           
            
            setEcipher(getEncryptionCipher());
            setDcipher(getDecryptionCipher());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private Cipher getEncryptionCipher() throws Exception {
        // JDK fix - by specifying the ALGORITHM_NAME and not using the key.getAlgorithm()
        final Cipher ec = Cipher.getInstance(ALGORITHM_NAME);
        ec.init(Cipher.ENCRYPT_MODE, key,paramSpec);
        return ec;    
    }
    
    private Cipher getDecryptionCipher() throws Exception {
        // JDK fix - by specifying the ALGORITHM_NAME and not using the key.getAlgorithm()
        final Cipher dc = Cipher.getInstance(ALGORITHM_NAME);
        dc.init(Cipher.DECRYPT_MODE, key,paramSpec); 
        return dc;
    }    
  

  
  
    /**
     * allows a hardcoded value to be encrypted from the command line
     * @param args
     */
    public static void main(String[] args) throws Exception {
    	if (ArrayUtils.isEmpty(args)) {
    		printCommandHelp(OPTIONS);
    	} else {
    		try {
				CommandLine line = PARSER.parse(OPTIONS, args);
				
				if (line.hasOption(PLAIN_PASSWORD_OPTION.getOpt())) {
					final String enVal = enc(line.getOptionValue(PLAIN_PASSWORD_OPTION.getOpt()));
					
				} else if (line.hasOption(ENCRYPTED_PASSWORD_OPTION.getOpt())) {
					final String deVal = dec(line.getOptionValue(ENCRYPTED_PASSWORD_OPTION.getOpt()));
				
				}
			} catch (ParseException e) {
				e.printStackTrace();
				
				printCommandHelp(OPTIONS);
			}
    	}
    }

	/**
	 * Encrypt and return the given String.
	 * @param plainPassword
	 * @return
	 */
	private static String enc(final String plainPassword) {
		final PasswordCipherImpl c = new PasswordCipherImpl();
		return c.encrypt(plainPassword);
	}

	/**
	 * Decrypt and return the given String.
	 * @param encryptedPassword
	 * @return
	 */
	private static String dec(final String encryptedPassword) {		
		final PasswordCipherImpl c = new PasswordCipherImpl();
		return c.decrypt(encryptedPassword);
	}

	private static void printCommandHelp(Options options) {
		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("Password Cipher", options);
	}
  
}