package rubicsmain;

import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeLayer;
import rubicscube.CubePosition;
import rubicscube.CubeRotation;
import rubicscube.Edge;
import rubicscube.FacePosition;
import rubicscube.Fragment;
import rubicscube.LayerNotAllowedException;
import rubicscube.Middle;
import rubicscube.PositionNotAllowedException;
import rubicsrobot.OpticalArm;
import rubicsrobot.Robot;

public class Logic {
	
	private Robot robot;
	
	public Logic(Robot robot) {
		this.robot = robot;
	}
	
	public Cube scanCube() throws PositionNotAllowedException, LayerNotAllowedException {
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
	private void ScanCubeSide(Cube cube) throws PositionNotAllowedException, LayerNotAllowedException {
		OpticalArm opticalArm = this.robot.getOpticalArm();
		int color = opticalArm.scanMiddleBlock();
		cube.setFragmentByPosition(4, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, color));
		
		// rundherum die Farben auslesen
		for(int i = 0; i < 4; i++) {
			color = opticalArm.scanEdgeBlock();
			
			// Es kann sein, dass an der Kante bereits ein Fragment mit einer Farbe und einer Fake-Farbe platziert wurde
			Fragment f = cube.getFragmentByPosition(1);
			if (f != null) {
				f.setFace(color, FacePosition.TOP);
			} else {
				cube.setFragmentByPosition(1, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, color, -1)); // -1 ist Fakefarbe, welche später gesetzt wird
			}
			
			this.robot.rotateCubeToCornerPosition();
			color = opticalArm.scanCornerBlock();
			
			f = cube.getFragmentByPosition(2);
			if (f != null) {
				f.setFace(color, FacePosition.TOP);
			} else {
				cube.setFragmentByPosition(2, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, color, -1, -1)); // -1 ist Fakefarbe, welche später gesetzt wird
			}
			
			// um 45° weiter drehen, damit er wieder auf Kantenposition steht
			this.robot.rotateCubeToCornerPosition();
			
			// virutellen Cube drehen
			cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		}
		opticalArm.defaultPosition();
	}
}
