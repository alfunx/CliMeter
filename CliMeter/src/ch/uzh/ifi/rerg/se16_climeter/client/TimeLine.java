package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.user.client.ui.ChangeListener;
import com.google.gwt.user.client.ui.Widget;

/**
 * The class TimeLine extends the SliderBar class
 * and creates a SliderBar object which is used
 * in the MapComposite class.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-23 TS First Version 
 * @version	2016-11-23 TS 1.0
 * 
 * @responsibilities 
 */
@SuppressWarnings("deprecation")
public class TimeLine extends SliderBar {

	public TimeLine(double minValue, double maxValue) {
		super(minValue, maxValue, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
		        }
		});
		
		this.setStepSize((maxValue-minValue)*12);

		this.setCurrentValue((minValue + maxValue)/2);
		this.setNumTicks((int) (maxValue - minValue));
	
		Console.log("Hola");
		
		this.setNumLabels(10);
		this.setSize("100%", "300%");
		this.addChangeListener(new ChangeListener() {

			@Override
			public void onChange(Widget sender) {
				Console.log("Ciao, you changed timeline!!");

				
			}
			
		});
	
		
	}
}