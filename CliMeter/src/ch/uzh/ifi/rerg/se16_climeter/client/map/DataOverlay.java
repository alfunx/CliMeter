package ch.uzh.ifi.rerg.se16_climeter.client.map;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.Element;
import com.google.gwt.maps.client.MapImpl;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.mvc.MVCObject;
import com.google.gwt.maps.client.overlays.InfoWindow;
import com.google.gwt.maps.client.overlays.MapCanvasProjection;
import com.google.gwt.maps.client.overlays.OverlayView;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewMethods;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnAddHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnDrawHandler;
import com.google.gwt.maps.client.overlays.overlayhandlers.OverlayViewOnRemoveHandler;

/**
 * This class is a copy of OverlayView, to make a small adjustment on lines 
 * 72 and 90. InfoWindow will be shown on mouseover and hidden on mouseout.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-12-08 AM Initial commit
 * @version 	2016-12-08 AM 1.0
 * @responsibilities 
 * 				This class generates a JavaScriptObject displaying one 
 * 				data point.
 * 
 * @see 		{@link OverlayView}
 * @see			com.google.gwt.maps.client.overlays.OverlayView
 */
public class DataOverlay extends MVCObject<DataOverlay> {

	/**
	 * use newInstance();
	 */
	protected DataOverlay() {
	}

	/**
	 * You should inherit from this class by setting your overlay's prototype to new OverlayView.prototype. You must
	 * implement three methods: onAdd(), draw(), and onRemove(). In the add() method, you should create DOM objects and
	 * append them as children of the panes. In the draw() method, you should position these elements. In the onRemove()
	 * method, you should remove the objects from the DOM. You must call setMap() with a valid Map object to trigger the
	 * call to the onAdd() method and setMap(null) in order to trigger the onRemove() method. The setMap() method can be
	 * called at the time of construction or at any point afterward when the overlay should be re-shown after removing.
	 * The draw() method will then be called whenever a map property changes that could change the position of the
	 * element, such as zoom, center, or map type.
	 * 
	 * draw(): Implement this method to draw or update the overlay. This method is called after onAdd() and when the
	 * position from projection.fromLatLngToPixel() would return a new value for a given LatLng. This can happen on change
	 * of zoom, center, or map type. It is not necessarily called on drag or resize.
	 * 
	 * onAdd(): Implement this method to initialize the overlay DOM elements. This method is called once after setMap() is
	 * called with a valid map. At this point, panes and projection will have been initialized.
	 * 
	 * onRemove(): Implement this method to remove your elements from the DOM. This method is called once following a call
	 * to setMap(null).
	 * 
	 * @return {@link OverlayView}
	 */
	public final static DataOverlay newInstance(MapWidget mapWidget, OverlayViewOnDrawHandler onDrawHandler,
			OverlayViewOnAddHandler onAddDandler, OverlayViewOnRemoveHandler onRemoveHandler, Element panel, InfoWindow infoWindow) {
		return createJso(mapWidget.getJso(), onDrawHandler, onAddDandler, onRemoveHandler, panel, infoWindow).cast();
	}

	private final static native JavaScriptObject createJso() /*-{
		return new new $wnd.google.maps.OverlayView();
	}-*/;

	private final static native JavaScriptObject createJso(MapImpl map, OverlayViewOnDrawHandler onDrawHandler,
			OverlayViewOnAddHandler onAddHandler, OverlayViewOnRemoveHandler onRemoveHandler, Element panel, InfoWindow infoWindow) /*-{
		function MapOverlay(map) {
			// setMap() will be called manually when adding a point
			// this.setMap(map);
		}

		MapOverlay.prototype = new $wnd.google.maps.OverlayView();

		MapOverlay.prototype.onAdd = function() {
			$entry(@com.google.gwt.maps.client.overlays.OverlayView::onAddCallback(Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewOnAddHandler;Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewMethods;)(onAddHandler, this));
		};

		MapOverlay.prototype.onRemove = function() {
			$entry(@com.google.gwt.maps.client.overlays.OverlayView::onRemoveCallback(Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewOnRemoveHandler;Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewMethods;)(onRemoveHandler, this));
		};

		MapOverlay.prototype.draw = function() {
			$entry(@com.google.gwt.maps.client.overlays.OverlayView::onDrawCallback(Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewOnDrawHandler;Lcom/google/gwt/maps/client/overlays/overlayhandlers/OverlayViewMethods;)(onDrawHandler, this));			
		};

		// add click handler with info window
		$wnd.google.maps.event.addDomListener(panel, 'mouseover', function() {
			infoWindow.open(map);
		});
		$wnd.google.maps.event.addDomListener(panel, 'mouseout', function() {
			infoWindow.close();
		});

		var jso = new MapOverlay(map);
		return jso;
	}-*/;

	private final static void onDrawCallback(OverlayViewOnDrawHandler handler, OverlayViewMethods methods) {
		handler.onDraw(methods);
	}

	private final static void onAddCallback(OverlayViewOnAddHandler handler, OverlayViewMethods methods) {
		handler.onAdd(methods);
	}

	private final static void onRemoveCallback(OverlayViewOnRemoveHandler handler, OverlayViewMethods methods) {
		handler.onRemove(methods);
	}

	/**
	 * set Map
	 * 
	 * @param mapWidget
	 */
	public final void setMap(MapWidget mapWidget) {
		if (mapWidget == null) {
			setMapImpl(null);
		} else {
			setMapImpl(mapWidget.getJso());
		}
	}

	/**
	 * Invokes this.setMap()
	 */
	public final native void close() /*-{
		this.setMap();
	}-*/;

	/**
	 * Invokes this.setMap()
	 */
	public final native void clear() /*-{
		this.setMap();
	}-*/;

	/**
	 * Returns the MapCanvasProjection object associated with this OverlayView. The projection is not initialized until
	 * onAdd is called by the API.
	 */
	public final native MapCanvasProjection getProjection() /*-{
		return this.getProjection();
	}-*/;

	private final native void setMapImpl(MapImpl map) /*-{
		this.setMap(map);
	}-*/;

}
