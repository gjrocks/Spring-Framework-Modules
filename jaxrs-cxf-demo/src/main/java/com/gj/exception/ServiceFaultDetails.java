package com.gj.exception;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="ServiceFaultDetails")
public class ServiceFaultDetails implements Serializable {

	String faultMessage;
	int faultCode;
	
	public ServiceFaultDetails(String faultMessage, int faultCode) {
		super();
		this.faultMessage = faultMessage;
		this.faultCode = faultCode;
	}
	public String getFaultMessage() {
		return faultMessage;
	}
	public void setFaultMessage(String faultMessage) {
		this.faultMessage = faultMessage;
	}
	public int getFaultCode() {
		return faultCode;
	}
	public void setFaultCode(int faultCode) {
		this.faultCode = faultCode;
	}
	
}
