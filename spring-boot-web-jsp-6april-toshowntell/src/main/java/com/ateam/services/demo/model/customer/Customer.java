
package com.ateam.services.demo.model.customer;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bank_id",
    "customer_id",
    "customer_number",
    "legal_name",
    "mobile_phone_number",
    "email",
    "face_image",
    "date_of_birth",
    "relationship_status",
    "dependants",
    "dob_of_dependants",
    "credit_rating",
    "credit_limit",
    "highest_education_attained",
    "employment_status",
    "kyc_status",
    "last_ok_date"
})
public class Customer {

    @JsonProperty("bank_id")
    private String bankId;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("customer_number")
    private String customerNumber;
    @JsonProperty("legal_name")
    private String legalName;
    @JsonProperty("mobile_phone_number")
    private String mobilePhoneNumber;
    @JsonProperty("email")
    private String email;
    @JsonProperty("face_image")
    private FaceImage faceImage;
    @JsonProperty("date_of_birth")
    private String dateOfBirth;
    @JsonProperty("relationship_status")
    private String relationshipStatus;
    @JsonProperty("dependants")
    private Integer dependants;
    @JsonProperty("dob_of_dependants")
    private List<String> dobOfDependants = null;
    @JsonProperty("credit_rating")
    private CreditRating creditRating;
    @JsonProperty("credit_limit")
    private CreditLimit creditLimit;
    @JsonProperty("highest_education_attained")
    private String highestEducationAttained;
    @JsonProperty("employment_status")
    private String employmentStatus;
    @JsonProperty("kyc_status")
    private Boolean kycStatus;
    @JsonProperty("last_ok_date")
    private String lastOkDate;

    @JsonProperty("bank_id")
    public String getBankId() {
        return bankId;
    }

    @JsonProperty("bank_id")
    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    @JsonProperty("customer_id")
    public String getCustomerId() {
        return customerId;
    }

    @JsonProperty("customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("customer_number")
    public String getCustomerNumber() {
        return customerNumber;
    }

    @JsonProperty("customer_number")
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @JsonProperty("legal_name")
    public String getLegalName() {
        return legalName;
    }

    @JsonProperty("legal_name")
    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    @JsonProperty("mobile_phone_number")
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    @JsonProperty("mobile_phone_number")
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("face_image")
    public FaceImage getFaceImage() {
        return faceImage;
    }

    @JsonProperty("face_image")
    public void setFaceImage(FaceImage faceImage) {
        this.faceImage = faceImage;
    }

    @JsonProperty("date_of_birth")
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    @JsonProperty("date_of_birth")
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @JsonProperty("relationship_status")
    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    @JsonProperty("relationship_status")
    public void setRelationshipStatus(String relationshipStatus) {
        this.relationshipStatus = relationshipStatus;
    }

    @JsonProperty("dependants")
    public Integer getDependants() {
        return dependants;
    }

    @JsonProperty("dependants")
    public void setDependants(Integer dependants) {
        this.dependants = dependants;
    }

    @JsonProperty("dob_of_dependants")
    public List<String> getDobOfDependants() {
        return dobOfDependants;
    }

    @JsonProperty("dob_of_dependants")
    public void setDobOfDependants(List<String> dobOfDependants) {
        this.dobOfDependants = dobOfDependants;
    }

    @JsonProperty("credit_rating")
    public CreditRating getCreditRating() {
        return creditRating;
    }

    @JsonProperty("credit_rating")
    public void setCreditRating(CreditRating creditRating) {
        this.creditRating = creditRating;
    }

    @JsonProperty("credit_limit")
    public CreditLimit getCreditLimit() {
        return creditLimit;
    }

    @JsonProperty("credit_limit")
    public void setCreditLimit(CreditLimit creditLimit) {
        this.creditLimit = creditLimit;
    }

    @JsonProperty("highest_education_attained")
    public String getHighestEducationAttained() {
        return highestEducationAttained;
    }

    @JsonProperty("highest_education_attained")
    public void setHighestEducationAttained(String highestEducationAttained) {
        this.highestEducationAttained = highestEducationAttained;
    }

    @JsonProperty("employment_status")
    public String getEmploymentStatus() {
        return employmentStatus;
    }

    @JsonProperty("employment_status")
    public void setEmploymentStatus(String employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    @JsonProperty("kyc_status")
    public Boolean getKycStatus() {
        return kycStatus;
    }

    @JsonProperty("kyc_status")
    public void setKycStatus(Boolean kycStatus) {
        this.kycStatus = kycStatus;
    }

    @JsonProperty("last_ok_date")
    public String getLastOkDate() {
        return lastOkDate;
    }

    @JsonProperty("last_ok_date")
    public void setLastOkDate(String lastOkDate) {
        this.lastOkDate = lastOkDate;
    }

}
