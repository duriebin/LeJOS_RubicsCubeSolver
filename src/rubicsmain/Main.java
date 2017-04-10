package rubicsmain;

import rubicscube.Cube;
import rubicscube.MoveSequence;
import rubicsrobot.Robot;
import rubicstest.FakeData;

public class Main {

	public static void main(String[] args) {
		Robot r = new Robot();
		Logic l = new Logic(r);
		l.solveCube();
		r.turnAllToDefault();
		
		
//		Cube cube = FakeData.getFakeCube();
//		HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
//		MoveSequence moves = alg.solveCube();
//		l.opitimizeMoveSequence(moves);
//		System.out.println(moves.getMoves().size());
//		MoveSequence translatedSequence = RotationTranslationHandler.translateToRobotRotations(moves);
//		l.opitimizeMoveSequence(translatedSequence);
//		MoveHandler.doMoveSequence(r, translatedSequence);
	}
}