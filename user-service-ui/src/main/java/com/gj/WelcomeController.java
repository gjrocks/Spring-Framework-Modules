package com.gj;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URI;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
@RestController

public class WelcomeController {

	@Value("${gateway.url}")
	@NotNull
	private String gatewayUrl;
	
	@Value("${server.contextPath:/}")
	@NotNull
	private String contextPathUrl;
	
	private String whereAbouts=getWhereAbouts();

	public static Map<String,String> userValues=new HashMap<String,String>();
	
	
	@Value("${userservice.service.name:user-service}")
	@NotNull
	private String userServiceName;
	
	@Autowired
	private RestTemplate client;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	public Optional<URI> serviceUrl(String serviceName) {
		
		return discoveryClient.getInstances(serviceName).stream().map(si -> si.getUri()).findFirst();
	}
	
	 @PostMapping("/getuser") // //new annotation since 4.3
	public @ResponseBody String getUserDetails(HttpServletRequest request, Model model) {
		 String data=null;
		try {
			String url=serviceUrl("user-service").get()+"/user-service";
			ResponseEntity<String> resp=client.getForEntity(url+"/user", String.class);
			data=resp.getBody();
			//String data="[{\"id\":1,\"fname\":\"fname\",\"lname\":\"lname\",\"address\":\"address\",\"userid\":\"userid\",\"mobileNumber\":\"mobileNumber\",\"email\":\"email@email.com\"},{\"id\":2,\"fname\":\"ganesh\",\"lname\":\"jadhav\",\"address\":\"ganesh address\",\"userid\":\"ganesh-1\",\"mobileNumber\":\"999\",\"email\":\"ganesh@ganesh.com\"},{\"id\":3,\"fname\":\"sidh\",\"lname\":\"nirwane\",\"address\":\"sidh address\",\"userid\":\"sidh-1\",\"mobileNumber\":\"998\",\"email\":\"sidh@sidh.com\"},{\"id\":4,\"fname\":\"aarvi\",\"lname\":\"jadhav\",\"address\":\"aarvi address\",\"userid\":\"aarvi-1\",\"mobileNumber\":\"997\",\"email\":\"aarvi@aarvi.com\"}]";
			model.addAttribute("data", data);
		System.out.println("All Values :" + data);

		} catch (Exception e) {
			model.addAttribute("data", "''");
			e.printStackTrace();
		}
		
		return data;
	}

	 @RequestMapping("/")
		public ModelAndView welcome(ModelAndView mav) {
		
			mav.setViewName("index");
			mav.addObject("context-path", contextPathUrl);
			
			mav.addObject("data", "''");
			
			return mav;
		}
	 
	 @RequestMapping(value="/ping", method=RequestMethod.GET)
		public ResponseEntity<String> ping(){
	    	System.out.println("Returning from the ping from container :" +System.getenv("HOSTNAME") + "::" +System.getProperty("HOSTNAME"));
			return new ResponseEntity<String>("<H1>Service is running at <br>"+getWhereAbouts()+"</H1>", HttpStatus.OK);
		}
	 
	 private static String getWhereAbouts() {
		    StringBuilder whereAbouts=new StringBuilder();
		    	if(System.getenv("HOSTNAME") !=null) {
		    		whereAbouts.append("Docker Container id : ");
		    		whereAbouts.append(System.getenv("HOSTNAME"));
		    	}
		    	whereAbouts.append("-------------------------------------<br> ");
		    		whereAbouts.append("Instance ip  : ");
		    		try {
		    		 Enumeration<NetworkInterface> n = NetworkInterface.getNetworkInterfaces();
		            while (n.hasMoreElements())
		             {
		                     NetworkInterface e = n.nextElement();
		                   //  System.out.println("Interface: " + e.getName());
		                     Enumeration<InetAddress> a = e.getInetAddresses();
		                     for (; a.hasMoreElements();)
		                     {
		                             InetAddress addr = a.nextElement();
		                             whereAbouts.append( addr.getHostAddress());
		                             whereAbouts.append("<br>");
		                     }
		             }
		    		}catch(Exception e) {
		    			e.printStackTrace();
		    		}
	    	return whereAbouts.toString();
		    }
	 
	 
}


