package ch.uzh.ifi.rerg.se16_climeter.client.map;

/**
 * The class ColorTransition calculates a color in a color transition 
 * between two colors for a given value in a set.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-16 AM Initial Commit
 * @version 	2016-11-16 AM 1.0
 * @responsibilities 
 * 				This class calculates a color for a given value.
 */
public class ColorTransition {
	
	private Color minColor;
	private Color maxColor;
	
	/**
	 * Initialize ColorTransition object.
	 * @pre minColor != null, maxColor != null
	 * @post -
	 * @param minColor color for min value
	 * @param maxColor color for max value
	 */
	public ColorTransition(Color minColor, Color maxColor) {
		if (minColor == null) {
			minColor = new Color(0, 0, 0);
		}
		if (maxColor == null) {
			maxColor = new Color(0, 0, 0);
		}
		
		this.minColor = minColor;
		this.maxColor = maxColor;
	}
	
	/**
	 * Initialize ColorTransition object with default colors.
	 * @pre -
	 * @post -
	 */
	public ColorTransition() {
		this(new Color(70, 70, 255), new Color(255, 70, 70));
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
	public Color getPercentageColor(double value, double min, double max) {
		double p = normalize(value, min, max);
		int r = (int) Math.round(maxColor.getRed() * p + minColor.getRed() * (1 - p));
		int g = (int) Math.round(maxColor.getGreen() * p + minColor.getGreen() * (1 - p));
		int b = (int) Math.round(maxColor.getBlue() * p + minColor.getBlue() * (1 - p));
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
		}
		if (max < min) {
			double temp = max;
			max = min;
			min = temp;
		}
		if (value < min) {
			value = min;
		}
		if (value > max) {
			value = max;
		}
		if (min < 0) {
			value += min;
			max += min;
			min += min;
		}
		
		double p = (value - min) / (max - min);
		return (p < 0) ? 0 : (p > 1) ? 1 : p;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the minColor
	 */
	public Color getMinColor() {
		return minColor;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param minColor the minColor to set
	 */
	public void setMinColor(Color minColor) {
		this.minColor = minColor;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the maxColor
	 */
	public Color getMaxColor() {
		return maxColor;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param maxColor the maxColor to set
	 */
	public void setMaxColor(Color maxColor) {
		this.maxColor = maxColor;
	}
	
}
