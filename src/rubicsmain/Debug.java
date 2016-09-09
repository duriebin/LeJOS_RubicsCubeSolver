package rubicsmain;

import lejos.hardware.Button;
import rubicsrobot.OpticalArm;
import rubicsrobot.Robot;

public class Debug {
	private Robot robot;
	
	public Debug(Robot r) {
		this.robot = r;
	}
	
	public void debugOpticalArm() {
		boolean doLoop = true;
		OpticalArm opticalArm = this.robot.getOpticalArm();
		while(doLoop) {
			int button = Button.waitForAnyPress();
			switch(button) {
			case Button.ID_LEFT:
				opticalArm.moveOneDegreeClockwise();
				opticalArm.debugScan();
				break;
			case Button.ID_RIGHT:
				opticalArm.moveOneDegreeCounterclockwise();
				opticalArm.debugScan();
				break;
			default:
				doLoop = false;
				break;
			}
		}
	}
}
