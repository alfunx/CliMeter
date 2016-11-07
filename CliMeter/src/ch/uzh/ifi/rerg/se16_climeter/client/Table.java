package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Label;




public class Table extends Visualisation implements Exportable{
	
	private DataGrid<Data> table;
	
	
	
	public Table(){
		initTable();
	}
	
	/*
	 * init a new table 
	 */
	private void initTable() {
		table  = new DataGrid<Data>();
		
		/*
	    * Do not refresh the headers every time the data is updated. The footer
	    * depends on the current data, so we do not disable auto refresh on the
	    * footer.
	    */
		table.setAutoHeaderRefreshDisabled(true);
		
		
		// Set the message to display when the table is empty.
	    table.setEmptyTableWidget(new Label("Table does not contain any data"));
		
		// create header cells
		
		// add dates
		
		DateCell dateCell = new DateCell();
		Column<Data, Date> dateColumn = new Column<Data, Date>(dateCell) {
			 
			@Override
			public Date getValue(Data object) {
				return object.getDate();
		    }
		};
		table.addColumn(dateColumn, "Date");
		 
		// add aveTemps
		
		Column<Data, Number> aveTempColumn = new Column<Data, Number>(new NumberCell()) {
			@Override
			public Double getValue(Data object) {
				return object.getAverageTemperature();
			}
			
		};
		table.addColumn(aveTempColumn, "AveTemperature");
		
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
		
		// add country
		
		TextColumn<Data> countryColumn = new TextColumn<Data>() {
			@Override
			public String getValue(Data object) {
				return object.getCountry();
				}
		};
		table.addColumn(countryColumn, "Country");
		
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
		
		ArrayList<Data> DATA = new ArrayList<Data>();
		
		Data testData = new Data();
		testData.setAverageTemperature(23.4);
		testData.setCity("Winterthur");
		testData.setUncertainty(0.5);
		testData.setCountry("Switzerland");
		testData.setDate(new Date());
		testData.setLatitude(123456);
		testData.setLongitude(7890);
		DATA.add(testData);
		
		Data testData1 = new Data();
		testData1.setAverageTemperature(15);
		testData1.setCity("Stockholm");
		testData1.setUncertainty(0.9);
		testData1.setCountry("Sweden");
		testData1.setDate(new Date());
		testData1.setLatitude(1357);
		testData1.setLongitude(2468);
		DATA.add(testData1);
		
		/*
		 * END TEST DATA
		 */
	
		
		table.setRowCount(DATA.size(), true);
		table.setRowData(0, DATA);
		
		panel.add(table);
	}
	
	/*
	 * add a Data object to the table
	 * @param Data object
	 */
	public void addData(Data data){
		
		// get all the information out of the object 
		
		
		Date date = data.getDate();
		double averageTemperature = data.getAverageTemperature();
		double uncertainty = data.getUncertainty();
		String city = data.getCity();
		String country = data.getCountry();
		double latitude = data.getLatitude();
		double longitude = data.getLongitude();
		
		
			
	}
	
	/*
	 * add an arrayList with Data objects to the table
	 * @param Data objects in arrayList
	 */
	public void addData(ArrayList<Data> data){
		
	}
	
	

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
