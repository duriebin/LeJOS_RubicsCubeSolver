package rubicsmain;

import rubicscube.Cube;
import rubicscube.Move;
import rubicscube.MoveSequence;
import rubicsrobot.Robot;

/*
 * Führt Züge am Würfel durch
 */
public class MoveHandler {
	public static void doMove(Robot robot, Move move) {
		RotationTranslationHandler.doRobotRotation(robot, move.getRotation(), move.getDirection());
	}
	
	public static void doMoveSequence(Robot robot, MoveSequence moveSequence) {
		for (Move m : moveSequence) {
			MoveHandler.doMove(robot, m);
		}
	}
	
	public static void doMove(Cube cube, Move move) {
		cube.rotateCube(move.getRotation(), move.getDirection());
	}
	
	public static void doMoveSequence(Cube cube, MoveSequence moveSequence) {
		for (Move m : moveSequence) {
			MoveHandler.doMove(cube, m);
		}
	}
}
