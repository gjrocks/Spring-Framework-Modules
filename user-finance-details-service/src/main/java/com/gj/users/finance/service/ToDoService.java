package com.gj.users.finance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gj.users.finance.config.ToDoServiceRibbonConfig;

@Service(value = "toDoService")
@RibbonClient(name = "todo-service", configuration = ToDoServiceRibbonConfig.class)
public class ToDoService implements IToDoService {

	@LoadBalanced
	@Bean
	RestTemplate getRestTemplate2() {
		return new RestTemplate();
	}

	@Autowired
	RestTemplate restTemplate2;
	@Override
	public void retrieveToDos() {
		
		String servLoc = this.restTemplate2.getForObject("http://todo-service/todo", String.class);
		System.out.println(servLoc);
	}

}
