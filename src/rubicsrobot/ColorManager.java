package rubicsrobot;

import java.util.AbstractMap.SimpleEntry;

import common.Utilitis;

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
	
	/*
	 * ist public damit ColorManager mehrfach verwendet werden kann
	 */
	public ColorManager() {
		
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
	 * Gibt die Farbgruppe mit der entsprechenden Id zurück.
	 * Ist die Id nicht vorhanden, wird null zurückgegeben.
	 */
	private ArrayList<float[]> getColorGroupValuesById(int id) {
		ArrayList<float[]> result = null;
		for (SimpleEntry<Integer, ArrayList<float[]>> colorGroup : this.colorList) {
			if (colorGroup.getKey() == id) {
				result = colorGroup.getValue();
				break;
			}
		}
		return result;
	}
	
	/*
	 * Gibt alle Ids der Farbgruppen zurück
	 */
	private ArrayList<Integer> getAllColorGroupIds() {
		ArrayList<Integer> result = new ArrayList<>();
		for (SimpleEntry<Integer, ArrayList<float[]>> colorGroup : this.colorList) {
			result.add(new Integer(colorGroup.getKey()));
		}
		return result;
	}
	
	/*
	 * Ordnet die übergebenen RGB-Werte Gruppen nach Ähnlichkeit zu.
	 * Dabei wird eine Abweichung von bis zu 0,04 toleriert, 
	 * welche auf mindestens ein Element der Gruppe zutreffen muss.
	 * Liegen mehrere Treffer vor, so wird die ähnlichste Gruppe gewählt
	 */
	public int parseRGB(float[] rgb) {		
		float tolerance = .04f;
		ArrayList<Integer> ids = getAllColorGroupIds();
		Integer bestMatchId = -1;
		float bestMatchDifference = 1000;
		
		for (Integer id : ids) {
			ArrayList<float[]> colorGroupValues = getColorGroupValuesById(id);
			for (float[] colorGroupValue : colorGroupValues) {
				int matchCounter = 0;
				float difference = 0;
				for(int j = 0; j < colorGroupValue.length; j++) {
					
					// Toleranzbereich prüfen
					if (rgb[j] > colorGroupValue[j] - tolerance && rgb[j] < colorGroupValue[j] + tolerance) {
						matchCounter++;
						difference += Math.abs(rgb[j] - colorGroupValue[j]);
						
						// Alle 3 Werte müssen im Toleranzbereich liegen und
						// Gruppe muss ähnlicher sein als die bereits gefundene Gruppe, falls dies der Fall ist.
						if (matchCounter >= colorGroupValue.length && difference < bestMatchDifference) {
							bestMatchId = id;
						}
					} else {
						break;
					}
				}
			}
		}
		
		// Wenn ähnliche Gruppe gefunden wurde,
		// dann rgb-Wert ergänzen
		if (bestMatchId != -1) {
			
			// zugeordnete RGB-Werte zur Auflistung ergänzen
			// Kopie dazu erstellen, damit keine Probleme auftreten, 
			// falls die rgb-Referenz geändert wird
			getColorGroupValuesById(bestMatchId).add(Utilitis.copy(rgb));
		} else {
			
			// wenn keine Zuordnung möglich war, dann neue Gruppe anlegen
			ArrayList<float[]> l = new ArrayList<>();
			l.add(Utilitis.copy(rgb));
			bestMatchId = this.colorList.size() + 1;
			this.colorList.add(new SimpleEntry<Integer, ArrayList<float[]>>(bestMatchId, l));
		}
		
		return bestMatchId;
	}
	
	/*
	 * Berechnet aus einer Gruppe von gleichartigen Farben die Durchschnittswerte
	 * Wenn Gruppe nicht vorhanden ist, wird -1 zurückgegeben als erstes und 
	 * einzigestes Element des Float-Arrays.
	 * Ansonsten wird ein Float-Array mit 3 Werten (in diesem Projekt sind es 3, 
	 * ist aber nicht zwingend so) zurückgegeben mit den entsprechenden Durchschnittswerten.
	 * Der erste Wert in der Auflistung wird dabei ignoriert, da jener meist am ungenausten ist.
	 */
	public float[] calculateAverage(int colorGroupId) {
		float[] result = new float[] { -1f }; // Default Returnwert
		
		// alle Farbgruppen durchgehen
		for (int i = 0; i < this.colorList.size(); i++) {
			
			// Wenn richtige Gruppe gefunden wurde
			if (this.colorList.get(i).getKey() == colorGroupId) {
				ArrayList<float[]> rgbValues = this.colorList.get(i).getValue();
				if (!rgbValues.isEmpty()) {
					
					// Ergebnis definieren, in welchem die Durchschnittswerte berechnet werden
					result = new float[rgbValues.get(0).length];
					
					// Ergebnis initalisieren mit Nullen, damit schön addiert werden kann
					for (int j = 0; j < result.length; j++) {
						result[j] = 0;
					}
					
					int k = 0;
					// Alle RGB-Werte in der Gruppe durchgehen
					for (float[] rgbValue : rgbValues) {
						
						// Ersten Wert auslassen, da jener am ungenauesten
						if (k == 0) {
							k++;
							continue;
						}
						
						// RGB-Werte zu Ergebnis addieren
						for (int j = 0; j < rgbValue.length; j++) {
							result[j] += rgbValue[j];
						}
					}
					
					// Ergebnis durch Anzahl der RGB-Werte der Farbgruppe teilen und den ausgelassenen Wert berücksichtigen, 
					// um Durchschnitt zu berechnen
					for (int j = 0; j < result.length; j++) {
						result[j] = result[j] / (rgbValues.size() - 1);
					}
				}
			}
		}
		return result;
	}
}
