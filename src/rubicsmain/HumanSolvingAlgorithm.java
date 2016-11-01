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
			if (pos	!= 2 && neededFragment.getFaceTop().getColor() != topLayerColor) {
				
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
