package rubicscube;

import lejos.robotics.Color;

public class Edge extends Fragment {
	
	public Edge() {
		// Alle Seiten leer setzen
		super.setAllEmptyFaces();
	}
	
	/*
	 * Initialisiert ein Randstück mit den entsprechenden Seiten
	 */
	/**
	 * @param colorTopBottom: im Falle von CubeLayer MIDDLE muss hier die vordere oder hintere Farbe angegeben werden
	 */
	public Edge(CubeLayer layer, 
			CubePosition position, 
			int colorTopBottom, 
			int colorLeftRightFrontBack) throws PositionNotAllowedException, LayerNotAllowedException {
		
		// Alle Seiten leer setzen
		super.setAllEmptyFaces();
		
		if (layer == CubeLayer.TOP) {
			// Rand oben
			super.setFace(colorTopBottom, FacePosition.TOP);
		} else if (layer == CubeLayer.BOTTOM) {
			// Rand unten
			super.setFace(colorTopBottom, FacePosition.BOTTOM);
		}
		if (layer == CubeLayer.TOP || layer == CubeLayer.BOTTOM) {
			if (position == CubePosition.LEFTMIDDLE) {
				// Rand links
				super.setFace(colorLeftRightFrontBack, FacePosition.LEFT);
			} else if (position == CubePosition.RIGHTMIDDLE) {
				// Rand rechts
				super.setFace(colorLeftRightFrontBack, FacePosition.RIGHT);
			} else if(position == CubePosition.FRONTMIDDLE) {
				// Rand vorne
				super.setFace(colorLeftRightFrontBack, FacePosition.FRONT);
			} else if(position == CubePosition.BACKMIDDLE) {
				// Rand hinten
				super.setFace(colorLeftRightFrontBack, FacePosition.BACK);
			} else {
				throw new PositionNotAllowedException(position);
			}
		} else if (layer == CubeLayer.MIDDLE) {
			// Mittelschicht muss seperat betrachtet werden
			// Zunächst Farbe vorne oder hinten setzen
			if (position == CubePosition.LEFTFRONT || 
					position == CubePosition.RIGHTFRONT) {
				// Rand mitte vorne
				super.setFace(colorTopBottom, FacePosition.FRONT);
			} else if (position == CubePosition.LEFTBACK || 
					position == CubePosition.RIGHTBACK) {
				// Rand mitte hinten
				super.setFace(colorTopBottom, FacePosition.BACK);
			} else {
				throw new PositionNotAllowedException(position);
			}
			
			// Dann Farbe links oder rechts setzen
			if (position == CubePosition.LEFTFRONT || 
					position == CubePosition.LEFTBACK) {
				// Rand mitte links
				super.setFace(colorLeftRightFrontBack, FacePosition.LEFT);
			} else if (position == CubePosition.RIGHTFRONT || 
					position == CubePosition.RIGHTBACK) {
				// Rand mitte rechts
				super.setFace(colorLeftRightFrontBack, FacePosition.RIGHT);
			} else {
				throw new PositionNotAllowedException(position);
			}
		} else {
			throw new LayerNotAllowedException(layer);
		}
	}
}
