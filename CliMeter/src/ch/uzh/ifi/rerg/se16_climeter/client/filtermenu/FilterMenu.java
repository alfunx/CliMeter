package ch.uzh.ifi.rerg.se16_climeter.client.filtermenu;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;

/**
 * The class FilterMenu includes the different widgets with which the date can be filtered.
 * 
 * @author 		Joachim Baumann
 * @history 	2016-11-09 JB Initial Commit
 * @version 	2016-11-09 JB 1.0
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */

public class FilterMenu extends Visualisation {

	String[] countryArray = {"Schweiz","Deutschland","Frankreich","Schweiz1","Schweiz2"};
	String[] cityArray = {"Zürich1","Zürich2","Zürich3","Winterthur","Winterthur1"};
	
	private VerticalPanel dock;
	private MultiWordSuggestOracle countryOracle;
	private SuggestBox countrySuggestBox;
	private VerticalPanel CountrySuggestPanel;

	public FilterMenu(ArrayList<Data> data){

		dock = new VerticalPanel();
		dock.setStyleName("cw-DockPanel");
		dock.setSpacing(30);

		dock.add(initCountryBox(data));
		dock.add(initCityBox(data));
		dock.add(addInaccuracyBox());
		dock.add(addResetButton());
		
		dock.setStyleName("filterMenuPanel");
		panel.add(dock);
	}



	public Widget initCountryBox(ArrayList<Data> data) {
		// Define the oracle that finds suggestions
		countryOracle = new MultiWordSuggestOracle();

		// add strings which are displayed when typing character(s)
		String[] countries = countryArray;
		for (int i = 0; i < countries.length; ++i) {
			countryOracle.add(countries[i]);
		}

		// Create the suggest box
		countrySuggestBox = new SuggestBox(countryOracle);
		countrySuggestBox.ensureDebugId("cwSuggestBox");
		CountrySuggestPanel = new VerticalPanel();
		CountrySuggestPanel.add(new Label("Country:"));
		CountrySuggestPanel.add(countrySuggestBox);
		CountrySuggestPanel.add(addFilterButton());
		return CountrySuggestPanel;
	}


	public Widget initCityBox(ArrayList<Data> data) {
		// Define the oracle that finds suggestions
		MultiWordSuggestOracle cityOracle = new MultiWordSuggestOracle();
		// add strings which are displayed when typing character(s)
		String[] cities = 	cityArray;
		for (int i = 0; i < cities.length; ++i) {
			cityOracle.add(cities[i]);
		}

		// Create the suggest box
		final SuggestBox suggestBox = new SuggestBox(cityOracle);
		suggestBox.ensureDebugId("cwSuggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("City:"));
		suggestPanel.add(suggestBox);
		suggestPanel.add(addFilterButton());
		return suggestPanel;
	}

	public Widget addFilterButton() {

		Button filterButton = new Button("Filter");
		filterButton.setStyleName("filterButton");
//		filterButton.setWidth("200px");
		return filterButton;
	}

	public Widget addResetButton() {

		Button resetButton = new Button("RESET");
		resetButton.setStyleName("resetButton");
//		resetButton.setWidth("200px");
		return resetButton;
	}

	public Widget addInaccuracyBox() {

		CheckBox inaccuracyBox = new CheckBox("Hide inaccurate data");
		inaccuracyBox.setStyleName("inaccuracyBox");
//		inaccuracyBox.setWidth("200px");
		return inaccuracyBox;
	}
}
