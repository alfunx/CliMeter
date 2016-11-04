package ch.uzh.ifi.rerg.se16_climeter.client;

import java.util.Date;

import com.google.gwt.cell.client.DateCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;

import java.text.DateFormat;


public class Table extends Visualisation implements Exportable{
	
	private CellTable<Data> table;
	
	
	public Table(){
		initTable();
	}
	
	/*
	 * init a new table 
	 */
	private void initTable() {
		table  = new CellTable<Data>();
		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);
		
		// create header cells
		
		 DateCell dateCell = new DateCell();
		 Column<Data, Date> dateColumn = new Column<Data, Date>(dateCell) {
			 
			 @Override
			 public Date getValue(Data object) {
				 return object.getDate();
		     }
		  };
		  
		  table.addColumn(dateColumn, "Date");
		  
		  
		    
		
		// add styles
		
		
		
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

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
