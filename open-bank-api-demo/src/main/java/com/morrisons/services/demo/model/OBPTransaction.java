package com.morrisons.services.demo.model;

import java.util.ArrayList;
import java.util.List;

public class OBPTransaction {

	String accountId;
	String bankId;
    List<TranDetails> tranDetails;
	
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public List<TranDetails> getTranDetails() {
		if(tranDetails == null)
		{
			tranDetails = new ArrayList<>();
		}
		return tranDetails;
	}

	public void setTranDetails(List<TranDetails> tranDetails) {
		this.tranDetails = tranDetails;
	}


	
}
