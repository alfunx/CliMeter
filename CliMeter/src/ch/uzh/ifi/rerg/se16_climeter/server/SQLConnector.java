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
	 * Returns list of Data, selected using the filter.
	 * @param filter the filter
	 * @return the resulted database query as a ArrayList of Data
	 */
	public ArrayList<Data> getData(Filter filter) {
		ArrayList<Data> dataList = new ArrayList<Data>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT * FROM primaryTable";
		query += " WHERE AverageTemperatureUncertainty<='" + filter.getMaxUncertaintyFloat() + "'";
		if (filter.getBeginDate() != null) {
			query += " AND dt>='" + filter.getBeginDateString() + "'";
		}
		if (filter.getEndDate() != null) {
			query += " AND dt<='" + filter.getEndDateString() + "'";
		}
		if (filter.getCountry() != null) {
			query += " AND Country='" + filter.getCountry() + "'";
		}
		if (filter.getCity() != null) {
			query += " AND City='" + filter.getCity() + "'";
		}
		query += ";";

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
	 * Returns list of Data for the map, selected using the filter.
	 * @param filter the filter
	 * @return the resulted database query as a ArrayList of Data
	 */
	public ArrayList<Data> getMapData(Filter filter) {
		ArrayList<Data> dataList = new ArrayList<Data>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		String query = "SELECT dt, City, Country, Latitude, Longitude, "
				 + "AVG(AverageTemperatureUncertainty) AS u, AVG(AverageTemperature) AS t FROM primaryTable ";
		query += " WHERE YEAR(dt)='" + filter.getYear() + "'";
		query += " AND AverageTemperatureUncertainty<='" + filter.getMaxUncertaintyFloat() + "'";
		if (filter.getCountry() != null) {
			query += " AND Country='" + filter.getCountry() + "'";
		}
		if (filter.getCity() != null) {
			query += " AND City='" + filter.getCity() + "'";
		}
		query += " GROUP BY City;";

		try {
			connection = getConnection();
			try {
				statement = connection.createStatement();
				result = statement.executeQuery(query);
				while (result.next()) {
					Data data = new Data();
					data.setDate(result.getDate("dt"));
					data.setAverageTemperature(result.getFloat("t"));
					data.setUncertainty(result.getFloat("u"));
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
	 * Returns list of strings, containing a distinct list of a column.
	 * @param filter the filter
	 * @return the resulted database query as a ArrayList of Data
	 */
	public ArrayList<String> getDistinctList(String column) {
		ArrayList<String> dataList = new ArrayList<String>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;

		try {
			connection = getConnection();
			try {
				statement = connection.createStatement();
				// query = "select * from primaryTable" oder mit query (in Argumente der Funktion einfügen)
				result = statement.executeQuery("SELECT DISTINCT " + column + " FROM primaryTable");
				while (result.next()) {
					dataList.add(result.getString(column));
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

}
