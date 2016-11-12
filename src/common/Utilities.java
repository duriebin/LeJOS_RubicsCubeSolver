package common;

import java.util.ArrayList;
import java.util.Arrays;

public class Utilities {
	
	/*
	 * Gibt das nächste Listenitem zurück.
	 * Wenn man sich am Ende der Liste befindet, wird das erste Element zurückgegeben.
	 */
	public static <T> T getNextArrayListItem(ArrayList<T> list, T currentElement) {
		int currentIndex = list.indexOf(currentElement);
		int nextIndex = currentIndex + 1;
		if (currentIndex == list.size() - 1) {
			nextIndex = 0;
		}
		return list.get(nextIndex);
	}
	
	/*
	 * Methode zum Klonen von Float-Arrays
	 */
	public static float[] copy(float[] array) {
		float[] copy = new float[array.length];
		for (int i = 0; i < array.length; i++) {
			copy[i] = array[i];
		}
		return copy;
	}
	
	/*
	 * Kopiert eine ArrayList mit Float-Arrays
	 */
	public static ArrayList<float[]> copy(ArrayList<float[]> list) {
		ArrayList<float[]> result = new ArrayList<>(list.size());
		for (int i = 0; i < list.size(); i++) {
			result.set(i, Utilities.copy(list.get(i)));
		}
		return result;
	}
	
	/*
	 * Erste eine tiefe Kopie einer int-Matrix
	 * von http://stackoverflow.com/questions/9106131/how-to-clone-a-multidimensional-array-in-java
	 */
	public static int[][] deepCopyIntMatrix(int[][] input) {
	    if (input == null) {
	        return null;
	    }
	    int[][] result = new int[input.length][];
	    for (int r = 0; r < input.length; r++) {
	        result[r] = input[r].clone();
	    }
	    return result;
	}
	
	/*
	 * Verbindet n-2D-int-Arrays
	 * von http://stackoverflow.com/questions/80476/how-can-i-concatenate-two-arrays-in-java
	 */
	public static int[][] concatAll(int[][] first, int[][]... rest) {
		int totalLength = first.length;
		for (int[][] array : rest) {
			totalLength += array.length;
		}
		int[][] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (int[][] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}
	
	/*
	 * Berechnet von zwei gleichlangen float-Arrays die Differenz der Werte.
	 * Sind die Arrays nicht gleich lang wird -1 zurückgegeben.
	 * Es werden dabei immer Absolutwerte addiert, sodass nur positive Differenzen möglich sind.
	 */
	public static float calcDifference(float[] first, float[] seconde) {
		float result = -1;
		if (first.length == seconde.length) {
			result = 0;
			for (int i = 0; i < first.length; i++) {
				result += Math.abs(first[i] - seconde[i]);  
			}
//			float[] hsbf = Color.RGBtoHSB((int)(first[0] * calibrationConstance), (int)(first[1] * calibrationConstance), (int)(first[2] * calibrationConstance), null);
//			float[] hsbs = Color.RGBtoHSB((int)(seconde[0] * calibrationConstance), (int)(seconde[1] * calibrationConstance), (int)(seconde[2] * calibrationConstance), null);
//			return hsbf[0] - hsbs[0];
//			result = 0;
//			for (int i = 0; i < first.length; i++) {
//				result += Math.pow(Math.abs(first[i] - seconde[i]) * 100, 10);
//			}
//			result = (float)Math.sqrt(result);
//			for (int i = 0; i < first.length; i++) {
//				result += Math.pow(Math.abs(first[i] - seconde[i]) * 100, 2); // hoch 2 damit sich große Abweichungen in einem Farbbereicht stärker auswirken
//			}
		}
		
		return result;
	}
}
