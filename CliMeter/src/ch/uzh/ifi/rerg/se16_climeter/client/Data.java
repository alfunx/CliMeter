package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class Data {
	
	private Date date;
	private double averageTemperature;
	private double uncertainty;
	private String city;
	private String country;
	private double latitude;
	private double longitude;
	
	/**
	 * @return an ArrayList<Data> with random data
	 */
	public static ArrayList<Data> getRandomData() {
		ArrayList<Data> dataSet = new ArrayList<Data>();
		
		// one data-point in zurich
		Data d = new Data();
		d.setAverageTemperature(88.888);
		d.setCity("Zurich");
		d.setCountry("Switzerland");
		d.setDate(new Date());
		d.setLatitude(47.37174);
		d.setLongitude(8.54226);
		d.setUncertainty(1.5);
		dataSet.add(d);
		
		// 100 random data-points for testing
		Random r = new Random();
		for(int i = 0; i < 100; i++) {
			d = new Data();
			d.setAverageTemperature((int) ((r.nextDouble() * 2 - 1) * 30));
			d.setCity("City");
			d.setCountry("Country");
			d.setDate(new Date(r.nextLong()));
			d.setLatitude((r.nextDouble() * 2 - 1) * 100);
			d.setLongitude((r.nextDouble() * 2 - 1) * 100);
			d.setUncertainty(r.nextDouble() * 10);
			dataSet.add(d);
		}
		
		return dataSet;
	}
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	/**
	 * @return the averageTemperature
	 */
	public double getAverageTemperature() {
		return averageTemperature;
	}
	
	/**
	 * @param averageTemperature the averageTemperature to set
	 */
	public void setAverageTemperature(double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}
	
	/**
	 * @return the uncertainty
	 */
	public double getUncertainty() {
		return uncertainty;
	}
	
	/**
	 * @param uncertainty the uncertainty to set
	 */
	public void setUncertainty(double uncertainty) {
		this.uncertainty = uncertainty;
	}
	
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}
	
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}
	
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
}
