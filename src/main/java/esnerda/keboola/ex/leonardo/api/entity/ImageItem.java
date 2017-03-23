
package esnerda.keboola.ex.leonardo.api.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "updateTimestamp",
    "propertyId",
    "category",
    "inDistribution",
    "sourceFilename",
    "metadata",
    "encodings",
    "status",
    "provider",
    "imageGroupId",
    "bestImage",
    "_links"
})
public class ImageItem {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("updateTimestamp")
    private String updateTimestamp;
    @JsonProperty("propertyId")
    private String propertyId;
    @JsonProperty("category")
    private String category;
    @JsonProperty("inDistribution")
    private Boolean inDistribution;
    @JsonProperty("sourceFilename")
    private String sourceFilename;
    @JsonProperty("metadata")
    private List<Metadatum> metadata = null;
    @JsonProperty("encodings")
    private Encodings encodings;
    @JsonProperty("status")
    private String status;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("imageGroupId")
    private Integer imageGroupId;
    @JsonProperty("bestImage")
    private Boolean bestImage;
    @JsonProperty("_links")
    private Links links;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("updateTimestamp")
    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    @JsonProperty("updateTimestamp")
    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    @JsonProperty("propertyId")
    public String getPropertyId() {
        return propertyId;
    }

    @JsonProperty("propertyId")
    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    @JsonProperty("category")
    public String getCategory() {
        return category;
    }

    @JsonProperty("category")
    public void setCategory(String category) {
        this.category = category;
    }

    @JsonProperty("inDistribution")
    public Boolean getInDistribution() {
        return inDistribution;
    }

    @JsonProperty("inDistribution")
    public void setInDistribution(Boolean inDistribution) {
        this.inDistribution = inDistribution;
    }

    @JsonProperty("sourceFilename")
    public String getSourceFilename() {
        return sourceFilename;
    }

    @JsonProperty("sourceFilename")
    public void setSourceFilename(String sourceFilename) {
        this.sourceFilename = sourceFilename;
    }

    @JsonProperty("metadata")
    public List<Metadatum> getMetadata() {
        return metadata;
    }

    @JsonProperty("metadata")
    public void setMetadata(List<Metadatum> metadata) {
        this.metadata = metadata;
    }

    @JsonProperty("encodings")
    public Encodings getEncodings() {
        return encodings;
    }

    @JsonProperty("encodings")
    public void setEncodings(Encodings encodings) {
        this.encodings = encodings;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("provider")
    public String getProvider() {
        return provider;
    }

    @JsonProperty("provider")
    public void setProvider(String provider) {
        this.provider = provider;
    }

    @JsonProperty("imageGroupId")
    public Integer getImageGroupId() {
        return imageGroupId;
    }

    @JsonProperty("imageGroupId")
    public void setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
    }

    @JsonProperty("bestImage")
    public Boolean getBestImage() {
        return bestImage;
    }

    @JsonProperty("bestImage")
    public void setBestImage(Boolean bestImage) {
        this.bestImage = bestImage;
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
