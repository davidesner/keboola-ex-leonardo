
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
    "self",
    "images",
    "image-sequence",
    "roomtypes"
})
public class Links {

    @JsonProperty("self")
    private Self self;
    @JsonProperty("images")
    private Images images;
    @JsonProperty("image-sequence")
    private ImageSequence imageSequence;
    @JsonProperty("roomtypes")
    private Roomtypes roomtypes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("self")
    public Self getSelf() {
        return self;
    }

    @JsonProperty("self")
    public void setSelf(Self self) {
        this.self = self;
    }

    @JsonProperty("images")
    public Images getImages() {
        return images;
    }

    @JsonProperty("images")
    public void setImages(Images images) {
        this.images = images;
    }

    @JsonProperty("image-sequence")
    public ImageSequence getImageSequence() {
        return imageSequence;
    }

    @JsonProperty("image-sequence")
    public void setImageSequence(ImageSequence imageSequence) {
        this.imageSequence = imageSequence;
    }

    @JsonProperty("roomtypes")
    public Roomtypes getRoomtypes() {
        return roomtypes;
    }

    @JsonProperty("roomtypes")
    public void setRoomtypes(Roomtypes roomtypes) {
        this.roomtypes = roomtypes;
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
