package com.ateam.services.demo.model;

public class TranDetails {

	String fromAccountId;
	String toAccountId;
	String type;
	String transactionAmount;
	String newBalance;
	String currency;
	String cdate;
	String desc;
    int srNo;
    String inamt;
    String outamt;
    
    
    
	public String getInamt() {
		return inamt;
	}

	public void setInamt(String inamt) {
		this.inamt = inamt;
	}

	public String getOutamt() {
		return outamt;
	}

	public void setOutamt(String outamt) {
		this.outamt = outamt;
	}

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(String transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getNewBalance() {
		return newBalance;
	}

	public void setNewBalance(String newBalance) {
		this.newBalance = newBalance;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	

	public String getCdate() {
		return cdate;
	}

	public void setCdate(String cdate) {
		this.cdate = cdate;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	
}
