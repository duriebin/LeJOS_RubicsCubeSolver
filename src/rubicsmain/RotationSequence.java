package rubicsmain;

import java.util.ArrayList;
import java.util.Arrays;

import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Move;
import rubicscube.MoveSequence;

public class RotationSequence {
	
	/*
	 * Abfolge von Zugsequenzen für das Scannen des Würfels
	 * Die erste Seite muss bereits zuvor eingescanned werden
	 */
	public final static ArrayList<MoveSequence> scanSequence = new ArrayList<>(
			Arrays.asList(new MoveSequence[] { 
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.COUNTERCLOCKWISE), new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE), new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE))
			}));
	
	/*
	 * Abfolge von Zugsequenzen entgegengesetzt der scanSequence
	 */
	public final static ArrayList<MoveSequence> scanSequenceReverse = new ArrayList<>(
			Arrays.asList(new MoveSequence[] { 
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE), new Move(CubeRotation.HORIZONTALWHOLE, CubeDirection.CLOCKWISE)),
				new MoveSequence(new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE), new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE))
			}));
}
