package common;

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
}
