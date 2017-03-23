package esnerda.keboola.ex.leonardo.result.wrapper;

import esnerda.keboola.ex.leonardo.api.entity.PropertyEntity;

/**
 * @author David Esner
 */
public class PropertyEntityWrapper {

	private String propertyId;
	private String propertyName;
	private String brand;
	// address
	private String addressLine1;
	private String addressLine2;
	private String city;
	private String state;
	private String country;
	private String zip;
	// coords
	private String latitude;
	private String longitude;
	private String fax;
	private String phone;

	// codes
	private String pegasusCode;
	private String sabreCode;
	private String amadeusCode;
	private String galileoCode;
	private String mediaUpdateTimestamp;
	private String status;

	
	public PropertyEntityWrapper() {
		// TODO Auto-generated constructor stub
	}

	public PropertyEntityWrapper(PropertyEntity ent) {
		super();
		this.propertyId = ent.getPropertyId();
		this.propertyName = ent.getPropertyName();
		this.brand = ent.getBrand();
		if (ent.getAddress() != null) {
			this.addressLine1 = ent.getAddress().getAddressLine1();
			this.addressLine2 = ent.getAddress().getAddressLine2();
			this.city = ent.getAddress().getCity();
			this.state = ent.getAddress().getState();
			this.country = ent.getAddress().getCountry();
			this.zip = ent.getAddress().getZip();
		}
		if (ent.getCoordinates() != null) {
			this.latitude = ent.getCoordinates().getLatitude();
			this.longitude = ent.getCoordinates().getLongitude();
		}
		if (ent.getPhoneNumbers() != null) {
			this.fax = ent.getPhoneNumbers().getFax();
			this.phone = ent.getPhoneNumbers().getPhone();
		}
		if (ent.getCodes() != null) {
			this.pegasusCode = ent.getCodes().getPegasusCode();
			this.sabreCode = ent.getCodes().getSabreCode();
			this.amadeusCode = ent.getCodes().getAmadeusCode();
			this.galileoCode = ent.getCodes().getGalileoCode();
		}
		this.mediaUpdateTimestamp = ent.getMediaUpdateTimestamp();
		this.status = ent.getStatus();
	}

	public String getPropertyId() {
		return propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public String getBrand() {
		return brand;
	}

	public String getAddressLine1() {
		return addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getCountry() {
		return country;
	}

	public String getZip() {
		return zip;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getFax() {
		return fax;
	}

	public String getPhone() {
		return phone;
	}

	public String getPegasusCode() {
		return pegasusCode;
	}

	public String getSabreCode() {
		return sabreCode;
	}

	public String getAmadeusCode() {
		return amadeusCode;
	}

	public String getGalileoCode() {
		return galileoCode;
	}

	public String getMediaUpdateTimestamp() {
		return mediaUpdateTimestamp;
	}

	public String getStatus() {
		return status;
	}

}
