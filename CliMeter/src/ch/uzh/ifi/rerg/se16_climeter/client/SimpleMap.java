package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.user.client.ui.LayoutPanel;

public class SimpleMap extends AbstractMap {
	
	public SimpleMap() {
		super();
	}
	
	@Override
	protected void draw() {
		// zoom out for the clouds
		LatLng center = LatLng.newInstance(47.37174, 8.54226);
		MapOptions options = MapOptions.newInstance();
		options.setZoom(11);
		options.setCenter(center);
		options.setMapTypeId(MapTypeId.TERRAIN);
		
		// set mapWidget object
		mapWidget = new MapWidget(options);
		panel.add(mapWidget);
		mapWidget.setSize("100%", "100%");
	}
	
}
