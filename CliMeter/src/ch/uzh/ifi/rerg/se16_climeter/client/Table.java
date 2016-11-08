package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.ColumnSortList;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

/**
 * The class Table initializes a table and returns it in a pane.
 * 
 * @author 		Jonathan Stahl
 * @history 	2016-11-01 JS Initial Commit
 * 				2016-11-03 JS Implemented table with FlexTable
 * 				2016-11-04 JS Changed table to a CellTable
 * 				2016-11-05 JS Changed table to a DataGrid
 *          
 * @version 	2016-11-08 JS 1.0
 * @responsibilities 
 * 				This class inherits from the class Visualisation. It initializes 
 * 				a table in form of a DataGrid. Then adds 
 * 				data in form of an ArrayList and wraps it in a panel.
 * 					  
 */
public class Table extends Visualisation implements Exportable{
	
	private DataGrid<Data> table;
	private List<Data> dataList;  // needed for ListDataProvider

	/**
	 * Constructor which initializes a new table and adds it to a panel
	 * @param data
	 */
	protected Table(ArrayList<Data> data){
		initTable(data);
	}
	
	/**
	 * init a new table
	 * @pre
	 * @post
	 * @param data An ArraList which contains all data added to the table
	 * @returns the initialized table
	 */
	private DataGrid<Data> initTable(ArrayList<Data> data) {
		table  = new DataGrid<Data>();
		
		// set size of table
		table.setRowCount(data.size(), true);
		
		// Do not refresh the headers every time the data is updated.
		table.setAutoHeaderRefreshDisabled(true);
		
		// Set the message to display when the table is empty.
	    table.setEmptyTableWidget(new Label("Table does not contain any data"));
	    
	    // create pager for page handling and set table as the display
	    SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER);
	    pager.setDisplay(table);
	  
	    
		// create columns with header cells
		
		// add dates
		DateCell dateCell = new DateCell();
		Column<Data, Date> dateColumn = new Column<Data, Date>(dateCell) {
			 
			@Override
			public Date getValue(Data object) {
				return object.getDate();
		    }
		};
		table.addColumn(dateColumn, "Date");
		dateColumn.setSortable(true);
		
		 
		// add aveTemps
		Column<Data, Number> avgTempColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getAverageTemperature();
			}
			
		};
		table.addColumn(avgTempColumn, "AvgTemperature");
		avgTempColumn.setSortable(true);
		
		
		//add uncertainty
		Column<Data, Number> uncertainityColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getUncertainty();
			}
			
		};
		table.addColumn(uncertainityColumn, "Uncertainity");
		
		
		// add city 	
		TextColumn<Data> cityColumn = new TextColumn<Data>() {
			@Override
			public String getValue(Data object) {
				return object.getCity();
				}
		};
		table.addColumn(cityColumn, "City");
		cityColumn.setSortable(true);
		
		// add country
		TextColumn<Data> countryColumn = new TextColumn<Data>() {
			@Override
			public String getValue(Data object) {
				return object.getCountry();
				}
		};
		table.addColumn(countryColumn, "Country");
		countryColumn.setSortable(true);
		
		
		// add latitude
		Column<Data, Number> latitudeColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getLatitude();
			}
			
		};
		table.addColumn(latitudeColumn, "Latitude");
		
		
		// add longitude
		Column<Data, Number> longitudeColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getLongitude();
			}
			
		};
		table.addColumn(longitudeColumn, "Longitude");
			
		// add style
		table.addStyleName("table");
		table.addColumnStyleName(0, "tableHeader");
		table.addColumnStyleName(1, "tableHeader");
		table.addColumnStyleName(2, "tableHeader");
		table.addColumnStyleName(3, "tableHeader");
		table.addColumnStyleName(4, "tableHeader");
		table.addColumnStyleName(5, "tableHeader");
		table.addColumnStyleName(6, "tableHeader");
		
		
		/*
		 * TEST DATA!!
		 */
		
		// create a dataProvider which handles updating the data 
		ListDataProvider<Data> dataProvider = new ListDataProvider<Data>();
		
		// set table as display of dataProvider
		dataProvider.addDataDisplay(table);
		addData(data, table, dataProvider);
		
		// set size of page 1 equal to the number of data objects
		pager.setPageSize(data.size());
		
		/*
		 * END TEST DATA
		 */
		
		// create SortHandler
	    ListHandler<Data> columnSortHandler = new ListHandler<Data>(dataList);
	    
	    // create Comparator for cityColumn
	    columnSortHandler.setComparator(cityColumn, new Comparator<Data>() {
			
			@Override
			public int compare(Data o1, Data o2) {
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
				if (o1 == o2) {
					return 0;
				}
				if (o1 != null){
					return (o2 != null) ? o1.getCity().compareTo(o2.getCity()) : 1;
				}
				return -1;
			}
		});
		table.addColumnSortHandler(columnSortHandler);
	    
		// add table to panel
		panel.add(table);
	
		return table;
	}
	
	/**NOT IMPLEMENTED YET
	 * @pre
	 * @post
	 * @param data
	 */
	public void addData(Data data){
		
		// get all the information out of the object 
		Date date = data.getDate();
		double avgTemperature = data.getAverageTemperature();
		double uncertainty = data.getUncertainty();
		String city = data.getCity();
		String country = data.getCountry();
		double latitude = data.getLatitude();
		double longitude = data.getLongitude();
		
		// to be continued...
			
	}
	
	/**
	 * adds an arrayList with Data objects to a DataGrid
	 * @pre
	 * @post
	 * @param data
	 * @param table
	 * @param dataProvider
	 */
	public void addData(ArrayList<Data> data, DataGrid<Data> table, ListDataProvider<Data> dataProvider){
		
		// sets dataProvider as holder of the data 
		dataList = dataProvider.getList();
		for(Data d : data){
			dataList.add(d);
		}	
	}
	
	

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
