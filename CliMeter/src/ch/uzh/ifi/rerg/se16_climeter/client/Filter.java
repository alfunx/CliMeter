package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;

/**
 * The class Filter contains all filtering criteria.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial Commit
 * 				2016-12-04 AM Adjustments and added equals()
 * @version 	2016-11-02 AM 1.1
 * @responsibilities 
 * 				This class contains filtering criteria.
 */
public class Filter {

	private Date beginDate;
	private Date endDate;
	private double maxUncertainty;
	private String country;
	private String city;

	/**
	 * Checks if a given filter is equal to this object.
	 * @param filter the filter to compare
	 * @return if the filter is equal
	 */
	public boolean equals(Filter filter) {
		if (filter == null || 
				!this.beginDate.equals(filter.getBeginDate()) || 
				!this.endDate.equals(filter.getEndDate()) || 
				this.maxUncertainty != filter.getMaxUncertainty() || 
				!this.country.equals(filter.getCountry()) || 
				!this.city.equals(filter.getCity())) {
			return false;
		}

		return true;
	}

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
	public double getMaxUncertainty() {
		return maxUncertainty;
	}

	/**
	 * @pre -
	 * @post -
	 * @return the maxCertainty
	 */
	public float getMaxUncertaintyFloat() {
		return (float) maxUncertainty;
	}

	/**
	 * @pre -
	 * @post -
	 * @param maxCertainty the maxCertainty to set
	 */
	public void setMaxUncertainty(double maxCertainty) {
		this.maxUncertainty = maxCertainty;
	}

	/**
	 * @pre -
	 * @post -
	 * @return the countries
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @pre -
	 * @post -
	 * @param countries the countries to set
	 */
	public void setCountry(String countries) {
		this.country = countries;
	}

	/**
	 * @pre -
	 * @post -
	 * @return the cities
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @pre -
	 * @post -
	 * @param cities the cities to set
	 */
	public void setCity(String cities) {
		this.city = cities;
	}

}
