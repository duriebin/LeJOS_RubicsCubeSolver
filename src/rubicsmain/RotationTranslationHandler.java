package rubicsmain;

import java.util.ArrayList;

import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Move;
import rubicscube.MoveSequence;
import rubicsrobot.Robot;

/*
 * Sorgt für eine einheitliche Rotationsvorgehensweise
 * Dabei werden die Enums aus dem Package rubicscube verwendet
 */
public class RotationTranslationHandler {
	
	/*
	 * Führt, anhand des Rotationsmodels vom Package rubicscube, Drehungen am mechanischen Cube durch.
	 * Die übergebenen Rotationen müssen bereits in mechanische Drehungen übersetzt worden sein
	 * mit der Methode RotationTranslationHandler.translateToRobotRotations.
	 */
	public static void doRobotRotation(Robot robot, CubeRotation rotation, CubeDirection direction) {
		
		// Andere Rotationen, außer die hier aufgelisteten, 
		// kann der Roboter mechanisch nicht ausführen.
		switch(rotation) {
		case HORIZONTALBOTTOM:
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			break;
		case HORIZONTALWHOLE:
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotatePlatformClockwise();
			} else {
				robot.rotatePlatformCounterclockwise();
			}
			break;
		case FORWARDWHOLE:
			robot.flipCube();
			break;
		default: 
			break;
		}
	}
	
	/*
	 * Übersetzt, anhand des Rotationsmodels vom Package rubicscube, Drehungen in Drehungen, 
	 * welche der Roboter mechanisch ausführen kann.
	 * Da mechanisch nur die untere Ebene gedreht werden kann, 
	 * sind für viele Operationen mehrere mechanische Schritte notwendig.
	 */
	public static MoveSequence translateToRobotRotations(MoveSequence movesToConvert) {
		ArrayList<Move> translatedMoves = new ArrayList<Move>();
		for (Move m : movesToConvert) {
			CubeDirection direction = m.getDirection();
			switch (m.getRotation()) {
			case HORIZONTALBOTTOM:
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case HORIZONTALMIDDLE:
				if (direction == CubeDirection.CLOCKWISE) {
					
					// Gegen Uhrzeigersinn, weil oben und unten entgegengesetzt gedreht werden muss, 
					// damit Mitte mit Uhrzeigersinn gedreht wird
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case HORIZONTALTOP:
				for (int i = 0; i < 2; i++) {
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}

				if (direction != CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
				}
				for (int i = 0; i < 2; i++) {
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case HORIZONTALWHOLE:
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case VERTICALBACK:
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
				}
				for (int i = 0; i < 3; i++) {
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case VERTICALMIDDLE:
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));				
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case VERTICALFRONT:
				for (int i = 0; i < 3; i++) {
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				
				// Hier ist Rotationsrichtung genau umgedreht, da der Cube umgedreht wurde
				if (direction != CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
				}
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				break;
			case VERTICALWHOLE:
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			case FORWARDLEFT:
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE)); // umgedreht, weil Ansicht von der Seite die Richtung vertauscht
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				}
				for (int i = 0; i < 2; i++) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				break;
			case FORWARDMIDDLE:
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));				
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
					for (int i = 0; i < 2; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
					
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				break;
			case FORWARDRIGHT:
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				if (direction == CubeDirection.CLOCKWISE) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE));
				} else {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE));
				}
				for (int i = 0; i < 2; i++) {
					translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				translatedMoves.add(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE));
				break;
			case FORWARDWHOLE:
				if (direction == CubeDirection.CLOCKWISE) {
					for (int i = 0; i < 3; i++) {
						translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
					}
				} else {
					translatedMoves.add(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE));
				}
				break;
			default: 
				break;
			}
		}
		
		MoveSequence result = new MoveSequence();
		result.setMoves(translatedMoves);
		return result;
	}
}
