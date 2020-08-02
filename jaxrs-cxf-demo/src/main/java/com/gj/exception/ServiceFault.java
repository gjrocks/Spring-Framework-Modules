package com.gj.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ServiceFault")
public class ServiceFault extends Exception implements Serializable {

	ServiceFaultDetails[] faults;

	
	public ServiceFault(String message,ServiceFaultDetails[] faults) {
		super(message);
		this.faults = faults;
	}
	
	public ServiceFault(ServiceFaultDetails[] faults) {
		super();
		this.faults = faults;
	}

	public ServiceFaultDetails[] getFaults() {
		return faults;
	}

	public void setFaults(ServiceFaultDetails[] faults) {
		this.faults = faults;
	}
	
	
	
}
