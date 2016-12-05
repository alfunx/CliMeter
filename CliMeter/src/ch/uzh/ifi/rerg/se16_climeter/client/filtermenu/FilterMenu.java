package ch.uzh.ifi.rerg.se16_climeter.client.filtermenu;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.Filter;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;
import ch.uzh.ifi.rerg.se16_climeter.client.table.Table;

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

	String[] countryArray = {"Schweiz","Deutschland","Frankreich","Schweiz1","Schweiz2"};
	String[] cityArray = {"Zürich1","Zürich2","Zürich3","Winterthur","Winterthur1"};
	
	private Table table;
	
	private SuggestBox citySuggestBox;
	private SuggestBox countrySuggestBox;
	
	private TextBox beginDateBox;
	private TextBox endDateBox;
	
	private CheckBox inaccuracyCheckBox;
	private TextBox inaccuracyBox;
	

	public FilterMenu(ArrayList<Data> data, Table table){

		VerticalPanel filterMenuPanel = new VerticalPanel();
		this.table = table;
		
		filterMenuPanel.setSpacing(10);
		
		filterMenuPanel.add(addFilterTitle());
		filterMenuPanel.add(countryBox(data));
		filterMenuPanel.add(cityBox(data));
		filterMenuPanel.add(addDateFilterPanel());	
		filterMenuPanel.add(addInaccuracyPanel());	
		filterMenuPanel.add(addButtons());

		filterMenuPanel.setStyleName("filterMenuPanel");
		panel.add(filterMenuPanel);
	}



	private Widget addFilterTitle() {
		Label title = new Label("Filter");
		title.setStyleName("filterTitle");
		return title;
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
		countrySuggestBox = new SuggestBox(oracle);
		countrySuggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("Country:"));
		suggestPanel.add(countrySuggestBox);
		
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
		citySuggestBox = new SuggestBox(cityOracle);		
		citySuggestBox.setStyleName("suggestBox");
		VerticalPanel suggestPanel = new VerticalPanel();
		suggestPanel.add(new Label("City:"));
		suggestPanel.add(citySuggestBox);
		
		return suggestPanel;
	}

	public Widget addFilterButton() {

		Button filterButton = new Button("Filter");
		filterButton.setStyleName("filterButton");
		return filterButton;
	}

	public Widget addButtons() {
		
		HorizontalPanel buttonPanel = new HorizontalPanel();
		
		Button applyButton = new Button("Apply", new ClickHandler() {
			public void onClick(ClickEvent event) {
				table.apply(getFilterValues());
			}
		});
		applyButton.setStyleName("applyButton");
		
		Button resetButton = new Button("RESET", new ClickHandler() {
			public void onClick(ClickEvent event) {
				table.addData(Data.getRandomData(200000));
			}
		});
		resetButton.setStyleName("resetButton");
		
		buttonPanel.add(applyButton);
		buttonPanel.add(resetButton);
		
		
		return buttonPanel;
	}

	public Widget addInaccuracyPanel() {

		VerticalPanel inaccuracyPanel = new VerticalPanel();
		inaccuracyCheckBox = new CheckBox("Hide inaccurate data");
		inaccuracyBox = new TextBox();
		inaccuracyBox.setStyleName("inaccuracyBox");
		inaccuracyPanel.add(inaccuracyCheckBox);
		inaccuracyPanel.add(inaccuracyBox);
		return inaccuracyPanel;
	}

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
	
	/** NOT fully implemented! 
	 * TODO: dates
	 * 
	 * @return filter
	 */
	
	public Filter getFilterValues(){
		Filter filter = new Filter();
		
		String country = null;
		String city = null;
		Date beginDate = null; // TODO!
		Date endDate = null ; //TODO!
		double maxUncertainty = 0.0d;
		
		if (countrySuggestBox.getValue() != ""){
			country = countrySuggestBox.getValue();
		}
		
		if (citySuggestBox.getValue() != ""){
			city = countrySuggestBox.getValue();
		}
		
		if (inaccuracyCheckBox.getValue() == true){
			maxUncertainty = Double.parseDouble(inaccuracyBox.getValue());
		}
		
		filter.setCountry(country);
		filter.setCity(city);
		filter.setBeginDate(beginDate);
		filter.setEndDate(endDate);
		filter.setMaxUncertainty(maxUncertainty);
		
		return filter;
	}
}
