package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	// Mit String string, am Anfang wenn mit Argumenten gearbeitet wird.
	public void getData(Filter filter, AsyncCallback<ArrayList<Data>> callback);
}