package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	public void getData(Filter filter, AsyncCallback<ArrayList<Data>> callback);
	public void getMapData(Filter filter, AsyncCallback<ArrayList<Data>> callback);
	public void getDistinctCity(AsyncCallback<ArrayList<String>> callback);
	public void getDistinctCountry(AsyncCallback<ArrayList<String>> callback);
}