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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;



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
 * @version 	2016-12-12 AM 1.4
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */

public class FilterMenu extends Visualisation {
	
	final static int FIRST_YEAR = 1730;
	final static int LAST_YEAR = 2015;
	
	final static Date STANDARD_BEGIN_DATE = new Date(FIRST_YEAR-1900, 0, 1);
	final static Date STANDARD_END_DATE = new Date(LAST_YEAR-1900, 11, 31);
	
	final static String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July",
			"August", "September", "October", "November", "December"};
	
	private Filterable filterable;
	
	private SuggestBox citySuggestBox;
	private SuggestBox countrySuggestBox;
	
	private boolean isTable;
	
	private ListBox beginYearListBox;
	private ListBox beginMonthListBox;
	private ListBox endYearListBox;
	private ListBox endMonthListBox;
	
	private CheckBox inaccuracyCheckBox;
	private TextBox inaccuracyBox;
	
	private TextBox statusBox;
	
	private CheckBox groupByYearCheckBox;
	

	public FilterMenu(Filterable filterable, boolean isTable){

		VerticalPanel filterMenuPanel = new VerticalPanel();
		this.filterable = filterable;
		this.isTable = isTable;
		
		
		filterMenuPanel.setSpacing(10);
		
		filterMenuPanel.add(initFilterTitle());
		filterMenuPanel.add(countryBox());
		filterMenuPanel.add(cityBox());
		if (isTable) {
			filterMenuPanel.add(addDateFilterPanel());
		}
		filterMenuPanel.add(addInaccuracyPanel());
		if(isTable) {
			filterMenuPanel.add(getGroupByYearCheckBox());
		}
		filterMenuPanel.add(addButtons());
		filterMenuPanel.add(initStatusBox());

		filterMenuPanel.setStyleName("filterMenuPanel");
		resetFilter();
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
		setStatus("", FilterStatus.red);
		
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
				Console.log("Filter applied.");
				setStatus("Updating data...", FilterStatus.yellow);
			}
			//TODO keyevent ENTER
		});
		applyButton.setStyleName("applyButton");
		
		Button resetButton = new Button("Reset", new ClickHandler() {
			public void onClick(ClickEvent event) {
				resetFilter();
				filterable.apply(new Filter());
				Console.log("Reset successful.");
				setStatus("Resetting data...", FilterStatus.yellow);
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
	
	public Widget getGroupByYearCheckBox() {
		groupByYearCheckBox = new CheckBox("Group by year avg.");
		groupByYearCheckBox.setValue(true);
		
		return groupByYearCheckBox;
	}
	
	/**
	 * Creates date pickers with list boxes for date filtering
	 * @return a panel with date filter option
	 */
	public Widget addDateFilterPanel() {

		VerticalPanel dateFilterPanel = new VerticalPanel();
		HorizontalPanel beginDatePanel = new HorizontalPanel();
		HorizontalPanel endDatePanel = new HorizontalPanel();
		
		beginYearListBox = new ListBox();
		endYearListBox = new ListBox();
		for (int i = FIRST_YEAR; i <= LAST_YEAR; i++){
			beginYearListBox.addItem(i+"");
			endYearListBox.addItem(i+"");
			
		}
		beginDatePanel.add(beginYearListBox);
		endDatePanel.add(endYearListBox);
		
		beginMonthListBox = new ListBox();
		endMonthListBox = new ListBox();
		for (int i = 0; i < MONTHS.length; i++) {
			beginMonthListBox.addItem(MONTHS[i]);
			endMonthListBox.addItem(MONTHS[i]);
		}
		beginDatePanel.add(beginMonthListBox);
		endDatePanel.add(endMonthListBox);
		
		dateFilterPanel.add(new Label("Dates from: "));
		dateFilterPanel.add(beginDatePanel);
		
		dateFilterPanel.add(new Label("To: "));
		dateFilterPanel.add(endDatePanel);

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
		Boolean groupByYear = true;
		
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
		
		if (isTable) {
			String year = beginYearListBox.getSelectedValue();
			String month = beginMonthListBox.getSelectedValue();
			
			DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MMMM-dd");
		    beginDate = dateTimeFormat.parse(year + "-" + month + "-01");
			Console.log("Start date: " + beginDate);
		}
		
		if (isTable) {
			String year = endYearListBox.getSelectedValue();
			String month = endMonthListBox.getSelectedValue();
			
			DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy-MMMM-dd");
		    endDate = dateTimeFormat.parse(year + "-" + month + "-01");
			Console.log("End date: " + endDate);
		}
		
		if (isTable) {
			groupByYear = groupByYearCheckBox.getValue();
		}
		
		filter.setCountry(country);
		filter.setCity(city);
		filter.setMaxUncertainty(maxUncertainty);
		if (isTable) {
			filter.setBeginDate(beginDate);
			filter.setEndDate(endDate);
		}
		filter.setGroupByYear(groupByYear);
		
		return filter;
	}
	
	/**
	 * Resets all text boxes of the filter
	 */
	public void resetFilter() {
		countrySuggestBox.setValue("");
		citySuggestBox.setValue("");
		if (isTable) {
			beginYearListBox.setSelectedIndex(0);
			endYearListBox.setSelectedIndex(LAST_YEAR-FIRST_YEAR);
			beginMonthListBox.setSelectedIndex(0);
			endMonthListBox.setSelectedIndex(11);
			groupByYearCheckBox.setValue(true);
		}
		inaccuracyBox.setValue("");
		inaccuracyCheckBox.setValue(false);
	}
	
	/**
	 * Set the status of the FilterMenu.
	 * @pre -
	 * @post -
	 * @param text the status text
	 * @param filterStatus the color of the statusbox
	 */
	public void setStatus(String text, FilterStatus filterStatus) {
		statusBox.setText(text);
		switch (filterStatus) {
			case green: statusBox.setStyleName("statusBoxReady");
				break;
			case yellow: statusBox.setStyleName("statusBoxLoading");
				break;
			case red: statusBox.setStyleName("statusBoxError");
				break;
		}
	}
	
}
