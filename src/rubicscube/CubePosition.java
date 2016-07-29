package rubicscube;

/*
 * Gibt die Position des Steins beim Blick von oben an
 */
public enum CubePosition {
	LEFTFRONT(0, 0),
	LEFTMIDDLE(1, 0),
	LEFTBACK(2, 0),
	BACKMIDDLE(2, 1),
	FRONTMIDDLE(0, 1),
	MIDDLE(1, 1),
	RIGHTFRONT(0, 2),
	RIGHTMIDDLE(1, 2),
	RIGHTBACK(2, 2);
	
	private int positionXValue;
	private int positionYValue;
	
	private CubePosition(int positionXValue, int positionYValue) {
		this.positionXValue = positionXValue;
		this.positionYValue = positionYValue;
	}
	
	/*
	 * X-Position also von links nach rechts im Cube-Modell (ArrayListe zweiter Ebene)
	 */
	public int getXPositionValue() {
		return positionXValue;
	}
	
	/*
	 * Y-Position also von vorne nach hinten im Cube-Modell (ArrayListe dritter Ebene)
	 */
	public int getYPositionValue() {
		return positionYValue;
	}
}