package rubicscube;

public class Move {
	private CubeRotation rotation;
	private CubeDirection direction;
	
	/*
	 * Bildet einen Zug/Drehung am Würfel ab
	 */
	public Move(CubeRotation rotation, CubeDirection direction) {
		this.rotation = rotation;
		this.direction = direction;
	}
	
	/*
	 * Default-Konstruktor
	 */
	public Move() {	}

	public CubeRotation getRotation() {
		return rotation;
	}

	public void setRotation(CubeRotation rotation) {
		this.rotation = rotation;
	}

	public CubeDirection getDirection() {
		return direction;
	}

	public void setDirection(CubeDirection direction) {
		this.direction = direction;
	}
}
