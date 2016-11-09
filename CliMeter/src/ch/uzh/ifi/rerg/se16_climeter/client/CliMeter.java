package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.DockLayoutPanel;

/**
 * Entry point class which defines the onModuleLoad()-method.
 */
public class CliMeter extends DockLayoutPanel implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	
	
	//Create DockLayoutPanel -- first Panel inserted to Root Panel
	super(Style.Unit.EM);

	
	UserInterface ui = new UserInterface();
	
	@Override

	public void onModuleLoad() {

		ui.createGUI();

	}
	
}
