package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;
import ch.uzh.ifi.rerg.se16_climeter.client.SQL;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;

/**
 * The class FilterMenu includes the different widgets with which the date can be filtered.
 * 
 * @author 		Joachim Baumann
 * @history 	2016-11-09 JB Initial Commit
 * 				2016-11-09 JB Visual changes
 * 				2016-12-04 JS Prepared to apply filter on table
 * 				2016-12-04 JS Visual changes and filter now applicable on Table  				
 * 
 * @version 	2016-11-28 JB 1.2
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */

public class FilterMenu extends Visualisation {

	String[] countryArray;
	String[] cityArray;
	
	private Filterable filterable;
	
	private SuggestBox citySuggestBox;
	private SuggestBox countrySuggestBox;
	
	private TextBox beginDateBox;
	private TextBox endDateBox;
	
	private CheckBox inaccuracyCheckBox;
	private TextBox inaccuracyBox;
	

	public FilterMenu(Filterable filterable) {

		VerticalPanel filterMenuPanel = new VerticalPanel();
		this.filterable = filterable;
		
		
		filterMenuPanel.setSpacing(10);
		
		filterMenuPanel.add(addFilterTitle());
		filterMenuPanel.add(countryBox());
		filterMenuPanel.add(cityBox());
		filterMenuPanel.add(addDateFilterPanel());	
		filterMenuPanel.add(addInaccuracyPanel());	
		filterMenuPanel.add(addButtons());

		filterMenuPanel.setStyleName("filterMenuPanel");
		panel.add(filterMenuPanel);
	}
	
	

	/** Creates a title for the FilterMenu
	 * 
	 * @return title widget
	 */
	private Widget addFilterTitle() {
		Label title = new Label("Filter");
		title.setStyleName("filterTitle");
		return title;
	}
	
	/** Creates a suggestion ox for countries
	 * 
	 * @return a panel with a suggestion box
	 */
	public Widget countryBox() {
		// Define the oracle that finds suggestions
		final MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
		
		// load country suggestions
		SQL sql = new SQL();
		sql.getDistinctCountry(new AsyncCallback<ArrayList<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				Console.log("SQL Error.");
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				countryArray = new String[result.size()];
				countryArray = result.toArray(countryArray);
				// add strings which are displayed when typing character(s)
				for (int i = 0; i < countryArray.length; ++i) {
				oracle.add(countryArray[i]);
		}
			}
		});
	
		// Create the suggest box
		countrySuggestBox = new SuggestBox(oracle);
		countrySuggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("Country:"));
		suggestPanel.add(countrySuggestBox);
		
		return suggestPanel;
	}

	/** Creates a suggestion ox for cities
	 * 
	 * @return a panel with a suggestion box
	 */
	public Widget cityBox() {
		// Define the oracle that finds suggestions
		final MultiWordSuggestOracle cityOracle = new MultiWordSuggestOracle();
		
		// load city suggestions
		SQL sql = new SQL();
		sql.getDistinctCity(new AsyncCallback<ArrayList<String>>() {
			@Override
			public void onFailure(Throwable caught) {
				Console.log("SQL Error.");
			}

			@Override
			public void onSuccess(ArrayList<String> result) {
				cityArray = new String[result.size()];
				cityArray = result.toArray(cityArray);
				
				// add strings which are displayed when typing character(s)
				for (int i = 0; i < cityArray.length; ++i) {
				cityOracle.add(cityArray[i]);
		}
			}
		});

		// Create the suggest box
		citySuggestBox = new SuggestBox(cityOracle);		
		citySuggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("City:"));
		suggestPanel.add(citySuggestBox);
		
		return suggestPanel;
	}
	
	/**
	 * Creates the apply and reset buttons for the filter
	 * @return a horizontal panel with 2 buttons
	 */
	public Widget addButtons() {
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		
		Button applyButton = new Button("Apply", new ClickHandler() {
			public void onClick(ClickEvent event) {
				filterable.apply(getFilterValues());
				Console.log("Filter applied, wait for table to be updated...");
			}
			//TODO keyevent ENTER
		});
		applyButton.setStyleName("applyButton");
		
		Button resetButton = new Button("RESET", new ClickHandler() {
			public void onClick(ClickEvent event) {
				resetFilter();
				filterable.apply(new Filter());
				Console.log("Reset successful, wait for table to be updated...");
				countrySuggestBox.setFocus(true);
			}
		});
		resetButton.setStyleName("resetButton");
		
		buttonPanel.add(applyButton);
		buttonPanel.add(resetButton);
		
		
		return buttonPanel;
	}

	/**
	 * Creates the box to set max. uncertainty
	 * @return panel with uncertainty option
	 */
	public Widget addInaccuracyPanel() {

		VerticalPanel inaccuracyPanel = new VerticalPanel();
		inaccuracyCheckBox = new CheckBox("Set max. uncertainty");
		inaccuracyBox = new TextBox();
		inaccuracyBox.setStyleName("inaccuracyBox");
		inaccuracyPanel.add(inaccuracyCheckBox);
		inaccuracyPanel.add(inaccuracyBox);
		return inaccuracyPanel;
	}
	
	/**
	 * Creates text boxes for date filtering
	 * @return a panel with date filter option
	 */
	public Widget addDateFilterPanel() {

		VerticalPanel dateFilterPanel = new VerticalPanel();
		
		dateFilterPanel.add(new Label("Filter dates from: "));
		beginDateBox = new TextBox();
		beginDateBox.setStyleName("beginDateBox");
		dateFilterPanel.add(beginDateBox);
		
		dateFilterPanel.add(new Label("To: "));
		endDateBox = new TextBox();
		endDateBox.setStyleName("endDateBox");
		dateFilterPanel.add(endDateBox);

		return dateFilterPanel;
	}
	
	/** 
	 * Method collects all inputs set by the user for the filter and creates a Filter object
	 * TODO: dates
	 * 
	 * @return a Filter object
	 */
	public Filter getFilterValues(){
		Filter filter = new Filter();
		
		String country = null;
		String city = null;
		Date beginDate = new Date(0, 0, 1); // TODO!
		Date endDate = new Date(115, 0, 1); //TODO!
		float maxUncertainty = Float.MAX_VALUE;
		
		if (countrySuggestBox.getValue() != ""){
			country = countrySuggestBox.getValue();
			Console.log("Country Filter: " + country);
		}
		
		if (citySuggestBox.getValue() != ""){
			city = citySuggestBox.getValue();
			Console.log("City Filter: " + city);
			
		}
		
		if (inaccuracyCheckBox.getValue() == true && inaccuracyBox.getValue() != ""){
			maxUncertainty = Float.parseFloat(inaccuracyBox.getValue());
			Console.log("MaxUncertainty: " + maxUncertainty);
		}
		
		
		Console.log("BeginDate: " + beginDate.toString());
		Console.log("EndDate: " + endDate.toString());
		
		
		filter.setCountry(country);
		filter.setCity(city);
		filter.setBeginDate(beginDate);
		filter.setEndDate(endDate);
		filter.setMaxUncertainty(maxUncertainty);
		
		return filter;
	}
	
	/**
	 * Resets all text boxes of the filter
	 */
	public void resetFilter() {
		countrySuggestBox.setValue("");
		citySuggestBox.setValue("");
		beginDateBox.setValue("");
		endDateBox.setValue("");
		inaccuracyBox.setValue("");
		inaccuracyCheckBox.setValue(false);
	}
	
	public SuggestBox getCountrySuggestBox() {
		return countrySuggestBox;
	}
}
