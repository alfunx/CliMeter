package ch.uzh.ifi.rerg.se16_climeter.client.filter;

import java.util.ArrayList;
import java.util.Date;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

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
 * 				2016-12-09 JS Gets data from database
 * 				2016-12-10 JS Visual changes
 * 				2016-12-12 AM Adjustment to make FilterMenu work with map				
 * 
 * @version 	2016-11-28 JB 1.2
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */

public class FilterMenu extends Visualisation {
	
	final static Date STANDARD_BEGIN_DATE = new Date(-200, 0, 1);
	final static Date STANDARD_END_DATE = new Date(116, 11, 31);
	
	private Filterable filterable;
	
	private SuggestBox citySuggestBox;
	private SuggestBox countrySuggestBox;
	
	private DateBox beginDateBox;
	private DateBox endDateBox;
	private boolean isDateFilter;
	
	private CheckBox inaccuracyCheckBox;
	private TextBox inaccuracyBox;
	
	private TextBox statusBox;
	

	public FilterMenu(Filterable filterable, boolean isDateFilter){

		VerticalPanel filterMenuPanel = new VerticalPanel();
		this.filterable = filterable;
		this.isDateFilter = isDateFilter;
		
		
		filterMenuPanel.setSpacing(10);
		
		filterMenuPanel.add(initFilterTitle());
		filterMenuPanel.add(countryBox());
		filterMenuPanel.add(cityBox());
		if (isDateFilter) {
			filterMenuPanel.add(addDateFilterPanel());
		}
		filterMenuPanel.add(addInaccuracyPanel());
		filterMenuPanel.add(addButtons());
		filterMenuPanel.add(initStatusBox());

		filterMenuPanel.setStyleName("filterMenuPanel");
		panel.add(filterMenuPanel);
	}
	
	public FilterMenu(Filterable filterable) {
		this(filterable, true);
	}

	/** 
	 * Creates a title for the FilterMenu
	 * @return title widget
	 */
	private Widget initFilterTitle() {
		Label title = new Label("Filter");
		title.setStyleName("filterTitle");
		return title;
	}
	
	/**
	 * Creates text box to show table status
	 */
	private Widget initStatusBox() {
		statusBox = new TextBox();
		statusBox.setEnabled(false);
		statusBox.setText("");
		
		return statusBox;
	}
	
	/** Creates a suggestion box for countries
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
				oracle.addAll(result);
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

	/** Creates a suggestion box for cities
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
				cityOracle.addAll(result);
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
				statusBox.setText("Updating table...");
				statusBox.setStyleName("statusBoxLoading");
			}
			//TODO keyevent ENTER
		});
		applyButton.setStyleName("applyButton");
		
		Button resetButton = new Button("RESET", new ClickHandler() {
			public void onClick(ClickEvent event) {
				resetFilter();
				filterable.apply(new Filter());
				Console.log("Reset successful, wait for table to be updated...");
				statusBox.setText("Resetting table...");
				statusBox.setStyleName("statusBoxLoading");
				countrySuggestBox.setFocus(true);
			}
		});
		resetButton.setStyleName("resetButton");
		
		buttonPanel.add(applyButton);
		buttonPanel.add(resetButton);
		
		
		return buttonPanel;
	}

	/**
	 * Creates box to set max. uncertainty
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
	 * Creates date pickers with boxes for date filtering
	 * @return a panel with date filter option
	 */
	public Widget addDateFilterPanel() {

		VerticalPanel dateFilterPanel = new VerticalPanel();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getLongDateFormat();
		
		dateFilterPanel.add(new Label("Dates from: "));
		beginDateBox = new DateBox();
		beginDateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));
		DatePicker beginDatePicker = beginDateBox.getDatePicker();
		beginDatePicker.setValue(STANDARD_BEGIN_DATE, true);
		beginDatePicker.setYearAndMonthDropdownVisible(true);
		beginDatePicker.setVisibleYearCount(600);
		beginDateBox.setStyleName("beginDateBox");
		dateFilterPanel.add(beginDateBox);
		
		dateFilterPanel.add(new Label("To: "));
		endDateBox = new DateBox();
		endDateBox.setFormat(new DateBox.DefaultFormat(dateTimeFormat));
		DatePicker endDatePicker = endDateBox.getDatePicker();
		endDatePicker.setValue(STANDARD_END_DATE, true);
		endDatePicker.setYearAndMonthDropdownVisible(true);
		endDatePicker.setVisibleYearCount(600);
		endDateBox.setStyleName("endDateBox");
		dateFilterPanel.add(endDateBox);

		return dateFilterPanel;
	}
	
	/** 
	 * Method collects all inputs set by the user for the filter and creates a Filter object
	 * @return a Filter object
	 */
	public Filter getFilterValues(){
		Filter filter = filterable.getOldFilter();
		
		String country = null;
		String city = null;
		Date beginDate = STANDARD_BEGIN_DATE;
		Date endDate = STANDARD_END_DATE;
		float maxUncertainty = Float.MAX_VALUE;
		
		if (countrySuggestBox.getValue() != "") {
			country = countrySuggestBox.getValue();
			Console.log("Country Filter: " + country);
		}
		
		if (citySuggestBox.getValue() != "") {
			city = citySuggestBox.getValue();
			Console.log("City Filter: " + city);
		}
		
		if (inaccuracyCheckBox.getValue() == true && inaccuracyBox.getValue() != "") {
			maxUncertainty = Float.parseFloat(inaccuracyBox.getValue());
			Console.log("MaxUncertainty: " + maxUncertainty);
		}
		
		if (isDateFilter && beginDateBox.getValue() != null) {
			beginDate = beginDateBox.getValue();
			Console.log("Start date: " + beginDate);
		}
		
		if (isDateFilter && endDateBox.getValue() != null) {
			endDate = endDateBox.getValue();
			Console.log("End date: " + endDate);
		}
		
		filter.setCountry(country);
		filter.setCity(city);
		filter.setMaxUncertainty(maxUncertainty);
		if (isDateFilter) {
			filter.setBeginDate(beginDate);
			filter.setEndDate(endDate);
		}
		
		return filter;
	}
	
	/**
	 * Resets all text boxes of the filter
	 */
	public void resetFilter() {
		countrySuggestBox.setValue("");
		citySuggestBox.setValue("");
		if (isDateFilter) {
			beginDateBox.setValue(STANDARD_BEGIN_DATE);
			endDateBox.setValue(STANDARD_END_DATE);
		}
		inaccuracyBox.setValue("");
		inaccuracyCheckBox.setValue(false);
	}
	
	public SuggestBox getCountrySuggestBox() {
		return countrySuggestBox;
	}
	
	public TextBox getStatusBox() {
		return statusBox;
	}
}
