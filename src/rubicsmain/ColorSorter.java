package rubicsmain;

import java.util.ArrayList;

import common.Utilitis;

import java.util.AbstractMap.SimpleEntry;

/*
 * Sortiert die Farben nach Ähnlichkeit, wobei immer die 9 am ähnlichsten Farben gesucht werden
 */
public class ColorSorter {
	
	/*
	 * Sortiert eine Liste mit Werten so, 
     * dass die Differenz der Werte zum Vorgänger und Nachfolger möglichst gering ist.
     * Die float-Arrays werden dabei nicht kopiert ==> Referenzen bleiben bestehen
	 */
	public static ArrayList<float[]> sortColors(ArrayList<float[]> colors) {
		ArrayList<float[]> result = new ArrayList<>();
		
		// Liste welche die aktuellen Differenzen speichert
		// z.B.: differenceList.get(0) enthält die Differenze zwischen result.get(0) und result.get(1)
		ArrayList<Float> differenceList = new ArrayList<>();
		
		// Alle Farben durchgehen
		for (int i = 0; i < colors.size(); i++) {
			int bestIndex = 0;
			for (int j = 0; j < result.size() - 1; j++) { // -1 weil letztes Element bei Vorgängerdurchlauf überprüft wird

				// Differenze zum Vorgänger
				float differencePredecessor  = Utilitis.calcDifference(colors.get(i), result.get(j));
				
				// Differenze zum Nachfolger
				float differenceFollower = Utilitis.calcDifference(colors.get(i), result.get(j + 1));
				
				// Wenn Differenze zum Vorgänger kleiner ist als die aktuelle Differenz zum Nachfolger,
				// muss aktueller Wert vor oder nach aktuellem Element eingeordnet werden
				if (differencePredecessor < differenceList.get(j)) {
					
					// Entscheiden, ob Element vor Vorgänger oder nach Vorgänger eingefügt werden 
					if (differenceFollower < differenceList.get(j)) {
						bestIndex = j + 1; // nach Vorgänger einfügen
					} else {
						bestIndex = j; // vor Vorgänger einfügen
					}
					break; // Beste Position gefunden
				} else {
					
					// Wenn Vorgänger und Nachfolger nicht passen, dann hinten ergänzen
					bestIndex = j + 2;
				}
				
			}
			result.add(bestIndex, colors.get(i));
			
			// Differenzliste aktualisieren
			if (bestIndex > 0) {
				
				// Vorgängerdifferenz updaten oder wenn bestIndex letztes Element ist dann hinzufügen
				if (differenceList.size() == bestIndex - 1) {
					
					// hinzufügen
					differenceList.add(Utilitis.calcDifference(result.get(bestIndex - 1), result.get(bestIndex)));
				} else {
					
					// update
					Float dif = differenceList.get(bestIndex - 1);
					dif = Utilitis.calcDifference(result.get(bestIndex - 1), result.get(bestIndex));
				}
			}
			if (bestIndex < result.size() - 1) {
				differenceList.add(bestIndex, Utilitis.calcDifference(result.get(bestIndex), result.get(bestIndex + 1)));
			}
		}
		return result;
	}
}
