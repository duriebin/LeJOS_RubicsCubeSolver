package rubicsmain;

import java.util.ArrayList;
import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.Edge;
import rubicscube.FacePosition;
import rubicscube.Fragment;
import rubicscube.LayerNotAllowedException;
import rubicscube.Middle;
import rubicscube.MoveSequence;
import rubicscube.PositionNotAllowedException;
import rubicsrobot.OpticalArm;
import rubicsrobot.Robot;

public class Logic {
	private Robot robot;
	
	public Logic(Robot robot) {
		this.robot = robot;
	}
	
	/*
	 * Scannt den gesamten Würfel und erzeugt ihn virtuell
	 */
	public Cube scanCube() throws PositionNotAllowedException, LayerNotAllowedException {
		ArrayList<float[]> scannedColors = new ArrayList<>();
		
		// Obige Seite einscannen
		ScanCubeSide(scannedColors);
		
		// Restliche Seiten einscannen
		ArrayList<MoveSequence> scanSequence = RotationSequence.scanSequence;
		for (MoveSequence moveSequence : scanSequence) {
			MoveHandler.doMoveSequence(robot, moveSequence);
			ScanCubeSide(scannedColors);
		}
	
		// Alles eingescannt
		// ==> Farb-Werte parsen und Würfel zusammensetzen
		// Zunächst die Werte nach Ähnlichkeit sortieren damit immer 9 Elemente einer Farbe entsprechen.
		// Die Zuordnung zur Position am Würfel bleibt durch die Ausgangsliste erhalten.
		ArrayList<float[]> orderedColors = ColorSorter.sortColors(scannedColors);
		Cube cube = new Cube();
		ArrayList<MoveSequence> scanSequenceReverse = RotationSequence.scanSequenceReverse;
		int foundIndex;
		int sideNumber;
		int position;
		Fragment f;
		int colorCounter = 0;
		for (int i = 0; i < orderedColors.size(); i++) {
			
			// Immer 9 Farben in einer Reihe sind gleiche Farben
			if (i % 9 == 0) {
				colorCounter++;
			}
			
			// Fragmentposition ermitteln (54 verschiedene von 0-53 gibt es)
			foundIndex = scannedColors.indexOf(orderedColors.get(i));
			sideNumber = foundIndex / 9; // Seite auf der die Farbe liegt (Einscannreihenfolge)
			position = parsePosition(foundIndex % 9); // Position 
			
			// Würfel so drehen, dass richtige Seite oben ist
			for (int j = 0; j < sideNumber; j++) {
				MoveHandler.doMoveSequence(cube, scanSequence.get(j));
			}
			
			f = cube.getFragmentByPosition(position);
			if (f == null) {
				f = getFragmentByPosition(position);
			}
			f.setFace(colorCounter, FacePosition.TOP);
			cube.setFragmentByPosition(position, f);
			
			// Würfel wieder zurückdrehen, damit Ausgangssituation wieder hergestellt ist
			for (int j = sideNumber - 1; j >= 0; j--) {
				MoveHandler.doMoveSequence(cube, scanSequenceReverse.get(j));
			}
		}
		
		// Würfel so drehen, dass er mit echtem Würfel übereinstimmt
		for (MoveSequence moveSequence : scanSequence) {
			MoveHandler.doMoveSequence(cube, moveSequence);
		}
		
		return cube;
	}
	
	public Cube forTesting(ArrayList<float[]> scannedColors) {
		// Alles eingescannt
		// ==> Farb-Werte parsen und Würfel zusammensetzen
		// Zunächst die Werte nach Ähnlichkeit sortieren damit immer 9 Elemente einer Farbe entsprechen.
		// Die Zuordnung zur Position am Würfel bleibt durch die Ausgangsliste erhalten.
		ArrayList<MoveSequence> scanSequence = RotationSequence.scanSequence;
		ArrayList<float[]> orderedColors = ColorSorter.sortColors(scannedColors);
		Cube cube = new Cube();
		ArrayList<MoveSequence> scanSequenceReverse = RotationSequence.scanSequenceReverse;
		int foundIndex;
		int sideNumber;
		int position;
		Fragment f;
		int colorCounter = 0;
		for (int i = 0; i < orderedColors.size(); i++) {
			
			// Immer 9 Farben in einer Reihe sind gleiche Farben
			if (i % 9 == 0) {
				colorCounter++;
			}
			
			// Fragmentposition ermitteln (54 verschiedene von 0-53 gibt es)
			foundIndex = scannedColors.indexOf(orderedColors.get(i));
			sideNumber = foundIndex / 9; // Seite auf der die Farbe liegt (Einscannreihenfolge)
			position = parsePosition(foundIndex % 9); // Position 
			
			// Würfel so drehen, dass richtige Seite oben ist
			for (int j = 0; j < sideNumber; j++) {
				MoveHandler.doMoveSequence(cube, scanSequence.get(j));
			}
			
			f = cube.getFragmentByPosition(position);
			if (f == null) {
				f = getFragmentByPosition(position);
			}
			f.setFace(colorCounter, FacePosition.TOP);
			cube.setFragmentByPosition(position, f);
			
			// Würfel wieder zurückdrehen, damit Ausgangssituation wieder hergestellt ist
			for (int j = sideNumber - 1; j >= 0; j--) {
				MoveHandler.doMoveSequence(cube, scanSequenceReverse.get(j));
			}
		}
		
		// Würfel so drehen, dass er mit echtem Würfel übereinstimmt
		for (MoveSequence moveSequence : scanSequence) {
			MoveHandler.doMoveSequence(cube, moveSequence);
		}
		
		return cube;
	}
	
	/*
	 * Erstellt ein Fragment anhand der Position.
	 * Ein Fragment ist entweder Ecke, Kante oder Mitte.
	 */
	private Fragment getFragmentByPosition(int position) {
		Fragment result;
		if (position == 0 || position == 2 || position == 6 || position == 8) {
			result = new Corner();
		} else if (position == 4) {
			result = new Middle();
		} else {
			result = new Edge();
		}
		return result;
	}
	
	/*
	 * gibt aufgrund der Position auf der Oberfläche beim Einscannvorgang an, 
	 * um welche Position es sich beim Cube-Modell handelt
	 */
	private int parsePosition(int arrayPos) {
		int result = -1;
		switch(arrayPos) {
		case 0:
			result = 4;
			break;
		case 1:
			result = 1;
			break;
		case 2:
			result = 2;
			break;
		case 3: 
			result = 5;
			break;
		case 4:
			result = 8;
			break;
		case 5:
			result = 7;
			break;
		case 6:
			result = 6;
			break;
		case 7:
			result = 3;
			break;
		case 8:
			result = 0;
			break;		
		default:
			break;
		}
		return result;
	}
	
	
	/*
	 * Scannt eine Seite des Cubes
	 */
	private void ScanCubeSide(ArrayList<float[]> scannedColors) throws PositionNotAllowedException, LayerNotAllowedException {
		OpticalArm opticalArm = this.robot.getOpticalArm();
		scannedColors.add(opticalArm.scanMiddleBlock());
		
		// rundherum die Farben auslesen
		for(int i = 0; i < 4; i++) {
			scannedColors.add(opticalArm.scanEdgeBlock());
			this.robot.rotateCubeToCornerPosition();
			scannedColors.add(opticalArm.scanCornerBlock());

			// um 45° weiter drehen, damit er wieder auf Kantenposition steht
			this.robot.rotateCubeToCornerPosition();
		}
		opticalArm.defaultPosition();
	}
}
