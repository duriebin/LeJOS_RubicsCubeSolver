package common;

import java.util.ArrayList;
import java.util.Arrays;

public class Utilitis {
	
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
			result.set(i, Utilitis.copy(list.get(i)));
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
			for (int i = 0; i < first.length; i++) {
				result += Math.abs(first[i] - seconde[i]);
			}
		}
		
		return result;
	}
}
