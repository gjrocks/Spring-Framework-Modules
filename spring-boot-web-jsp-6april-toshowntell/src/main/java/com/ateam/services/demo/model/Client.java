package com.ateam.services.demo.model;

public class Client {

	String fullName;
	String accountId;
	String bankId;
	String customerNumber;
	String customerId;
	String passportNumber;
	String drivingLicence;
	String kycDate;
	String kycMode;
	String dob;
	String mobileNumber;

	boolean isAccountAvailable;
	boolean isCustomerAvailable;
	boolean isKYCDone;
	boolean isDetailedKYC;
	boolean isPassportKYCSuccessful;
	boolean isDrivingLicenceKYCSuccessful;
	boolean isDOBVerificationSuccessful;



	public boolean isCustomerAvailable() {
		return isCustomerAvailable;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

	public String getDrivingLicence() {
		return drivingLicence;
	}

	public void setDrivingLicence(String drivingLicence) {
		this.drivingLicence = drivingLicence;
	}

	public boolean isDetailedKYC() {
		return isDetailedKYC;
	}

	public void setDetailedKYC(boolean isDetailedKYC) {
		this.isDetailedKYC = isDetailedKYC;
	}

	public void setCustomerAvailable(boolean isCustomerAvailable) {
		this.isCustomerAvailable = isCustomerAvailable;
	}

	public boolean isAccountAvailable() {
		return isAccountAvailable;
	}

	public void setAccountAvailable(boolean isAccountAvailable) {
		this.isAccountAvailable = isAccountAvailable;
	}

	public String getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	public boolean isKYCDone() {
		return isKYCDone;
	}

	public void setKYCDone(boolean isKYCDone) {
		this.isKYCDone = isKYCDone;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

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

	public String getKycDate() {
		return kycDate;
	}

	public void setKycDate(String kycDate) {
		this.kycDate = kycDate;
	}

	public String getKycMode() {
		return kycMode;
	}

	public void setKycMode(String kycMode) {
		this.kycMode = kycMode;
	}

	public boolean isPassportKYCSuccessful() {
		return isPassportKYCSuccessful;
	}

	public void setPassportKYCSuccessful(boolean isPassportKYCSuccessful) {
		this.isPassportKYCSuccessful = isPassportKYCSuccessful;
	}

	public boolean isDrivingLicenceKYCSuccessful() {
		return isDrivingLicenceKYCSuccessful;
	}

	public void setDrivingLicenceKYCSuccessful(boolean isDrivingLicenceKYCSuccessful) {
		this.isDrivingLicenceKYCSuccessful = isDrivingLicenceKYCSuccessful;
	}
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	public boolean isDOBVerificationSuccessful() {
		return isDOBVerificationSuccessful;
	}

	public void setDOBVerificationSuccessful(boolean isDOBVerificationSuccessful) {
		this.isDOBVerificationSuccessful = isDOBVerificationSuccessful;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	
}
