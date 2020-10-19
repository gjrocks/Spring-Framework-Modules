package com.ateam.services.demo.model;

import java.util.ArrayList;
import java.util.List;

import com.ateam.services.demo.model.transaction.Transaction;

public class OBPTransactionRaw {

	String accountId;
	String bankId;
    List<Transaction> tranDetails;
	
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

	public List<Transaction> getTranDetails() {
		if(tranDetails == null)
		{
			tranDetails = new ArrayList<>();
		}
		return tranDetails;
	}

	public void setTranDetails(List<Transaction> tranDetails) {
		this.tranDetails = tranDetails;
	}


	
}
