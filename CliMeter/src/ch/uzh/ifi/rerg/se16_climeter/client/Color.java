package ch.uzh.ifi.rerg.se16_climeter.client;

/**
 * The class Color represents one RGB color.
 * 
 * @author 		Alphonse Mariyagnanaseelan
 * @history 	2016-11-16 AM Initial Commit
 * @version 	2016-11-16 AM 1.0
 * @responsibilities 
 * 				This class represents a color. Can convert an RGB color to 
 * 				a hex-color string.
 */
public class Color {
	
	private int red;
	private int green;
	private int blue;
	
	/**
	 * Initialize Color object.
	 * @pre -
	 * @post -
	 * @param red
	 * @param green
	 * @param blue
	 */
	public Color(int red, int green, int blue) {
		this.red = intTo2Bytes(red);
		this.green = intTo2Bytes(green);
		this.blue = intTo2Bytes(blue);
	}
	
	/**
	 * Convert RGB color to a hex-color string.
	 * @pre -
	 * @post -
	 * @return the Hex-Color string
	 */
	public String getHexString() {
		String str = "#"
				.concat(intTo2BytesStr(this.red))
				.concat(intTo2BytesStr(this.green))
				.concat(intTo2BytesStr(this.blue));
		return str;
	}
	
	/**
	 * Reduce int to the size of 2 bytes.
	 * @pre -
	 * @post 0 >= i >= 255
	 * @param i a number
	 * @return int with size of 2 bytes
	 */
	public int intTo2Bytes(int i) {
		return (i < 0) ? 0 : (i > 255) ? 255 : i;
	}
	
	/**
	 * Convert an int of 2 byte size to hex representation with length 2.
	 * @pre -
	 * @post -
	 * @param i a number
	 * @return hex representation of i with length 2
	 */
	public String intTo2BytesStr(int i) {
		return this.padding(Integer.toHexString(intTo2Bytes(i)));
	}
	
	/**
	 * Add padding to string, if it's shorter than length 2.
	 * @pre 1 <= str.length <= 2
	 * @post str.length() == 2
	 * @param str a string
	 * @return str with length 2
	 */
	public String padding(String str) {
		//assertTrue(str.length() <= 2 & str.length() >= 1);
		if (str.length() < 2) {
			str = "0" + str;
		}
		return str;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the red tone
	 */
	public int getRed() {
		return red;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param red the red tone to set
	 */
	public void setRed(int red) {
		this.red = red;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the green tone
	 */
	public int getGreen() {
		return green;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param green the green tone to set
	 */
	public void setGreen(int green) {
		this.green = green;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @return the blue tone
	 */
	public int getBlue() {
		return blue;
	}
	
	/**
	 * @pre -
	 * @post -
	 * @param blue the blue tone to set
	 */
	public void setBlue(int blue) {
		this.blue = blue;
	}
	
}
