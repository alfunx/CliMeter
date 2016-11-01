package ch.uzh.ifi.rerg.se16_climeter.client;

import ch.uzh.ifi.rerg.se16_climeter.shared.FieldVerifier;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class UserInterface {
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network " + "connection and try again.";

	private TabLayoutPanel tabs;
	//private Map map;
	//private Table table;
	
	/**
	 * This is the entry point method.
	 */
	public void createGUI() {
		tabs = new TabLayoutPanel(20, Unit.PX);
		
		tabs.add(new Label("Map"), "Map");
		tabs.add(new Label("Table"), "Table");
		tabs.add(new Label("Filter"), "Filter");
		
		RootPanel.get().add(tabs);

		tabs.setHeight("400px");
	}
}
