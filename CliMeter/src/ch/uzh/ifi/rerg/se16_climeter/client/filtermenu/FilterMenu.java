package ch.uzh.ifi.rerg.se16_climeter.client.filtermenu;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;

/**
 * The class FilterMenu includes the different widgets with which the date can be filtered.
 * 
 * @author 		Joachim Baumann
 * @history 	2016-11-09 JB Initial Commit
 * 				2016-11-09 JB Visual changes
 * @version 	2016-11-28 JB 1.2
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */

public class FilterMenu extends Visualisation {

	String[] countryArray = {"Schweiz","Deutschland","Frankreich","Schweiz1","Schweiz2"};
	String[] cityArray = {"Zürich1","Zürich2","Zürich3","Winterthur","Winterthur1"};

	public FilterMenu(ArrayList<Data> data){

		VerticalPanel dock = new VerticalPanel();
		dock.setStyleName("cw-DockPanel");
		dock.setSpacing(10);

		dock.add(countryBox(data));
		dock.add(cityBox(data));
		dock.add(addDateFilterPanel());	
		dock.add(addInaccuracyPanel());	
		dock.add(addResetButton());

		dock.setStyleName("filterMenuPanel");
		panel.add(dock);
	}



	public Widget countryBox(ArrayList<Data> data) {
		// Define the oracle that finds suggestions
		MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

		// add strings which are displayed when typing character(s)
		String[] countries = countryArray;
		for (int i = 0; i < countries.length; ++i) {
			oracle.add(countries[i]);
		}

		// Create the suggest box
		final SuggestBox suggestBox = new SuggestBox(oracle);
		suggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("Country:"));
		suggestPanel.add(suggestBox);
		suggestPanel.add(addFilterButton());
		return suggestPanel;
	}


	public Widget cityBox(ArrayList<Data> data) {
		// Define the oracle that finds suggestions
		MultiWordSuggestOracle cityOracle = new MultiWordSuggestOracle();
		// add strings which are displayed when typing character(s)
		String[] cities = 	cityArray;
		for (int i = 0; i < cities.length; ++i) {
			cityOracle.add(cities[i]);
		}

		// Create the suggest box
		final SuggestBox suggestBox = new SuggestBox(cityOracle);		
		suggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("City:"));
		suggestPanel.add(suggestBox);
		suggestPanel.add(addFilterButton());
		return suggestPanel;
	}

	public Widget addFilterButton() {

		Button filterButton = new Button("Filter");
		filterButton.setStyleName("filterButton");
		return filterButton;
	}

	public Widget addResetButton() {

		Button resetButton = new Button("RESET");
		resetButton.setStyleName("resetButton");
		return resetButton;
	}

	public Widget addInaccuracyPanel() {

		VerticalPanel inaccuracyPanel = new VerticalPanel();
		CheckBox inaccuracyCheckBox = new CheckBox("Hide inaccurate data");
		TextBox inaccuracyBox = new TextBox();
		inaccuracyBox.setStyleName("inaccuracyBox");
		inaccuracyPanel.add(inaccuracyCheckBox);
		inaccuracyPanel.add(inaccuracyBox);
		return inaccuracyPanel;
	}

	public Widget addDateFilterPanel() {

		VerticalPanel dateFilterPanel = new VerticalPanel();
		dateFilterPanel.add(new Label("Filter dates from: "));

		TextBox dateFilterBoxFrom = new TextBox();
		dateFilterBoxFrom.setStyleName("dateFilterBoxFrom");
		dateFilterPanel.add(dateFilterBoxFrom);
		
		dateFilterPanel.add(new Label("To: "));
		TextBox dateFilterBoxTo = new TextBox();
		dateFilterBoxTo.setStyleName("dateFilterBoxTo");
		dateFilterPanel.add(dateFilterBoxTo);

		Button dateButton = new Button("Filter date");
		dateButton.setStyleName("dateButton");

		dateFilterPanel.add(dateButton);


		return dateFilterPanel;
	}
}
