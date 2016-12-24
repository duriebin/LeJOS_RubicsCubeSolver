package rubicsmain;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.LayerNotAllowedException;
import rubicscube.Move;
import rubicscube.PositionNotAllowedException;
import rubicsrobot.Robot;
import rubicstest.FakeData;

public class Main {

	public static void main(String[] args) {
		Robot r = new Robot();
//		for (int i = 0; i < 10; i++) {
//			r.flipCube();
//			r.rotateCubeClockwise();
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			r.flipCube();
//			r.rotateCubeCounterclockwise();
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			r.flipCube();
//			r.rotatePlatformClockwise();
//		}
//		
//		for (int i = 0; i < 10; i++) {
//			r.flipCube();
//			r.rotatePlatformCounterclockwise();
//		}
		
//		for (int i = 0; i < 100; i++) {
//			MoveHandler.doMove(r, new Move(CubeRotation.getRandom(), CubeDirection.getRandom()));
//		}
		
		Logic l = new Logic(r);
		l.solveCube();
		
//		Cube c = null;
//		Cube fakeCube = FakeData.getFakeCube();
//		int errorCounter = 0;
//		for (int j = 0; j < 10; j++) {
//			try {
//				c = l.scanCube();
//				
//				// Fakewürfel in die Lage drehen, wie echter Würfel liegt
//				for (int i = 0; i < 2; i++) {
//					fakeCube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
//				}
//				fakeCube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
//				
//				// Vergleichen
//				if (!fakeCube.equals(c)) {
//					errorCounter++;
//					l.solveCube(c);
//				}
//				
//				// Alles zurückdrehen
//				//r.turnAllToDefault();
//			} catch (PositionNotAllowedException e) {
//				e.printStackTrace();
//			} catch (LayerNotAllowedException e) {
//				e.printStackTrace();
//			} 
//		}
	}
}