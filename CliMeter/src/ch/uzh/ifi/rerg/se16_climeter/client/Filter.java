package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;
import java.util.List;

/**
 * The class Filter contains all filtering criteria.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial Commit
 * @version 	2016-11-02 AM 1.0
 * @responsibilities 
 * 				This class contains filtering criteria.
 */
public class Filter {
	
	private Date beginDate;
	private Date endDate;
	private double maxCertainty;
	private List<String> countries;
	private List<String> cities;
	
	/**
	 * @pre -
	 * @post -
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the maxCertainty
	 */
	public double getMaxCertainty() {
		return maxCertainty;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param maxCertainty the maxCertainty to set
	 */
	public void setMaxCertainty(double maxCertainty) {
		this.maxCertainty = maxCertainty;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the cities
	 */
	public List<String> getCities() {
		return cities;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param cities the cities to set
	 */
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
}
