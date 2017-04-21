package esnerda.keboola.ex.leonardo.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Logger;

import esnerda.keboola.ex.leonardo.api.entity.ImageItem;
import esnerda.keboola.ex.leonardo.api.entity.ImageItemsEntity;
import esnerda.keboola.ex.leonardo.api.entity.PropertyEntity;
import esnerda.keboola.ex.leonardo.api.filters.ErrorResponseFilter.RatelimitExceededException;
import esnerda.keboola.ex.leonardo.util.SimpleTimer;

/**
 * @author David Esner
 */
public class LeonardoWs {
	
	private final static long TIMEOUT = 3600000L;
	private static final long BACK_OFF_INTERVAL = 120000L;

	private LeonardoApiRestClient client;
	private final Logger log;
	private long startTime;
	
	public LeonardoWs(String apiKey, String endpointUrl, Logger log) {
		this.client  = new LeonardoApiRestClient(apiKey, endpointUrl);
		this.log = log;
	}

	public PropertyEntity getProperty(String propertyId) {
		startTimer();
		Response res = null;
		while (res == null && !isTimedOut()) {
			try {
				res = client.sendGetRequest("properties/" + propertyId, null);
			} catch (RatelimitExceededException ex) {
				if (isTimedOut()) {
					throw ex;
				}
				log.warn("Rate limit exceeded, waiting to restore...");
				waitNmilis(BACK_OFF_INTERVAL);
			}
		}
		return res != null ? res.readEntity(PropertyEntity.class) : null;
	}

	public List<ImageItem> getPropertyImages(String propertyId, List<String> encodings, String expand) {
		startTimer();
		Map<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(expand)) {
			params.put("expand", expand);
		}
		if (encodings != null && !encodings.isEmpty()) {
			params.put("encodings", expand);
		}
		Response res = null;
		while (res == null && !isTimedOut()) {
			try {
				res = client.sendGetRequest("properties/" + propertyId + "/images", params);
			} catch (RatelimitExceededException ex) {
				if (isTimedOut()) {
					throw ex;
				}
				log.warn("Rate limit exceeded, waiting to restore...");
				waitNmilis(BACK_OFF_INTERVAL);
			}
		}
		if (res == null) {
			return null;
		}
		ImageItemsEntity images =  res.readEntity(ImageItemsEntity.class);
		
		return Optional.ofNullable(images).map(ImageItemsEntity::getItems).orElse(Collections.EMPTY_LIST);
	}

	/* -- time counter methods -- */
	private void startTimer() {
		startTime = System.currentTimeMillis();
	}

	private boolean isTimedOut() {
		long elapsed = System.currentTimeMillis() - startTime;
		return elapsed >= TIMEOUT;
	}

	private void waitNmilis(long interval) {
		try {
			SimpleTimer.reallySleep(interval);
		} catch (RuntimeException ex) {
			log.warn("Thread sleep failed " + ex.getMessage());

		}
	}
}
