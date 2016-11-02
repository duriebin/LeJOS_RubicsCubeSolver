package rubicscube;

/*
 * Gibt die Rotationsebene an
 * Die mittlere Ebene kann nicht gedreht werden
 */
public enum CubeRotation {
	HORIZONTALTOP,
	HORIZONTALMIDDLE,
	HORIZONTALBOTTOM,
	HORIZONTALWHOLE,
	VERTICALFRONT,
	VERTICALMIDDLE,
	VERTICALBACK,
	VERTICALWHOLE,
	FORWARDLEFT,
	FORWARDMIDDLE,
	FORWARDRIGHT,
	FORWARDWHOLE;
	
	/*
	 * Gibt eine horizontale Rotation anhand der Position zurück
	 */
	public static CubeRotation getHorizontalRotation(int pos) {
		CubeRotation result = null;
		int layer = Cube.getLayer(pos);
		if (layer == 0) {
			result = CubeRotation.HORIZONTALTOP;
		} else if (layer == 1) {
			result = CubeRotation.HORIZONTALMIDDLE;
		} else if(layer == 2) {
			result = CubeRotation.HORIZONTALBOTTOM;
		}
		return result;
	}
	
	/*
	 * Gibt eine vertical Rotation anhand der Position zurück
	 */
	public static CubeRotation getVerticalRotation(int pos) {
		CubeRotation result = null;
		int row = Cube.getRow(pos);
		if (row == 0) {
			result = CubeRotation.VERTICALFRONT;
		} else if (row == 1) {
			result = CubeRotation.VERTICALMIDDLE;
		} else if(row == 2) {
			result = CubeRotation.VERTICALBACK;
		}
		return result;
	}
	
	/*
	 * Gibt eine horizontale Rotation anhand der Position zurück
	 */
	public static CubeRotation getForwardRotation(int pos) {
		CubeRotation result = null;
		int column = Cube.getColumn(pos);
		if (column == 0) {
			result = CubeRotation.FORWARDLEFT;
		} else if (column == 1) {
			result = CubeRotation.FORWARDMIDDLE;
		} else if(column == 2) {
			result = CubeRotation.FORWARDRIGHT;
		}
		return result;
	}
	
	/*
	 * Gibt eine zufällige Rotation zurück
	 */
	public static CubeRotation getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
