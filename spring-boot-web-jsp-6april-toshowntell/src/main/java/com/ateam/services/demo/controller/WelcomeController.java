package com.ateam.services.demo.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ateam.services.demo.authentication.DirectAuthenticationService;


@Controller

public class WelcomeController {
	private static String UPLOADED_FOLDER = "C://temp//output//pdf//";
	
	// inject via application.properties
	/*@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Value("${randomFlag:true}")
    private String randomFlag;
	@Value("${system.config.dir:true}")
	private String location;
	*/
	
	@Value("${obp.url}")
	private String obpUrl;
	
	
	@Autowired
	private DirectAuthenticationService directAuthenticationService;
	
	public static Map<String,String> userValues=new HashMap<String,String>();
	
	@RequestMapping("/login")
	public String login(Map<String, Object> model,HttpServletRequest request) {
	
		
		return "login";
	}
	
	
	@RequestMapping("/submitpersonaldetails")
	public String welcome(Map<String, Object> model,HttpServletRequest request) {
	
		String username=request.getParameter("uname");
		String password=request.getParameter("upassword");
		
		String authToken = directAuthenticationService.login(username, password);
		
		if(authToken.equalsIgnoreCase("NOT_AUTH")){
			model.put("message", "Not authorized user !!!");
			return "login";
		}
		
		model.put("fullName", request.getParameter("fullName"));
		model.put("exampleInputEmail", request.getParameter("email"));
		model.put("mobileNumber", request.getParameter("mobileNumber"));
		model.put("dl",  request.getParameter("dl"));
		model.put("dob", request.getParameter("dob"));
		model.put("sortCode", request.getParameter("sort"));
		model.put("acc", request.getParameter("acc"));
		return "index-gj2";
	}
	
	@RequestMapping("/")
	public String welcomed(Map<String, Object> model) {
	/*	System.out.println("Ganesh  :" +randomFlag);
    	System.out.println("ffff :" +location);
		model.put("message", this.message);*/
		return "index";
	}
	@RequestMapping("/driverslicense.html")
	public String welcome2(Map<String, Object> model) {
	/*	System.out.println("Ganesh  :" +randomFlag);
    	System.out.println("ffff :" +location);
		model.put("message", this.message);*/
		return "driverslicense";
	}
	
	@RequestMapping("/displayInfo.html")
	public String welcome23(Map<String, Object> model) {
	/*	System.out.println("Ganesh  :" +randomFlag);
    	System.out.println("ffff :" +location);
		model.put("message", this.message);*/
		return "displayInfo";
	}
	
	@RequestMapping("/passport.html")
	public String welcome3(Map<String, Object> model) {
	/*	System.out.println("Ganesh  :" +randomFlag);
    	System.out.println("ffff :" +location);
		model.put("message", this.message);*/
		return "passport";
	}
	
