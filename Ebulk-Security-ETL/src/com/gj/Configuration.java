package com.gj;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

	List<Config> configurations=new ArrayList<>();

	public List<Config> getConfigurations() {
		return configurations;
	}

	public void setConfigurations(List<Config> configurations) {
		this.configurations = configurations;
	}

	public Configuration(List<Config> configurations) {
		super();
		this.configurations = configurations;
	}

	public Configuration() {
		super();
		
	}
	
}
