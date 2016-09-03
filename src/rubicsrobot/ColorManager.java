package rubicsrobot;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/*
 * Ordnet ähnliche Farben zu
 */
public class ColorManager {
	private static ColorManager instance = null;
	
	/*
	 * Auflistung für alle bisherigen geparsten RGB-Werte
	 * 
	 */
	private ArrayList<SimpleEntry<Integer, ArrayList<float[]>>> colorList = new ArrayList<>();
	
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
	
	/*
	 * Ordnet die übergebenen RGB-Werte Gruppen nach Ähnlichkeit zu.
	 * Dabei wird eine Abweichung von bis zu 0,04 toleriert, 
	 * welche auf mindestens ein Element der Gruppe zutreffen muss.
	 */
	public int parseRGB(float[] rgb) {
		boolean matched = false;
		int result = -1;
		
		// Liste mit bisherigen RGB-Werten durchsuchen und Zuordnung suchen 
		// mit einer Abweichung bis max. 0,04
		for (int i = 0; i < this.colorList.size() && !matched; i++) {
			SimpleEntry<Integer, ArrayList<float[]>> item = this.colorList.get(i);
			for(float[] rgbValue : item.getValue()) {
				int matchCounter = 0;
				for(int j = 0; j < rgbValue.length; i++) {
					if (rgb[j] > rgbValue[j] - 0.04f && rgb[j] < rgbValue[j] + 0.04f) {
						matchCounter++;
						
						// Alle 3 Werte müssen im Toleranzbereich liegen
						if (matchCounter >= rgbValue.length) {
							matched = true;
							
							// Schlüssel ist zugeordnete Farbidentifikation
							result = item.getKey();
							
							// zugeordnete RGB-Werte zur Auflistung ergänzen
							item.getValue().add(rgb);
							break;
						}
					}
				}
				if (matched) {
					break;
				}
			}
		}
		
		// wenn keine Zuordnung möglich war, dann neue Gruppe anlegen
		if (!matched) {
			ArrayList<float[]> l = new ArrayList<>();
			l.add(rgb);
			result = this.colorList.size() + 1;
			this.colorList.add(new SimpleEntry<Integer, ArrayList<float[]>>(result, l));
		}
		
		return result;
	}
}
