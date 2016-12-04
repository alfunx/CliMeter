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

	/* (non-Javadoc)
	 * Generated by eclipse.
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beginDate == null) ? 0 : beginDate.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		long temp;
		temp = Double.doubleToLongBits(maxUncertainty);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * Generated by eclipse.
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Filter other = (Filter) obj;
		if (beginDate == null) {
			if (other.beginDate != null)
				return false;
		} else if (!beginDate.equals(other.beginDate))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (Double.doubleToLongBits(maxUncertainty) != Double.doubleToLongBits(other.maxUncertainty))
			return false;
		return true;
	}

}
