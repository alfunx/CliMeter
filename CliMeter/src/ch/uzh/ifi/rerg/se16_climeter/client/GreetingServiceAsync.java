package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.LinkedList;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	// Mit String string, am Anfang wenn mit Argumenten gearbeitet wird.
	void getData(String string, AsyncCallback<LinkedList<LinkedList<String>>> callback);
}