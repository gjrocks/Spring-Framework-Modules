
package com.ateam.services.demo.model.transaction;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "public_alias",
    "private_alias",
    "more_info",
    "url",
    "image_url",
    "open_corporates_url",
    "corporate_location",
    "physical_location"
})
public class Metadata {

    @JsonProperty("public_alias")
    private String publicAlias;
    @JsonProperty("private_alias")
    private String privateAlias;
    @JsonProperty("more_info")
    private String moreInfo;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("open_corporates_url")
    private String openCorporatesUrl;
    @JsonProperty("corporate_location")
    private CorporateLocation corporateLocation;
    @JsonProperty("physical_location")
    private PhysicalLocation physicalLocation;

    @JsonProperty("public_alias")
    public String getPublicAlias() {
        return publicAlias;
    }

    @JsonProperty("public_alias")
    public void setPublicAlias(String publicAlias) {
        this.publicAlias = publicAlias;
    }

    @JsonProperty("private_alias")
    public String getPrivateAlias() {
        return privateAlias;
    }

    @JsonProperty("private_alias")
    public void setPrivateAlias(String privateAlias) {
        this.privateAlias = privateAlias;
    }

    @JsonProperty("more_info")
    public String getMoreInfo() {
        return moreInfo;
    }

    @JsonProperty("more_info")
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("open_corporates_url")
    public String getOpenCorporatesUrl() {
        return openCorporatesUrl;
    }

    @JsonProperty("open_corporates_url")
    public void setOpenCorporatesUrl(String openCorporatesUrl) {
        this.openCorporatesUrl = openCorporatesUrl;
    }

    @JsonProperty("corporate_location")
    public CorporateLocation getCorporateLocation() {
        return corporateLocation;
    }

    @JsonProperty("corporate_location")
    public void setCorporateLocation(CorporateLocation corporateLocation) {
        this.corporateLocation = corporateLocation;
    }

    @JsonProperty("physical_location")
    public PhysicalLocation getPhysicalLocation() {
        return physicalLocation;
    }

    @JsonProperty("physical_location")
    public void setPhysicalLocation(PhysicalLocation physicalLocation) {
        this.physicalLocation = physicalLocation;
    }

}
