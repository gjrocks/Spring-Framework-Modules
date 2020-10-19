
package com.ateam.services.demo.model.kyc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "bank_id",
    "customer_id",
    "id",
    "customer_number",
    "type",
    "number",
    "issue_date",
    "issue_place",
    "expiry_date"
})
public class Document {

    @JsonProperty("bank_id")
    private String bankId;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("customer_number")
    private String customerNumber;
    @JsonProperty("type")
    private String type;
    @JsonProperty("number")
    private String number;
    @JsonProperty("issue_date")
    private String issueDate;
    @JsonProperty("issue_place")
    private String issuePlace;
    @JsonProperty("expiry_date")
    private String expiryDate;

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

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("customer_number")
    public String getCustomerNumber() {
        return customerNumber;
    }

    @JsonProperty("customer_number")
    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("issue_date")
    public String getIssueDate() {
        return issueDate;
    }

    @JsonProperty("issue_date")
    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @JsonProperty("issue_place")
    public String getIssuePlace() {
        return issuePlace;
    }

    @JsonProperty("issue_place")
    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    @JsonProperty("expiry_date")
    public String getExpiryDate() {
        return expiryDate;
    }

    @JsonProperty("expiry_date")
    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

}
