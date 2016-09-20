package rubicsmain;

import java.util.ArrayList;

import common.Utilitis;

import java.util.AbstractMap.SimpleEntry;

/*
 * Sortiert die Farben nach �hnlichkeit, wobei immer die 9 am �hnlichsten Farben gesucht werden
 */
public class ColorSorter {
	
	/*
	 * Sortiert eine Liste mit Werten so, 
     * dass die Differenz der Werte zum Vorg�nger und Nachfolger m�glichst gering ist.
     * Die float-Arrays werden dabei nicht kopiert ==> Referenzen bleiben bestehen
	 */
	public static ArrayList<float[]> sortColors(ArrayList<float[]> colors) {
		ArrayList<float[]> result = new ArrayList<>();
		
		// Liste welche die aktuellen Differenzen speichert
		// z.B.: differenceList.get(0) enth�lt die Differenze zwischen result.get(0) und result.get(1)
		ArrayList<Float> differenceList = new ArrayList<>();
		
		// Alle Farben durchgehen
		for (int i = 0; i < colors.size(); i++) {
			int bestIndex = 0;
			for (int j = 0; j < result.size() - 1; j++) { // -1 weil letztes Element bei Vorg�ngerdurchlauf �berpr�ft wird

				// Differenze zum Vorg�nger
				float differencePredecessor  = Utilitis.calcDifference(colors.get(i), result.get(j));
				
				// Differenze zum Nachfolger
				float differenceFollower = Utilitis.calcDifference(colors.get(i), result.get(j + 1));
				
				// Wenn Differenze zum Vorg�nger kleiner ist als die aktuelle Differenz zum Nachfolger,
				// muss aktueller Wert vor oder nach aktuellem Element eingeordnet werden
				if (differencePredecessor < differenceList.get(j)) {
					
					// Entscheiden, ob Element vor Vorg�nger oder nach Vorg�nger eingef�gt werden 
					if (differenceFollower < differenceList.get(j)) {
						bestIndex = j + 1; // nach Vorg�nger einf�gen
					} else {
						bestIndex = j; // vor Vorg�nger einf�gen
					}
					break; // Beste Position gefunden
				} else {
					
					// Wenn Vorg�nger und Nachfolger nicht passen, dann hinten erg�nzen
					bestIndex = j + 2;
				}
				
			}
			result.add(bestIndex, colors.get(i));
			
			// Differenzliste aktualisieren
			if (bestIndex > 0) {
				
				// Vorg�ngerdifferenz updaten oder wenn bestIndex letztes Element ist dann hinzuf�gen
				if (differenceList.size() == bestIndex - 1) {
					
					// hinzuf�gen
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
