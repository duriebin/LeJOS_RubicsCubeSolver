package rubicscube;

public class Corner extends Fragment {
	/*
	 * Initialisiert ein Eckenstück mit den entsprechenden Seiten
	 */
	public Corner(CubeLayer layer, 
			CubePosition position, 
			int colorTopBottom, 
			int colorLeftRight, 
			int colorFrontBack) throws PositionNotAllowedException, LayerNotAllowedException {
		
		// Alle Seiten leer setzen
		super.setAllEmptyFaces();
		
		if (layer == CubeLayer.TOP) {
			// Ecke oben
			super.setFace(colorTopBottom, FacePosition.TOP);
		} else if (layer == CubeLayer.BOTTOM){
			// Ecke unten
			super.setFace(colorTopBottom, FacePosition.BOTTOM);
		} else {
			throw new LayerNotAllowedException(layer);
		}
		
		if (position == CubePosition.LEFTBACK || 
				position == CubePosition.LEFTFRONT) {
			// Ecke links
			super.setFace(colorLeftRight, FacePosition.LEFT);
		} else if (position == CubePosition.RIGHTBACK ||
				position == CubePosition.RIGHTFRONT) {
			// Ecke rechts
			super.setFace(colorLeftRight, FacePosition.RIGHT);
		} else {
			throw new PositionNotAllowedException(position);
		}
		
		if (position == CubePosition.LEFTBACK ||
				position == CubePosition.RIGHTBACK) {
			// Ecke hinten
			super.setFace(colorFrontBack, FacePosition.BACK);
		} else if (position == CubePosition.LEFTFRONT ||
				position == CubePosition.RIGHTFRONT) {
			// Ecke vorne
			super.setFace(colorFrontBack, FacePosition.FRONT);
		} else {
			throw new PositionNotAllowedException(position);
		}
	}
}
