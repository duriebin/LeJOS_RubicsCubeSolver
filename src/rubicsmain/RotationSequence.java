package rubicsmain;

import java.util.ArrayList;
import java.util.Arrays;

import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Move;
import rubicscube.MoveSequence;

public class RotationSequence {
	
	/*
	 * Abfolge von Zugsequenzen f�r das Scannen des W�rfels
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
	
	/*
	 * Bef�rdert das untenliegende Eckfragment in das dar�ber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe f�r oben liegt an der Vorderseite.
	 */
	public final static MoveSequence firstLayerCornerBottomToTopColorFrontSequence = new MoveSequence(
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	
	/*
	 * Bef�rdert das untenliegende Eckfragment in das dar�ber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe f�r oben liegt an der rechten Seite.
	 */
	public final static MoveSequence firstLayerCornerBottomToTopColorRightSequence = new MoveSequence( 
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	
	/*
	 * Bef�rdert das untenliegende Eckfragment in das dar�ber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe f�r oben liegt an der unteren Seite.
	 */
	public final static MoveSequence firstLayerCornerBottomToTopColorBottomSequence = new MoveSequence(  
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	/*
	 * Bef�rdert eine Kante der untersten Ebene in die linke L�cke der mittleren Ebene
	 */
	public final static MoveSequence middleLayerEdgeLeftSequence = new MoveSequence(  
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE)
		);
	
	/*
	 * Bef�rdert eine Kante der untersten Ebene in die rechte L�cke der mittleren Ebene
	 */
	public final static MoveSequence middleLayerEdgeRightSequence = new MoveSequence(  
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE)
		);
}
