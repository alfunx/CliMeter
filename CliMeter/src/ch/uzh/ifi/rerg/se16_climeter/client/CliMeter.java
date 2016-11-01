package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CliMeter implements EntryPoint {
	
	UserInterface ui = new UserInterface();
	
	@Override
	public void onModuleLoad() {
		ui.createGUI();
	}
	
}
