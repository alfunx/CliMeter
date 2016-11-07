package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;
import java.util.List;
import java.util.Observable;

public class Filter extends Observable {
	
	private Date beginDate;
	private Date endDate;
	private double maxCertainty;
	private List<String> countries;
	private List<String> cities;
	
	/*
	 *  TODO: implement observer - pattern
	 */
	
	/**
	 * @return the beginDate
	 */
	public Date getBeginDate() {
		return beginDate;
	}
	
	/**
	 * @param beginDate the beginDate to set
	 */
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}
	
	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	/**
	 * @return the maxCertainty
	 */
	public double getMaxCertainty() {
		return maxCertainty;
	}
	
	/**
	 * @param maxCertainty the maxCertainty to set
	 */
	public void setMaxCertainty(double maxCertainty) {
		this.maxCertainty = maxCertainty;
	}
	
	/**
	 * @return the countries
	 */
	public List<String> getCountries() {
		return countries;
	}
	
	/**
	 * @param countries the countries to set
	 */
	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
	
	/**
	 * @return the cities
	 */
	public List<String> getCities() {
		return cities;
	}
	
	/**
	 * @param cities the cities to set
	 */
	public void setCities(List<String> cities) {
		this.cities = cities;
	}
	
}
