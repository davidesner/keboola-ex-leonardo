
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
    "pegasusCode",
    "sabreCode",
    "amadeusCode",
    "galileoCode",
    "worldspanCode"
})
public class Codes {

    @JsonProperty("pegasusCode")
    private String pegasusCode;
    @JsonProperty("sabreCode")
    private String sabreCode;
    @JsonProperty("amadeusCode")
    private String amadeusCode;
    @JsonProperty("galileoCode")
    private String galileoCode;
    @JsonProperty("worldspanCode")
    private String worldspanCode;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("pegasusCode")
    public String getPegasusCode() {
        return pegasusCode;
    }

    @JsonProperty("pegasusCode")
    public void setPegasusCode(String pegasusCode) {
        this.pegasusCode = pegasusCode;
    }

    @JsonProperty("sabreCode")
    public String getSabreCode() {
        return sabreCode;
    }

    @JsonProperty("sabreCode")
    public void setSabreCode(String sabreCode) {
        this.sabreCode = sabreCode;
    }

    @JsonProperty("amadeusCode")
    public String getAmadeusCode() {
        return amadeusCode;
    }

    @JsonProperty("amadeusCode")
    public void setAmadeusCode(String amadeusCode) {
        this.amadeusCode = amadeusCode;
    }

    @JsonProperty("galileoCode")
    public String getGalileoCode() {
        return galileoCode;
    }

    @JsonProperty("galileoCode")
    public void setGalileoCode(String galileoCode) {
        this.galileoCode = galileoCode;
    }

    @JsonProperty("worldspanCode")
    public String getWorldspanCode() {
        return worldspanCode;
    }

    @JsonProperty("worldspanCode")
    public void setWorldspanCode(String worldspanCode) {
        this.worldspanCode = worldspanCode;
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
