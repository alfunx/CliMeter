package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.layers.TransitLayer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SimpleMap extends Composite{
	
	private final VerticalPanel panel;
	private MapWidget mapWidget;
	
	public SimpleMap() {
		panel = new VerticalPanel();
		initWidget(panel);
		draw();
	}
	
	private void draw() {
		panel.clear();
		
		// zoom out for the clouds
		LatLng center = LatLng.newInstance(40.74, -73.94);
		MapOptions opts = MapOptions.newInstance();
		opts.setZoom(11);
		opts.setCenter(center);
		opts.setMapTypeId(MapTypeId.TERRAIN);
		
		mapWidget = new MapWidget(opts);
		panel.add(mapWidget);
		mapWidget.setSize("300px", "300px");
		
		// show transit layer
		TransitLayer transitLayer = TransitLayer.newInstance();
		transitLayer.setMap(mapWidget);	
	}
	
}
