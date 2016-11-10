package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;

/**
 * The class Table initializes a table and returns it in a panel.
 * 
 * @author 		Jonathan Stahl
 * @history 	2016-11-01 JS Initial Commit
 * 				2016-11-03 JS Implemented table with FlexTable
 * 				2016-11-04 JS Changed table to a CellTable
 * 				2016-11-05 JS Changed table to a DataGrid
 * 				2016-11-09 JS Implemented sorting
 * 				2016-11-10 JS Implemented pager
 *          
 * @version 	2016-11-08 JS 1.0
 * @responsibilities 
 * 				This class inherits from the class Visualisation. It initializes 
 * 				a table in form of a DataGrid. Then adds 
 * 				data from an ArrayList and wraps it in a panel.
 * 					  
 */
public class Table extends Visualisation implements Exportable{
	
	private DataGrid<Data> table;
	
	// create a dataProvider which handles updating the data in table 
	private ListDataProvider<Data> dataProvider = new ListDataProvider<Data>();
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
	    SimplePager pager = new SimplePager();
	    pager.addStyleName("pager");
	    pager.setDisplay(table);
	    
	    // set how many rows per page
		pager.setPageSize(200);
		
		// set table as display of dataProvider
		dataProvider.addDataDisplay(table);
		
	    
		/*
		 * create columns with header cells
		 */
		
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
		uncertainityColumn.setSortable(true);
		
		
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
		latitudeColumn.setSortable(true);
		
		
		// add longitude
		Column<Data, Number> longitudeColumn = new Column<Data, Number>(new NumberCell()) {
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
		
		
		/*
		 * TEST DATA!!
		 */
		

		addData(data, table, dataProvider);
		
		
		/*
		 * END TEST DATA
		 */

		
		// create SortHandler
	    ListHandler<Data> columnSortHandler = new ListHandler<Data>(dataList);

	    // create Comparator for dateColumn
	    columnSortHandler.setComparator(dateColumn, new Comparator<Data>() {
			
	    	@Override
			public int compare(Data o1, Data o2) {
	    		/*
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
				 * return 0 => o1 and o2 are equal
				 * return 1 => o1 is greater than o2
				 * return -1 => o1 is less than o2
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
		
		// create docklayoutPanel to organize the view of table and pager
		DockLayoutPanel docklayoutPanel = new DockLayoutPanel(Unit.EM);
		docklayoutPanel.addSouth(pager, 3);
		docklayoutPanel.add(table);
		
		// add docklayoutPanel to panel
		panel.add(docklayoutPanel);
	
		return table;
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

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
