package ch.uzh.ifi.rerg.se16_climeter.client;

import com.google.gwt.widgetideas.client.SliderBar;
import com.google.gwt.widgetideas.client.SliderBar.LabelFormatter;

/**
 * The class TimeLine creates a SliderBar object which
 * is used to adjust the timespan on the map widget.
 * 
 * @author 	Timo Surbeck
 * @history 2016-11-23 TS First Version 
 * @version	2016-11-23 TS 1.0
 * 
 * @responsibilities 
 */
public class TimeLine {

	private SliderBar timeLine;
	private int startDate;
	private int endDate;

	public int getStartDate() {
		return startDate;
	}

	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}

	public int getEndDate() {
		return endDate;
	}

	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}

	public SliderBar getSliderBar() {
		int start = this.getStartDate();
		int end = this.getEndDate();
		timeLine = new SliderBar(start, end, new LabelFormatter() {
			@Override
			public String formatLabel(SliderBar slider, double value) {
				return (int) (10 * value) / 10 + "";
			}
		});
		
		timeLine.setStepSize(50);

		timeLine.setCurrentValue((start + end)/2);
		timeLine.setNumTicks((int) end - start);
	
		timeLine.setNumLabels(1);
		timeLine.setSize("100%", "50%");
		timeLine.setVisible(true);
		
		return timeLine;
	}
}
