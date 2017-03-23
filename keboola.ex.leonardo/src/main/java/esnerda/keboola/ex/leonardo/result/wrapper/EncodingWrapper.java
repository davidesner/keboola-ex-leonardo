package esnerda.keboola.ex.leonardo.result.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import esnerda.keboola.ex.leonardo.api.entity.EncodingItem;

/**
 * @author David Esner
 */
public class EncodingWrapper {
	
		private Integer imageId;
	    private String type;
	    private Integer width;
	    private Integer height;
	    private String url;
	    private String encoding;

	    
	    
	    
	    public EncodingWrapper() {
		}

		public EncodingWrapper(Integer imageId, EncodingItem enc) {
			this.imageId = imageId;
			this.type = enc.getType();
			this.width = enc.getWidth();
			this.height = enc.getHeight();
			this.url = enc.getUrl();
			this.encoding = enc.getEncoding();
		}

		public static class Builder {
			public static List<EncodingWrapper> build(Integer imgId,List<EncodingItem> encodings) {
				List<EncodingWrapper> result = new ArrayList<>();
				if ( encodings == null) {
					return result;
				}
				encodings.forEach(t -> result.add(new EncodingWrapper(imgId,  t)));
				return result;
			}
		}

		public Integer getImageId() {
			return imageId;
		}

		public String getType() {
	        return type;
	    }

	    @JsonProperty("type")
	    public void setType(String type) {
	        this.type = type;
	    }

	    @JsonProperty("width")
	    public Integer getWidth() {
	        return width;
	    }

	    @JsonProperty("width")
	    public void setWidth(Integer width) {
	        this.width = width;
	    }

	    @JsonProperty("height")
	    public Integer getHeight() {
	        return height;
	    }

	    @JsonProperty("height")
	    public void setHeight(Integer height) {
	        this.height = height;
	    }

	    @JsonProperty("url")
	    public String getUrl() {
	        return url;
	    }

	    @JsonProperty("url")
	    public void setUrl(String url) {
	        this.url = url;
	    }

	    @JsonProperty("encoding")
	    public String getEncoding() {
	        return encoding;
	    }

	    @JsonProperty("encoding")
	    public void setEncoding(String encoding) {
	        this.encoding = encoding;
	    }



}
