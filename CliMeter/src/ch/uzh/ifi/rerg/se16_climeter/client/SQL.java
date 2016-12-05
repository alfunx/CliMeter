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
	 * Returns all rows of the databank that matches the criteria in filter.
	 * @param filter the filter to apply on database
	 * @return a LinkedList of Data of the corresponding server data
	 */
	public LinkedList<Data> getData(Filter filter) {
		if (filter == null) {
			return null;
		}

		String query = "SELECT * FROM primaryTable";

		query += " WHERE AverageTemperatureUncertainty<='%" + filter.getMaxUncertaintyFloat() + "%'";

		if (filter.getBeginDate() != null && filter.getEndDate() != null) {
			query += " AND dt>='%" + filter.getBeginDate() + "%' AND dt<='%" + filter.getEndDate() + "%'";
		}

		if (filter.getCountry() != null) {
			query += " AND Country='%" + filter.getCountry() + "%'";
		}

		if (filter.getCity() != null) {
			query += " AND City='%" + filter.getCity() + "%'";
		}

		getList(query, new Listener() {
			public void onQuerySuccess(LinkedList<LinkedList<String>> result) {
				list = result;
			}
		});

		return stringToData(list);	
	}

	/**
	 * Converts lists of string to a list of Data.
	 * @param stringList the list as list of strings
	 * @return the list of Data
	 */
	public LinkedList<Data> stringToData(LinkedList<LinkedList<String>> stringList) {
		LinkedList<Data> dataList = new LinkedList<Data>();

		for (LinkedList<String> l : stringList) {
			dataList.add(new Data(l));
		}

		return dataList;
	}

	/**
	 * Gets all distinct values of a column.
	 * @param column the column of the database
	 * @return the list of strings
	 */
	public LinkedList<String> getDistinct(String column) {
		LinkedList<String> stringList = new LinkedList<String>();
		String query = "SELECT DISTINCT " + column + " FROM primaryTable";

		getList(query, new Listener() {
			public void onQuerySuccess(LinkedList<LinkedList<String>> result) {
				list = result;
			}
		});

		for (LinkedList<String> l : list) {
			stringList.add(l.get(0));
		}

		return stringList;
	}

	/**
	 * Gets all distinct values of the column Country.
	 * @return the list of strings
	 */
	public LinkedList<String> getCountriesList() {
		return getDistinct("Country");
	}

	/**
	 * Gets all distinct values of the column City.
	 * @return the list of strings
	 */
	public LinkedList<String> getCitiesList() {
		return getDistinct("City");
	}

}
