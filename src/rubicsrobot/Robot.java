package rubicsrobot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class Robot {
	private RotationPlatform rotationPlatform;
	private OpticalArm opticalArm;
	private Grappler grappler;
	
	/*
	 * Konstruktor zum Initialisieren der Motoren und Sensor
	 */
	public Robot() {
		this.rotationPlatform = new RotationPlatform(MotorPort.B);
		this.grappler = new Grappler(MotorPort.A);
		this.opticalArm = new OpticalArm(MotorPort.C);
	}
	
	/*
	 * Dreht den Cube von einer Seite auf die andere
	 */
	public void flipCube() {
		this.grappler.flipCube();
	}
	
	/*
	 * Dreht die Platform um 90� im Uhrzeigersinn
	 */
	public void rotatePlatformClockwise() {
		this.rotationPlatform.rotatePlatformClockwise();
	}
	
	/*
	 * Dreht die Platform um 90� gegen den Uhrzeigersinn
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
	}
	
	/*
	 * Rotiert die untenliegende Ebene des Cubes gegen den Uhrzeigersinn
	 */
	public void rotateCubeCounterclockwise() {
		this.grappler.holdCube();
		this.rotationPlatform.rotateCounterclockwiseForCubeRotation();
	}
	

	/*
	 * Zeigt die �bergebenen Infos f�r die �bergebene Wartezeit in ms an
	 */
	public void displayInformation(String info, int sleep) {
		LCD.drawString(info, 0, 4);
		sleep(sleep);
	}
	
	/*
	 * macht f�r die angegebenen ms nichts
	 */
	public void sleep(int ms) {
		Delay.msDelay(ms);
	}
	
	/*
	 * Dreht alle Motoren auf ihren Anfangswert (0�) zur�ck
	 */
	public void turnAllToDefault() {
		this.grappler.defaultPosition();
		this.opticalArm.defaultPosition();
		this.rotationPlatform.defaultPosition();
	}
}