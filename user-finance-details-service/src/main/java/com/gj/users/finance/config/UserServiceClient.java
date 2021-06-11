package com.gj.users.finance.config;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;



@FeignClient(name="user-service", configuration=FeignConfiguration.class)
public interface UserServiceClient {
	
	@GetMapping(path = "user/1")
	public String getInfo();
	
}
