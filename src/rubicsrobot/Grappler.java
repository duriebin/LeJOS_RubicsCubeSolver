package rubicsrobot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class Grappler {
	private RegulatedMotor grapplerMotor;
	
	/*
	 * Initialisiert den Greifarm
	 */
	public Grappler(Port port) {
		this.grapplerMotor = new EV3LargeRegulatedMotor(port);
		this.grapplerMotor.setSpeed(360); // in Grad pro Sekunde
	}
	
	/*
	 * Dreht den Cube
	 */
	public void flipCube() {
		holdCube();
		this.grapplerMotor.rotate(100);
		this.grapplerMotor.rotate(-100);
		releaseCube();
	}
	
	/*
	 * H�lt den Cube
	 */
	public void holdCube() {
		this.grapplerMotor.rotateTo(90);
	}
	
	/*
	 * L�sst den Cube los (f�hrt auf Ausgangsposition 0� zur�ck)
	 */
	public void releaseCube() {
		this.grapplerMotor.rotateTo(0);
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0�) zur�ckgedreht
	 */
	public void defaultPosition() {
		this.grapplerMotor.rotateTo(0);
	}
}
