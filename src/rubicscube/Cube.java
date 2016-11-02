package rubicscube;

import java.util.ArrayList;
import java.util.Random;

public class Cube implements Cloneable {
	
	/*
	 * 3x3x3 ArrayLists zur Abbildung des Rubiks Cubes
	 * 1-Array: die horizontale Schicht/Ebene von vorne
	 * 2-Array: die Schnittmenge aus horizontaler Ebene und vertikaler Ebene (von oben eine Zeile)
	 * 3-Array: ein Fragment in einer Zeile
	 * 
	 * Von oben (unten links ist vordere linke obere Ecke):
	 * 		///////////////////
	 * 		/ 020 / 021 / 022 /
	 * 		///////////////////
	 * 		/ 010 / 011 / 012 /
	 * 		///////////////////
	 * 		/ 000 / 001 / 002 /
	 * 		///////////////////
	 * 
	 * von vorne (oben links ist vordere linke obere Ecke): 
	 * 		///////////////////
	 * 		/ 000 / 001 / 002 /
	 * 		///////////////////
	 * 		/ 100 / 101 / 102 /
	 * 		///////////////////
	 * 		/ 200 / 201 / 202 /
	 * 		///////////////////
	 */
	private ArrayList<ArrayList<ArrayList<Fragment>>> cubeFragments;
	
	public Cube() {
		// Initialisiere cubeFragments damit keine null-Zugriffe bei den Listen erfolgen
		cubeFragments = new ArrayList<>(3);
		for (int i = 0; i < 3; i++) {
			ArrayList<ArrayList<Fragment>> innerList = new ArrayList<>(3);
			for (int j = 0; j < 3; j++) {
				ArrayList<Fragment> innerInnerList = new ArrayList<>(3);
				for (int k = 0; k < 3; k++) {
					innerInnerList.add(null);
				}
				innerList.add(innerInnerList);
			}
			cubeFragments.add(innerList);
		}
	}
	
	/*
	 * Gibt die Position eines Fragmentes zurück.
	 * Wird das Fragment nicht gefunden, wird -1 zurückgegeben.
	 */
	public int findCubePositionByReference(Fragment fragment) {
		int position = -1;
		for (int i = 0; i < this.cubeFragments.size(); i++) {
			ArrayList<ArrayList<Fragment>> ml = this.cubeFragments.get(i);
			for (int j = 0; j < ml.size(); j++) {
				ArrayList<Fragment> il = ml.get(j);
				for(int k = 0; k < il.size(); k++) {
					if (il.get(k) == fragment) {
						position = i * 9 + j * 3 + k; // Berechnen der Position
					}
				}
			}
		}
		return position;
	}
	
	/*
	 * Gibt alle Fragmente, welche der übergebenen Klasse entsprechen, zurück
	 */
	public ArrayList<Fragment> getFragmentsByType(Class<?> type) {
		ArrayList<Fragment> result = new ArrayList<>();
		for (ArrayList<ArrayList<Fragment>> ml : this.cubeFragments) {
			for (ArrayList<Fragment> il : ml) {
				for(Fragment f : il) {
					if (f != null && f.getClass() == type) {
						result.add(f);
					}
				}
			}
		}
		return result;
	}
	
	/*
	 * Setzt ein Mittel-, Rand- oder Eckenstück an der Position im Cube
	 */
	public void setCubeFragment(CubeLayer layer, CubePosition position, Fragment fragment) {
		int layerNr = layer.ordinal();
		int rowNr = position.getXPositionValue();
		int columnNr = position.getYPositionValue();
		cubeFragments.get(layerNr).get(rowNr).set(columnNr, fragment);
	}
	
	/*
	 * Liefert ein Fragment anhand seiner Position (von 0-26)
	 * 
	 * Von oben (unten links ist vordere linke obere Ecke):
	 * 		/////////////
	 * 		/ 6 / 7 / 8 /
	 * 		/////////////
	 * 		/ 3 / 4 / 5 /
	 * 		/////////////
	 * 		/ 0 / 1 / 2 /
	 * 		/////////////
	 * 
	 * von vorne (oben links ist vordere linke obere Ecke): 
	 * 		////////////////
	 * 		/ 0  / 1  / 2  /
	 * 		////////////////
	 * 		/ 9  / 10 / 11 /
	 * 		////////////////
	 * 		/ 18 / 19 / 20 /
	 * 		////////////////
	 * 		
	 */
	public Fragment getFragmentByPosition(int position) {
		int layer = getLayer(position);
		int row = getRow(position);
		int cell = getColumn(position);
		return cubeFragments.get(layer).get(row).get(cell);
	}
	
	/*
	 * Gibt die Schicht anhand der übergeben Position (0-26) zurück
	 */
	public static int getLayer(int pos) {
		return pos / 9;
	}
	
	/*
	 * Gibt die Reihe (von oben betrachtet eine horizontale Scheibe)
	 * anhand der übergebenen Position (0-26) zurück
	 */
	public static int getRow(int pos) {
		return (pos % 9) / 3;
	}
	
	/*
	 * Gibt die Spalte (von oben betrachtet eine vertikale Scheibe)
	 * anhand der übergbenen Position (0-26) zurück
	 */
	public static int getColumn(int pos) {
		return pos % 3;
	}
	
	/*
	 * Setzt analog zu getFragmentByPosition ein Fragment an einer Position
	 */
	public Fragment setFragmentByPosition(int position, Fragment fragment) {
		int layer = position / 9;
		int row = (position % 9) / 3;
		int cell = position % 3;
		return cubeFragments.get(layer).get(row).set(cell, fragment);
	}
	
