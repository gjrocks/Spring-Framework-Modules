package com.gj;

import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import scriptella.execution.EtlExecutor;
/**
 * 
 * @author gjadhav
 *
 */  
public class Test {    

	 static {
	        Security.addProvider(new BouncyCastleProvider());
	 }
	
	public static void main(String[] args) throws Exception {

		String folder = null;
		String targetFolder = null;
		String newPasswordPlain = null;
		boolean allSet=false;
		switch (args.length) {
		case 1:
			folder = args[0];
			allSet=isFolder(folder);
			break;
		case 3:
			folder = args[0];
			targetFolder = args[1];
			newPasswordPlain = args[2];
			allSet=(isFolder(folder) && isFolder(targetFolder));
			break;
		default:
			//System.out.println("Please read the readme.rd for details...this program needs few command line parameters");
			break;
		}
	
		if(allSet){
			System.out.println("Updating the encryption for deployement :" + folder);
			if(targetFolder!=null){
				System.out.println("New properties files will be available at :" +targetFolder);
				System.out.println("new Password :" +newPasswordPlain);
			}
			new Test().doIt(folder, targetFolder, newPasswordPlain);
		}else{
			System.out.println("Please read the readme.rd for details...this program needs few command line parameters");
			System.exit(1);
		}
			
		
	}

	private static boolean isFolder(String folder){
		if(!(new File(folder).isDirectory())){
		System.out.println("this location is not a valid folder :" +folder);
			return false;
		}
		return true;
	}
	public void doIt(String folder, String targetFolder, String newPasswordPlain) throws Exception {
		long start = System.currentTimeMillis();
		Map<String, String> mp = new HashMap<>();

		XmlBeanFactory factory = new XmlBeanFactory(new ClassPathResource("/beans.xml"));
		Resource resource0 = new FileSystemResource(folder + "/runtime.properties");

		Resource resource1 = new FileSystemResource(folder +  "/jdbc.properties");

		Resource resource2 = new ClassPathResource("etl-generator.properties");

		Resource resource3 = new ClassPathResource("etl-config.properties");
		
		Resource[] rs = new Resource[4];
		rs[0] = resource0;
		rs[1] = resource1;
		rs[2] = resource2;
		rs[3] = resource3;

		SpringPropertiesUtil util = new SpringPropertiesUtil();
		util.setLocations(rs);
		util.postProcessBeanFactory(factory);

		mp.putAll(util.getPropertiesMap());

		// String encryptedPasswordPlain=null;
		NewCryptor obj = null;
		boolean fileCreated = true;
		
		manageDatabasePassword(mp);
		
		if (StringUtils.hasText(newPasswordPlain)) {
			obj = NewCryptor.getInstance(newPasswordPlain, true);
			if (obj != null) {
				fileCreated = writeNewPropertiesFile(folder, targetFolder, newPasswordPlain, obj.getEncryptionPassword());
				if(fileCreated){
					EtlExecutor.newExecutor(new File("./resources/etl/etl.xml").toURI().toURL(), mp).execute();
				}
			}

		}else{
			EtlExecutor.newExecutor(new File("./resources/etl/etl.xml").toURI().toURL(), mp).execute();
		}

		long end = System.currentTimeMillis();
		if(newPasswordPlain!=null && obj!=null){
		System.out.println("New password : plain:: " + newPasswordPlain);
		System.out.println("New password : encrypted:: " + obj.getEncryptionPassword());
		}
		System.out.println("Encryption Time Taken :" + (Util.getLng().get()) / 1000);
		System.out.println("Time Taken :" + (end - start) / 1000);
	}		
	
	private void manageDatabasePassword(Map<String, String> mp) {
		
		PasswordCipherImpl cipher=new PasswordCipherImpl();
		String encryptionPassword=SpringPropertiesUtil.getProperty("jdbc.password");
	  	String encryptionPasswordIsEncrypted=SpringPropertiesUtil.getProperty("jdbc.password.isEncrypted");
	  	boolean isEncrypted=(StringUtils.hasText(encryptionPasswordIsEncrypted) && encryptionPasswordIsEncrypted.equalsIgnoreCase("true"))?true:false;
	  	EncryptedPropertyConverter encryptedPropertyConverter=new EncryptedPropertyConverter(cipher,encryptionPassword,isEncrypted);
	  	String dbPass=encryptedPropertyConverter.getValue();
	  	mp.put("jdbc.password",dbPass);
		
	}

	private boolean writeNewPropertiesFile(String folder,String targetFolder, String newPasswordPlain, final String encryptedPasswordPlain ){

		//final String encryptedPasswordPlain=obj.getEncryptionPassword();
		try {
			System.out.println("New password : plain:: " + newPasswordPlain);
			System.out.println("New password : encrypted:: " + encryptedPasswordPlain);

			String keyName = "encryptionPassword=";
			String location = folder  + "/runtime.properties";
			String targetLocation = targetFolder + "/" +"runtime.properties";

			Stream<String> lines = Files.lines(Paths.get(new URI("file:///" + location)));
			FileWriter fw = new FileWriter(targetLocation);
			lines.forEach(

					line -> {

						try {
							if (line.startsWith(keyName)) {
								fw.write("#" + line);
								fw.write("\n");
								fw.write(keyName + encryptedPasswordPlain);

							} else {
								fw.write(line);
							}
							fw.write("\n");
						} catch (Exception e) {

							e.printStackTrace();
						}
					}

			);
			lines.close();
			fw.close();
		}catch(Exception e){
		e.printStackTrace();
		System.out.println("Problem in writing/updating the runtime.properties, please fix the problem and re-run");
		return false;
	}
	
	System.out.println("Please copy the file runtime.properties from folder"+targetFolder);
	return true;
	
	}
	
}


