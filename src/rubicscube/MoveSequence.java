package rubicscube;

import java.util.ArrayList;
import java.util.Iterator;

public class MoveSequence implements Iterable<Move> {
	private ArrayList<Move> moves = new ArrayList<>();
	
	/*
	 * Speichert eine Reihe von Züge
	 * Dabei können beliebig viele Züge angegeben werden
	 */
	public MoveSequence(Move ... moves) {
		for(Move m : moves) {
			this.moves.add(m);
		}
	}

	public ArrayList<Move> getMoves() {
		return moves;
	}

	public void setMoves(ArrayList<Move> moves) {
		this.moves = moves;
	}

	@Override
	public Iterator<Move> iterator() {
		return this.moves.iterator();
	}
}
