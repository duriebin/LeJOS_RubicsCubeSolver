package rubicsmain;

import org.unitils.reflectionassert.ReflectionAssert;

import junit.framework.AssertionFailedError;
import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.LayerNotAllowedException;
import rubicscube.PositionNotAllowedException;
import rubicsrobot.Robot;
import rubicstest.FakeData;

public class Main {

	public static void main(String[] args) {
		Robot r = new Robot();
		Logic l = new Logic(r);
		Cube c = null;
		Cube fakeCube = FakeData.getFakeCube();
		int errorCounter = 0;
		for (int j = 0; j < 10; j++) {
			try {
				c = l.scanCube();
				
				// Fakewürfel in die Lage drehen, wie echter Würfel liegt
				for (int i = 0; i < 2; i++) {
					fakeCube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
				}
				fakeCube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
				
				// Vergleichen
				if (!fakeCube.equals(c)) {
					errorCounter++;
				}
				
				// Alles zurückdrehen
				//r.turnAllToDefault();
			} catch (PositionNotAllowedException e) {
				e.printStackTrace();
			} catch (LayerNotAllowedException e) {
				e.printStackTrace();
			} 
		}
	}
}