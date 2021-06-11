package com.gj.users.finance.service;

import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.gj.users.finance.config.ToDoServiceRibbonConfig;


public interface IToDoService {

	public void retrieveToDos();
}
