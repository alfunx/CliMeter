package ch.uzh.ifi.rerg.se16_climeter.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.Filter;
import ch.uzh.ifi.rerg.se16_climeter.client.GreetingService;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The class MapComposite is a concrete Map, load into a Composite object.
 * 
 * @author 		Christian Himmel
 * @history 	2016-11-22 CH Initial commit
 * 				2016-12-06 AM Adjustments
 * @version 	2016-12-06 AM 1.3
 * @responsibilities 
 * 				This class creates a connection between the project and the database 
 * 				and converts the results into a LinkedList.
 */
@SuppressWarnings("serial")
public class SQLConnector extends RemoteServiceServlet implements GreetingService {

	static Logger log = Logger.getLogger("SQLConnector");

	public SQLConnector() {
		// GWT needs this
	}

	/**
	 * Tries to connect to the database.
	 * @return Returns the connection to the database.
	 * @throws Exception
	 */
	private static Connection getConnection() throws Exception {
		String url;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			Class.forName("com.mysql.jdbc.GoogleDriver").newInstance();
			Properties properties = new Properties();
			properties.setProperty("user", "root");
			properties.setProperty("password", "3Vy;Jf/X#Hey");

			url = "jdbc:google:mysql://climeter-150120:us-central1:myinstance/cliMeter";
			log.info("Connection to Google SQL ready");
			return DriverManager.getConnection(url, properties);
		} else {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Properties properties = new Properties();
			properties.setProperty("user", "sql7147888");
			properties.setProperty("password", "fRyBV2ZPHE");

			url = "jdbc:mysql://sql7.freesqldatabase.com:3306/sql7147888";
			return DriverManager.getConnection(url, properties);
		}
	}

	/**
	 * Returns list of Data for a query.
	 * @param query the query
	 * @return the resulted table as a ArrayList of Data
	 */
	private ArrayList<Data> getData(String query) {
		ArrayList<Data> dataList = new ArrayList<Data>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = getConnection();
			try {
				statement = connection.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					Data data = new Data();
					data.setDate(result.getDate("dt"));
					data.setAverageTemperature(result.getFloat("AverageTemperature"));
					data.setUncertainty(result.getFloat("AverageTemperatureUncertainty"));
					data.setCity(result.getString("City"));
					data.setCountry(result.getString("Country"));
					data.setLatitude(result.getString("Latitude"));
					data.setLongitude(result.getString("Longitude"));
					dataList.add(data);
				}
			} finally {
				connection.close();
				statement.close();
				result.close();
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception: " + e.getMessage());
		}

		return dataList;
	}

	/**
	 * Returns list of Data, selected using the filter.
	 * @param filter the filter
	 * @return the resulted database query as a ArrayList of Data
	 */
	public ArrayList<Data> getData(Filter filter) {
		return getData(getQuery(filter, "*", null));
	}

	/**
	 * Returns list of Data for the map, selected using the filter.
	 * @param filter the filter
	 * @return the resulted database query as a ArrayList of Data
	 */
	public ArrayList<Data> getMapData(Filter filter) {
		return getData(getQuery(filter, "dt, AVG(AverageTemperature) AS AverageTemperature, " + 
				"AVG(AverageTemperatureUncertainty) AS AverageTemperatureUncertainty, City, Country, " + 
				"Latitude, Longitude", "City"));
	}

	/**
	 * Returns list of String for a query.
	 * @param query the query
	 * @return the resulted table as a ArrayList of String
	 */
	private ArrayList<String> getDistinctList(String query) {
		ArrayList<String> dataList = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = getConnection();
			try {
				statement = connection.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					dataList.add(result.getString(1));
				}
			} finally {
				connection.close();
				statement.close();
				result.close();
			}
		} catch (Exception e) {
			log.log(Level.WARNING, "Exception: " + e.getMessage());
		}

		return dataList;
	}

	/**
	 * Returns list of strings, containing a distinct list of all cities.
	 * @return the distinct list of cities
	 */
	public ArrayList<String> getDistinctCity() {
		return getDistinctList(getQuery(null, "DISTINCT City", null));
	}

	/**
	 * Returns list of strings, containing a distinct list of all countries.
	 * @return the distinct list of countries
	 */
	public ArrayList<String> getDistinctCountry() {
		return getDistinctList(getQuery(null, "DISTINCT Country", null));
	}

	/**
	 * Generates an SQL query.
	 * @param filter the filter
	 * @param select the select statement
	 * @param groupBy the group by statement
	 * @return the complete query string
	 */
	private String getQuery(Filter filter, String select, String groupBy) {
		// check if query contains illegal chars
		if (isQueryDangerous(select) || 
				isQueryDangerous(groupBy)) {
			throw new IllegalArgumentException("Query contains invalid symbols.");
		}
		if (filter != null && 
				(isQueryDangerous(filter.getCity()) || 
						isQueryDangerous(filter.getCountry()))) {
			throw new IllegalArgumentException("Query contains invalid symbols.");
		}

		String query = "SELECT " + select + " FROM primaryTable";

		if (filter != null) {
			query += " WHERE AverageTemperatureUncertainty<='" + filter.getMaxUncertaintyFloat() + "'";

			if (filter.getBeginDate() != null && filter.getEndDate() != null && 
					filter.getBeginDate().equals(filter.getEndDate())) {
				query += " AND YEAR(dt)='" + filter.getYear() + "'";
			} else {
				if (filter.getBeginDate() != null) {
					query += " AND dt>='" + filter.getBeginDateString() + "'";
				}
				if (filter.getEndDate() != null) {
					query += " AND dt<='" + filter.getEndDateString() + "'";
				}
			}

			if (filter.getCountry() != null) {
				query += " AND Country='" + filter.getCountry() + "'";
			}
			if (filter.getCity() != null) {
				query += " AND City='" + filter.getCity() + "'";
			}

			if (groupBy != null) {
				query += " GROUP BY " + groupBy + "";
			}
		}
		query += ";";

		return query;
	}

	private boolean isQueryDangerous(String string) {
		if (string == null) {
			return false;
		}
		if (string.contains("'") || string.contains(";")) {
			return true;
		}
		return false;
	}

}
