
package com.ateam.services.demo.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "is_alias"
})
public class Holder {

    @JsonProperty("name")
    private String name;
    @JsonProperty("is_alias")
    private Boolean isAlias;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("is_alias")
    public Boolean getIsAlias() {
        return isAlias;
    }

    @JsonProperty("is_alias")
    public void setIsAlias(Boolean isAlias) {
        this.isAlias = isAlias;
    }

}
