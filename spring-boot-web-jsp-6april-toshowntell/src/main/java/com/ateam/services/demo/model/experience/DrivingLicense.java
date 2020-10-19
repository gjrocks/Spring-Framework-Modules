package com.ateam.services.demo.model.experience;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "driver_full_name",
    "date_of_birth",
    "driver_license_number",
    "address_details",
    "result"
})

public class DrivingLicense {
	
	public DrivingLicense(String driverFullName, String dateOfBirth, String driverLicenseNumber,
			String addressDetails) {
		super();
		this.driverFullName = driverFullName;
		this.dateOfBirth = dateOfBirth;
		this.driverLicenseNumber = driverLicenseNumber;
		this.addressDetails = addressDetails;
	}
	
	@JsonProperty("driver_full_name")
    private String driverFullName;
    
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    
    
    @JsonProperty("driver_license_number")
    private String driverLicenseNumber;
    
    @JsonProperty("address_details")
    private String addressDetails;
    
    @JsonProperty("result")
    private String result;
    
    
    @JsonProperty("driver_full_name")
    public String getDriverFullName() {
		return driverFullName;
	}

    @JsonProperty("driver_full_name")
   public void setDriverFullName(String driverFullName) {
		this.driverFullName = driverFullName;
	}
    
    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
		return dateOfBirth;
	}
    @JsonProperty("date_of_birth")
    public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
    
    
    @JsonProperty("driver_license_number")
    public String getDriverLicenseNumber() {
		return driverLicenseNumber;
	}
    @JsonProperty("driver_license_number")
	public void setDriverLicenseNumber(String driverLicenseNumber) {
		this.driverLicenseNumber = driverLicenseNumber;
	}

    @JsonProperty("address_details")
    public String getAddressDetails() {
		return addressDetails;
	}
    @JsonProperty("address_details")
    public void setAddressDetails(String addressDetails) {
		this.addressDetails = addressDetails;
	}
    @JsonProperty("result")
    public String getResult() {
		return result;
	}
    @JsonProperty("result")
    public void setResult(String result) {
		this.result = result;
	}
	

}
