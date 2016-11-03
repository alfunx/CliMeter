package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.user.client.ui.FlexTable;

public class Table extends Visualisation implements Exportable{
	
	private FlexTable table;
	
	
	public Table(){
		initTable();
	}
	
	
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

	@Override
	public void export() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