	/*
	 * Dreht den Cube einmal in der entsprechenden Rotationsebene und Richtung
	 */
	public Move rotateCube(CubeRotation rotation, CubeDirection direction) {
		int[][] schema = CubeRotationSchema.getRotationSchema(rotation, direction);
		int counter = 0;
		for (int[] swap : schema) {
			Fragment f1 = this.getFragmentByPosition(swap[0]);
			Fragment f2 = this.getFragmentByPosition(swap[1]);
			
			// Fragmente vertauschen im Cube
			this.setFragmentByPosition(swap[0], f2);
			this.setFragmentByPosition(swap[1], f1);
			
			// Fragmente 1 drehen
			if (f1 != null) {
				f1.rotate(rotation, direction);
			}
			
			// Fragment 2 muss auch einmal gedreht werden,
			// weil vom Schema nur 3 Tauschaktionen vorgesehen sind und somit nur 
			// 3 Ecken/Kanten gedreht werden würden
			if (counter % 3 == 0 && f2 != null) {
				f2.rotate(rotation, direction);
			}
			counter++;
		}
		
		return new Move(rotation, direction);
	}
	
	/*
	 * Überprüft ob der Würfel gelöst wurde.
	 */
	public boolean isSolved() {
		boolean isSolved = true;
		for (int i = 1; i <= 6 && isSolved; i++) {
			String level = getLevelFaces(i);
			char levelColor = level.charAt(4);
			for (int j = 0; j < level.length(); j++) {
				if (level.charAt(j) != levelColor) {
					isSolved = false;
					break;
				}
			}
		}
		return isSolved;
	}
	
	/*
	 * Gibt eine Auflistung aller Oberflächenfarben einer Ebene zurück.
	 * 
	 * Darstellung der Level(Ebenen):
	 * 
	 * 		   ////////////////
	 * 		  /		  1		 /
	 * 		 /				/ 
	 * 		////////////////
	 * 		/    /    /    /
	 * 		////////////////
	 * 	2	/    / 3  /    /	4
	 * 		////////////////
	 * 		/    /    /    /
	 * 		////////////////
	 * 				6
	 * 
	 */
	public String getLevelFaces(int level) {
		StringBuilder result = new StringBuilder();
		
		// Obere und untere Ebene
		if (level == 1 || level == 6) {
			int layer = 0;
			if (level == 6) {
				layer = 2;
			}
			ArrayList<ArrayList<Fragment>> layerFragments = this.cubeFragments.get(layer);
			for (ArrayList<Fragment> rowFragments : layerFragments) {
				for(Fragment f : rowFragments) {
					result.append(f.getFaceBottom().getColor() == -1 ? f.getFaceTop().getColor() : f.getFaceBottom().getColor());
				}
			}
		} else if (level == 2 || level == 4) { // Linke und rechte Ebene
			int column = 0;
			if (level == 4) {
				column = 2;
			}
			for (ArrayList<ArrayList<Fragment>> layerFragments : this.cubeFragments) {
				for (ArrayList<Fragment> rowFragments : layerFragments) {
					Fragment f = rowFragments.get(column);
					result.append(f.getFaceLeft().getColor() == -1 ? f.getFaceRight().getColor() : f.getFaceLeft().getColor());
				}
			}
		} else if (level == 3 || level == 5) { // vordere und hintere Ebene
			int row = 0;
			if (level == 5) {
				row = 2;
			}
			for (ArrayList<ArrayList<Fragment>> layerFragments : this.cubeFragments) {
				ArrayList<Fragment> rowFragments = layerFragments.get(row);
				for (Fragment f : rowFragments) {
					result.append(f.getFaceFront().getColor() == -1 ? f.getFaceBack().getColor() : f.getFaceFront().getColor());
				}
			}
		}
		
		return result.toString();
	}
	
	/*
	 * Führt eine zufällige Rotation am Würfel aus
	 */
	public Move performRandomMove() {
		return this.rotateCube(CubeRotation.getRandom(), CubeDirection.getRandom());
	}
	
	/*
	 * Für die Ausgabe in der Console (Debugzwecke)
	 */
	public void displayInConsole() {
		System.out.println("--------------------Start Display Cube--------------------");
		for (ArrayList<ArrayList<Fragment>> layer : cubeFragments) {
			System.out.println("--------------------Horizontaler Layer--------------------");
			for (ArrayList<Fragment> row : layer) {
				String rowString = "| ";
				for (Fragment f : row) {
					if (f == null) {
						rowString += "000000 | ";
					} else {
						rowString += f.toString() + " | ";
					}
				}
				System.out.println(rowString);
			}
		}
		System.out.println("--------------------End Display Cube--------------------");
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		Cube c = null;
		if (!(obj instanceof Cube)){
			result = false;
		} else {
			c = (Cube)obj;
		}
		for (int i = 0; i < 3 && result == true; i++) {
			for (int j = 0; j < 3&& result == true; j++) {
				for (int k = 0; k < 3&& result == true; k++) {
					if (this.cubeFragments.get(i).get(j).get(k) != null) {
						result = this.cubeFragments.get(i).get(j).get(k).equals(c.cubeFragments.get(i).get(j).get(k));
					} else if(c.cubeFragments.get(i).get(j).get(k)!= null) {
						result = false;
					}
				}
			}
		}
		return result;
	}
	
	@Override
	public Object clone() {
		Cube c = new Cube();
		for (int i = 0; i < 3*3*3; i++) {
			Fragment f = this.getFragmentByPosition(i);
			if (f != null) {
				c.setFragmentByPosition(i, (Fragment)f.clone());
			}
		}
		return c;
	}
}
