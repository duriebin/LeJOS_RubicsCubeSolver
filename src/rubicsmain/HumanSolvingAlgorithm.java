package rubicsmain;

import java.util.ArrayList;

import common.Utilities;
import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Edge;
import rubicscube.Fragment;
import rubicscube.Middle;
import rubicscube.MoveSequence;

public class HumanSolvingAlgorithm implements Solveable {
	private Cube cube;
	private ArrayList<Fragment> corners;
	private ArrayList<Fragment> middles;
	private ArrayList<Fragment> edges;
	
	public HumanSolvingAlgorithm(Cube c) {
		this.cube = (Cube)c.clone();
	}
	
	/*
	 * Der Würfel wird gelöst nach der Layer-by-Layer Methodik
	 * Die übergebene Cubeinstanz wird nicht verändert.
	 */
	public MoveSequence solveCube() {
		MoveSequence resultSequence = new MoveSequence();
		
		// Alle Fragmente nach Typ suchen
		this.corners = this.cube.getFragmentsByType(Corner.class);
		this.middles = this.cube.getFragmentsByType(Middle.class);
		this.edges = this.cube.getFragmentsByType(Edge.class);
		
		solveTopLayer(resultSequence);
		solveMiddleLayer(resultSequence);
		solveBottomLayer(resultSequence);
		
		return resultSequence;
	}
	
