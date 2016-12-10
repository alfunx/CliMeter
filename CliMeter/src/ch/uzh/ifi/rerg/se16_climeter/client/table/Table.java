package ch.uzh.ifi.rerg.se16_climeter.client.table;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.view.client.ListDataProvider;

import ch.uzh.ifi.rerg.se16_climeter.client.Console;
import ch.uzh.ifi.rerg.se16_climeter.client.Data;
import ch.uzh.ifi.rerg.se16_climeter.client.SQL;
import ch.uzh.ifi.rerg.se16_climeter.client.Visualisation;
import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filter;
import ch.uzh.ifi.rerg.se16_climeter.client.filter.FilterMenu;
import ch.uzh.ifi.rerg.se16_climeter.client.filter.FilterStatus;
import ch.uzh.ifi.rerg.se16_climeter.client.filter.Filterable;

/**
 * The class Table initializes a table and returns it in a panel.
 * 
 * @author 		Jonathan Stahl
 * @history 	2016-11-01 JS Initial commit
 * 				2016-11-03 JS Implemented table with FlexTable
 * 				2016-11-04 JS Changed table to a CellTable
 * 				2016-11-05 JS Changed table to a DataGrid
 * 				2016-11-09 JS Implemented sorting
 * 				2016-11-10 JS Implemented pager
 * 				2016-11-22 JS Moved filterMenu into table tab
 *          
 * @version 	2016-11-08 JS 1.0
 * @responsibilities 
 * 				This class inherits from the class Visualisation. It initializes 
 * 				a table in form of a DataGrid. Then adds 
 * 				data from an ArrayList and wraps it in a panel.
 * 					  
 */
public class Table extends Visualisation implements Filterable {
	
	private static final int PAGE_SIZE = 200;
	
	protected DataGrid<Data> table; // modifier changed to protected for JUnit tests
	
	// dataProvider which handles updating the data in table 
	private ListDataProvider<Data> dataProvider;
	private List<Data> dataList;  // needed for ListDataProvider
	
	private FilterMenu filterMenu;
	
	private SimplePager pager;
	
	private DateCell dateCell;
	private Column<Data, Date> dateColumn;
	private Column<Data, Number> avgTempColumn;
	private Column<Data, Number> uncertainityColumn;
	private TextColumn<Data> cityColumn;
	private TextColumn<Data> countryColumn;
	private Column<Data, Number> latitudeColumn;
	private Column<Data, Number> longitudeColumn;
	
	private ListHandler<Data> columnSortHandler;
	
	private boolean filterHidden;
	private Button toggleFilterButton;
	
	private DockLayoutPanel footerPanel;
	private DockLayoutPanel dockLayoutPanel;
	
 
	/**
	 * Constructor which initializes a new table and adds it to a panel
	 * @pre -
	 * @post table initialized
	 * @param data
	 */
	public Table(){
		initTable();
	}
	
	/**
	 * init a new table
	 * @pre table == null && dataProvider == null && dataList == null
	 * @post table != null 
	 * @param data, An ArraList which contains all data added to the table
	 * @returns the initialized table
	 */
	private DataGrid<Data> initTable() {
		table  = new DataGrid<Data>();
		
		dataProvider = new ListDataProvider<Data>();
		
		// create filterMenu
		initFilterMenu();
		
		// Do not refresh the headers every time the data is updated.
		table.setAutoHeaderRefreshDisabled(true);
		
		// Set the message to display when the table is empty.
	    table.setEmptyTableWidget(new Label("Table does not contain any data."));
	    
	    // create pager for page handling and set table as the display
	    pager = new SimplePager();
	    pager.addStyleName("pager");
	    pager.setDisplay(table);
	    
	    // set how many rows per page
		pager.setPageSize(PAGE_SIZE);
		
		// set table as display of dataProvider
		dataProvider.addDataDisplay(table);
		// sets dataProvider as holder of the data
		dataList = dataProvider.getList();
		
		// create columns with header cells
		initColumns();
		
		// add raw data
		addRawData();
		
		// Create sortHandler
		initSortHandler();
				
		// create footerpanel for pager and toggleButton
		footerPanel = new DockLayoutPanel(Unit.EM);
		footerPanel.addEast(toggleFilterButton, 5);
		footerPanel.add(pager);
		
		// create docklayoutPanel to organize the view of table, filter and pager
		dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		dockLayoutPanel.addEast(filterMenu.getPanel(), 15);
		//dockLayoutPanel.setWidgetHidden(filterMenu.getPanel(), false);
		dockLayoutPanel.addSouth(footerPanel, 3);
		dockLayoutPanel.add(table);
		
		// add docklayoutPanel to panel
		panel.add(dockLayoutPanel);
	
		return table;
	}
	

