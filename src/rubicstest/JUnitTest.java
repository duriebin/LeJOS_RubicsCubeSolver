package rubicstest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import org.unitils.reflectionassert.ReflectionAssert;

import rubicscube.Cube;
import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.MoveSequence;
import rubicsmain.HumanSolvingAlgorithm;
import rubicsmain.Logic;
import rubicsmain.MoveHandler;
import rubicsmain.RotationTranslationHandler;

public class JUnitTest {
	
	@Test
	public void startSolvingTest() {
		Cube cube = FakeData.getFakeCube();
		HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
		MoveSequence moves = alg.solveCube();
		MoveHandler.doMoveSequence(cube, moves);
		assertEquals(true, cube.isSolved());
	}
	
	@Test
	public void startOptimizationTest() {
		Cube cube = FakeData.getFakeCube();
		HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
		MoveSequence moves = alg.solveCube();
		System.out.println(moves.getMoves().size());
		Logic l = new Logic(null);
		l.opitimizeMoveSequence(moves, false);
		System.out.println(moves.getMoves().size());
		MoveSequence translatedSequence = RotationTranslationHandler.translateToRobotRotations(moves);
		System.out.println(translatedSequence.getMoves().size());
		l.opitimizeMoveSequence(translatedSequence, true);
		System.out.println(translatedSequence.getMoves().size());
		MoveHandler.doMoveSequence(cube, translatedSequence);
		assertEquals(true, cube.isSolved());
	}
	
	@Test
	public void startOptimizationBatchTest() {
		Cube cube = FakeData.getFakeCube();
		
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < 100; i++) {
				cube.performRandomMove();
			}
			HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
			MoveSequence moves = alg.solveCube();
			System.out.println(moves.getMoves().size());
			Logic l = new Logic(null);
			l.opitimizeMoveSequence(moves, false);
			System.out.println(moves.getMoves().size());
			MoveSequence translatedSequence = RotationTranslationHandler.translateToRobotRotations(moves);
			System.out.println(translatedSequence.getMoves().size());
			l.opitimizeMoveSequence(translatedSequence, true);
			System.out.println(translatedSequence.getMoves().size());
			MoveHandler.doMoveSequence(cube, translatedSequence);
			assertEquals(true, cube.isSolved());
		}
	}
	
	@Test
	public void startRandomSolvingTest() {
		Cube cube = FakeData.getFakeCube();
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < 100; i++) {
				cube.performRandomMove();
			}
			HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
			MoveSequence solveSequence = alg.solveCube();
			MoveHandler.doMoveSequence(cube, solveSequence);
			assertEquals(true, cube.isSolved());
		}
	}
	
	@Test
	public void startMechanicalTranslationTest() {
		Cube cube = FakeData.getFakeCube();
		for (int j = 0; j < 1000; j++) {
			for (int i = 0; i < 100; i++) {
				cube.performRandomMove();
			}
			HumanSolvingAlgorithm alg = new HumanSolvingAlgorithm(cube);
			MoveSequence solveSequence = alg.solveCube();
			MoveSequence translatedSequence = RotationTranslationHandler.translateToRobotRotations(solveSequence);
			MoveHandler.doMoveSequence(cube, translatedSequence);
			assertEquals(true, cube.isSolved());
		}
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
		
		// Fakew�rfel in die Lage drehen, wie echter W�rfel liegt
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