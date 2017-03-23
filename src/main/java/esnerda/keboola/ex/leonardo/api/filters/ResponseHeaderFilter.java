package esnerda.keboola.ex.leonardo.api.filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;

/**
 * @author David Esner
 */
public class ResponseHeaderFilter implements ClientResponseFilter {
	 
	@Override
	public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
		 if (responseContext.getHeaders().get("X-Rate-Limit-Remaining") != null) {
	            int remaining = Integer.parseInt(responseContext.getHeaders().get("X-Rate-Limit-Remaining").get(0));
	         }
		
	}
}