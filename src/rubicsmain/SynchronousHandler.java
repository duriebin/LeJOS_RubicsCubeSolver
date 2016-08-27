package rubicsmain;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicsrobot.Robot;

/*
 * Führt synchrone Operationen am echten sowie am virtuellen Cube durch
 */
public class SynchronousHandler {
	private Cube cube;
	private Robot robot;
	
	/*
	 * Der Cube sowie der Robot an dem die Operationen durchgeführt werden
	 */
	public SynchronousHandler(Cube cube, Robot robot) {
		this.cube = cube;
		this.robot = robot;
	}
	
	/*
	 * Führt sowohl am mechanischen als auch am virtuellem Cube Drehungen durch
	 */
	public void doSynchronousRotation(CubeRotation rotation, CubeDirection direction) {
		this.cube.rotateCube(rotation, direction);
		RotationTranslationHandler.doRobotRotation(this.robot, rotation, direction);
	}
}
