package rubicsrobot;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.robotics.RegulatedMotor;

public class RotationPlatform {
	private RegulatedMotor platformMotor;
	
	/*
	 * Initialisiert die Rotations-Platform
	 */
	public RotationPlatform(Port port) {
		this.platformMotor = new EV3LargeRegulatedMotor(port);
		this.platformMotor.setSpeed(360); // in Grad pro Sekunde
	}
	
	/*
	 * Dreht die Platform um 90° in Uhrzeigerrichtung
	 */
	public void rotatePlatformClockwise() {
		this.platformMotor.rotate(90 * 3); // 270 Grad = 1/4 Drehung da 1:3 Übersetzung mit Getriebe
	}
	
	/*
	 * Dreht die Platform um 90° gegen Uhrzeigerrichtung
	 */
	public void rotatePlatformCounterclockwise() {
		this.platformMotor.rotate(-90 * 3); // 270 Grad = 1/4 Drehung da 1:3 Übersetzung mit Getriebe
	}
	
	/*
	 * Dreht die Platform in Uhrzeigerrichtung, aber 15° weiter und wieder zurück,
	 * damit der Würfel vollständig gedreht wird
	 */
	public void rotateClockwiseForCubeRotation() {
		this.platformMotor.rotate(105 * 3); // um 15° weiter als 90° drehen, da Platform größer ist als Cube
		this.platformMotor.rotate(-15 * 3); // 15° zurückdrehen
	}
	
	/*
	 * Dreht die Platform gegen die Uhrzeigerrichtung, aber 15° weiter und wieder zurück,
	 * damit der Würfel vollständig gedreht wird
	 */
	public void rotateCounterclockwiseForCubeRotation() {
		this.platformMotor.rotate(-105 * 3); // um 15° weiter als 90° drehen, da Platform größer ist als Cube
		this.platformMotor.rotate(15 * 3); // 15° zurückdrehen
	}
	
	/*
	 * Rotiert 45° im Uhrzeigersinn
	 */
	public void rotateToCornerPosition() {
		this.platformMotor.rotate(45 * 3);
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0°) zurückgedreht
	 */
	public void defaultPosition() {
		this.platformMotor.rotateTo(0);
	}
}
