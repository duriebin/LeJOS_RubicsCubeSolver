package rubicscube;

import javax.swing.text.LayeredHighlighter.LayerPainter;

@SuppressWarnings("serial")
public class LayerNotAllowedException extends Exception {
	public LayerNotAllowedException() {}
	
	public LayerNotAllowedException(CubeLayer layer) {
		super("Die Schicht \"" + layer.toString() + "\" ist in diesem Kontext nicht erlaubt!");
	}
}
