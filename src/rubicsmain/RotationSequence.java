package rubicsmain;

import java.util.ArrayList;
import java.util.Arrays;

import rubicscube.CubeDirection;
import rubicscube.CubeRotation;
import rubicscube.Move;
import rubicscube.MoveSequence;

public class RotationSequence {
	
	/*
	 * Abfolge von Zugsequenzen für das Scannen des Würfels.
	 * Die erste Seite muss bereits zuvor eingescannet werden.
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
	 * Befördert das untenliegende Eckfragment in das darüber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe für oben liegt an der Vorderseite.
	 */
	public final static MoveSequence firstLayerCornerBottomToTopColorFrontSequence = new MoveSequence(
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	
	/*
	 * Befördert das untenliegende Eckfragment in das darüber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe für oben liegt an der rechten Seite.
	 */
	public final static MoveSequence firstLayerCornerBottomToTopColorRightSequence = new MoveSequence( 
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	
	/*
	 * Befördert das untenliegende Eckfragment in das darüber liegende Eckfragment, 
	 * wenn die erste Schicht mit den Ecken positioniert wird.
	 * Vorausgesetzt die Farbe für oben liegt an der unteren Seite.
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
	 * Befördert eine Kante der untersten Ebene in die linke Lücke der mittleren Ebene
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
	 * Befördert eine Kante der untersten Ebene in die rechte Lücke der mittleren Ebene
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
	
	/*
	 * OLL 2Look 2
	 * Löst das untenliegende Kreuz der unteren Schicht, 
	 * wenn sich zwei richtige Farben bereits gegenüber befinden.
	 * Die richtigen Farben müssen sich in der mittleren Zeile gegenüberliegen.
	 */
	public final static MoveSequence bottomLayerCrossTwoAcrossSequence = new MoveSequence(
			new Move(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE)
		);
	
	/*
	 * OLL 2Look 3
	 * Löst das untenliegende Kreuz der unteren Schicht, 
	 * wenn sich zwei richtige Farben nebeneinander befinden (hinten und rechts).
	 */
	public final static MoveSequence bottomLayerCrossTwoBesideSequence = new MoveSequence(
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALWHOLE, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALFRONT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.VERTICALWHOLE, CubeDirection.CLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerOLL04Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerOLL05Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE) // ergänzt damit Seite wieder unten ist
		);
	
	public final static MoveSequence bottomLayerCornerOLL06Sequence = new MoveSequence(
			new Move(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE) // ergänzt damit Seite wieder unten ist
		);
	
	public final static MoveSequence bottomLayerCornerOLL07Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerOLL08Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerOLL09Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerOLL10Sequence = new MoveSequence(
			new Move(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerPLL01Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerCornerPLL02Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE), 
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE) // ergänzt damit Seite wieder unten ist
		);
	
	/*
	 * Wird in der aktuellen Umsetzung nicht benötigt
	 */
	public final static MoveSequence bottomLayerCornerPLL03Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALTOP, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDLEFT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE) // ergänzt damit Seite wieder unten ist
		);
	
	public final static MoveSequence bottomLayerEdgePLL04Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.VERTICALBACK, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.VERTICALBACK, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDWHOLE, CubeDirection.COUNTERCLOCKWISE) // ergänzt damit Seite wieder unten ist
		);
	
	public final static MoveSequence bottomLayerEdgePLL05Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerEdgePLL06Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE)
		);
	
	public final static MoveSequence bottomLayerEdgePLL07Sequence = new MoveSequence(
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE),
			new Move(CubeRotation.HORIZONTALBOTTOM, CubeDirection.COUNTERCLOCKWISE),
			new Move(CubeRotation.FORWARDRIGHT, CubeDirection.CLOCKWISE)
		);
}
