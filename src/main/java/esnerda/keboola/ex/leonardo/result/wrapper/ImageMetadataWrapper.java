package esnerda.keboola.ex.leonardo.result.wrapper;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import esnerda.keboola.ex.leonardo.api.entity.Metadatum;

/**
 * @author David Esner
 */
public class ImageMetadataWrapper {
	
		private Integer imageId;
	    private String languageCode;
	    private String shortDescription;
	    private String longDescription;
	    private String caption;

	    
	    
	    
	    public ImageMetadataWrapper() {
		}

		public ImageMetadataWrapper(Integer imageId, Metadatum enc) {
			this.imageId = imageId;
			this.languageCode = enc.getLanguageCode();
			this.shortDescription = enc.getShortDescription();
			this.longDescription = enc.getLongDescription();
			this.caption = enc.getCaption();
		}

		public static class Builder {
			public static List<ImageMetadataWrapper> build(Integer imgId,List<Metadatum> encodings) {
				List<ImageMetadataWrapper> result = new ArrayList<>();
				if ( encodings == null) {
					return result;
				}
				encodings.forEach(t -> result.add(new ImageMetadataWrapper(imgId,  t)));
				return result;
			}
		}

		public Integer getImageId() {
			return imageId;
		}

	    @JsonProperty("languageCode")
	    public String getLanguageCode() {
	        return languageCode;
	    }

	    @JsonProperty("shortDescription")
	    public String getShortDescription() {
	        return shortDescription;
	    }

	    @JsonProperty("longDescription")
	    public String getLongDescription() {
	        return longDescription;
	    }

	    @JsonProperty("caption")
	    public String getCaption() {
	        return caption;
	    }



}
