package esnerda.keboola.ex.leonardo.api;

import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.logging.LoggingFeature;
import org.glassfish.jersey.logging.LoggingFeature.Verbosity;

import esnerda.keboola.ex.leonardo.api.filters.ErrorResponseFilter;

/**
 * @author David Esner
 */
public class LeonardoApiRestClient{
	
	private static final long RATE_LIMIT_TIME_WINDOW_LENGTH = 3600000L;
	private final String endpointUrl;
	private final Client client;
	private final String apiKey;
	private Integer reqRemaining = null;
	private int currentReqLimit = 0;
	
	public LeonardoApiRestClient (String apiKey, String endpointUrl) {
		this.endpointUrl = endpointUrl;
		this.apiKey = apiKey;
		this.client = ClientBuilder.newClient(
				new ClientConfig().register(new LoggingFeature(Logger.getAnonymousLogger(), Verbosity.HEADERS_ONLY))
						.register(ErrorResponseFilter.class));
	}

	public Response sendGetRequest(String path, Map<String, String> params) {
		waitBeforeNext();
		WebTarget paramTarget = setParams(buildWebTarget(path), params);
		
		Response resp =  prepareTargetBuilder(paramTarget).get();	
		setRateLimitValues(resp);
		return resp;
	}

	public Response sendPostRequest(String path, Object entity) throws Exception {		
		waitBeforeNext();
		Response response = prepareTargetBuilder(buildWebTarget(path))
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
				.post(Entity.entity(entity, MediaType.APPLICATION_JSON));
		setRateLimitValues(response);
		return response;
	}

	protected Builder prepareTargetBuilder(WebTarget target) {
		return target.request(MediaType.APPLICATION_JSON)
		.header("api_key",apiKey);
	}

	protected WebTarget buildWebTarget(String path){
		return client.target(endpointUrl).path(path);
	}
	protected WebTarget setParams(WebTarget webTarget, Map<String, String> params) {
		if (params != null) {
			for (Entry<String, String> param : params.entrySet()) {
				webTarget = webTarget.queryParam(param.getKey(), param.getValue());
			}
		}
		return webTarget;
	}

	private void setRateLimitValues(Response resp) {
		if (resp.getHeaders().get("X-Rate-Limit-Remaining") != null) {
            reqRemaining = Integer.parseInt((String)resp.getHeaders().get("X-Rate-Limit-Remaining").get(0));
         }
		if (resp.getHeaders().get("X-Rate-Limit-Limit") != null) {
			currentReqLimit = Integer.parseInt((String)resp.getHeaders().get("X-Rate-Limit-Limit").get(0));
		}
	}
	protected void waitBeforeNext() {
		if (reqRemaining == null || currentReqLimit == 0) {
			return;
		}
		waitNmilis((RATE_LIMIT_TIME_WINDOW_LENGTH/currentReqLimit));		
	}

	private void waitNmilis(long interval) {
		try {

			Thread.sleep(interval);
		} catch (InterruptedException | RuntimeException ex) {
			Logger.getLogger(getClass().getName()).warning("Thread sleep failed " + ex.getMessage());

		}
	}

}
