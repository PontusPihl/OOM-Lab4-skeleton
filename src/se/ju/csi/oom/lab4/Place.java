package se.ju.csi.oom.lab4;

/**
 * This is a simple data class representing a named place with a latitude, longitude, and altitude.
 * Per this model, places do not have any spatial _extent_ -- they are simply points.
 * @author Karl Hammar
 */
public class Place {
	
	// Constructor
	public Place(String name, Double latitude, Double longitude, Double altitiude) {
		super();
		this.name = name;
		this.latitude = latitude;
		this.longitude = longitude;
		this.altitiude = altitiude;
	}
	
	// Private fields
	private String name;
	private Double latitude;
	private Double longitude;
	private Double altitiude;
	
	// Getters
	public String getName() {
		return name;
	}
	public Double getLatitude() {
		return latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public Double getAltitiude() {
		return altitiude;
	}
	
	// Setters
	public void setName(String name) {
		this.name = name;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public void setAltitiude(Double altitiude) {
		this.altitiude = altitiude;
	}
}
