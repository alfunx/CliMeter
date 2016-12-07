package ch.uzh.ifi.rerg.se16_climeter.client.map;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.maps.client.events.MapEventType;
import com.google.gwt.maps.client.events.MapHandlerRegistration;
import com.google.gwt.maps.client.events.click.ClickEventFormatter;
import com.google.gwt.maps.client.events.click.ClickMapHandler;
import com.google.gwt.maps.client.overlays.OverlayView;

public class DataOverlay extends OverlayView {

	protected DataOverlay() {
		// GWT needs this
	}

	/**
	 * This event is fired when the marker icon was clicked.
	 * 
	 * @param handler
	 */
	public final HandlerRegistration addClickHandler(ClickMapHandler handler) {
		return MapHandlerRegistration.addHandler(this, MapEventType.CLICK, handler, new ClickEventFormatter());
	}

}
