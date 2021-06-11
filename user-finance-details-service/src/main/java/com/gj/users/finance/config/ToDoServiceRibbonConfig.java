package com.gj.users.finance.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.BestAvailableRule;
import com.netflix.loadbalancer.ConfigurationBasedServerList;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListSubsetFilter;

//@Configuration
public class ToDoServiceRibbonConfig {
	 @Autowired
	    IClientConfig ribbonClientConfig;
	@Bean
	public IRule ribbonRule() {
		System.out.println("here 1");
		return new BestAvailableRule();
	}

	@Bean
	public IPing ribbonPing() {
		System.out.println("here 2");
		return new PingUrl();
	}

	@Bean
	public ServerList<Server> ribbonServerList(IClientConfig config) {
		//config.set(CommonClientConfigKey.ListOfServers, "");
		System.out.println("here 3");
		config.set(CommonClientConfigKey.ListOfServers, "localhost:9001,localhost:9002");
		config.set(CommonClientConfigKey.ServerListRefreshInterval, 10);
		ConfigurationBasedServerList list=new ConfigurationBasedServerList();
		list.initWithNiwsConfig(config);
		return list;
		
				//new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
	}

	@Bean
	public ServerListSubsetFilter serverListFilter() {
		ServerListSubsetFilter filter = new ServerListSubsetFilter();
		return filter;
	}
}
