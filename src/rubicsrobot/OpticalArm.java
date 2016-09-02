package rubicsrobot;

import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
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
		this.opticalMotor.rotateTo(-95 * 3); // TODO: testen; zu 115° drehen (1:3 Übersetzung)
	}
	
	public void moveToEdgeBlock() {
		this.opticalMotor.rotateTo(-115 * 3); // TODO: testen; zu 120° drehen (1:3 Übersetzung)
	}
	
	public int scanBlock() {
		float[] rgb = new float[this.sampleSize];
		this.sampleProvider.fetchSample(rgb, 0);
		return ColorManager.getInstance().parseRGB(rgb);
	}
	
	/*
	 * Motor wird zu seiner Ausgangsposition (0°) zurückgedreht
	 */
	public void defaultPosition() {
		this.opticalMotor.rotateTo(0);
	}
}
