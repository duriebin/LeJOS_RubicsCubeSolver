package rubicscube;

import java.util.Arrays;
import java.util.Collections;

import common.Utilities;

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
		
	private static int[][] forwardRotationSchema = new int[][] { { 18, 0 }, { 24, 18 }, { 6, 24 }, 
		{ 9, 3 }, { 21, 9 }, { 15, 21 } }; // Schema ist gegen Uhrzeigersinn
	
	/*
	 * Kopiert das Schema und verschiebt alle Werte um den angegebenen Faktor(layer)
	 */
	private static int[][] getHorizontalSchemaByLayer(int layer) {
		int[][] matrix = Utilities.deepCopyIntMatrix(horizontalRotationSchema);
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
		int[][] matrix = Utilities.deepCopyIntMatrix(verticalRotationSchema);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] += row * 3; // um n-Zeilen jeden Wert verschieben
			}
		}
		return matrix;
	}
	
	/*
	 * Kopiert das Schema und verschiebt alle Werte um den angegebenen Faktor(layer)
	 */
	private static int[][] getForwardSchemaByLayer(int layer) {
		int[][] matrix = Utilities.deepCopyIntMatrix(forwardRotationSchema);
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] += layer; // um n-Schichten jeden Wert verschieben
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
			case HORIZONTALMIDDLE:
				result = getHorizontalSchemaByLayer(1);
				break;
			case HORIZONTALTOP:
				result = Utilities.deepCopyIntMatrix(horizontalRotationSchema);
				break;
			case VERTICALBACK:
				result = getVerticalSchemaByLayer(2);
				break;
			case VERTICALMIDDLE:
				result = getVerticalSchemaByLayer(1);
				break;
			case VERTICALFRONT: 
				result = Utilities.deepCopyIntMatrix(verticalRotationSchema);
				break;
			case HORIZONTALWHOLE:
				result = Utilities.concatAll(Utilities.deepCopyIntMatrix(horizontalRotationSchema), getHorizontalSchemaByLayer(1), getHorizontalSchemaByLayer(2));
				break;
			case VERTICALWHOLE:
				result = Utilities.concatAll(Utilities.deepCopyIntMatrix(verticalRotationSchema), getVerticalSchemaByLayer(1), getVerticalSchemaByLayer(2));
				break;
			case FORWARDLEFT:
				result = Utilities.deepCopyIntMatrix(forwardRotationSchema);
				break;
			case FORWARDMIDDLE:
				result = getForwardSchemaByLayer(1);
				break;
			case FORWARDRIGHT:
				result = getForwardSchemaByLayer(2);
				break;
			case FORWARDWHOLE:
				result = Utilities.concatAll(Utilities.deepCopyIntMatrix(forwardRotationSchema), getForwardSchemaByLayer(1), getForwardSchemaByLayer(2));
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
}
