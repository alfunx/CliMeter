package ch.uzh.ifi.rerg.se16_climeter.client;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import com.google.gwt.maps.client.base.LatLng;

/**
 * The class Data represents one data point of the measurements.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-01 AM Initial Commit
 * 				2016-11-07 AM Added method getRandomData()
 * 				2016-12-04 AM Added constructor to import list of strings
 * @version 	2016-12-04 AM 1.1
 * @responsibilities 
 * 				This class represents one data point. Offers method
 * 				getRandomData() for testing purposes.
 */
public class Data {

	private Date date;
	private double averageTemperature;
	private double uncertainty;
	private String city;
	private String country;
	private double latitude;
	private double longitude;

	/**
	 * Default constructor.
	 * @pre -
	 * @post -
	 */
	public Data() {
		// do nothing
	}

	/**
	 * Load a Data object from a list of string.
	 * @pre dataAsList != null && dataAsList.size >= 7
	 * @post -
	 * @param dataAsList the list containing data as strings
	 */
	public Data(List<String> dataAsList) {
		if (dataAsList != null && dataAsList.size() >= 7) {
			setDate(dataAsList.get(0));
			setAverageTemperature(dataAsList.get(1));
			setUncertainty(dataAsList.get(2));
			setCity(dataAsList.get(3));
			setCountry(dataAsList.get(4));
			setLatitude(dataAsList.get(5));
			setLongitude(dataAsList.get(6));
		}
	}

	/**
	 * Generate ArrayList with random data (only for testing purposes).
	 * @pre quantity > 0
	 * @post dataSet.size() = quantity
	 * @return an ArrayList of Data with random data
	 */
	public static ArrayList<Data> getRandomData(int quantity) {
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

		if (quantity <= 1) {
			return dataSet;
		}

		// random data-points for testing
		Random r = new Random();
		for (int i = 2; i <= quantity; i++) {
			d = new Data();
			d.setAverageTemperature((int) ((r.nextDouble() * 2 - 1) * 30));
			d.setCity("City" + i);
			d.setCountry("Country");
			d.setDate(new Date(r.nextInt(Integer.MAX_VALUE)));
			d.setLatitude((r.nextDouble() * 2 - 1) * 80);
			d.setLongitude((r.nextDouble() * 2 - 1) * 80);
			d.setUncertainty(r.nextDouble() * 20);
			dataSet.add(d);
		}

		return dataSet;
	}

	/**
	 * Converts the coordinates of a Data object into a LatLng Object.
	 * @pre -
	 * @post -
	 * @param data Data object whose coordinates are needed
	 * @return LatLng object containing the coordinates
	 */
	public LatLng getLatLng() {
		return LatLng.newInstance(this.getLatitude(), this.getLongitude());
	}

	/**
	 * @pre -
	 * @post -
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @pre -
	 * @post -
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @pre -
	 * @post -
	 * @param date the date to set
	 * @throws ParseException date must be in the format yyyy-MM-dd
	 */
	public void setDate(String date) {
		try {
			this.date = new SimpleDateFormat("yyyy-MM-dd").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			Console.log("ParseException: " + e.getMessage());
		}
	}

	/**
	 * @pre -
	 * @post -
	 * @return the averageTemperature
	 */
	public double getAverageTemperature() {
		return averageTemperature;
	}

	/**
	 * @pre -
	 * @post -
	 * @param averageTemperature the averageTemperature to set
	 */
	public void setAverageTemperature(double averageTemperature) {
		this.averageTemperature = averageTemperature;
	}

	/**
	 * @pre -
	 * @post -
	 * @param averageTemperature the averageTemperature to set
	 */
	public void setAverageTemperature(String averageTemperature) {
		this.averageTemperature = Double.parseDouble(averageTemperature);
	}

	/**
	 * @pre -
	 * @post -
	 * @return the uncertainty
	 */
	public double getUncertainty() {
		return uncertainty;
	}

	/**
	 * @pre -
	 * @post -
	 * @param uncertainty the uncertainty to set
	 */
	public void setUncertainty(double uncertainty) {
		this.uncertainty = uncertainty;
	}

	/**
	 * @pre -
	 * @post -
	 * @param uncertainty the uncertainty to set
	 */
	public void setUncertainty(String uncertainty) {
		this.uncertainty = Double.parseDouble(uncertainty);
	}

	/**
	 * @pre -
	 * @post -
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @pre -
	 * @post -
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @pre -
	 * @post -
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @pre -
	 * @post -
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @pre -
	 * @post -
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @pre -
	 * @post -
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @pre latitude.endsWith("S") || latitude.endsWith("N")
	 * @post -
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		if (latitude.endsWith("S")) {
			this.latitude = Double.parseDouble(latitude.substring(0, latitude.length() - 1)) * -1;
		} else {
			this.latitude = Double.parseDouble(latitude.substring(0, latitude.length() - 1));
		}
	}

	/**
	 * @pre -
	 * @post -
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @pre -
	 * @post -
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @pre longitude.endsWith("W") || longitude.endsWith("E")
	 * @post -
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		if (longitude.endsWith("W")) {
			this.longitude = Double.parseDouble(longitude.substring(0, longitude.length() - 1)) * -1;
		} else {
			this.longitude = Double.parseDouble(longitude.substring(0, longitude.length() - 1));
		}
	}

}
