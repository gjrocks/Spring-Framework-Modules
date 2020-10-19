
package com.ateam.services.demo.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "holder",
    "bank_routing",
    "account_routing",
    "metadata"
})
public class OtherAccount {

    @JsonProperty("id")
    private String id;
    @JsonProperty("holder")
    private Holder_ holder;
    @JsonProperty("bank_routing")
    private BankRouting_ bankRouting;
    @JsonProperty("account_routing")
    private AccountRouting_ accountRouting;
    @JsonProperty("metadata")
    private Metadata metadata;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("holder")
    public Holder_ getHolder() {
        return holder;
    }

    @JsonProperty("holder")
    public void setHolder(Holder_ holder) {
        this.holder = holder;
    }

    @JsonProperty("bank_routing")
    public BankRouting_ getBankRouting() {
        return bankRouting;
    }

    @JsonProperty("bank_routing")
    public void setBankRouting(BankRouting_ bankRouting) {
        this.bankRouting = bankRouting;
    }

    @JsonProperty("account_routing")
    public AccountRouting_ getAccountRouting() {
        return accountRouting;
    }

    @JsonProperty("account_routing")
    public void setAccountRouting(AccountRouting_ accountRouting) {
        this.accountRouting = accountRouting;
    }

    @JsonProperty("metadata")
    public Metadata getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

}
