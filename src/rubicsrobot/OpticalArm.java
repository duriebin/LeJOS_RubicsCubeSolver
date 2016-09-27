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
	
	public float[] scanMiddleBlock() {
		searchStart();
		this.opticalMotor.rotate(-24 * 3);
		return scanBlock(2, 20);
	}
	
	public float[] scanCornerBlock() {
		searchStart();
		this.opticalMotor.rotate(-2 * 3);
		return scanBlock(1, 18);
	}
	
	public float[] scanEdgeBlock() {
		searchStart();
		this.opticalMotor.rotate(-2 * 3);
		return scanBlock(2, 15);
	}
	
	/*
	 * Sucht den Anfang des Cubes anhand kleiner Sensorwerte
	 */
	private void searchStart() {
		this.opticalMotor.rotateTo(-50 * 3);
		float[] rgb = new float[this.sampleSize];
		boolean isSmall = true;
		
		// 25 mal wird 3 Grad vorgerückt
		for (int i = 0; i < 25 && isSmall; i++) {
			this.sampleProvider.fetchSample(rgb, 0); // Block wird gescannt
			
			// Sensor außerhalb Cube => ganz kleine Sensorwerte
			for (int j = 0; j < rgb.length && isSmall; j++) {
				
				// Wenn 2 Stellen hinter dem Komma nicht 0 sind
				// und die 2. Stelle nicht 1 ist
				int convertedNumber = (int)(rgb[j] * 100);
				if (convertedNumber != 0 && convertedNumber != 1) {
					isSmall = false;
				}
			}
			if (isSmall) {
				this.opticalMotor.rotate(-3 * 3);
			}
		}
	}
	
	/*
	 * Scannt einen Block ab der aktuellen Position des Arms.
	 * Es wird dabei immer um 3° vorgerückt und 10 gleiche Farben in Folge gesucht.
	 * Ergebnis is eine für diese Farbe einheitliche Id.
	 */
	private float[] scanBlock(int degreesToMove, int countOfMoves) {
		
		// ColorManager-Instanze zur Überprüfung, ob 10 mal die gleiche Farbe gefunden wurde
		ColorManager colorManager = new ColorManager();
		int successCounter = 0;
		int previousColor = -1;
		boolean success = false;
		float[] rgb = new float[this.sampleSize];
		
		// countOfMoves mal wird degreesToMove Grad vorgerückt
		for (int i = 0; i < countOfMoves; i++) {
			this.sampleProvider.fetchSample(rgb, 0); // Block wird gescannt

			int color = colorManager.parseRGB(rgb);
			if (previousColor == color) {
				++successCounter;
			} else {
				successCounter = 0;
			}
			previousColor = color;
			if (successCounter == 5) {
				success = true;
				break;
			}
			this.opticalMotor.rotate(-degreesToMove * 3);
		}
		
		// TODO: Fehlerhandling
		// Eine Idee wäre, dass der Block nochmals gescannt wird, 
		// oder dass der Cube 4 mal gedreht wird, damit die Ebene erneut eingescannt werden kann
		if (!success) {
			LCD.drawString("Fehler beim Einlesen der Farbe.", 0, 0);
			LCD.drawString("Farbe konnte nicht richtig gelesen werden.", 0, 0);
		}
		
		// Durchschnittswert berechnen aus den gesamelten Farbenwerten
		rgb = colorManager.calculateAverage(previousColor);
		return rgb;
		//int result = ColorManager.getInstance().parseRGB(rgb); // TODO: result-Variable entferen (zu Debugzwecken erstellt)
		//return result;
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
