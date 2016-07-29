package rubicstest;

import static org.junit.Assert.*;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;

public class JUnitTest {

	@Test
	public void startRotationTest() {
		Cube cube = FakeData.getFakeCube();
//		cube.displayInConsole();
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getHorizontalTopClockwiseRotatedFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
		
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		cube.rotateCube(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE);
		ReflectionAssert.assertReflectionEquals(cube, FakeData.getFakeCube());
	}
}