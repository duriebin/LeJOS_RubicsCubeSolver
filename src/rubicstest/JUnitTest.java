package rubicstest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicsmain.ColorSorter;
import rubicsmain.HumanSolvingAlgorithm;
import rubicsmain.Logic;

public class JUnitTest {
	
	@Test
	public void startSolvingTest() {
		Cube cube = FakeData.getFakeCube();
		HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
		alg.solveCube();
	}
	
	@Test
	public void startColorSortingTest() {
//		ArrayList<float[]> colors = FakeData.getFakeColorsCube();
//		ArrayList<float[]> colors = FakeData.getFakeColorsCube2();
//		ArrayList<float[]> colors = FakeData.getFakeColorsCube3();
//		ArrayList<float[]> colors = FakeData.getFakeColorsCube4();
		ArrayList<float[]> colors = FakeData.getFakeColorsCube5();
		Cube cube = new Logic(null).forTesting(colors);
		Cube fakeCube = FakeData.getFakeCube();
		
		// Fakewürfel in die Lage drehen, wie echter Würfel liegt
		for (int i = 0; i < 2; i++) {
			fakeCube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		}
		fakeCube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		
		if (cube.equals(fakeCube)) {
			@SuppressWarnings("unused")
			boolean c = true;
		}
		ReflectionAssert.assertReflectionEquals(fakeCube, cube);
	}
	
	@Test
	public void startRotationTest() {
		Cube cube = FakeData.getFakeCube();
//		cube.displayInConsole();
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals( FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.FORWARDMIDDLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALMIDDLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
		
		
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALMIDDLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(FakeData.getFakeCube(), cube);
	}
}