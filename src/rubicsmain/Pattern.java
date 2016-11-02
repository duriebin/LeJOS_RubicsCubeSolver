package rubicsmain;

import java.util.ArrayList;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeLayer;
import rubicscube.CubeRotation;
import rubicscube.Fragment;

/*
 * Repräsentiert eine Anordnung von Faces auf einen 5*5 Feld,
 * wobei die äußere Reihe für die Faces der angrenzenden Ebenen verwendet wird.
 * Erstellt für die Verwendung der Muster auf:
 * http://www.speedcube.de/fridrich_2look_oll.php
 */
public class Pattern {
	private String faces = null;
	
	/*
	 * Ein Muster besteht nur aus Nullen, Einsen und Zweien
	 * Nullen: Egal welche Farbe nur nicht die gesuchte Farbe
	 * Einsen: Die gesuchte Farbe
	 * Zweien: Egal welche Farbe
	 */
	public Pattern(String faces) {
		if (faces.length() == 25) {
			this.faces = faces;
		}
	}
	
	/*
	 * Untersucht ob das Pattern auf die untere Schicht des Würfels zutrifft.
	 * Es werden auch Rotationen berücksichtigt 
	 * --> rotationCounter gibt die Anzahl der in Uhrzeigersinn horizontal notwendigen Rotation zurück
	 * Da Java pass-by-value und nicht pass-by-reference ist, muss hier auf ein Array zurückgegriffen werden.
	 * 
	 * Achtung: Aktuell nur so umgesetzt, dass es für untere Schicht funktioniert, 
	 * da für dieses Projekt nicht mehr erforderlich ist.
	 */
	public boolean match(int matchColor, Cube c, int[] rotationCounter) {
		boolean success = false;
		Cube cube = (Cube)c.clone();
		rotationCounter[0] = 0;
		
		for (int i = 0; i < 4; i++) {
			
			// Erstellung des aktuellen Standes des Würfels im Patternformat
			StringBuilder order = new StringBuilder();
			order.append("7"); // Existiert eigentlich gar nicht
			order.append(cube.getLevelFaces(3).substring(6));
			order.append("7"); // Existiert eigentlich gar nicht
			
			String leftSide = cube.getLevelFaces(2);
			String rightSide = cube.getLevelFaces(4);
			String bottomSide = cube.getLevelFaces(6);
			for (int j = 0; j < 3; j++) {
				order.append(leftSide.charAt(6 + j));
				order.append(bottomSide.substring(3 * j, 3 * j + 3));
				order.append(rightSide.charAt(6 + j));
			}
			
			order.append("7"); // Existiert eigentlich gar nicht
			order.append(cube.getLevelFaces(5).substring(6));
			order.append("7"); // Existiert eigentlich gar nicht
			
			// Alle Farben als String darstellen, so dass nur 0 und 1 vorkommen
			StringBuilder orderReplaced = new StringBuilder(order.toString()
					.replaceAll("(?!" + String.valueOf(matchColor) + ").", "0").replaceAll(String.valueOf(matchColor), "1"));
			
			// Ignorieren der zweier Stellen
			for (int j = 0; j < faces.length(); j++) {
				if (faces.charAt(j) == '2') {
					orderReplaced.setCharAt(j, '2');
				}
			}
			
			if (orderReplaced.toString().equals(faces)) {
				success = true;
				break;
			}
			
			rotationCounter[0]++;
			cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		}
		return success;
	}
}
