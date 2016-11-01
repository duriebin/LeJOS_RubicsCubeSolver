package rubicsmain;

import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicsrobot.Robot;

/*
 * Sorgt f�r eine einheitliche Rotationsvorgehensweise
 * Dabei werden die Enums aus dem Package rubicscube verwendet
 */
public class RotationTranslationHandler {
	
	/*
	 * F�hrt anhand des Rotationsmodels vom Package rubicscube Drehungen am mechanischen Cube durch.
	 * Da mechanisch nur die untere Ebene gedreht werden kann, 
	 * sind f�r manche Operationen mehrere mechanische Schritte notwendig.
	 */
	public static void doRobotRotation(Robot robot, CubeRotation rotation, CubeDirection direction) {
		// TODO: Middle-Rotations hinzuf�gen
		
		switch(rotation) {
		case HORIZONTALBOTTOM:
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			break;
		case HORIZONTALTOP:
			for (int i = 0; i < 2; i++) {
				robot.flipCube();
			}
			
			// Hier ist Rotationsrichtung genau umgedreht, da der Cube umgedreht wurde
			if (direction != CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			for (int i = 0; i < 2; i++) {
				robot.flipCube();
			}
			break;
		case HORIZONTALWHOLE:
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotatePlatformClockwise();
			} else {
				robot.rotatePlatformCounterclockwise();;
			}
			break;
		case VERTICALBACK:
			robot.flipCube();
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			for (int i = 0; i < 3; i++) {
				robot.flipCube();
			}
			break;
		case VERTICALFRONT:
			for (int i = 0; i < 3; i++) {
				robot.flipCube();
			}
			
			// Hier ist Rotationsrichtung genau umgedreht, da der Cube umgedreht wurde
			if (direction != CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			robot.flipCube();
			break;
		case VERTICALWHOLE:
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotatePlatformCounterclockwise();
				robot.flipCube();
				robot.rotatePlatformClockwise();
			} else {
				robot.rotatePlatformClockwise();
				robot.flipCube();
				robot.rotatePlatformCounterclockwise();
			}
			break;
		case FORWARDLEFT:
			robot.rotatePlatformClockwise();
			robot.flipCube();
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotateCubeCounterclockwise(); // umgedreht, weil Ansicht von der Seite die Richtung vertauscht
			} else {
				robot.rotateCubeClockwise();
			}
			for (int i = 0; i < 2; i++) {
				robot.rotatePlatformCounterclockwise();
			}
			robot.flipCube();
			robot.rotatePlatformClockwise();
			break;
		case FORWARDRIGHT:
			robot.rotatePlatformCounterclockwise();
			robot.flipCube();
			if (direction == CubeDirection.CLOCKWISE) {
				robot.rotateCubeClockwise();
			} else {
				robot.rotateCubeCounterclockwise();
			}
			for (int i = 0; i < 2; i++) {
				robot.rotatePlatformCounterclockwise();
			}
			robot.flipCube();
			robot.rotatePlatformCounterclockwise();
			break;
		case FORWARDWHOLE:
			if (direction == CubeDirection.CLOCKWISE) {
				for (int i = 0; i < 3; i++) {
					robot.flipCube();
				}
			} else {
				robot.flipCube();
			}
			break;
		default: 
			break;
		}
	}
}
