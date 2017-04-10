package platformcalibration;

import java.util.prefs.Preferences;

import lejos.hardware.Button;
import rubicsrobot.Robot;

public class PlatformCalibration {

	public static void main(String[] args) {
		Robot r = new Robot();
		boolean doLoop = true;
		while(doLoop) {
			int button = Button.waitForAnyPress();
			switch(button) {
				case Button.ID_LEFT:
					r.rotateOneDegreeClockwise();
					break;
				case Button.ID_RIGHT:
					r.rotateOneDegreeCounterclockwise();
					break;
				case Button.ID_ESCAPE:
					doLoop = false;
					break;
				case Button.ID_ENTER:
					r.rotatePlatformClockwise();
					break;
				case Button.ID_UP:
					r.rotateCubeClockwise();
					break;
				case Button.ID_DOWN:
					r.rotateCubeCounterclockwise();
					break;
				default:
					doLoop = false;
					break;
			}
		}
	}

}
