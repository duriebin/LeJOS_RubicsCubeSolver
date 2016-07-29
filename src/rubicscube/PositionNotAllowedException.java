package rubicscube;

@SuppressWarnings("serial")
public class PositionNotAllowedException extends Exception {
	public PositionNotAllowedException() {}
	
	public PositionNotAllowedException(CubePosition position) {
		super("Die Position \"" + position.toString() + "\" ist in diesem Kontext nicht erlaubt!");
	}
}
