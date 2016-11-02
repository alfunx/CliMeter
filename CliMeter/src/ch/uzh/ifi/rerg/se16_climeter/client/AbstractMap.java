package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;

public abstract class AbstractMap extends Composite {
	
	protected LayoutPanel panel;
	protected MapWidget mapWidget;
	
	public AbstractMap() {
		panel = new LayoutPanel();
		initWidget(panel);
		draw();
	}
	
	protected abstract void draw();
	
}
