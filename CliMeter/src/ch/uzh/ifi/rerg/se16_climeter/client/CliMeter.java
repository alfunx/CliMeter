package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point class which defines the onModuleLoad()-method.
 */
public class CliMeter implements EntryPoint {

	/**
	 * This is the entry point method.
	 */

	
	UserInterface ui = new UserInterface();
	
	@Override

	public void onModuleLoad() {
		
		// override theme with custom css
		Resources.INSTANCE.css().ensureInjected(); 
		
		ui.createGUI();
		
	}
	
}
