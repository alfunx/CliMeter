package ch.uzh.ifi.rerg.se16_climeter.client;

import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;

import com.google.gwt.layout.client.Layout.Alignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * The class Timeline
 * 
 * @author 		Joachim Baumann
 * @history 	2016-11-09 JB Initial Commit
 * @version 	2016-11-09 JB 1.0
 * @responsibilities 
 * 				This class inherits from the class Visualisation.
 */
public class Timeline extends Visualisation {

	public Timeline() {

		final RangeSlider timelineSlider = new RangeSlider();
//		timelineSlider.setStyleName("timelineSlider");
		timelineSlider.setMin(1900);
		timelineSlider.setMax(2000);
		timelineSlider.setWidth("400px");
		
		VerticalPanel timelinePanel = new VerticalPanel();
		timelinePanel.addStyleName("timelinePanel");
		timelinePanel.setHorizontalAlignment(VerticalPanel.ALIGN_CENTER);
		timelinePanel.add(new Label("Timeline"));
/*		timelinePanel.setHorizontalAlignment(VerticalPanel.ALIGN_LEFT);
		timelinePanel.add(new Label("1900"));
		timelinePanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
		timelinePanel.add(new Label("2000"));	*/
		timelinePanel.setSize("50%", "50%");
		timelinePanel.add(timelineSlider);


		
		
//		panel.add(timelineSlider);
		panel.add(timelinePanel);

	} 
}
