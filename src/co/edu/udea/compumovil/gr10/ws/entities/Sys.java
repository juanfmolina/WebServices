package co.edu.udea.compumovil.gr10.ws.entities;

public class Sys {
	private int type;
	private int id;
	private float message;
	private String country;
	private double sunrise;
	private double sunset;

	public float getMessage() {
		return message;
	}

	public void setMessage(float message) {
		this.message = message;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getSunrise() {
		return sunrise;
	}

	public void setSunrise(double sunrise) {
		this.sunrise = sunrise;
	}

	public double getSunset() {
		return sunset;
	}

	public void setSunset(double sunset) {
		this.sunset = sunset;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
