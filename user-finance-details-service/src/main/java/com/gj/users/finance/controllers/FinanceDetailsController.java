package com.gj.users.finance.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gj.users.finance.config.UserServiceClient;
import com.gj.users.finance.config.UserServiceRibbonConfig;
import com.gj.users.finance.service.IToDoService;

@RestController
@RibbonClient(name = "user-service", configuration = UserServiceRibbonConfig.class)
public class FinanceDetailsController {

	@Autowired
	UserServiceClient fact;

	/*
	 * @LoadBalanced
	 * 
	 * @Bean RestTemplate getRestTemplate() { return new RestTemplate(); }
	 * 
	 * @Autowired RestTemplate restTemplate;
	 */

	@Autowired
	@Qualifier(value = "toDoService")
	IToDoService iToDoService;
	
	@RequestMapping(value = "/ping", produces = MediaType.APPLICATION_JSON_VALUE)
	public String ping() {

		//String servLoc = this.restTemplate.getForObject("http://user-service/user/1", String.class);
		String servLoc =fact.getInfo();
		System.out.println(servLoc);
		iToDoService.retrieveToDos();
		return "OK";
	}
}