	 @PostMapping("/upload") // //new annotation since 4.3
	    public String singleFileUpload(@RequestParam("file") MultipartFile file,
	                                   RedirectAttributes redirectAttributes,HttpServletRequest request) {

	        if (file.isEmpty()) {
	            redirectAttributes.addFlashAttribute("message", "Please select a file to upload");
	            return "redirect:uploadStatus";
	        }

	        try {

	            // Get the file and save it somewhere
	            byte[] bytes = file.getBytes();
	            
	            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
	            Files.write(path, bytes);

	            redirectAttributes.addFlashAttribute("message",
	                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
	            
	            userValues.put("filePath", UPLOADED_FOLDER + file.getOriginalFilename());
	            userValues.put("fileName", file.getOriginalFilename());
	            userValues.put("fullName", request.getParameter("fullName"));
	            userValues.put("exampleInputEmail", request.getParameter("exampleInputEmail1"));
	            userValues.put("mobileNumber", request.getParameter("mobileNumber"));
	            userValues.put("dl",  request.getParameter("dl"));
	            userValues.put("dob", request.getParameter("dob"));
	            
	           System.out.println("All Values :" +userValues);
	          
	         
      
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        return "redirect:/uploadStatus";
	    }

	 @PostMapping("/uploadNew") // //new annotation since 4.3
	    public String singleFileUpload(
	                                   RedirectAttributes redirectAttributes,HttpServletRequest request) {
	        try {

	            userValues.put("fullName", request.getParameter("fullName"));
	            userValues.put("exampleInputEmail", request.getParameter("exampleInputEmail1"));
	            userValues.put("mobileNumber", request.getParameter("mobileNumber"));
	            userValues.put("dl",  request.getParameter("dl"));
	            userValues.put("dob", request.getParameter("dob"));
	            userValues.put("sortCode",  request.getParameter("sortCode"));
	            userValues.put("acc", request.getParameter("acc"));
	            userValues.put("cust", request.getParameter("cust"));
	           System.out.println("All Values :" +userValues);
	          
	         
   
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return "redirect:/uploadStatusNew";
	    }
	 
	 
	    @GetMapping("/uploadStatusNew")
	public String uploadStatusNew(Map<String, Object> model) {

		//Map<String, Object> map = new WebPagePdfExtractor().processRecordFromFile(userValues.get("filePath"));
		//String text1 = map.get("text").toString();
		//System.out.println("Text :" +text);
	    	/* String hh="C:\\temp\\output\\text\\";
	    	 try{
	    		 userValues.get("fileName"); 
	    	 Path path = Paths.get(hh + userValues.get("fileName").replace(".pdf", ".txt"));
	        
	    	 String text = new String (Files.readAllBytes(path));
		    // System.out.println(content);
			//Files.write(path, text1.getBytes());
	    	 String sortCode = WebPagePdfExtractor.extract("code:", text);
	    	 String bic = WebPagePdfExtractor.extract("BIC:", text);
		 	 String account = WebPagePdfExtractor.extract("number:", text);
		 	 String iban = WebPagePdfExtractor.extract("IBAN:", text);
	    	
		 	 if(!text.contains("lloydsbank.com")){
	    		 String[] y=WebPagePdfExtractor.extractHSBC("-", text);
	    		 sortCode=y[0];
	    		 account=y[1];
	    	 }*/
	 		
	 		model.put("sortCode", userValues.get("sortCode"));
	 		model.put("bic", userValues.get("bic"));
	 		model.put("account", userValues.get("acc"));
	 		model.put("iban", userValues.get("iban"));
	 		model.put("fullName",  userValues.get("fullName"));
	 		model.put("dl",  userValues.get("dl"));    	
	 		model.put("dob",  userValues.get("dob"));
	 		model.put("cust",  userValues.get("cust"));
		    model.put("obpUrl", obpUrl);    		
		/*} catch (IOException e) {
			
			e.printStackTrace();
		}
      
    

		
		
*/		return "uploadedNew";
	}
	 
	 
	 
	    @GetMapping("/uploadStatus")
	public String uploadStatus(Map<String, Object> model) {

		//Map<String, Object> map = new WebPagePdfExtractor().processRecordFromFile(userValues.get("filePath"));
		//String text1 = map.get("text").toString();
		//System.out.println("Text :" +text);
	    	 String hh="C:\\temp\\output\\text\\";
	    	 try{
	    		 userValues.get("fileName"); 
	    	 Path path = Paths.get(hh + userValues.get("fileName").replace(".pdf", ".txt"));
	        
	    	 String text = new String (Files.readAllBytes(path));
		    // System.out.println(content);
			//Files.write(path, text1.getBytes());
	    	 String sortCode = WebPagePdfExtractor.extract("code:", text);
	    	 String bic = WebPagePdfExtractor.extract("BIC:", text);
		 	 String account = WebPagePdfExtractor.extract("number:", text);
		 	 String iban = WebPagePdfExtractor.extract("IBAN:", text);
	    	
		 	 if(!text.contains("lloydsbank.com")){
	    		 String[] y=WebPagePdfExtractor.extractHSBC("-", text);
	    		 sortCode=y[0];
	    		 account=y[1];
	    	 }
	 		
	 		model.put("sortCode", sortCode.trim());
	 		model.put("bic", bic.trim());
	 		model.put("account", account.trim());
	 		model.put("iban", iban.trim());
	 		model.put("fullName",  userValues.get("fullName"));
	 		model.put("dl",  userValues.get("dl"));    	
	 		model.put("dob",  userValues.get("dob"));
		        		
		} catch (IOException e) {
			
			e.printStackTrace();
		}
         
       

		
		
		return "uploadedNew";
	}
}


/*@Controller
@PropertySource(value="${system.config.dir}/basicws.properties",ignoreResourceNotFound = false)
public class WelcomeController {

	// inject via application.properties
	@Value("${welcome.message:test}")
	private String message = "Hello World";

	@Value("${randomFlag:true}")
    private String randomFlag;
	@Value("${system.config.dir:true}")
	private String location;
	
	@RequestMapping("/")
	public String welcome(Map<String, Object> model) {
		System.out.println("Ganesh  :" +randomFlag);
    	System.out.println("ffff :" +location);
		model.put("message", this.message);
		return "welcome";
	}

}*/