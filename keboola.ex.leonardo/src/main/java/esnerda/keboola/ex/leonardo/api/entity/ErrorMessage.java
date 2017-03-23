
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
    "message",
    "httpStatusMsg",
    "errorDetails"
})
public class ErrorMessage {

    @JsonProperty("message")
    private String message;
    @JsonProperty("httpStatusMsg")
    private String httpStatusMsg;
    @JsonProperty("errorDetails")
    private List<ErrorDetail> errorDetails = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    @JsonProperty("httpStatusMsg")
    public String getHttpStatusMsg() {
        return httpStatusMsg;
    }

    @JsonProperty("httpStatusMsg")
    public void setHttpStatusMsg(String httpStatusMsg) {
        this.httpStatusMsg = httpStatusMsg;
    }

    @JsonProperty("errorDetails")
    public List<ErrorDetail> getErrorDetails() {
        return errorDetails;
    }

    @JsonProperty("errorDetails")
    public void setErrorDetails(List<ErrorDetail> errorDetails) {
        this.errorDetails = errorDetails;
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
