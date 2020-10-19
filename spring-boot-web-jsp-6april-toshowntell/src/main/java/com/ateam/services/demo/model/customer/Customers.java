
package com.ateam.services.demo.model.customer;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "customers"
})
public class Customers {

    @JsonProperty("customers")
    private List<Customer> customers = null;

    @JsonProperty("customers")
    public List<Customer> getCustomers() {
        return customers;
    }

    @JsonProperty("customers")
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

}
