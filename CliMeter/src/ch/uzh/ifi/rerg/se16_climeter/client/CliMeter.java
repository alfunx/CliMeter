package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point class which defines the onModuleLoad()-method.
 */
public class CliMeter implements EntryPoint {
	
	UserInterface ui = new UserInterface();
	
	@Override
	public void onModuleLoad() {
		ui.createGUI();
	}
	
}
