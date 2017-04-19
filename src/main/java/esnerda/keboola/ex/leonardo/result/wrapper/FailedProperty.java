package esnerda.keboola.ex.leonardo.result.wrapper;

/**
 * @author David Esner
 */
public class FailedProperty {

	private String propertyId;
	private String cause;

	public FailedProperty() {
		// TODO Auto-generated constructor stub
	}

	public FailedProperty(String propertyId, String cause) {
		super();
		this.propertyId = propertyId;
		this.cause = cause;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public String getCause() {
		return cause;
	}

}
