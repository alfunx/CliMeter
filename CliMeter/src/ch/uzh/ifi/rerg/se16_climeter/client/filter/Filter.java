package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * The class Filter contains all filtering criteria.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-02 AM Initial commit
 * 				2016-12-04 AM Adjustments and added equals()
 * @version 	2016-12-04 AM 1.1
 * @responsibilities 
 * 				This class contains filtering criteria.
 */
public class Filter implements IsSerializable {

	private Date beginDate;
	private String beginDateString;
	private Date endDate;
	private String endDateString;
	private float maxUncertainty = Float.MAX_VALUE;
	private String country;
	private String city;
	private boolean groupByYear = true;

	/**
	 * Default constructor.
	 * @pre -
	 * @post -
	 */
	public Filter() {
		// GWT needs this
	}

	/**
	 * @pre -
	 * @post -
	 * @return the beginDateString
	 */
	@SuppressWarnings("deprecation")
	public int getYear() {
		return beginDate.getYear() + 1900;
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
		beginDateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(beginDate);
	}

	/**
	 * @pre -
	 * @post -
	 * @return the beginDateString
	 */
	public String getBeginDateString() {
		return beginDateString;
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
		endDateString = DateTimeFormat.getFormat("yyyy-MM-dd").format(endDate);
	}

	/**
	 * @pre -
	 * @post -
	 * @return the endDate
	 */
	public String getEndDateString() {
		return endDateString;
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
	public void setMaxUncertainty(float maxCertainty) {
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

	/**
	 * @pre -
	 * @post -
	 * @return the groupByYear
	 */
	public boolean isGroupByYear() {
		return groupByYear;
	}

	/**
	 * @pre -
	 * @post -
	 * @param groupByYear the groupByYear to set
	 */
	public void setGroupByYear(boolean groupByYear) {
		this.groupByYear = groupByYear;
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
		result = prime * result + ((beginDateString == null) ? 0 : beginDateString.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((endDateString == null) ? 0 : endDateString.hashCode());
		result = prime * result + (groupByYear ? 1231 : 1237);
		result = prime * result + Float.floatToIntBits(maxUncertainty);
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
		if (beginDateString == null) {
			if (other.beginDateString != null)
				return false;
		} else if (!beginDateString.equals(other.beginDateString))
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
		if (endDateString == null) {
			if (other.endDateString != null)
				return false;
		} else if (!endDateString.equals(other.endDateString))
			return false;
		if (groupByYear != other.groupByYear)
			return false;
		if (Float.floatToIntBits(maxUncertainty) != Float.floatToIntBits(other.maxUncertainty))
			return false;
		return true;
	}

}
