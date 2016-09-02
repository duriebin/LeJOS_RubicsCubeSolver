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
	 * Hält den Cube
	 */
	public void holdCube() {
		this.grapplerMotor.rotateTo(90);
	}
	
	/*
	 * Lässt den Cube los (fährt auf Ausgangsposition 0° zurück)
	 */
	public void releaseCube() {
		this.grapplerMotor.rotateTo(0);
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0°) zurückgedreht
	 */
	public void defaultPosition() {
		this.grapplerMotor.rotateTo(0);
	}
}
