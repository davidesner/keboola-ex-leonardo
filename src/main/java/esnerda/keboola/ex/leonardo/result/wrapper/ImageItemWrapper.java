package esnerda.keboola.ex.leonardo.result.wrapper;

import java.util.List;

import esnerda.keboola.ex.leonardo.api.entity.ImageItem;
import esnerda.keboola.ex.leonardo.api.entity.Metadatum;

/**
 * @author David Esner
 */
public class ImageItemWrapper {
  
    private Integer id;
    private String updateTimestamp;
    private String propertyId;
    private String category;
    private Boolean inDistribution;
    private String sourceFilename;
    private List<Metadatum> metadata = null;
    
    private String status;
    private String provider;
    private Integer imageGroupId;
    private Boolean bestImage;
   
    

	public ImageItemWrapper() {
		// TODO Auto-generated constructor stub
	}

	public ImageItemWrapper(ImageItem img) {
		this.id = img.getId();
		this.updateTimestamp = img.getUpdateTimestamp();
		this.propertyId = img.getPropertyId();
		this.category = img.getCategory();
		this.inDistribution = img.getInDistribution();
		this.sourceFilename = img.getSourceFilename();
		this.metadata = img.getMetadata();
		this.status = img.getStatus();
		this.provider = img.getProvider();
		this.imageGroupId = img.getImageGroupId();
		this.bestImage = img.getBestImage();
	}

	public Integer getId() {
		return id;
	}

	public String getUpdateTimestamp() {
		return updateTimestamp;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public String getCategory() {
		return category;
	}

	public Boolean getInDistribution() {
		return inDistribution;
	}

	public String getSourceFilename() {
		return sourceFilename;
	}

	public List<Metadatum> getMetadata() {
		return metadata;
	}

	public String getStatus() {
		return status;
	}

	public String getProvider() {
		return provider;
	}

	public Integer getImageGroupId() {
		return imageGroupId;
	}

	public Boolean getBestImage() {
		return bestImage;
	}
    
    
}
