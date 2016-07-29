package rubicscube;

import java.util.Arrays;
import java.util.Collections;

/*
 * Schema zum Rotieren/Vertauschen nach dem Model von Cube.getFragmentByPosition
 * Erste Position im inneren Array gibt an welcher Stein vertauscht werden muss
 * Zweite Position im inneren Array gibt an wohin der Stein verschoben wird
 */
public class CubeRotationSchema {
	private static int[][] horizontalRotationSchema = new int[][] { { 6, 0 }, { 8, 6 }, { 2, 8 }, 
		{ 3, 1 }, { 7, 3 }, { 5, 7 } }; // Schema ist gegen Uhrzeigersinn
	
	private static int[][] verticalRotationSchema = new int[][] { { 2, 0 }, { 20, 2 }, { 18, 20 }, 
		{ 11, 1 }, { 19, 11 }, { 9, 19 } }; // Schema ist gegen Uhrzeigersinn
	
	/*
	 * Kopiert das Schema und verschiebt alle Werte um den angegebenen Faktor(layer)
	 */
	private static int[][] getHorizontalSchemaByLayer(int layer) {
		int[][] matrix = deepCopyIntMatrix(horizontalRotationSchema);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] += layer * 9; // um n-Schichten jeden Wert verschieben
			}
		}
		return matrix;
	}
	
	/*
	 * Kopiert das Schema und verschiebt alle Werte um den angegebenen Faktor(row)
	 */
	private static int[][] getVerticalSchemaByLayer(int row) {
		int[][] matrix = deepCopyIntMatrix(verticalRotationSchema);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] += row * 3; // um n-Zeilen jeden Wert verschieben
			}
		}
		return matrix;
	}
	
	/*
	 * Erstellt für die angegebene Rotation das entsprechende Vertauschungsschema 
	 * anhand der Positionen von 0-26
	 */
	public static int[][] getRotationSchema(CubeRotation rotation, CubeDirection direction) {
		int[][] result = null;
		switch(rotation) {
			case HORIZONTALBOTTOM:
				result = getHorizontalSchemaByLayer(2);
				break;
			case HORIZONTALTOP:
				result = deepCopyIntMatrix(horizontalRotationSchema);
				break;
			case VERTICALBACK:
				result = getVerticalSchemaByLayer(2);
				break;
			case VERTICALFRONT: 
				result = deepCopyIntMatrix(verticalRotationSchema);
				break;
			case HORIZONTALWHOLE:
				result = concatAll(deepCopyIntMatrix(horizontalRotationSchema), getHorizontalSchemaByLayer(1), getHorizontalSchemaByLayer(2));
				break;
			case VERTICALWHOLE:
				result = concatAll(deepCopyIntMatrix(verticalRotationSchema), getVerticalSchemaByLayer(1), getVerticalSchemaByLayer(2));
				break;
			default:
				break;
		}
		
		// Wenn mit Uhrzeigersinn dann alle Werte tauschen
		if (result != null && direction == CubeDirection.CLOCKWISE) {
			for (int i = 0; i < result.length; i++) {
				int tmpInt = result[i][0];
				result[i][0] = result[i][1];
				result[i][1] = tmpInt;
			}
			// Alle Elemente im Array tauschen, da Reihenfolge genau anders herum sein muss
			Collections.reverse(Arrays.asList(result));
		}
		
		return result;
	}
	
	/*
	 * Erste eine tiefe Kopie einer int-Matrix
	 * von http://stackoverflow.com/questions/9106131/how-to-clone-a-multidimensional-array-in-java
	 */
	private static int[][] deepCopyIntMatrix(int[][] input) {
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
	private static int[][] concatAll(int[][] first, int[][]... rest) {
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