	/** Initialize filtermenu 
	 * @pre -
	 * @post filtermenu =! null
	 */
	private void initFilterMenu() {
		filterMenu = new FilterMenu(this);
		
		// create button to toggle filter visibility
		filterHidden = false;
		toggleFilterButton = new Button();
		toggleFilterButton.removeStyleName("gwt-Button");
		toggleFilterButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				toggleFilterButton.setFocus(false);
				dockLayoutPanel.setWidgetHidden(filterMenu.getPanel(), !filterHidden);
				dockLayoutPanel.animate(300);
				filterHidden = !filterHidden;
			}
		});
		toggleFilterButton.addStyleName("toggleFilterButton");
	}

	/** Initializes columnSortHandler and provides correct 
	 * 	comparing methods for each column
	 * @pre -
	 * @post columnSortHandler =! null
	 */
	private void initSortHandler() {
		columnSortHandler = new ListHandler<Data>(dataList);

	    // create Comparator for dateColumn
	    columnSortHandler.setComparator(dateColumn, new Comparator<Data>() {
			
	    	@Override
			public int compare(Data o1, Data o2) {
	    		/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					return (o2 != null) ? o1.getDate().compareTo(o2.getDate()) : 1;
				}
				return -1;
			}
		});
	    
	    // create Comparator for avgTempColumn
	    columnSortHandler.setComparator(avgTempColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					if (o2 != null) {
						if(o1.getAverageTemperature() == o2.getAverageTemperature()){
							return 0;
						}
						if(o1.getAverageTemperature() > o2.getAverageTemperature() ){
							return 1;
						}
						return -1;
					}
					else{
						return 1;
					}
				}
				return -1;
			}
		});
	    
	    // create Comparator for uncertainityColumn
	    columnSortHandler.setComparator(uncertainityColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					if (o2 != null) {
						if(o1.getUncertainty() == o2.getUncertainty()) {
							return 0;
						}
						if(o1.getUncertainty() > o2.getUncertainty()) {
							return 1;
						}
						return -1;
					}
					else {
						return 1;
					}
				}
				return -1;
			}
		});
	    
	    // create Comparator for cityColumn
	    columnSortHandler.setComparator(cityColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					return (o2 != null) ? o1.getCity().compareTo(o2.getCity()) : 1;
				}
				return -1;
			}
		});
	    
	    // create Comparator for countryColumn
	    columnSortHandler.setComparator(countryColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					return (o2 != null) ? o1.getCountry().compareTo(o2.getCountry()) : 1;
				}
				return -1;
			}
		});
	    
	    // create Comparator for latitudeColumn
	    columnSortHandler.setComparator(latitudeColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					if (o2 != null) {
						if(o1.getLatitude() == o2.getLatitude()){
							return 0;
						}
						if(o1.getLatitude() > o2.getLatitude() ){
							return 1;
						}
						return -1;
					}
					else{
						return 1;
					}
				}
				return -1;
			}
		});
	    
	    // create Comparator for longitudeColumn
	    columnSortHandler.setComparator(longitudeColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
				/*
				 * return 0 -> o1 and o2 are equal
				 * return 1 -> o1 is greater than o2
				 * return -1 -> o1 is less than o2
				 */
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					if (o2 != null) {
						if(o1.getLongitude() == o2.getLongitude()){
							return 0;
						}
						if(o1.getLongitude() > o2.getLongitude()){
							return 1;
						}
						return -1;
					}
					else{
						return 1;
					}
				}
				return -1;
			}
		});
	    
	    
	    // add SortHandler to table
		table.addColumnSortHandler(columnSortHandler);
	}

	/**
	 * Creates all columns of table
	 * @pre table != null 
	 * @post all columns of table are initialized
	 */
	private void initColumns() {
		// add dates
		dateCell = new DateCell(DateTimeFormat.getFormat("dd-MM-yyyy"));
		dateColumn = new Column<Data, Date>(dateCell) {
			 
			@Override
			public Date getValue(Data object) {
				return object.getDate();
		    }
		};
		table.addColumn(dateColumn, "Date");
		dateColumn.setSortable(true);
		
		 
		// add aveTemps
		avgTempColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Float getValue(Data object) {
				return object.getAverageTemperature();
			}
			
		};
		table.addColumn(avgTempColumn, "AvgTemperature");
		avgTempColumn.setSortable(true);
		
		
		//add uncertainty
		uncertainityColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getUncertainty();
			}
			
		};
		table.addColumn(uncertainityColumn, "Uncertainity");
		uncertainityColumn.setSortable(true);
		
		
		// add city 	
		cityColumn = new TextColumn<Data>() {
			@Override
			public String getValue(Data object) {
				return object.getCity();
				}
		};
		table.addColumn(cityColumn, "City");
		cityColumn.setSortable(true);
		
		// add country
		countryColumn = new TextColumn<Data>() {
			@Override
			public String getValue(Data object) {
				return object.getCountry();
				}
		};
		table.addColumn(countryColumn, "Country");
		countryColumn.setSortable(true);
		
		
		// add latitude
		latitudeColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getLatitude();
			}
			
		};
		table.addColumn(latitudeColumn, "Latitude");
		latitudeColumn.setSortable(true);
		
		
		// add longitude
		longitudeColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getLongitude();
			}
			
		};
		table.addColumn(longitudeColumn, "Longitude");
		longitudeColumn.setSortable(true);
			
		// add styles
		table.addStyleName("table");
		table.addColumnStyleName(0, "tableHeader");
		table.addColumnStyleName(1, "tableHeader");
		table.addColumnStyleName(2, "tableHeader");
		table.addColumnStyleName(3, "tableHeader");
		table.addColumnStyleName(4, "tableHeader");
		table.addColumnStyleName(5, "tableHeader");
		table.addColumnStyleName(6, "tableHeader");
	}
	
	/**
	 * Requests raw data from database and adds them to the table
	 * 
	 */
	public void addRawData(){
		SQL sql = new SQL();
		
		filterMenu.setStatus("Loading raw data...", FilterStatus.yellow);
		
		sql.getData(new Filter(), new AsyncCallback<ArrayList<Data>>() {
			@Override
			public void onFailure(Throwable caught) {
				Console.log("SQL error.");
				filterMenu.setStatus("SQL error.", FilterStatus.red);
			}

			@Override
			public void onSuccess(ArrayList<Data> result) {
				
//				if (result.isEmpty() == true) {
//					filterMenu.getStatusBox().setText("Connection error!");
//				    filterMenu.getStatusBox().setStyleName("statusBoxError");
//				}
				
				dataProvider.getList().clear();
			    dataProvider.getList().addAll(result);
			    dataProvider.flush();
			    dataProvider.refresh();
			    table.redraw();
			    Console.log("Raw data loaded.");
			    filterMenu.setStatus("Table ready.", FilterStatus.green);
			}
		});
	}
	
	@Override
	public void apply(Filter filter) {
		SQL sql = new SQL();
		
		
		sql.getData(filter, new AsyncCallback<ArrayList<Data>>() {
			@Override
			public void onFailure(Throwable caught) {
				Console.log("SQL error.");
				filterMenu.setStatus("SQL error.", FilterStatus.red);
			}

			@Override
			public void onSuccess(ArrayList<Data> result) {
				
//				if (result.size() == 0) {
//					filterMenu.getStatusBox().setText("No data found!");
//				    filterMenu.getStatusBox().setStyleName("statusBoxError");
//				}
				
				dataProvider.getList().clear();
			    dataProvider.getList().addAll(result);
			    dataProvider.flush();
			    dataProvider.refresh();
			    table.redraw();
			    Console.log("Table updated.");
			    filterMenu.setStatus("Table ready.", FilterStatus.green);
			}
		});
	
	}	

	@Override
	public Filter getOldFilter() {
		return new Filter();
	}

}
