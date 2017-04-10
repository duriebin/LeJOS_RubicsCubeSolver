package rubicsrobot;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;

public class OpticalArm {
	private RegulatedMotor opticalMotor;
	private EV3ColorSensor colorSensor;
	private SampleProvider sampleProvider;
	private int sampleSize;
	private Robot robot;
	private Grappler grappler;
	private int[] positions;
	
	/*
	 * Initialisiert den optischen Arm
	 */
	public OpticalArm(Port enginePort, Port sensorPort, Robot robot, Grappler grappler) {
		this.opticalMotor = new EV3MediumRegulatedMotor(enginePort);
		this.opticalMotor.setSpeed(480); // in Grad pro Sekunde
		this.colorSensor = new EV3ColorSensor(sensorPort);
		this.sampleProvider = this.colorSensor.getRGBMode();
		this.sampleSize = this.sampleProvider.sampleSize();
		this.robot = robot;
		this.grappler = grappler;
		this.positions = new int[] { -150, -150, -150 }; // Standardstartwerte zum Suchen des Würfels
	}
	
	public float[] scanMiddleBlock() {
		this.opticalMotor.rotateTo(0);
		this.grappler.holdCube();
		this.grappler.releaseCube();
		float[] result = scanBlock(2, 20, 3, 30, 0);
		return result;
	}
	
	public float[] scanCornerBlock() {
		return scanBlock(2, 10, 3, 4, 1);
	}
	
	public float[] scanEdgeBlock(boolean withAdjustment) {
		if (withAdjustment) {
			this.opticalMotor.rotateTo(0);
			this.grappler.holdCube();
			this.grappler.releaseCube();
		}
		float[] result = scanBlock(2, 15, 5, 6, 2);
		return result;
	}
	
	/*
	 * Sucht den Anfang des Cubes anhand kleiner Sensorwerte
	 */
	private void searchStart(int savePosition) {
		this.opticalMotor.rotateTo(this.positions[savePosition] + 3 * 15);
		float[] rgb = new float[this.sampleSize];
		boolean isSmall = true;
		boolean posSaved = false;
		
		// 25 mal wird 3 Grad vorgerückt und mindestens 3 Durchgänge,
		// um Spiel des Getriebes auszugleichen
		for (int i = 0; i < 25 && isSmall; i++) {
			this.sampleProvider.fetchSample(rgb, 0); // Block wird gescannt
			
			// Sensor außerhalb Cube => ganz kleine Sensorwerte
			for (int j = 0; j < rgb.length && isSmall; j++) {
				
				// Wenn 2 Stellen hinter dem Komma nicht 0 sind
				// und die 2. Stelle nicht 1 ist
				int convertedNumber = (int)(rgb[j] * 100);
				if (convertedNumber != 0 && convertedNumber != 1) {
					if (i > 2) {
						isSmall = false;
					} else {
						this.positions[savePosition] = this.opticalMotor.getTachoCount();
						posSaved = true;
					}
				}
			}
			if (isSmall) {
				this.opticalMotor.rotate(-3 * 3);
			}
		}
		if (!posSaved) {
			this.positions[savePosition] = this.opticalMotor.getTachoCount();
		}
	}
	
	/*
	 * Scannt einen Block ab der aktuellen Position des Arms.
	 * Es wird dabei immer um degreesToMove vorgerückt und countOfSuccess gleiche Farben in Folge gesucht.
	 * Ergebnis is eine für diese Farbe einheitliche Id.
	 * countOfMoves gibt an, wie oft maximal vorgerückt wird.
	 * startRotationDegrees: Anzahl der Grad, wie viel bereits am Anfang nach vorne gegangen werden soll, 
	 * nachdem die Startposition eingenommen wurde.
	 * savePosition: Gibt den Index im Array positions an, in welcher die gefundene Scanposition gespeichert wird
	 */
	private float[] scanBlock(int degreesToMove, int countOfMoves, int countOfSuccess, int startRotationDegrees, int savePosition) {
		
		// ColorManager-Instanze zur Überprüfung, ob 10 mal die gleiche Farbe gefunden wurde
		ColorManager colorManager;
		int successCounter;
		int previousColor;
		boolean success = false;
		float[] rgb = new float[this.sampleSize];
		do {
			searchStart(savePosition); // Ausrichten der Kamera am Anfang des Würfels
			this.opticalMotor.rotate(-startRotationDegrees * 3); // Startposition zum Lesen einnehmen
			
			successCounter = 0;
			previousColor = -1;
			colorManager = new ColorManager();
			
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
				if (successCounter == countOfSuccess) {
					success = true;
					break;
				}
				this.opticalMotor.rotate(-degreesToMove * 3);
			}
			
			// Bei einem Fehler wird der Block nochmals gescanned
			if (!success) {
				LCD.drawString("Fehler beim Einlesen der Farbe.", 0, 0);
				LCD.drawString("Farbe konnte nicht richtig gelesen werden.", 0, 2);
				
				// Hin und her drehen der Platform, damit Fläche anders liegt
				this.robot.rotatePlatformClockwise();
				this.robot.rotatePlatformCounterclockwise();
				
				// Um 20 Grad weiter unten mit dem Scannen beginnen
				this.positions[savePosition] = this.positions[savePosition] + 20;
			}
		} while(!success);
		
		LCD.clear();
		
		// Durchschnittswert berechnen aus den gesammelten Farbenwerten
		rgb = colorManager.calculateAverage(previousColor);
		return rgb;
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
