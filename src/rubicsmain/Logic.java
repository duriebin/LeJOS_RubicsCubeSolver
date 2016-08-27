package rubicsmain;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicsrobot.Robot;

public class Logic {
	
	private Robot robot;
	
	public Logic(Robot robot) {
		this.robot = robot;
	}
	
	public Cube scanCube() {
		Cube cube = new Cube();
		SynchronousHandler syncHandler = new SynchronousHandler(cube, this.robot);
		
		// Ersten 4 Seiten einscannen
		for (int i = 0; i < 4; i++) {
			ScanCubeSide(cube);
			
			// beim letzten Mal muss der Cube nicht mehr gedreht werden, weil bereits alle 4 Seiten eingescannt sind
			if (i != 4) {
				syncHandler.doSynchronousRotation(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
			}
		}
		
		// die 2 verbliebenen Seiten einscannen
		syncHandler.doSynchronousRotation(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		syncHandler.doSynchronousRotation(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ScanCubeSide(cube);
		for (int i = 0; i < 2; i++) {
			syncHandler.doSynchronousRotation(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		}
		ScanCubeSide(cube);
		
		return cube;
	}
	
	/*
	 * Scannt eine Seite des Cubes
	 */
	private void ScanCubeSide(Cube cube) {
		
	}
}
