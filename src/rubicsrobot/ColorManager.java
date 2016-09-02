package rubicsrobot;

/*
 * Ordnet ähnliche Farben zu
 */
public class ColorManager {
	private static ColorManager instance = null;
	
	private ColorManager() {
		
	}
	
	/*
	 * Singelton-Pattern
	 */
	public static ColorManager getInstance() {
		if (instance == null) {
			instance = new ColorManager();
		}
		return instance;
	}
	
	public int parseRGB(float[] rgb) {
		return 1;
	}
}
