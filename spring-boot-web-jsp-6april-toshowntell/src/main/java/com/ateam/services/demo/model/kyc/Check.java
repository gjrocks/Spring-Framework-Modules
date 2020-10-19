
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
    "date",
    "how",
    "staff_user_id",
    "staff_name",
    "satisfied",
    "comments"
})
public class Check {

    @JsonProperty("bank_id")
    private String bankId;
    @JsonProperty("customer_id")
    private String customerId;
    @JsonProperty("id")
    private String id;
    @JsonProperty("customer_number")
    private String customerNumber;
    @JsonProperty("date")
    private String date;
    @JsonProperty("how")
    private String how;
    @JsonProperty("staff_user_id")
    private String staffUserId;
    @JsonProperty("staff_name")
    private String staffName;
    @JsonProperty("satisfied")
    private Boolean satisfied;
    @JsonProperty("comments")
    private String comments;

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

    @JsonProperty("date")
    public String getDate() {
        return date;
    }

    @JsonProperty("date")
    public void setDate(String date) {
        this.date = date;
    }

    @JsonProperty("how")
    public String getHow() {
        return how;
    }

    @JsonProperty("how")
    public void setHow(String how) {
        this.how = how;
    }

    @JsonProperty("staff_user_id")
    public String getStaffUserId() {
        return staffUserId;
    }

    @JsonProperty("staff_user_id")
    public void setStaffUserId(String staffUserId) {
        this.staffUserId = staffUserId;
    }

    @JsonProperty("staff_name")
    public String getStaffName() {
        return staffName;
    }

    @JsonProperty("staff_name")
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @JsonProperty("satisfied")
    public Boolean getSatisfied() {
        return satisfied;
    }

    @JsonProperty("satisfied")
    public void setSatisfied(Boolean satisfied) {
        this.satisfied = satisfied;
    }

    @JsonProperty("comments")
    public String getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(String comments) {
        this.comments = comments;
    }

}
