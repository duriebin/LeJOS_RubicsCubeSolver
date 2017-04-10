package rubicsmain;

import java.util.ArrayList;

import lejos.hardware.lcd.LCD;
import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.Edge;
import rubicscube.FacePosition;
import rubicscube.Fragment;
import rubicscube.LayerNotAllowedException;
import rubicscube.Middle;
import rubicscube.Move;
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
	 * Löst sowohl den virtuellen Cube als auch den Realen
	 */
	public void solveCube() {
		
		boolean cubeSuccessfullyScanned;
		Cube c = null;
		MoveSequence moves = null;
		do {
			cubeSuccessfullyScanned = true;
			try {
				c = this.scanCube();
			} catch (Exception ex) {
				this.robot.displayInformation("Würfel wurde nicht richtig eingelesen");
				cubeSuccessfullyScanned = false;
				continue;
			}
			this.robot.clearDisplay();
			Solveable algorithm = new HumanSolvingAlgorithm(c);
			try {
				moves = algorithm.solveCube();
			} catch (Exception ex) {
				this.robot.displayInformation("Würfel wurde nicht richtig eingelesen");
				
				// Würfel wurde nicht richtig eingescannt und konnte deshalb nicht gelöst werden.
				cubeSuccessfullyScanned = false;
			}
		} while(!cubeSuccessfullyScanned);
		opitimizeMoveSequence(moves, false);
		MoveSequence translatedSequence = RotationTranslationHandler.translateToRobotRotations(moves);
		opitimizeMoveSequence(translatedSequence, true);
		MoveHandler.doMoveSequence(this.robot, translatedSequence);
	}
	
	/*
	 * Scannt den gesamten Würfel und erzeugt ihn virtuell
	 */
	private Cube scanCube() throws PositionNotAllowedException, LayerNotAllowedException {
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
	 * Optimiert eine MoveSequence
	 * Sinnlose Züge werden entfernt
	 * isMechanicalTranslation: Gibt an, ob es sich um bereits in mechanische umgewandelte Züge 
	 * handelt oder nicht. Sind die Züge bereits umgewandelt, dürfen nur noch Änderungen vorgenommen werden,
	 * welche mit den zur Verfügung stehenden mechanischen Rotationen funktioniert.
	 */
	public void opitimizeMoveSequence(MoveSequence moveSequence, boolean isMechanicalTranslation) {
		ArrayList<Move> moves = moveSequence.getMoves();
		for (int i = moves.size() - 1; i >= 1; i--) {
			Move currentMove = moves.get(i);
			Move previousMove = moves.get(i - 1);
			
			if (i >= 3) {
				Move ppreviousMove = moves.get(i - 2);
				Move pppreviousMove = moves.get(i - 3);
				
				// Vier gleiche Rotationen hintereinander sind überflüssig
				// und werden gelöscht
				if (currentMove.getRotation() == previousMove.getRotation() &&
					currentMove.getDirection() == previousMove.getDirection() &&
					ppreviousMove.getRotation() == previousMove.getRotation() &&
					ppreviousMove.getDirection() == previousMove.getDirection() &&
					ppreviousMove.getRotation() == pppreviousMove.getRotation() &&
					ppreviousMove.getDirection() == pppreviousMove.getDirection()) {
					moves.remove(i);
					moves.remove(i - 1);
					moves.remove(i - 2);
					moves.remove(i - 3);
					i = i - 3;
					continue;
				}
			}
			
			if (i >= 2 && !isMechanicalTranslation) {
				Move ppreviousMove = moves.get(i - 2);
				
				// Drei gleiche Rotationen hintereinander können 
				// durch eine entgegengesetzte Rotation ausgetauscht werden
				if (currentMove.getRotation() == previousMove.getRotation() &&
					currentMove.getDirection() == previousMove.getDirection() &&
					ppreviousMove.getRotation() == previousMove.getRotation() &&
					ppreviousMove.getDirection() == previousMove.getDirection()) {
					Move newMove = new Move(currentMove.getRotation(), currentMove.getDirection().swap());
					moves.add(i + 1, newMove);
					moves.remove(i);
					moves.remove(i - 1);
					moves.remove(i - 2);
					i = i - 2;
					continue;
				}
			}
			
			// Gleiche Rotation, welche entgegengesetzt sind, sind überflüssig
			// und werden gelöscht
			if (currentMove.getRotation() == previousMove.getRotation() &&
					currentMove.getDirection() != previousMove.getDirection()) {
				moves.remove(i);
				moves.remove(i - 1);
				i--;
				continue;
			}
		}
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
		
		// Rundherum werden hier die Farben ausgelesen.
		for(int i = 0; i < 4; i++) {
			boolean withAdjustment = i == 0 ? false : true;
			scannedColors.add(opticalArm.scanEdgeBlock(withAdjustment));
			this.robot.rotateCubeToCornerPosition();
			scannedColors.add(opticalArm.scanCornerBlock());

			// Um 45° weiter drehen, damit er wieder auf einer Kantenposition steht.
			this.robot.rotateCubeToCornerPosition();
		}
		opticalArm.defaultPosition();
	}
}
