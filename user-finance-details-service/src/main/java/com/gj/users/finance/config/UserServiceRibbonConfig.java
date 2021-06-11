package com.gj.users.finance.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AvailabilityFilteringRule;
import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.PingUrl;
import com.netflix.loadbalancer.Server;
import com.netflix.loadbalancer.ServerList;
import com.netflix.loadbalancer.ServerListSubsetFilter;
import com.netflix.loadbalancer.ServerListUpdater;

//@Configuration
public class UserServiceRibbonConfig {
	 @Autowired
	    IClientConfig ribbonClientConfig;
	@Bean
	public IRule ribbonRule() {
		System.out.println("here 1");
		AvailabilityFilteringRule rl=null;
		return new AvailabilityFilteringRule();
	}

	@Bean
	public IPing ribbonPing() {
		System.out.println("here 2");
		//you can write ur custom implementation here
		PingUrl pingURL= new PingUrl(false,"/ping");
		pingURL.setExpectedContent(null);
		return pingURL;
	}

	/*@Bean
	public ServerList<Server> ribbonServerList(IClientConfig config) {
		//config.set(CommonClientConfigKey.ListOfServers, "");
		System.out.println("here 3");
		config.set(CommonClientConfigKey.ListOfServers, "localhost:8001,localhost:8002");
		config.set(CommonClientConfigKey.ServerListRefreshInterval, 1000);
		ConfigurationBasedServerList list=new ConfigurationBasedServerList();
		list.initWithNiwsConfig(config);
		return list;
		
				//new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
	}*/
	@Bean
	public ServerList<Server> ribbonServerList2(IClientConfig config) {
		//config.set(CommonClientConfigKey.ListOfServers, "");
		System.out.println("here 3");
		config.set(CommonClientConfigKey.ServerListRefreshInterval, 10);
		return new ServerList<Server>() {

			@Override
			public List<Server> getInitialListOfServers() {
				String listOfServers="localhost:8001,localhost:8002";
				return derive(listOfServers);
			}

			@Override
			public List<Server> getUpdatedListOfServers() {
				System.out.println("I am called");
				String listOfServers="localhost:8001,localhost:8002";
				return derive(listOfServers);
			}
			
			 List<Server> derive(String value) {
			    List<Server> list = Lists.newArrayList();
				if (!Strings.isNullOrEmpty(value)) {
					for (String s: value.split(",")) {
						list.add(new Server(s.trim()));
					}
				}
		        return list;
			}
		};
		
				//new RibbonClientDefaultConfigurationTestsConfig.BazServiceList(config);
	}
	
	@Bean
	public ServerListSubsetFilter serverListFilter() {
		ServerListSubsetFilter filter = new ServerListSubsetFilter();
		ribbonClientConfig.set(CommonClientConfigKey.ServerListRefreshInterval, 1000); //this is important 
		filter.initWithNiwsConfig(ribbonClientConfig);
		System.out.println("oye teri");
		ServerListUpdater l=null;
		return filter;
	}
	 
}


