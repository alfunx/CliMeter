package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.logging.Logger;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;

/**
 * The class MapComposite is a concrete Map, load into a Composite object.
 * 
 * @author 		Christian Himmel
 * @history 	2016-11-28 CH Initial commit
 * 				2016-12-06 AM Adjustments
 * @version 	2016-12-06 AM 1.1
 * @responsibilities 
 * 				This class gets an input and gives the corresponding query to the database 
 * 				which results in a List of the formLinkedList<LinkedList<String>>.
 */
public class SQL {

	static Logger log = Logger.getLogger("SQL");

	public SQL() {
		// GWT needs this
	}

	public void getData(Filter filter, AsyncCallback<ArrayList<Data>> asyncCallback) {
		GreetingServiceAsync rpcService = (GreetingServiceAsync) GWT.create(GreetingService.class);
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "greet";
		target.setServiceEntryPoint(moduleRelativeURL);

		rpcService.getData(filter, asyncCallback);
	}

	public void getMapData(Filter filter, AsyncCallback<ArrayList<Data>> asyncCallback) {
		GreetingServiceAsync rpcService = (GreetingServiceAsync) GWT.create(GreetingService.class);
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "greet";
		target.setServiceEntryPoint(moduleRelativeURL);

		rpcService.getMapData(filter, asyncCallback);
	}

	public void getDistinctCity(AsyncCallback<ArrayList<String>> asyncCallback) {
		GreetingServiceAsync rpcService = (GreetingServiceAsync) GWT.create(GreetingService.class);
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "greet";
		target.setServiceEntryPoint(moduleRelativeURL);

		rpcService.getDistinctCity(asyncCallback);
	}

	public void getDistinctCountry(AsyncCallback<ArrayList<String>> asyncCallback) {
		GreetingServiceAsync rpcService = (GreetingServiceAsync) GWT.create(GreetingService.class);
		ServiceDefTarget target = (ServiceDefTarget) rpcService;
		String moduleRelativeURL = GWT.getModuleBaseURL() + "greet";
		target.setServiceEntryPoint(moduleRelativeURL);

		rpcService.getDistinctCountry(asyncCallback);
	}

}
