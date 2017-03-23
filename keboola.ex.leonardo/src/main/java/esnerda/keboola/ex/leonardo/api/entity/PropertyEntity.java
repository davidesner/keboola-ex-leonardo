
package esnerda.keboola.ex.leonardo.api.entity;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "propertyId",
    "propertyName",
    "brand",
    "address",
    "coordinates",
    "phoneNumbers",
    "codes",
    "updateTimestamp",
    "mediaUpdateTimestamp",
    "status",
    "_links"
})
public class PropertyEntity {

    @JsonProperty("propertyId")
    private String propertyId;
    @JsonProperty("propertyName")
    private String propertyName;
    @JsonProperty("brand")
    private String brand;
    @JsonProperty("address")
    private Address address;
    @JsonProperty("coordinates")
    private Coordinates coordinates;
    @JsonProperty("phoneNumbers")
    private PhoneNumbers phoneNumbers;
    @JsonProperty("codes")
    private Codes codes;
    @JsonProperty("updateTimestamp")
    private String updateTimestamp;
    @JsonProperty("mediaUpdateTimestamp")
    private String mediaUpdateTimestamp;
    @JsonProperty("status")
    private String status;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("propertyId")
    public String getPropertyId() {
        return propertyId;
    }

    @JsonProperty("propertyId")
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @JsonProperty("propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    @JsonProperty("propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @JsonProperty("brand")
    public String getBrand() {
        return brand;
    }

    @JsonProperty("brand")
    public void setBrand(String brand) {
        this.brand = brand;
    }

    @JsonProperty("address")
    public Address getAddress() {
        return address;
    }

    @JsonProperty("address")
    public void setAddress(Address address) {
        this.address = address;
    }

    @JsonProperty("coordinates")
    public Coordinates getCoordinates() {
        return coordinates;
    }

    @JsonProperty("coordinates")
    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    @JsonProperty("phoneNumbers")
    public PhoneNumbers getPhoneNumbers() {
        return phoneNumbers;
    }

    @JsonProperty("phoneNumbers")
    public void setPhoneNumbers(PhoneNumbers phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @JsonProperty("codes")
    public Codes getCodes() {
        return codes;
    }

    @JsonProperty("codes")
    public void setCodes(Codes codes) {
        this.codes = codes;
    }

    @JsonProperty("updateTimestamp")
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonProperty("updateTimestamp")
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @JsonProperty("mediaUpdateTimestamp")
    public String getMediaUpdateTimestamp() {
        return mediaUpdateTimestamp;
    }

    @JsonProperty("mediaUpdateTimestamp")
    public void setMediaUpdateTimestamp(String mediaUpdateTimestamp) {
        this.mediaUpdateTimestamp = mediaUpdateTimestamp;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("_links")
    public Links getLinks() {
        return links;
    }

    @JsonProperty("_links")
    public void setLinks(Links links) {
        this.links = links;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
