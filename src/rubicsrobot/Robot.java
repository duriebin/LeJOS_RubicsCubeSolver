package rubicsrobot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Robot {
	private RotationPlatform rotationPlatform;
	private OpticalArm opticalArm;
	private Grappler grappler;
	
	/*
	 * Zugriff auf den optischen Arm
	 */
	public OpticalArm getOpticalArm() {
		return this.opticalArm;
	}
	
	/*
	 * Konstruktor zum Initialisieren der Motoren und Sensor
	 */
	public Robot() {
		this.rotationPlatform = new RotationPlatform(MotorPort.B);
		this.grappler = new Grappler(MotorPort.A);
		this.opticalArm = new OpticalArm(MotorPort.C, SensorPort.S1, this);
	}
	
	/*
	 * Dreht den Cube von einer Seite auf die andere
	 */
	public void flipCube() {
		this.grappler.flipCube();
	}
	
	/*
	 * Dreht die Platform um 90° im Uhrzeigersinn
	 */
	public void rotatePlatformClockwise() {
		this.rotationPlatform.rotatePlatformClockwise();
	}
	
	/*
	 * Dreht die Platform um 90° gegen den Uhrzeigersinn
	 */
	public void rotatePlatformCounterclockwise() {
		this.rotationPlatform.rotatePlatformCounterclockwise();
	}
	
	/*
	 * Rotiert die untenliegende Ebene des Cubes im Uhrzeigersinn
	 */
	public void rotateCubeClockwise() {
		this.grappler.holdCube();
		this.rotationPlatform.rotateClockwiseForCubeRotation();
		this.grappler.releaseCube();
	}
	
	/*
	 * Rotiert die untenliegende Ebene des Cubes gegen den Uhrzeigersinn
	 */
	public void rotateCubeCounterclockwise() {
		this.grappler.holdCube();
		this.rotationPlatform.rotateCounterclockwiseForCubeRotation();
		this.grappler.releaseCube();
	}
	
	/*
	 * Dreht den Cube um 45°
	 */
	public void rotateCubeToCornerPosition() {
		this.rotationPlatform.rotateToCornerPosition();
	}
	

	/*
	 * Zeigt die übergebenen Infos für die übergebene Wartezeit in ms an
	 */
	public void displayInformation(String info, int sleep) {
		LCD.drawString(info, 0, 4);
		sleep(sleep);
	}
	
	/*
	 * macht für die angegebenen ms nichts
	 */
	public void sleep(int ms) {
		Delay.msDelay(ms);
	}
	
	/*
	 * Dreht alle Motoren auf ihren Anfangswert (0°) zurück
	 */
	public void turnAllToDefault() {
		this.grappler.defaultPosition();
		this.opticalArm.defaultPosition();
		this.rotationPlatform.defaultPosition();
	}
}