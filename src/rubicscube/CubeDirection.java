package rubicscube;

public enum CubeDirection {
	CLOCKWISE,
	COUNTERCLOCKWISE;
	
	/*
	 * Tauscht die Richtung.
	 * Wenn zuvor Uhrzeigersinn war wird es zu Gegenuhrzeigersinn und umgekehrt.
	 */
	public CubeDirection swap() {
		return this == CubeDirection.CLOCKWISE ? CubeDirection.COUNTERCLOCKWISE : CubeDirection.CLOCKWISE;
	}
	
	/*
	 * Gibt eine zuf�llige Richtung zur�ck
	 */
	public static CubeDirection getRandom() {
        return values()[(int) (Math.random() * values().length)];
    }
}
