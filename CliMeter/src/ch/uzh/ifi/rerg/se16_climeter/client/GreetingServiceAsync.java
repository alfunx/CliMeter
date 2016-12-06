package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	public void getData(Filter filter, AsyncCallback<ArrayList<Data>> callback);
	public void getMapData(Filter filter, AsyncCallback<ArrayList<Data>> callback);
	public void getDistinctList(String column, AsyncCallback<ArrayList<String>> callback);
}