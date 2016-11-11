package ch.uzh.ifi.rerg.se16_climeter.client;

import org.gwtbootstrap3.extras.slider.client.ui.RangeSlider;

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
		timelineSlider.setStyleName("timelineSlider");
		timelineSlider.setMin(1900);
		timelineSlider.setMax(2016);
		timelineSlider.setWidth("400px");
		panel.add(timelineSlider);

	} 
}
