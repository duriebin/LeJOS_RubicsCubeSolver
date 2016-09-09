package rubicsrobot;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;

/*
 * Ordnet �hnliche Farben zu
 */
public class ColorManager {
	private static ColorManager instance = null;
	
	/*
	 * Auflistung f�r alle bisherigen geparsten RGB-Werte
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
	 * Ordnet die �bergebenen RGB-Werte Gruppen nach �hnlichkeit zu.
	 * Dabei wird eine Abweichung von bis zu 0,04 toleriert, 
	 * welche auf mindestens ein Element der Gruppe zutreffen muss.
	 */
	public int parseRGB(float[] rgb) {
		boolean matched = false;
		int result = -1;
		
		float tolerance = .03f;
		
		// Liste mit bisherigen RGB-Werten durchsuchen und Zuordnung suchen 
		// mit einer Abweichung bis max. (Wert von tolerance)
		for (int i = 0; i < this.colorList.size() && !matched; i++) {
			SimpleEntry<Integer, ArrayList<float[]>> item = this.colorList.get(i);
			for(float[] rgbValue : item.getValue()) {
				int matchCounter = 0;
				for(int j = 0; j < rgbValue.length; j++) {
					if (rgb[j] > rgbValue[j] - tolerance && rgb[j] < rgbValue[j] + tolerance) {
						matchCounter++;
						
						// Alle 3 Werte m�ssen im Toleranzbereich liegen
						if (matchCounter >= rgbValue.length) {
							matched = true;
							
							// Schl�ssel ist zugeordnete Farbidentifikation
							result = item.getKey();
							
							// zugeordnete RGB-Werte zur Auflistung erg�nzen
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
		
		// wenn keine Zuordnung m�glich war, dann neue Gruppe anlegen
		if (!matched) {
			ArrayList<float[]> l = new ArrayList<>();
			l.add(rgb);
			result = this.colorList.size() + 1;
			this.colorList.add(new SimpleEntry<Integer, ArrayList<float[]>>(result, l));
		}
		
		return result;
	}
}
