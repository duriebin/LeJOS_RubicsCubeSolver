package rubicsmain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.Color;

/*
 * Sortiert Farben nach Ähnlichkeit
 */
public class ColorSorter {
	
	/*
	 * Sortiert alle Farben anhand deren HSB-Werten.
	 * Dabei wird bei orange und gelb auch die Sättigung betrachtet.
	 */
	public static ArrayList<float[]> sortColors(ArrayList<float[]> colors) {
		ArrayList<float[]> result = new ArrayList<>(colors);
		Collections.sort(result, new Comparator<float[]>() {

			@Override
			public int compare(float[] o1, float[] o2) {
				float calibrationConstance = 689; // Kalibrierung der Raw-Sensorwerte
				
				float[] hsb1 = Color.RGBtoHSB((int)(o1[0] * calibrationConstance), (int)(o1[1] * calibrationConstance), (int)(o1[2] * calibrationConstance), null);
				float[] hsb2 = Color.RGBtoHSB((int)(o2[0] * calibrationConstance), (int)(o2[1] * calibrationConstance), (int)(o2[2] * calibrationConstance), null);
				float hue1 = hsb1[0] < 0.03f && hsb1[2] > 0.88f ? 1 - hsb1[0] : hsb1[0]; // weiß ist manchmal ein sehr kleiner Wert ==> richtigen Wert daraus machen
				float hue2 = hsb2[0] < 0.03f && hsb2[2] > 0.88f ? 1 - hsb2[0] : hsb2[0];
				float diff = hue1 - hue2;
				
				// Wenn Hue zwischen 0.045 und 0.11 liegt handelt es sich um orange oder gelb.
				// Um diese zu Unterscheiden muss auf die Sättigung geachtet werden. (orange > gelb)
				// Hue < 0.045 ist rot. Hue ca. 1 ist weiß.
				if (hue1 < 0.11f && hue1 > 0.045f && hue2 < 0.11f && hue2 > 0.045f) {
					diff = hsb2[1] - hsb1[1]; // Vertauscht, da zwar für rot - orange - gelb die Hue zunimmt, jedoch die Sättigung abnimmt
				}
				if (diff < 0) {
					return -1;
				} else if(diff > 0) {
					return 1;
				}
				return 0;
			}
		});
		return result;
	}
	
	/*
	 * Sortiert eine Liste mit Werten so, 
     * dass die Differenz der Werte zum Vorgänger und Nachfolger möglichst gering ist.
     * Die float-Arrays werden dabei nicht kopiert ==> Referenzen bleiben bestehen
	 */
//	public static ArrayList<float[]> sortColors(ArrayList<float[]> colors) {
//		ArrayList<float[]> result = new ArrayList<>();
//		
//		// Liste welche die aktuellen Differenzen speichert
//		// z.B.: differenceList.get(0) enthält die Differenze zwischen result.get(0) und result.get(1)
//		ArrayList<Float> differenceList = new ArrayList<>();
//		
//		// Alle Farben durchgehen
//		for (int i = 0; i < colors.size(); i++) {
//			int bestIndex = 0;
//			for (int j = 0; j < result.size() - 1; j++) { // -1 weil letztes Element bei Vorgängerdurchlauf überprüft wird
//
//				// Differenze zum Vorgänger
//				float differencePredecessor  = Utilitis.calcDifference(colors.get(i), result.get(j));
//				
//				// Differenze zum Nachfolger
//				float differenceFollower = Utilitis.calcDifference(colors.get(i), result.get(j + 1));
//				
//				// Wenn Differenze zum Vorgänger kleiner ist als die aktuelle Differenz zum Nachfolger,
//				// muss aktueller Wert vor oder nach aktuellem Element eingeordnet werden
//				if (differencePredecessor < differenceList.get(j)) {
//					
//					// Entscheiden, ob Element vor Vorgänger oder nach Vorgänger eingefügt werden 
//					if (differenceFollower < differenceList.get(j)) {
//						bestIndex = j + 1; // nach Vorgänger einfügen
//					} else {
//						bestIndex = j; // vor Vorgänger einfügen
//					}
//					break; // Beste Position gefunden
//				} else {
//					
//					// Wenn Vorgänger und Nachfolger nicht passen, dann hinten ergänzen
//					bestIndex = j + 2;
//				}
//				
//			}
//			result.add(bestIndex, colors.get(i));
//			
//			// Differenzliste aktualisieren
//			if (bestIndex > 0) {
//				
//				// Vorgängerdifferenz updaten oder wenn bestIndex letztes Element ist dann hinzufügen
//				if (differenceList.size() == bestIndex - 1) {
//					
//					// hinzufügen
//					differenceList.add(Utilitis.calcDifference(result.get(bestIndex - 1), result.get(bestIndex)));
//				} else {
//					
//					// update
//					differenceList.set(bestIndex - 1, Utilitis.calcDifference(result.get(bestIndex - 1), result.get(bestIndex)));
//				}
//			}
//			if (bestIndex < result.size() - 1) {
//				differenceList.add(bestIndex, Utilitis.calcDifference(result.get(bestIndex), result.get(bestIndex + 1)));
//			}
//		}
//		return result;
//	}
}
