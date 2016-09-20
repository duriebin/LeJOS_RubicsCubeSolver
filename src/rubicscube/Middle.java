package rubicscube;

public class Middle extends Fragment {
	
	public Middle() { }
	
	/*
	 * Initialisiert ein Mittelstück mit der entsprechenden Seite
	 */
	public Middle(CubeLayer layer, 
			CubePosition position, 
			int colorTopBottomLeftRightFrontBack) throws PositionNotAllowedException, LayerNotAllowedException {
		
		// Alle Seiten leer setzen
		super.setAllEmptyFaces();
		
		switch (layer) {
		case TOP:
			// Mitte oben
			super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.TOP);
			break;
		case BOTTOM:
			// Mitte unten
			super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.BOTTOM);
			break;
		case MIDDLE:
			// Mitte in der mittleren Schicht (entweder vorne, hinten, links oder rechts)
			switch (position) {
			case LEFTMIDDLE:
				super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.LEFT);
				break;
			case RIGHTMIDDLE:
				super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.RIGHT);
				break;
			case BACKMIDDLE:
				super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.BACK);
				break;
			case FRONTMIDDLE:
				super.setFace(colorTopBottomLeftRightFrontBack, FacePosition.FRONT);
				break;
			default:
				throw new PositionNotAllowedException(position);
			}
			break;
		default:
			throw new LayerNotAllowedException(layer);
		}
	}
}
