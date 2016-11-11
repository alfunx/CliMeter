package ch.uzh.ifi.rerg.se16_climeter.client;

public class ColorTransition {
	
	private static final int[] color1 = {255, 70, 70};
	private static final int[] color2 = {70, 70, 255};
	
	public static String getPercentageColor(double p) {
		int r = (int) Math.round((color1[0]) * p + (color2[0]) * (1 - p));
		int g = (int) Math.round((color1[1]) * p + (color2[1]) * (1 - p));
		int b = (int) Math.round((color1[2]) * p + (color2[2]) * (1 - p));
		return RgbToHex(r, g, b);
	}
	
	private static String RgbToHex(int r, int g, int b) {
		StringBuilder sb = new StringBuilder();
		sb.append('#')
		.append(intTo2BytesStr(r))
		.append(intTo2BytesStr(g))
		.append(intTo2BytesStr(b));
		return sb.toString();
	}
	
	private static String intTo2BytesStr(int i) {
		return pad(Integer.toHexString(intTo2Bytes(i)));
	}
	
	private static int intTo2Bytes(int i) {
		return (i < 0) ? 0 : (i > 255) ? 255 : i;
	}
	
	private static String pad(String str) {
		StringBuilder sb = new StringBuilder(str);
		if (sb.length()<2) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}
	
}
