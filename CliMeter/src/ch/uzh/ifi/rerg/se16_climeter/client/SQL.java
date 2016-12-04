package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
/**
 * 
 * @author Christian Himmel
 * @history 2016-12-3 CH initial commit
 * @version 2016-12-3 CH 1
 * @responsibilities This class gets an input and gives the corresponding query to the database which results in a List of the formLinkedList<LinkedList<String>>
 */
public class SQL {
	static Logger log = Logger.getLogger("SQL");
	private GreetingServiceAsync greet = GWT.create(GreetingService.class);
	LinkedList<LinkedList<String>> list = new LinkedList<LinkedList<String>>();
	/**
	 * Sends a query to the databse, after its connection, and converts it.
	 * @param query the query which needs that the database needs to convert.
	 * @param listener the querylistener which quecks for the status of the queries of the class listener
	 */
	public void getList(String query, final Listener listener){
		//Initialize the service proxy
		if (greet == null){
			greet = GWT.create(GreetingService.class);
		}
		//Set up the callback object
		AsyncCallback<LinkedList<LinkedList<String>>> callback = new AsyncCallback<LinkedList<LinkedList<String>>>(){
			public void onFailure(Throwable caught) {
	    		log.warning("AsyncCallback failed: " + caught.getMessage());
	    	}
	    	public void onSuccess(LinkedList<LinkedList<String>> result) {
	    		if(listener != null){
	    			listener.onQuerySuccess(result);
	    			Console.log("Success");
	    		}
	    	}	
	    };
	    // Makes the call to the service
	    greet.getData(query, callback);
	}
	/**
	 * Gets a Date input, gives it to the server and returns a LinkedList
	 * @param gets a Date dt and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getDateList(Date dt){
		String query = "SELECT * FROM primaryTable WHERE dt='%" + dt + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;
	}
	/**
	 * Gets a Temperature Input, gives it to the server and returns a LinkedList
	 * @param gets a float Temp and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getTemperatureList(float Temp){
		String query = "SELECT * FROM primaryTable WHERE AverageTemperature='%" + Temp + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;
	}
	/**
	 * Gets a Temperature-Uncertainty Input, gives it to the server and returns a LinkedList
	 * @param gets a float Unc and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getUncertaintyList(float Unc){
		String query = "SELECT * FROM primaryTable WHERE AverageTemperatureUncertainty='%" + Unc + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;	
	}
	/**
	 * Gets a City Input, gives it to the server and returns a LinkedList
	 * @param gets a String City and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getCityList(String City){
		String query = "SELECT * FROM primaryTable WHERE City='%" + City + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;	
	}
	/**
	 * Gets a Country Input, gives it to the server and returns a LinkedList
	 * @param gets a String Country and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getCountry(String Country){
		String query = "SELECT * FROM primaryTable WHERE Country='%" + Country + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;	
	}
	/**
	 * Gets a Latitude Input, gives it to the server and returns a LinkedList
	 * @param gets a String Lat and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getLatitudeList(String Lat){
		String query = "SELECT * FROM primaryTable WHERE Latitude='%" + Lat + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;	
	}
	/**
	 * Gets a Longitude Input, gives it to the server and returns a LinkedList
	 * @param gets a String Long and converts it into a string 
	 * @return a LinkedList<LinkedList<String>> of the corresponding server data
	 */
	public LinkedList<LinkedList<String>> getLongitudeList(String Long){
		String query = "SELECT * FROM primaryTable WHERE Longitude='%" + Long + "%'";
		getList(query, new Listener(){
			public void onQuerySuccess(LinkedList<LinkedList<String>> result){
				list = result;
			}
		});
		return list;	
	}
	
	public LinkedList<LinkedList<String>> getData(Filter filter) {
		if (filter == null) {
			return null;
		}
		
		String query = "SELECT * FROM primaryTable WHERE Longitude='%" + "Long" + "%'";
		query = "SELECT * FROM primaryTable WHERE";
		
		query += " AverageTemperatureUncertainty<='%" + filter.getMaxCertaintyFloat() + "%'";
		
		if (filter.getBeginDate() != null && filter.getEndDate() != null) {
			query += " dt>='%" + filter.getBeginDate() + "%' AND dt<='%" + filter.getEndDate() + "%'";
		}
		
		if (filter.getCountry() != null) {
			query += " Country='%" + filter.getCountry() + "%'";
		}
		
		if (filter.getCity() != null) {
			query += " City='%" + filter.getCity() + "%'";
		}
		
		getList(query, new Listener() {
			public void onQuerySuccess(LinkedList<LinkedList<String>> result) {
				list = result;
			}
		});
		return list;	
	}
}