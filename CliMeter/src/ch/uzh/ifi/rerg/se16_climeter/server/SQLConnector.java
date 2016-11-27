package ch.uzh.ifi.rerg.se16_climeter.server;

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.LinkedList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;
import ch.uzh.ifi.rerg.se16_climeter.client.GreetingService;
import com.google.appengine.api.utils.SystemProperty;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Christian Himmel
 * @history 2016-11-22 CH initial commit
 * @version 2016-11-22 CH 1
 * @responsibilities This file creates a connection between the project and the database
 * 						and converts the results into a LinkedList
 */
@SuppressWarnings("serial")
public class SQLConnector extends RemoteServiceServlet implements GreetingService{

	static Logger log = Logger.getLogger("Connect");
	
	/**
	 * Tries to connect to the database.
	 * @return Returns,if successful, the connection to the database.
	 */
	private static Connection ConnectionDB(){
		String url = null;
		Connection connection = null;
		try{
			if(SystemProperty.environment.value() == SystemProperty.Environment.Value.Production){
				Class.forName("com.mysql.jdbc.GoogleDriver");
				url = "jdbc:google:mysql://climeter-150120:us-central1:myinstance/dbname=cliMeter?user=root;password=3Vy;Jf/X#Hey";
				log.info("Connection to Google SQL ready");
				Console.log("Connection to Google SQL ready");
				return DriverManager.getConnection(url);
			}
			else{
				Class.forName("com.mysql.jdbc.Driver");
				url = "jdbc:mysql://104.198.227.110:3306/dbname=cliMeter?user=root;password=3Vy;Jf/X#Hey";
				log.info("Connection to local MySQL ready");
				Console.log("Connection to local MySQL ready");
				return DriverManager.getConnection(url);
			}
		}
		catch (SQLException sqle){
			log.log(Level.SEVERE, "SQLException" + sqle.getMessage());
			Console.log("SQLException");
			return connection;
		}
		catch (ClassNotFoundException cnfe){
			log.log(Level.SEVERE, "ClassNotFoundException" + cnfe.getMessage());
			Console.log("ClassNotFoundException");
			return connection;
		}
		catch (NullPointerException npe){
			log.log(Level.SEVERE, "NullPointerException" + npe.getMessage());
			Console.log("NullPointerException");
			return connection;
		}
		catch (Exception e){
			log.log(Level.WARNING, "Unknown exception" + e.getMessage());
			Console.log("Unknown exception");
			return connection;
		}
	}
	/**
	 * Sends the SELECT * from table to Google SQL and converts it into a LinkedList<LinkedList<String>>
	 * @param query sends a mySQL query to the database
	 * @return return the resulted database query as a LinkedList<LinkedList<String>>()
	 */
	public LinkedList<LinkedList<String>> getData (String query){
		LinkedList<LinkedList<String>> list = new LinkedList<LinkedList<String>>();
		Connection connection = null;
		Statement statement = null;
		ResultSet result = null;
		try{
			connection = ConnectionDB();
			try{
				statement = connection.createStatement();
				// query = "select * from primaryTable" oder mit query (in Argumente der Funktion einfügen)
				result = statement.executeQuery(query);
				while (result.next()){
					LinkedList<String> smallList = new LinkedList<String>();
					for (int i=1; i<=result.getMetaData().getColumnCount(); i++){
						smallList.add(result.getString(i));
					}
					list.add(smallList);
				}
				return list;
			}
			finally{
				connection.close();
				statement.close();
				result.close();
			}
		}
		catch (Exception e){
			log.log(Level.WARNING, "Unknown exception" + e.getMessage());
			Console.log("Unknown excepton");
		}
		return null;
	}
}
