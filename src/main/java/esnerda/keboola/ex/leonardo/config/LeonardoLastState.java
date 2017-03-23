package esnerda.keboola.ex.leonardo.config;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import esnerda.keboola.components.appstate.LastState;

/**
 * @author David Esner
 */
public class LeonardoLastState implements LastState {

   @JsonProperty("processedIds")
   private List<String> processedIds;

public LeonardoLastState(List<String> processedIds) {
	super();
	this.processedIds = processedIds;
}

public LeonardoLastState() {	
}

public List<String> getProcessedIds() {
	if (processedIds == null) {
		return Collections.EMPTY_LIST;
	}
	return processedIds;
}

public void setProcessedIds(List<String> processedIds) {
	this.processedIds = processedIds;
}





}