
package com.morrisons.services.demo.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "this_account",
    "other_account",
    "details",
    "metadata"
})
public class Transaction {

    @JsonProperty("id")
    private String id;
    @JsonProperty("this_account")
    private ThisAccount thisAccount;
    @JsonProperty("other_account")
    private OtherAccount otherAccount;
    @JsonProperty("details")
    private Details details;
    @JsonProperty("metadata")
    private Metadata_ metadata;

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("this_account")
    public ThisAccount getThisAccount() {
        return thisAccount;
    }

    @JsonProperty("this_account")
    public void setThisAccount(ThisAccount thisAccount) {
        this.thisAccount = thisAccount;
    }

    @JsonProperty("other_account")
    public OtherAccount getOtherAccount() {
        return otherAccount;
    }

    @JsonProperty("other_account")
    public void setOtherAccount(OtherAccount otherAccount) {
        this.otherAccount = otherAccount;
    }

    @JsonProperty("details")
    public Details getDetails() {
        return details;
    }

    @JsonProperty("details")
    public void setDetails(Details details) {
        this.details = details;
    }

    @JsonProperty("metadata")
    public Metadata_ getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(Metadata_ metadata) {
        this.metadata = metadata;
    }

}
