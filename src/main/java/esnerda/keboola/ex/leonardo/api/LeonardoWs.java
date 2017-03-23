package esnerda.keboola.ex.leonardo.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import esnerda.keboola.ex.leonardo.api.entity.ImageItem;
import esnerda.keboola.ex.leonardo.api.entity.ImageItemsEntity;
import esnerda.keboola.ex.leonardo.api.entity.PropertyEntity;

/**
 * @author David Esner
 */
public class LeonardoWs {
	
	private LeonardoApiRestClient client;
	
	public LeonardoWs(String apiKey, String endpointUrl) {
		this.client  = new LeonardoApiRestClient(apiKey, endpointUrl);
	}

	public PropertyEntity getProperty(String propertyId) {
		Response res = client.sendGetRequest("properties/" + propertyId, null);
		return res.readEntity(PropertyEntity.class);
	}

	public List<ImageItem> getPropertyImages(String propertyId, List<String> encodings, String expand) {
		Map<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(expand)) {
			params.put("expand", expand);
		}
		if (encodings != null && !encodings.isEmpty()) {
			params.put("encodings", expand);
		}
		Response res = client.sendGetRequest("properties/" + propertyId + "/images", params);
		ImageItemsEntity images =  res.readEntity(ImageItemsEntity.class);
		
		return Optional.ofNullable(images).map(ImageItemsEntity::getItems).orElse(Collections.EMPTY_LIST);
	}
}
