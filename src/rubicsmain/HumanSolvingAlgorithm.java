package rubicsmain;

import java.util.ArrayList;

import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Edge;
import rubicscube.Fragment;
import rubicscube.Middle;
import rubicscube.Move;
import rubicscube.MoveSequence;

public class HumanSolvingAlgorithm {
	private Cube cube;
	private ArrayList<Fragment> corners;
	private ArrayList<Fragment> middles;
	private ArrayList<Fragment> edges;
	
	public HumanSolvingAlgorithm(Cube c) {
		this.cube = (Cube)c.clone();
	}
	
	/*
	 * Der W�rfel wird gel�st nach der Layer-by-Layer Methodik
	 * Die �bergebene Cubeinstanz wird nicht ver�ndert.
	 */
	public MoveSequence solveCube() {
		MoveSequence resultSequence = new MoveSequence();
		
		// Alle Fragmente nach Typ suchen
		this.corners = this.cube.getFragmentsByType(Corner.class);
		this.middles = this.cube.getFragmentsByType(Middle.class);
		this.edges = this.cube.getFragmentsByType(Edge.class);
		
		solveTopLayer(resultSequence);
		solveMiddleLayer(resultSequence);
		
		return resultSequence;
	}
	
	/*
	 * L�st die mittlere Schicht
	 */
	private MoveSequence solveMiddleLayer(MoveSequence resultSequence) {
		
		// Alle ben�tigten Kantenst�cke suchen
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
					
					// gesuchtes Mittelst�cke ist nun an Vorderseite
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
					// Jetzt Kante in L�cke positionieren
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
					
					// gesuchte Kante ist in Mittelschicht --> muss nach unten, au�er wenn bereits richtig
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
						// Ansonsten eine beliebige Kante an jene Stelle einf�gen
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
	 * L�st die oberste Ebene
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
			
			// Horizontalrotation notwendig
			if (row > 0) {
				
				// Mittlere Schicht --> Unterscheiden ob links oder rechts herum gedreht werden muss
				if (row == 1) {
					CubeDirection dir = CubeDirection.CLOCKWISE;
					if (column == 0) {
						dir = CubeDirection.COUNTERCLOCKWISE;
					}
					resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getHorizontalRotation(pos), dir));
				} else {
					
					// Fragment ist hinten --> zweimal drehen (Richtung egal)
					for (int j = 0; j < 2; j++) {
						resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.getHorizontalRotation(pos), CubeDirection.COUNTERCLOCKWISE));
					}
				}
			}
			
			// Jetzt ist das Fragment auf der vorderen Seite und muss an seinen Platz gedreht werden
			pos = neededFragment.getPosition(this.cube);
			int layer = Cube.getLayer(pos);
			row = Cube.getRow(pos);
			column = Cube.getColumn(pos);
			
			// layer = 0 --> �berpr�fen, ob Kante gekippt werden muss
			// layer = 1 --> �berpr�fen, ob Kante gekippt werden muss und entsprechend einf�gen
			// layer = 2 --> �berpr�fen, ob Kante gekippt werden muss und entsprechend einf�gen
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
			
			// Gesamten W�rfel weiter drehen, damit die n�chste Kante bearbeitet werden kann.
			// Au�er beim letzten Durchgang
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
			if (pos	!= 2 && neededFragment.getFaceTop().getColor() != topLayerColor) {
				
				// Zun�chst Stein auf vordere untere rechte Ecke bef�rdern
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
				// in das dar�berliegende Eckfragment positioniert werden
				if (neededFragment.getFaceFront().getColor() == topLayerColor) {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorFrontSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorFrontSequence); // Z�ge auch am virtuellen W�rfel durchf�hren
				} else if (neededFragment.getFaceRight().getColor() == topLayerColor) {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorRightSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorRightSequence);
				} else {
					resultSequence.getMoves().addAll(RotationSequence.firstLayerCornerBottomToTopColorBottomSequence.getMoves());
					MoveHandler.doMoveSequence(this.cube, RotationSequence.firstLayerCornerBottomToTopColorBottomSequence);
				}
			}
			
			// Fragment ist in Position
			// Gesamten W�rfel weiter drehen, damit die n�chste Ecke bearbeitet werden kann.
			// Au�er beim letzten Durchgang
			if (i != 3) {
				resultSequence.getMoves().add(this.cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
			}
		}
		
		return resultSequence;
	}
	
	/*
	 * Findet ein entsprechendes Fragment mit dem den �bergebenen Farben
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
