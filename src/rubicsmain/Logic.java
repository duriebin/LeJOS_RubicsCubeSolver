package rubicsmain;

import rubicscube.Cube;
import rubicsrobot.Robot;

public class Logic {
	
	private Robot robot;
	
	public Logic(Robot robot) {
		this.robot = robot;
	}
	
	public Cube scanCube() {
		Cube cube = new Cube();
		
		// Ersten 4 Seiten einscannen
		for (int i = 0; i < 4; i++) {
			ScanCubeSide(cube);
			
			// beim letzten Mal muss der Cube nicht mehr gedreht werden, weil bereits alle 4 Seiten eingescannt sind
			if (i != 4) {
				this.robot.flipCube();
			}
		}
		
		// die 2 verbliebenen Seiten einscannen
		this.robot.rotatePlatformClockwise();
		this.robot.flipCube();
		ScanCubeSide(cube);
		for (int i = 0; i < 2; i++) {
			this.robot.flipCube();
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
