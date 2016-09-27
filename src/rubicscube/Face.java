package rubicscube;

import lejos.robotics.Color;

public class Face {
	private int color;
	
	public Face(int color) {
		this.setColor(color);
	}

	public int getColor() {
		return color;
	}

	private void setColor(int color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		// Für Testzwecke damit nicht -1 ausgegeben wird
		if (color == Color.NONE) {
			return "0";
		}
		return String.valueOf(color);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Face) {
			if (((Face)obj).color == this.color) {
				return true;
			}
		}
		return false;
	}
}
