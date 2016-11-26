package ch.uzh.ifi.rerg.se16_climeter.client.map;

/**
 * The class ColorTransition calculates a color in a color transition 
 * between two colors for a given value in a set.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-16 AM Initial Commit
 * 				2016-11-26 AM Added more checks
 * @version 	2016-11-26 AM 1.1
 * @responsibilities 
 * 				This class calculates a color for a given value.
 */
public class ColorTransition {
	
	private Color[] color;
	private double min;
	private double max;
	
	/**
	 * Initialize ColorTransition object. color[0] should contain the color 
	 * for the minimum value, color[n - 1] should contain the color for the 
	 * maximum value for n = color.length.
	 * @pre min != max
	 * @post min < max
	 * @param color array of colors for the color transition
	 */
	public ColorTransition(Color[] color, double min, double max) {
		if (max == min) {
			throw new IllegalArgumentException("\"min\" must not be equal to \"max\".");
		} else if (max < min) {
			this.min = max;
			this.max = min;
		} else {
			this.min = min;
			this.max = max;
		}
		
		if (color == null) {
			setDefaultColors();
			return;
		}
		
		for(int i = 0; i < color.length; i++) {
			if (color[i] == null) {
				setDefaultColors();
				return;
			}
		}
		
		this.color = color;
	}
	
	/**
	 * Initialize ColorTransition object with default colors.
	 * @pre min != max
	 * @post min < max
	 */
	public ColorTransition(double min, double max) {
		this(null, min, max);
	}
	
	/**
	 * Set color array with default colors.
	 * @pre -
	 * @post -
	 */
	private void setDefaultColors() {
		this.color = new Color[3];
		this.color[0] = new Color(0, 255, 0);
		this.color[1] = new Color(255, 255, 0);
		this.color[2] = new Color(255, 0, 0);
	}
	
	/**
	 * Calculate color based on value.
	 * @pre max >= value >= min
	 * @post -
	 * @param value current value
	 * @param min minimum value of dataset
	 * @param max maximum value of dataset
	 * @return the corresponding color
	 */
	public Color getPercentageColor(double value) {
		double p = normalize(value, this.min, this.max);
		
		// return, if p == 1
		if (p == 1) {
			return this.color[this.color.length - 1];
		}
		
		// calculate normalized numbers for specific color range
		double q = p * (this.color.length - 1);
		int i = (int) q;
		p = normalize(q, i, i + 1);
		
		int r = (int) Math.round(this.color[i + 1].getRed() * p + this.color[i].getRed() * (1 - p));
		int g = (int) Math.round(this.color[i + 1].getGreen() * p + this.color[i].getGreen() * (1 - p));
		int b = (int) Math.round(this.color[i + 1].getBlue() * p + this.color[i].getBlue() * (1 - p));
		
		Color c = new Color(r, g, b);
		return c;
	}
	
	/**
	 * Calculates the normalized value of a temperature.
	 * @pre max != min
	 * @post 0 >= value@return >= 1
	 * @param value current value
	 * @param min minimum value of dataset
	 * @param max maximum value of dataset
	 * @return the normalized value
	 */
	protected double normalize(double value, double min, double max) {
		if (max == min) {
			throw new IllegalArgumentException("Divide by 0.");
		} else if (max < min) {
			double temp = max;
			max = min;
			min = temp;
		}
		
		if (value < min) {
			value = min;
		} else if (value > max) {
			value = max;
		}
		
		double p = (value - min) / (max - min);
		return (p < 0) ? 0 : (p > 1) ? 1 : p;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the color
	 */
	public Color[] getColor() {
		return color;
	}
	
}
