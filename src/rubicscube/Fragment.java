package rubicscube;

import lejos.robotics.Color;

public abstract class Fragment {
	protected Face faceTop;
	protected Face faceBottom;
	protected Face faceLeft;
	protected Face faceRight;
	protected Face faceFront;
	protected Face faceBack;
	
	
	/*
	 * Konstruktor zum Initialisieren des Fragments mit allen Auﬂenfl‰chen
	 */
	public Fragment(int colorTop, 
					int colorLeft, 
					int colorFront, 
					int colorRight, 
					int colorBack, 
					int colorBottom) {
		setFaces(colorTop, colorLeft, colorFront, colorRight, colorBack, colorBottom);
	}
	
	/*
	 * Parameterloser Konstruktor
	 * Dabei muss beachtet werden, dass die Auﬂenfl‰chen noch initialisiert werden m¸ssen
	 */
	public Fragment() {}
	
	/*
	 * Setzt alle Oberfl‰chen auf eine leere Fl‰che
	 */
	protected void setAllEmptyFaces() {
		this.faceTop = new Face(Color.NONE);
		this.faceLeft = new Face(Color.NONE);
		this.faceFront = new Face(Color.NONE);
		this.faceRight = new Face(Color.NONE);
		this.faceBack = new Face(Color.NONE);
		this.faceBottom = new Face(Color.NONE);
	}
	
	/*
	 * Initialisiert alle Oberfl‰chen
	 */
	protected void setFaces(int colorTop, 
					int colorLeft, 
					int colorFront, 
					int colorRight, 
					int colorBack, 
					int colorBottom) {
		this.faceTop = new Face(colorTop);
		this.faceLeft = new Face(colorLeft);
		this.faceFront = new Face(colorFront);
		this.faceRight = new Face(colorRight);
		this.faceBack = new Face(colorBack);
		this.faceBottom = new Face(colorBottom);
	}
	
	/*
	 * Setzt eine Oberfl‰che an einer bestimmten Auﬂenseite
	 */
	protected void setFace(int color, FacePosition position) {
		switch(position) {
		case TOP:
			this.faceTop = new Face(color);
			break;
		case LEFT:
			this.faceLeft = new Face(color);
			break;
		case FRONT:
			this.faceFront = new Face(color);
			break;
		case RIGHT:
			this.faceRight = new Face(color);
			break;
		case BACK:
			this.faceBack = new Face(color);
			break;
		case BOTTOM:
			this.faceBottom = new Face(color);
			break;
		default:
				break;
		}
	}
	
	/*
	 * Dreht das Fragment im Uhrzeigersinn von vorne
	 */
	protected void rotateClockwise() {
		Face tmpFace = this.faceLeft;
		this.faceLeft = this.faceFront;
		this.faceFront = this.faceRight;
		this.faceRight = this.faceBack;
		this.faceBack = tmpFace;
	}
	
	/*
	 * Dreht das Fragment gegen den Uhrzeigersinn von vorne
	 */
	protected void rotateCounterclockwise() {
		Face tmpFace = this.faceLeft;
		this.faceLeft = this.faceBack;
		this.faceBack = this.faceRight;
		this.faceRight = this.faceFront;
		this.faceFront = tmpFace;
	}
	
	/*
	 * Dreht das Fragment im Uhrzeigersinn vertikal
	 */
	protected void rotateClockwiseVertical() {
		Face tmpFace = this.faceBack;
		this.faceBack = this.faceBottom;
		this.faceBottom = this.faceFront;
		this.faceFront = this.faceTop;
		this.faceTop = tmpFace;
	}
	
	/*
	 * Dreht das Fragment gegen den Uhrzeigersinn vertikal
	 */
	protected void rotateCounterclockwiseVertical() {
		Face tmpFace = this.faceBack;
		this.faceBack = this.faceTop;
		this.faceTop = this.faceFront;
		this.faceFront = this.faceBottom;
		this.faceBottom = tmpFace;
	}

	/*
	 * Dreht ein Fragment
	 */
	public void rotate(CubeRotation rotation, CubeDirection direction) {
		if (rotation == CubeRotation.HORIZONTALBOTTOM ||
				rotation == CubeRotation.HORIZONTALTOP) {
			if (direction == CubeDirection.CLOCKWISE) {
				this.rotateClockwise();
			} else {
				this.rotateCounterclockwise();
			}
		} else {
			if (direction == CubeDirection.CLOCKWISE) {
				this.rotateClockwiseVertical();
			} else {
				this.rotateCounterclockwiseVertical();
			}
		}
	}
	
	@Override
	public String toString() {
		return 	this.faceTop.toString() + this.faceLeft.toString() + 
				this.faceFront.toString() + this.faceRight.toString() + 
				this.faceBack.toString() + this.faceBottom.toString();
	}
}
