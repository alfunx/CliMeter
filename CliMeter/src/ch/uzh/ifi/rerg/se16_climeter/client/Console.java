package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.logging.Logger;

public class Console extends Logger {

	protected Console(String name, String resourceBundleName) {
		super(name, resourceBundleName);
	}

	public native static void log(String message) /*-{
		console.log(">> " + message);
	}-*/;

}
