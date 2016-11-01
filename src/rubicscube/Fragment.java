package rubicscube;

import java.util.ArrayList;

import lejos.robotics.Color;

public abstract class Fragment implements Cloneable {
	protected Face faceTop;
	protected Face faceBottom;
	protected Face faceLeft;
	protected Face faceRight;
	protected Face faceFront;
	protected Face faceBack;
	
	public Face getFaceTop() {
		return faceTop;
	}

	public Face getFaceBottom() {
		return faceBottom;
	}

	public Face getFaceLeft() {
		return faceLeft;
	}

	public Face getFaceRight() {
		return faceRight;
	}

	public Face getFaceFront() {
		return faceFront;
	}

	public Face getFaceBack() {
		return faceBack;
	}
	
	/*
	 * Gibt eine Auflistung aller Farben zurück
	 */
	public ArrayList<Integer> getColors() {
		ArrayList<Integer> result = new ArrayList<>();
		result.add(getFaceTop().getColor());
		result.add(getFaceLeft().getColor());
		result.add(getFaceFront().getColor());
		result.add(getFaceRight().getColor());
		result.add(getFaceBack().getColor());
		result.add(getFaceBottom().getColor());
		return result;
	}

	/*
	 * Konstruktor zum Initialisieren des Fragments mit allen Außenflächen
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
	 * Dabei muss beachtet werden, dass die Außenflächen noch initialisiert werden müssen
	 */
	public Fragment() {}
	
	/*
	 * Setzt alle Oberflächen auf eine leere Fläche
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
	 * Initialisiert alle Oberflächen
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
	 * Setzt eine Oberfläche an einer bestimmten Außenseite
	 */
	public void setFace(int color, FacePosition position) {
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
	 * Dreht das Fragment im Uhrzeigersinn vorwärts
	 */
	protected void rotateClockwiseForward() {
		Face tmpFace = this.faceBack;
		this.faceBack = this.faceBottom;
		this.faceBottom = this.faceFront;
		this.faceFront = this.faceTop;
		this.faceTop = tmpFace;
	}
	
	/*
	 * Dreht das Fragment gegen den Uhrzeigersinn vorwärts
	 */
	protected void rotateCounterclockwiseForward() {
		Face tmpFace = this.faceBack;
		this.faceBack = this.faceTop;
		this.faceTop = this.faceFront;
		this.faceFront = this.faceBottom;
		this.faceBottom = tmpFace;
	}
	
	/*
	 * Dreht das Fragment im Uhrzeigersinn vertikal
	 */
	protected void rotateClockwiseVertical() {
		Face tmpFace = this.faceTop;
		this.faceTop = this.faceLeft;
		this.faceLeft = this.faceBottom;
		this.faceBottom = this.faceRight;
		this.faceRight = tmpFace;
	}
	
	/*
	 * Dreht das Fragment gegen den Uhrzeigersinn vertikal
	 */
	protected void rotateCounterclockwiseVertical() {
		Face tmpFace = this.faceTop;
		this.faceTop = this.faceRight;
		this.faceRight = this.faceBottom;
		this.faceBottom = this.faceLeft;
		this.faceLeft = tmpFace;
	}

	/*
	 * Dreht ein Fragment
	 */
	public void rotate(CubeRotation rotation, CubeDirection direction) {
		if (rotation == CubeRotation.HORIZONTALBOTTOM ||
				rotation == CubeRotation.HORIZONTALTOP || 
				rotation == CubeRotation.HORIZONTALWHOLE ||
				rotation == CubeRotation.HORIZONTALMIDDLE) {
			if (direction == CubeDirection.CLOCKWISE) {
				this.rotateClockwise();
			} else {
				this.rotateCounterclockwise();
			}
		} else if (rotation == CubeRotation.FORWARDLEFT ||
				rotation == CubeRotation.FORWARDRIGHT || 
				rotation == CubeRotation.FORWARDWHOLE ||
				rotation == CubeRotation.FORWARDMIDDLE) {
			if (direction == CubeDirection.CLOCKWISE) {
				this.rotateClockwiseForward();
			} else {
				this.rotateCounterclockwiseForward();
			}
		} else {
			if (direction == CubeDirection.CLOCKWISE) {
				this.rotateClockwiseVertical();
			} else {
				this.rotateCounterclockwiseVertical();
			}
		}
	}
	
	public int getPosition(Cube c) {
		return c.findCubePositionByReference(this);
	}
	
	@Override
	public String toString() {
		return 	this.faceTop.toString() + this.faceLeft.toString() + 
				this.faceFront.toString() + this.faceRight.toString() + 
				this.faceBack.toString() + this.faceBottom.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (obj instanceof Fragment) {
			Fragment f = (Fragment)obj;

			if ((f.faceBack != null && this.faceBack != null && !f.faceBack.equals(this.faceBack))
					|| (f.faceBack == null && this.faceBack != null)
					|| (f.faceBack != null && this.faceBack == null)) {
				result = false;
			} 
			if ((f.faceBottom != null && this.faceBottom != null && !f.faceBottom.equals(this.faceBottom))
					|| (f.faceBottom == null && this.faceBottom != null)
					|| (f.faceBottom != null && this.faceBottom == null)) {
				result = false;
			} 
			if ((f.faceFront != null && this.faceFront != null && !f.faceFront.equals(this.faceFront))
					|| (f.faceFront == null && this.faceFront != null)
					|| (f.faceFront != null && this.faceFront == null)) {
				result = false;
			} 
			if ((f.faceLeft != null && this.faceLeft != null && !f.faceLeft.equals(this.faceLeft))
					|| (f.faceLeft == null && this.faceLeft != null)
					|| (f.faceLeft != null && this.faceLeft == null)) {
				result = false;
			} 
			if ((f.faceRight != null && this.faceRight != null && !f.faceRight.equals(this.faceRight))
					|| (f.faceRight == null && this.faceRight != null)
					|| (f.faceRight != null && this.faceRight == null)) {
				result = false;
			} 
			if ((f.faceTop != null && this.faceTop != null && !f.faceTop.equals(this.faceTop))
					|| (f.faceTop == null && this.faceTop != null)
					|| (f.faceTop != null && this.faceTop == null)) {
				result = false;
			}
		}
		return result;
	}
	
	@Override
	protected Object clone() {
		Fragment f;
		if (this instanceof Middle) {
			f = new Middle(
					this.faceTop.getColor(), 
					this.faceLeft.getColor(), 
					this.faceFront.getColor(), 
					this.faceRight.getColor(), 
					this.faceBack.getColor(), 
					this.faceBottom.getColor());
		} else if (this instanceof Edge) {
			f = new Edge(
					this.faceTop.getColor(), 
					this.faceLeft.getColor(), 
					this.faceFront.getColor(), 
					this.faceRight.getColor(), 
					this.faceBack.getColor(), 
					this.faceBottom.getColor());
		} else {
			f = new Corner(
					this.faceTop.getColor(), 
					this.faceLeft.getColor(), 
					this.faceFront.getColor(), 
					this.faceRight.getColor(), 
					this.faceBack.getColor(), 
					this.faceBottom.getColor());
		}
		return f;
	}
}