	/*
	 * Löst die untere Schicht nach der Fridrich Methode (4LLL).
	 * Siehe: http://www.speedcube.de/fridrich.php
	 * https://de.wikibooks.org/wiki/Zauberw%C3%BCrfel/_3x3x3/_Fridrich
	 * 
	 * Zunächst alle Steine so drehen, dass die richtige Farbe unten ist (Anordnung egal). 
	 * Siehe: http://www.speedcube.de/fridrich_2look_oll.php
	 * 
	 * Anschließend die Anordnung korrigieren.
	 */
	private MoveSequence solveBottomLayer(MoveSequence resultSequence) {
		int bottomLayerColor = getBottomLayerColor();
		int[] rotationCounter = new int[] { 0 };
		
		// Kanten nach unten ausrichten
		if (PatternStorage.OLL2Look1.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCrossTwoAcrossSequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCrossTwoAcrossSequence);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCrossTwoBesideSequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCrossTwoBesideSequence);
		} else if (PatternStorage.OLL2Look2.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCrossTwoAcrossSequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCrossTwoAcrossSequence);
		} 
		else if (PatternStorage.OLL2Look3.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCrossTwoBesideSequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCrossTwoBesideSequence);
		}
		
		// Ecken nach unten ausrichten
		if (PatternStorage.OLL04.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL04Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL04Sequence);
		} else if (PatternStorage.OLL05.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL05Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL05Sequence);
		} else if (PatternStorage.OLL06.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL06Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL06Sequence);
		} else if (PatternStorage.OLL07.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL07Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL07Sequence);
		} else if (PatternStorage.OLL08.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL08Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL08Sequence);
		} else if (PatternStorage.OLL09.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL09Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL09Sequence);
		} else if (PatternStorage.OLL10.match(bottomLayerColor, this.cube, rotationCounter)) {
			doRotationCounter(resultSequence, rotationCounter[0]);
			resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerOLL10Sequence.getMoves());
			MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerOLL10Sequence);
		} 
		
		// Ecken vertauschen
		// Dazu die Ecken reihum in ein Array speichern,
		// damit später die notwendige Zugreihenfolge bestimmt werden kann.
		ArrayList<Fragment> bottomCorners = new ArrayList<>();
		bottomCorners.add(this.cube.getFragmentByPosition(18)); // links vorne unten
		bottomCorners.add(this.cube.getFragmentByPosition(24)); // links hinten unten
		bottomCorners.add(this.cube.getFragmentByPosition(26)); // rechts hinten unten
		bottomCorners.add(this.cube.getFragmentByPosition(20)); // rechts vorne unten
		
		Fragment rightCorner = null;

		// Drehen der untersten Ebene bis eine Ecke richtig ist, oder alle vier richtig sind.
		// Es muss dabei darauf geachtet werden, dass nicht nur zwei Ecken richtig sind,
		// weil die Algorithmen diesen Fall nicht berücksichtigen.
		for (int i = 0; i < 4; i++) {
			int rightCornerCounter = 0;
			for (int j = 0; j < bottomCorners.size(); j++) {
				boolean tmpCornerIsRight = fragmentIsRight(bottomCorners.get(j));
				if (tmpCornerIsRight) {
					rightCorner = bottomCorners.get(j);
					rightCornerCounter++;
				}
			}
			if (rightCornerCounter == 1 || rightCornerCounter == 4) {
				break;
			}
			
			// Weiter drehen
			if (i != 3) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
			} else if (rightCornerCounter == 2 || rightCornerCounter == 0) {
				
				// Wenn alles vergeblich versucht wurde damit einer oder alle richtig sind,
				// werden hier die unteren Ecken beliebig vertauscht und das Prozedere erneut versucht.
				i = -1;
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerPLL02Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerPLL02Sequence);
				
				// Wenn das gemacht wird, müssen die Ecken neu eingelesen werden, 
				// weil die Eckenreihenfolge verändert wird.
				bottomCorners = new ArrayList<>();
				bottomCorners.add(this.cube.getFragmentByPosition(18)); // links vorne unten
				bottomCorners.add(this.cube.getFragmentByPosition(24)); // links hinten unten
				bottomCorners.add(this.cube.getFragmentByPosition(26)); // rechts hinten unten
				bottomCorners.add(this.cube.getFragmentByPosition(20)); // rechts vorne unten
			}
		}
		
		// jetzt ist mindestens eine Ecke richtig
		// Es gibt nun 3 Möglichkeiten.
		// 1: Alle Ecken sind richtig
		// 2: 3 falsche Ecken, welche im Uhrzeigersinn nach PLL02 vertauscht werden müssen
		// 3: 3 falsche Ecken, welche gegen den Uhrzeigersinn nach PLL01 vertauscht werden müssen
		
		// Zunächst überprüfen, ob alle Ecken richtig sind
		boolean cornersAreRight = true;
		for (int i = 0; i < bottomCorners.size(); i++) {
			if (!fragmentIsRight(bottomCorners.get(i))) {
				cornersAreRight = false;
				break;
			}
		}

		// Fall 2 und 3 überprüfen
		if (!cornersAreRight) {
			Fragment nextFragment = (Corner)Utilities.getNextArrayListItem(bottomCorners, rightCorner).clone();
			Fragment secondeFragment = (Corner)Utilities.getNextArrayListItem(bottomCorners, nextFragment);
			nextFragment.rotate(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
			if (fragmentIsRight(nextFragment, secondeFragment.getPosition(this.cube))) {

				// Fall 2 liegt vor --> richtige Ecke hinten rechts plazieren und Algorithmus ausführen
				while (rightCorner.getPosition(this.cube) != 26) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				}
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerPLL02Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerPLL02Sequence);
			} else {

				// Fall 3 liegt vor --> richtige Ecke hinten links plazieren und Algorithmus ausführen
				while (rightCorner.getPosition(this.cube) != 24) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				}
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerCornerPLL01Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerCornerPLL01Sequence);
			}
		}
		
		// Jetzt sind alle Ecken richtig und es müssen nur noch die 4 verbliebenen Kanten permutiert werden
		ArrayList<Fragment> bottomEdges = new ArrayList<>();
		bottomEdges.add(this.cube.getFragmentByPosition(19)); // unten vorne mitte
		bottomEdges.add(this.cube.getFragmentByPosition(21)); // unten links mitte
		bottomEdges.add(this.cube.getFragmentByPosition(25)); // unten hinten mitte
		bottomEdges.add(this.cube.getFragmentByPosition(23)); // unten rechts mitte
		
		// Feststellen wie viele Kanten richtig positioniert sind,
		// damit dann über weitere Schritte entschieden werden kann.
		// Sind 0 richtig, müssen OLL04 oder OLL05 angewendet werden.
		// Ist 1 richtig, müssen OLL06 oder OLL07 angewendet werden.
		// Sind 4 richtig, ist der Würfel gelöst.
		int rightEdgesCounter = 0;
		Fragment rightEdge = null;
		for (int i = 0; i < bottomEdges.size(); i++) {
			boolean tmpCcornerIsRight = fragmentIsRight(bottomEdges.get(i));
			if (tmpCcornerIsRight) {
				rightEdge = bottomEdges.get(i);
				rightEdgesCounter++;
			}
		}

		if (rightEdgesCounter == 1) {

			// Fall: Eine Kante richtig
			// Richtige Kante unten vorne mitte plazieren damit Algorithmus ausgewendet werden kann.
			while (rightEdge.getPosition(this.cube) != 19) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			}
			
			// Ermitteln in welche Richtung vertauscht werden muss
			Fragment nextFragment = (Edge)Utilities.getNextArrayListItem(bottomEdges, rightEdge).clone();
			Fragment secondeFragment = (Edge)Utilities.getNextArrayListItem(bottomEdges, nextFragment);
			nextFragment.rotate(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
			if (fragmentIsRight(nextFragment, secondeFragment.getPosition(this.cube))) {
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerEdgePLL06Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerEdgePLL06Sequence);
			} else {
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerEdgePLL07Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerEdgePLL07Sequence);
			} 
		} else if (rightEdgesCounter == 0) {
			
			// Fall: Keine Kanten richtig
			// Überprüfen ob irgendeine Kante an der Position um +-90° gedreht richtig wäre,
			// um zu ermitteln, ob nur über Ecke getauscht werden muss oder komplett über die Mitte.
			Fragment currentFragment = (Edge)bottomEdges.get(1).clone();
			Fragment currentFragment2 = (Edge)currentFragment.clone();
			Fragment nextFragment = (Edge)bottomEdges.get(2);
			Fragment previousFragment = (Edge)bottomEdges.get(0);
			currentFragment.rotate(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
			currentFragment2.rotate(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
			if (fragmentIsRight(currentFragment, nextFragment.getPosition(this.cube))) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerEdgePLL04Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerEdgePLL04Sequence);
			} else if (fragmentIsRight(currentFragment2, previousFragment.getPosition(this.cube))) {
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerEdgePLL04Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerEdgePLL04Sequence);
			} else {
				resultSequence.getMoves().addAll(RotationSequence.bottomLayerEdgePLL05Sequence.getMoves());
				MoveHandler.doMoveSequence(this.cube, RotationSequence.bottomLayerEdgePLL05Sequence);
			}
		}

		return resultSequence;
	}
	
	/*
	 * Überprüft ob ein Fragment richtig positioniert ist an einer bestimmten Position
	 */
	private boolean fragmentIsRight(Fragment f, int pos) {
		boolean result = true;
		int layer = Cube.getLayer(pos);
		int row = Cube.getRow(pos);
		int column = Cube.getColumn(pos);
		if (layer == 0) {
			if (f.getFaceTop().getColor() != this.getTopLayerColor()) {
				result = false;
			}
		} else if (layer == 2) {
			if (f.getFaceBottom().getColor() != this.getBottomLayerColor()) {
				result = false;
			}
		}
		if (row == 0) {
			if (f.getFaceFront().getColor() != this.getFrontLayerColor()) {
				result = false;
			}
		} else if (row == 2) {
			if (f.getFaceBack().getColor() != this.getBackLayerColor()) {
				result = false;
			}
		}
		if (column == 0) {
			if (f.getFaceLeft().getColor() != this.getLeftLayerColor()) {
				result = false;
			}
		} else if (column == 2){
			if (f.getFaceRight().getColor() != this.getRightLayerColor()) {
				result = false;
			}
		}
		
		return result;
	}
	
	/*
	 * Überprüft ob ein Fragment richtig positioniert ist
	 */
	private boolean fragmentIsRight(Fragment f) {
		int pos = f.getPosition(this.cube);
		return this.fragmentIsRight(f, pos);
	}
	
	
	/*
	 * Berücksichtigt bei den Patterns den RotationCounter
	 */
	private MoveSequence doRotationCounter(MoveSequence resultSequence, int rotationCounter) {
		switch (rotationCounter) {
		case 1:
			resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			}
			break;
		case 3:
			resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
			break;
			default:
				break;
		}
		return resultSequence;
	}
	
	/*
	 * Löst die mittlere Schicht
	 */
	private MoveSequence solveMiddleLayer(MoveSequence resultSequence) {
		
		// Alle benötigten Kantenstücke suchen
		ArrayList<Fragment> neededFragments = new ArrayList<>();
		neededFragments.add(findFragment(Edge.class, getRightLayerColor(), getBackLayerColor()));
		neededFragments.add(findFragment(Edge.class, getBackLayerColor(), getLeftLayerColor()));
		neededFragments.add(findFragment(Edge.class, getFrontLayerColor(), getRightLayerColor()));
		neededFragments.add(findFragment(Edge.class, getFrontLayerColor(), getLeftLayerColor()));
		
		Fragment currentFragment;
		Fragment middleFragment;
		int pos;
		int layer;
		int row;
		int column;
		boolean doneSth;
		
		while (neededFragments.size() > 0) {
			doneSth = false;
			for (int i = neededFragments.size() - 1; i >= 0; i--) {
				currentFragment = neededFragments.get(i);
				pos = currentFragment.getPosition(this.cube);
				layer = Cube.getLayer(pos);
				
				// Gesuchtes Fragment befindet sich in unterer Ebene
				if (layer == 2) {
					
					// Index 0 ist immer die Farbe, welche nicht nach unten zeigt
					middleFragment = findFragment(Middle.class, currentFragment.getColors().get(0));
					int middlePos = middleFragment.getPosition(this.cube);
					int middleRow = Cube.getRow(middlePos);
					int middleColumn = Cube.getColumn(middlePos);
					if (middleRow == 1) {
						if (middleColumn == 0) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
						} else {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
						}
					} else if (middleRow == 2) {
						for (int j = 0; j < 2; j++) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
						}
					}
					
					// gesuchtes Mittelstücke ist nun an Vorderseite
					// Jetzt Kante darunter platzieren
					pos = currentFragment.getPosition(this.cube);
					row = Cube.getRow(pos);
					column = Cube.getColumn(pos);
					if (row == 1) {
						if (column == 0) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
						} else {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
						}
					} else if (row == 2) {
						for (int j = 0; j < 2; j++) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
						}
					}
					
					// Kante ist darunter platziert
					// Jetzt Kante in Lücke positionieren
					// Index 1 ist untere Farbe der Kante
					if (currentFragment.getColors().get(1) == getLeftLayerColor()) {
						resultSequence.getMoves().addAll(RotationSequence.middleLayerEdgeLeftSequence.getMoves());
						MoveHandler.doMoveSequence(this.cube, RotationSequence.middleLayerEdgeLeftSequence);
					} else {
						resultSequence.getMoves().addAll(RotationSequence.middleLayerEdgeRightSequence.getMoves());
						MoveHandler.doMoveSequence(this.cube, RotationSequence.middleLayerEdgeRightSequence);
					}
					
					// Kante ist platziert
					neededFragments.remove(currentFragment);
					doneSth = true;
				} else {
					
					// gesuchte Kante ist in Mittelschicht --> muss nach unten, außer wenn bereits richtig
					if (!doneSth && i == 0) {
						boolean isCorrect = false;
						row = Cube.getRow(pos);
						column = Cube.getColumn(pos);
						if (row == 0) {
							if (currentFragment.getFaceFront().getColor() == getFrontLayerColor()) {
								if (column == 0) {
									if (currentFragment.getFaceLeft().getColor() == getLeftLayerColor()) {
										isCorrect = true;
									}
								} else {
									if (currentFragment.getFaceRight().getColor() == getRightLayerColor()) {
										isCorrect = true;
									}
								}
							}
						} else {
							if (currentFragment.getFaceBack().getColor() == getBackLayerColor()) {
								if (column == 0) {
									if (currentFragment.getFaceLeft().getColor() == getLeftLayerColor()) {
										isCorrect = true;
									}
								} else {
									if (currentFragment.getFaceRight().getColor() == getRightLayerColor()) {
										isCorrect = true;
									}
								}
							}
						}
						
						// Fragment ist bereits an richtiger Stelle --> muss nichts mehr bemacht werden
						// Ansonsten eine beliebige Kante an jene Stelle einfügen
						if (isCorrect) {
							neededFragments.remove(currentFragment);
						} else {
							
							// Fragment in vorderste Ebene bringen
							if (row == 2) {
								if (column == 0) {
									resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
								} else {
									resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
								}
							}
							
							// Fragment austauschen
							if (column == 0) {
								resultSequence.getMoves().addAll(RotationSequence.middleLayerEdgeLeftSequence.getMoves());
								MoveHandler.doMoveSequence(this.cube, RotationSequence.middleLayerEdgeLeftSequence);
							} else {
								resultSequence.getMoves().addAll(RotationSequence.middleLayerEdgeRightSequence.getMoves());
								MoveHandler.doMoveSequence(this.cube, RotationSequence.middleLayerEdgeRightSequence);
							}
						}
					}
				}
			}
		}
		
		return resultSequence;
	}
	
	/*
	 * Löst die oberste Ebene
	 */
	private MoveSequence solveTopLayer(MoveSequence resultSequence) {
		int topLayerColor = getTopLayerColor();
		
		// Obere Kanten positionieren
		for (int i = 0; i < 4; i++) {
			int frontLayerColor = getFrontLayerColor();
			Fragment neededFragment = findFragment(Edge.class, topLayerColor, frontLayerColor);
			int pos = neededFragment.getPosition(this.cube);
			int row = Cube.getRow(pos);
			int column = Cube.getColumn(pos);
			int layer = Cube.getLayer(pos);
			
			// Horizontalrotation notwendig
			if (row > 0) {
				
				// Mittlere Schicht --> Unterscheiden ob links oder rechts herum gedreht werden muss
				if (row == 1) {
					CubeDirection dir = CubeDirection.CLOCKWISE;
					if (column == 0) {
						dir = CubeDirection.COUNTERCLOCKWISE;
					}
					
					// Im ersten Durchgang darf die obere Schicht ohne Probleme verdreht werden.
					// Bei weiteren Durchgängen darf dies jedoch nicht mehr erfolgen, sonst verdreht man ggf etwas.
					if (layer != 0 || i == 0) {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getHorizontalRotation(pos), dir));
					} else {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALMIDDLE, dir));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, dir));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALMIDDLE, dir.swap()));
					}
				} else {
					// Im ersten Durchgang darf die obere Schicht ohne Probleme verdreht werden.
					// Bei weiteren Durchgängen darf dies jedoch nicht mehr erfolgen, sonst verdreht man ggf etwas.
					if (layer != 0 || i == 0) {
						
						// Fragment ist hinten --> zweimal drehen (Richtung egal)
						for (int j = 0; j < 2; j++) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getHorizontalRotation(pos), CubeDirection.COUNTERCLOCKWISE));
						}
					} else {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.CLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
					}
				}
			}
			
			// Jetzt ist das Fragment auf der vorderen Seite und muss an seinen Platz gedreht werden
			pos = neededFragment.getPosition(this.cube);
			layer = Cube.getLayer(pos);
			row = Cube.getRow(pos);
			column = Cube.getColumn(pos);
			
			// layer = 0 --> überprüfen, ob Kante gekippt werden muss
			// layer = 1 --> überprüfen, ob Kante gekippt werden muss und entsprechend einfügen
			// layer = 2 --> überprüfen, ob Kante gekippt werden muss und entsprechend einfügen
			if (layer == 0) {
				if (topLayerColor == neededFragment.getFaceFront().getColor()) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE));
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.CLOCKWISE));
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE));
				}
			} else if (layer == 1) {
				CubeDirection dir = CubeDirection.CLOCKWISE;
				if (column == 0) {
					dir = CubeDirection.COUNTERCLOCKWISE;
				}
				if (topLayerColor == neededFragment.getFaceFront().getColor()) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, dir));
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, dir));
				} else {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, dir.swap()));
				}
			} else if(layer == 2) {
				if (topLayerColor == neededFragment.getFaceFront().getColor()) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE));
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE));
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE));
				} else {
					for (int j = 0; j < 2; j++) {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE));
					}
				}
			}
			
			// frontLayerColor wieder an vorderste Position bringen
			Fragment frontFragment = findFragment(Middle.class, frontLayerColor);
			int frontPos = frontFragment.getPosition(this.cube);
			int frontRow = Cube.getRow(frontPos);
			int frontColumn = Cube.getColumn(frontPos);
			if (frontRow == 2) {
				for (int j = 0; j < 2; j++) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.CLOCKWISE));
				}
			} else if (frontRow == 1) {
				if (frontColumn == 0) {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE));
				} else {
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.CLOCKWISE));
				}
			}
			
			// Gesamten Würfel weiter drehen, damit die nächste Kante bearbeitet werden kann.
			// Außer beim letzten Durchgang
			if (i != 3) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			}
		}
		
		// Obere Ecken positionieren
		for (int i = 0; i < 4; i++) {
			int frontLayerColor = getFrontLayerColor();
			int rightLayerColor = getRightLayerColor();
			Fragment neededFragment = findFragment(Corner.class, topLayerColor, frontLayerColor, rightLayerColor);
			int pos = neededFragment.getPosition(this.cube);
			int layer = Cube.getLayer(pos);
			int row = Cube.getRow(pos);
			int column = Cube.getColumn(pos);
			
			// Fragment ist noch nicht richtig
			if (pos	!= 2 || (pos == 2 && neededFragment.getFaceTop().getColor() != topLayerColor)) {
				
				// Zunächst Stein auf vordere untere rechte Ecke befördern
				// Oberste Schicht
				if (layer == 0) {
					if (row == 0) {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getForwardRotation(pos), CubeDirection.CLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getForwardRotation(pos), CubeDirection.COUNTERCLOCKWISE));
					} else {
						
						// Hintere Reihe auf oberster Schicht
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getForwardRotation(pos), CubeDirection.COUNTERCLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getForwardRotation(pos), CubeDirection.CLOCKWISE));
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					}
				} else {
					
					// Fragment ist bereits unten dann nur noch entsprechend in Position drehen
					if (row == 0) {
						if (column == 0) {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
						}
					} else {
						if (column == 0) {
							for (int j = 0; j < 2; j++) {
								resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
							}
						} else {
							resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
						}
					}
				}
				
				// Fragment ist nun vorne rechts unten und muss jetzt nur noch mit der entsprechende Rotationsreihenfolge
				// in das darüberliegende Eckfragment positioniert werden
				if (neededFragment.getFaceFront().getColor() == topLayerColor) {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorFrontSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorFrontSequence); // Züge auch am virtuellen Würfel durchführen
				} else if (neededFragment.getFaceRight().getColor() == topLayerColor) {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorRightSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorRightSequence);
				} else {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorBottomSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorBottomSequence);
				}
			}
			
			// Fragment ist in Position
			// Gesamten Würfel weiter drehen, damit die nächste Ecke bearbeitet werden kann.
			// Außer beim letzten Durchgang
			if (i != 3) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			}
		}
		
		return resultSequence;
	}
	
	/*
	 * Findet ein entsprechendes Fragment mit dem den übergebenen Farben
	 */
	private Fragment findFragment(Class<?> cls, int... faceColors) {
		Fragment f = null;
		ArrayList<Fragment> fragments = null;
		
		if (cls == Middle.class) {
			fragments = this.middles;
		} else if(cls == Edge.class) {
			fragments = this.edges;
		} else if (cls == Corner.class) {
			fragments = this.corners;
		}

		for (Fragment fm : fragments) {
			ArrayList<Integer> colors = fm.getColors();
			for (int i = 0; i < faceColors.length; i++) {
				boolean exists = false;
				for (int c : colors) {
					if (faceColors[i] == c) {
						exists = true;
						break;
					}
				}
				if (!exists) {
					break;
				}
				if (i == faceColors.length - 1) {
					f = fm;
				}
			}
			if (f != null) {
				break;
			}
		}

		return f;
	}
	
	private int getTopLayerColor() {
		return this.cube.getFragmentByPosition(4).getFaceTop().getColor();
	}
	
	private int getLeftLayerColor() {
		return this.cube.getFragmentByPosition(12).getFaceLeft().getColor();
	}
	
	private int getFrontLayerColor() {
		return this.cube.getFragmentByPosition(10).getFaceFront().getColor();
	}
	
	private int getRightLayerColor() {
		return this.cube.getFragmentByPosition(14).getFaceRight().getColor();
	}
	
	private int getBackLayerColor() {
		return this.cube.getFragmentByPosition(16).getFaceBack().getColor();
	}
	
	private int getBottomLayerColor() {
		return this.cube.getFragmentByPosition(22).getFaceBottom().getColor();
	}
}
