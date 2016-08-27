package rubicsrobot;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class OpticalArm {
	private RegulatedMotor opticalMotor;
	
	/*
	 * Initialisiert den optischen Arm
	 */
	public OpticalArm(Port port) {
		this.opticalMotor = new EV3MediumRegulatedMotor(port);
		this.opticalMotor.setSpeed(360); // in Grad pro Sekunde
	}
	
	public void moveToMiddleBlock() {
		this.opticalMotor.rotateTo(-140 * 3); // zu 140° drehen (1:3 Übersetzung)
	}
	
	public void moveToCornerBlock() {
		this.opticalMotor.rotateTo(-115 * 3); // TODO: testen; zu 115° drehen (1:3 Übersetzung)
	}
	
	public void moveToEdgeBlock() {
		this.opticalMotor.rotateTo(-120 * 3); // TODO: testen; zu 120° drehen (1:3 Übersetzung)
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0°) zurückgedreht
	 */
	public void defaultPosition() {
		this.opticalMotor.rotateTo(0);
	}
}
