
package com.morrisons.services.demo.model.kyc;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "checks"
})
public class KYCChecks {

    @JsonProperty("checks")
    private List<Check> checks = null;

    @JsonProperty("checks")
    public List<Check> getChecks() {
        return checks;
    }

    @JsonProperty("checks")
    public void setChecks(List<Check> checks) {
        this.checks = checks;
    }

}
