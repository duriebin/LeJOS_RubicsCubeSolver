package rubicsmain;

import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.Color;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Robot {
//	public void testColorSensor() {
//		EV3ColorSensor colorSensor = new EV3ColorSensor(SensorPort.S1);
//		int colorId = colorSensor.getColorID();
//		Color c = ParseColor(colorId);
//		if (c != null) {
//			LCD.drawString(String.valueOf(c.getRGB()), 0, 0);
//			Sound.beep();
//		}
//		Delay.msDelay(2000);
//		Sound.twoBeeps();
//	}
	
//	private Color ParseColor(int colorId) {
//		Color result = null;
//		switch(colorId) {
//			case 0:
//				result = null; // keine Farbe
//				break;
//			case 1:
//				result = Color.BLACK;
//				break;
//			case 2:
//				result = Color.BLUE;
//				break;
//			case 3:
//				result = Color.GREEN;
//				break;
//			case 4:
//				result = Color.YELLOW;
//				break;
//			case 5:
//				result = Color.RED;
//				break;
//			case 6:
//				result = Color.WHITE;
//				break;
//			case 7:
//				result = Color.BROWN;
//				break;
//			default:
//		}
//		return result;
//	}
	
	public void testLargeEngine() {
		RegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.A);
		m.rotate(360);
		m.forward();
	    LCD.drawString(Float.toString(Battery.getBatteryCurrent()), 0, 4);
	    Delay.msDelay(10000);
	}
}
