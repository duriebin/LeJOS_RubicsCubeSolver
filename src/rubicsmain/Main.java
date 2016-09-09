package rubicsmain;

import rubicscube.Cube;
import rubicscube.LayerNotAllowedException;
import rubicscube.PositionNotAllowedException;
import rubicsrobot.Robot;

public class Main {

	public static void main(String[] args) {
		Robot r = new Robot();
		Debug d = new Debug(r);
//		d.debugOpticalArm();
		Logic l = new Logic(r);
		Cube c = null;
		try {
			c = l.scanCube();
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		r.turnAllToDefault();
	}
}