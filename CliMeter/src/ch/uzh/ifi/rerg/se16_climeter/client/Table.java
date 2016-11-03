package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;
import java.text.DateFormat;
import com.google.gwt.i18n.client.NumberFormat;

import com.google.gwt.user.client.ui.FlexTable;

public class Table extends Visualisation implements Exportable{
	
	private FlexTable table;
	
	
	public Table(){
		initTable();
	}
	
	/*
	 * init a new table 
	 */
	private void initTable() {
		table  = new FlexTable();
		
		// create header cells
		
		table.setText(0, 0 , "Date");
		table.setText(0, 1 , "AveTemperature");
		table.setText(0, 2 , "Uncertainty");
		table.setText(0, 3 , "City");
		table.setText(0, 4 , "Country");
		table.setText(0, 5 , "Latitude");
		table.setText(0, 6 , "Longitude");
		
		// add styles
		
		table.setStyleName("table");
		table.getRowFormatter().addStyleName(0, "tableHeader");
		table.getCellFormatter().addStyleName(0, 0, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 1, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 2, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 3, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 4, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 5, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 6, "tableNumericColumn");
		table.getCellFormatter().addStyleName(0, 7, "tableNumericColumn");
		
		
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
		
		// fill in information into cells
		
		int row = table.getRowCount();
		
//		table.setText(row, 0, date.toString());
//		table.setText(row, 1, NumberFormat.);
//		table.setText(row, 2, date.toString());
//		table.setText(row, 3, date.toString());
//		table.setText(row, 4, date.toString());
//		table.setText(row, 5, date.toString());
//		table.setText(row, 6, date.toString());
		
		
		
	}

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
