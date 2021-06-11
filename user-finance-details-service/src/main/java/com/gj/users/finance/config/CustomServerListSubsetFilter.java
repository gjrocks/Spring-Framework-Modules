package com.gj.users.finance.config;

import java.util.List;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerListSubsetFilter;

public class CustomServerListSubsetFilter extends ServerListSubsetFilter{
	
	
	  @Override
	    public void initWithNiwsConfig(IClientConfig clientConfig) {
		  super.initWithNiwsConfig(clientConfig);
	  }
	  
	/*
	 * @Override public List<T> getFilteredListOfServers(List<T> servers) { return
	 * super.getFilteredListOfServers(servers); }
	 */
	  
	  

}
