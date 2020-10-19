
package com.ateam.services.demo.model.transaction;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "bank_routing",
    "account_routing",
    "holders"
})
public class ThisAccount {

    @JsonProperty("id")
    private String id;
    @JsonProperty("bank_routing")
    private BankRouting bankRouting;
    @JsonProperty("account_routing")
    private AccountRouting accountRouting;
    @JsonProperty("holders")
    private List<Holder> holders = null;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("bank_routing")
    public BankRouting getBankRouting() {
        return bankRouting;
    }

    @JsonProperty("bank_routing")
    public void setBankRouting(BankRouting bankRouting) {
        this.bankRouting = bankRouting;
    }

    @JsonProperty("account_routing")
    public AccountRouting getAccountRouting() {
        return accountRouting;
    }

    @JsonProperty("account_routing")
    public void setAccountRouting(AccountRouting accountRouting) {
        this.accountRouting = accountRouting;
    }

    @JsonProperty("holders")
    public List<Holder> getHolders() {
        return holders;
    }

    @JsonProperty("holders")
    public void setHolders(List<Holder> holders) {
        this.holders = holders;
    }

}
