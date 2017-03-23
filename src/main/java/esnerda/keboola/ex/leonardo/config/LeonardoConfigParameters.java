package esnerda.keboola.ex.leonardo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import esnerda.keboola.components.configuration.IKBCParameters;
import esnerda.keboola.components.configuration.ValidationException;

/**
 * @author David Esner
 */
public class LeonardoConfigParameters extends IKBCParameters {
	private final static String[] REQUIRED_FIELDS = { "#apikey" };
	private final Map<String, Object> parametersMap;

	private final String DEFAULT_ENDPOINT = "";

	/* auth */
	@JsonProperty("#apikey")
	private String apikey;

	@JsonProperty("endpointUrl")
	private String endpointUrl;

	@JsonProperty("showDuplicates")
	private Boolean showDuplicates;

	@JsonProperty("showAllRoomtypes")
	private Boolean showAllRoomtypes;

	@JsonProperty("incremental")
	private Boolean incremental;

	@JsonProperty("getEntInfo")
	private Boolean getEntInfo;

	@JsonProperty("debug")
	private Boolean debug;

	@JsonCreator
	public LeonardoConfigParameters(@JsonProperty("#apiKey") String apikey,
			@JsonProperty("endpointUrl") String endpointUrl, @JsonProperty("showDuplicates") Boolean showDuplicates,
			@JsonProperty("showAllRoomtypes") Boolean showAllRoomtypes, @JsonProperty("getEntInfo") Boolean getEntInfo,
			@JsonProperty("incremental") Boolean incremental, @JsonProperty("debug") Boolean debug) {
		this.apikey = apikey;
		this.showDuplicates = Optional.ofNullable(showDuplicates).orElse(false);
		this.showAllRoomtypes = Optional.ofNullable(showAllRoomtypes).orElse(false);
		this.endpointUrl = Optional.ofNullable(endpointUrl).orElse(DEFAULT_ENDPOINT);
		this.incremental = Optional.ofNullable(incremental).orElse(true);
		this.debug = Optional.ofNullable(debug).orElse(false);

		// set param map
		parametersMap = new HashMap<>();
		parametersMap.put("#apikey", apikey);
	}

	@Override
	protected String[] getRequiredFields() {
		return REQUIRED_FIELDS;
	}

	@Override
	protected boolean validateParametres() throws ValidationException {
		// validate date format
		String error = "";

		error += this.missingFieldsMessage(parametersMap);

		if (error.equals("")) {
			return true;
		} else {
			throw new ValidationException("Invalid configuration parameters!", "Config validation error: " + error,
					null);
		}
	}

	public Boolean getShowAllRoomtypes() {
		return showAllRoomtypes;
	}

	public String getApikey() {
		return apikey;
	}

	public Boolean getShowDuplicates() {
		return showDuplicates;
	}

	public Boolean getDebug() {
		return debug;
	}

	public String getEndpointUrl() {
		return endpointUrl;
	}

	public Boolean getIncremental() {
		return incremental;
	}

	public Boolean getGetEntInfo() {
		return getEntInfo;
	}

	/* getters */

}
