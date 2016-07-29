package rubicstest;

import rubicscube.Corner;
import rubicscube.Cube;
import rubicscube.CubeLayer;
import rubicscube.CubePosition;
import rubicscube.Edge;
import rubicscube.LayerNotAllowedException;
import rubicscube.Middle;
import rubicscube.PositionNotAllowedException;

public class FakeData {
	
	/*
	 * Initialisiert einen Cube mit plausiblen Fakedaten
	 */
	public static Cube getFakeCube() {
		Cube result = new Cube();
		try {
			// 8 Ecken
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTFRONT, new Corner(CubeLayer.TOP, CubePosition.LEFTFRONT, 1, 3, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTFRONT, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, 1, 6, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTBACK, new Corner(CubeLayer.TOP, CubePosition.LEFTBACK, 3, 2, 4));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTBACK, new Corner(CubeLayer.TOP, CubePosition.RIGHTBACK, 3, 1, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, 4, 6, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, 5, 4, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTBACK, 6, 5, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, 2, 6, 4));
			
			// 12 Kanten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.LEFTMIDDLE, 4, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, 3, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, 2, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.BACKMIDDLE, new Edge(CubeLayer.TOP, CubePosition.BACKMIDDLE, 1, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, 2, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, 4, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTBACK, 1, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, 5, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, 5, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, 6, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, 4, 6));
			
			// 6 Mitten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.MIDDLE, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.MIDDLE, new Middle(CubeLayer.BOTTOM, CubePosition.MIDDLE, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, 6));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, 5));
			
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Cube getHorizontalTopClockwiseRotatedFakeCube() {
		Cube result = new Cube();
		try {
			// 8 Ecken
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTFRONT, new Corner(CubeLayer.TOP, CubePosition.LEFTFRONT, 1, 2, 6));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTFRONT, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, 3, 5, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTBACK, new Corner(CubeLayer.TOP, CubePosition.LEFTBACK, 1, 2, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTBACK, new Corner(CubeLayer.TOP, CubePosition.RIGHTBACK, 3, 4, 2));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, 4, 6, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, 5, 4, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTBACK, 6, 5, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, 2, 6, 4));
			
			// 12 Kanten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.LEFTMIDDLE, 3, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, 2, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, 1, 5));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.BACKMIDDLE, new Edge(CubeLayer.TOP, CubePosition.BACKMIDDLE, 4, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, 2, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, 4, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTBACK, 1, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, 5, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, 5, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, 6, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, 4, 6));
			
			// 6 Mitten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.MIDDLE, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.MIDDLE, new Middle(CubeLayer.BOTTOM, CubePosition.MIDDLE, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, 6));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, 5));
			
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Cube getHorizontalTopCounterclockwiseRotatedFakeCube() {
		Cube result = new Cube();
		try {
			// 8 Ecken
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTFRONT, new Corner(CubeLayer.TOP, CubePosition.LEFTFRONT, 3, 4, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTFRONT, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, 1, 2, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTBACK, new Corner(CubeLayer.TOP, CubePosition.LEFTBACK, 3, 5, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTBACK, new Corner(CubeLayer.TOP, CubePosition.RIGHTBACK, 1, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, 4, 6, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, 5, 4, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTBACK, 6, 5, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, 2, 6, 4));
			
			// 12 Kanten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.LEFTMIDDLE, 1, 5));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, 4, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, 3, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.BACKMIDDLE, new Edge(CubeLayer.TOP, CubePosition.BACKMIDDLE, 2, 1));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, 2, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, 4, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTBACK, 1, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, 5, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, 5, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, 6, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, 4, 6));
			
			// 6 Mitten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.MIDDLE, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.MIDDLE, new Middle(CubeLayer.BOTTOM, CubePosition.MIDDLE, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, 6));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, 5));
			
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public static Cube getVerticalFrontClockwiseRotatedFakeCube() {
		Cube result = new Cube();
		try {
			// 8 Ecken
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTFRONT, new Corner(CubeLayer.TOP, CubePosition.LEFTFRONT, 6, 4, 5));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTFRONT, new Corner(CubeLayer.TOP, CubePosition.RIGHTFRONT, 3, 1, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTBACK, new Corner(CubeLayer.TOP, CubePosition.LEFTBACK, 3, 2, 4));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTBACK, new Corner(CubeLayer.TOP, CubePosition.RIGHTBACK, 3, 1, 5));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTFRONT, 4, 5, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTFRONT, 6, 1, 2));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.LEFTBACK, 6, 5, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, new Corner(CubeLayer.BOTTOM, CubePosition.RIGHTBACK, 2, 6, 4));
			
			// 12 Kanten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.LEFTMIDDLE, 4, 3));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.FRONTMIDDLE, 4, 2));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.TOP, CubePosition.RIGHTMIDDLE, 2, 1));
			result.setCubeFragment(CubeLayer.TOP, CubePosition.BACKMIDDLE, new Edge(CubeLayer.TOP, CubePosition.BACKMIDDLE, 1, 5));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTFRONT, 1, 6));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTFRONT, 2, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.LEFTBACK, 1, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, new Edge(CubeLayer.MIDDLE, CubePosition.RIGHTBACK, 5, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.LEFTMIDDLE, 5, 3));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.FRONTMIDDLE, 5, 4));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.RIGHTMIDDLE, 2, 6));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, new Edge(CubeLayer.BOTTOM, CubePosition.BACKMIDDLE, 4, 6));
			
			// 6 Mitten
			result.setCubeFragment(CubeLayer.TOP, CubePosition.MIDDLE, new Middle(CubeLayer.TOP, CubePosition.MIDDLE, 1));
			result.setCubeFragment(CubeLayer.BOTTOM, CubePosition.MIDDLE, new Middle(CubeLayer.BOTTOM, CubePosition.MIDDLE, 4));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.LEFTMIDDLE, 3));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.FRONTMIDDLE, 2));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.RIGHTMIDDLE, 6));
			result.setCubeFragment(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, new Middle(CubeLayer.MIDDLE, CubePosition.BACKMIDDLE, 5));
			
		} catch (PositionNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LayerNotAllowedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
