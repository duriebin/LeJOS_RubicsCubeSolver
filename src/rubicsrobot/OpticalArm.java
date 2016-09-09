package rubicsrobot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class OpticalArm {
	private RegulatedMotor opticalMotor;
	private EV3ColorSensor colorSensor;
	private SampleProvider sampleProvider;
	private int sampleSize;
	
	/*
	 * Initialisiert den optischen Arm
	 */
	public OpticalArm(Port engingePort, Port sensorPort) {
		this.opticalMotor = new EV3MediumRegulatedMotor(engingePort);
		this.opticalMotor.setSpeed(360); // in Grad pro Sekunde
		this.colorSensor = new EV3ColorSensor(sensorPort);
		this.sampleProvider = this.colorSensor.getRGBMode();
		this.sampleSize = this.sampleProvider.sampleSize();
	}
	
	public void moveToMiddleBlock() {
		this.opticalMotor.rotateTo(-145 * 3); // zu 140° drehen (1:3 Übersetzung)
	}
	
	public void moveToCornerBlock() {
		this.opticalMotor.rotateTo(-103 * 3); // zu 103° drehen (1:3 Übersetzung)
	}
	
	public void moveToEdgeBlock() {
		this.opticalMotor.rotateTo(-118 * 3); // zu 118° drehen (1:3 Übersetzung)
	}
	
	public int scanBlock() {
		float[] rgb = new float[this.sampleSize];
		this.sampleProvider.fetchSample(rgb, 0);
		return ColorManager.getInstance().parseRGB(rgb);
	}
	
	public void debugScan() {
		
		// Ausgabe der RGB-Werte
		float[] rgb = new float[this.sampleSize];
		this.sampleProvider.fetchSample(rgb, 0);
		LCD.drawString("RGB-Werte: ", 0, 0);
		for (int i = 1; i <= this.sampleSize; i++) {
			LCD.drawString(String.valueOf(rgb[i-1]), 1, i);
		}
		
		// Ausgabe des Position des Arms
		LCD.drawString("Position in Grad * 3:", 0, 4);
		LCD.drawString(String.valueOf(this.opticalMotor.getTachoCount()), 1, 5);
	}
	
	public void moveOneDegreeClockwise() {
		this.opticalMotor.rotate(1 * 3);
	}
	
	public void moveOneDegreeCounterclockwise() {
		this.opticalMotor.rotate(-1 * 3);
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0°) zurückgedreht
	 */
	public void defaultPosition() {
		this.opticalMotor.rotateTo(0);
	}
}
