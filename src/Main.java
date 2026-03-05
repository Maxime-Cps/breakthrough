import java.util.List;
import java.util.Random;

class Main {

	public static void main(String[] args) {
		Board b = new Board();
		System.out.println(b.toString());
		int x = 0;
		int y = 0;
		Owner oTurn = Owner.O;
		Random r = new Random(1);
		while (b.hasWinner() == null) {
			List<List<Move>> moves = b.getMoves(oTurn);
			int i = r.nextInt(0, moves.size());
			int j = r.nextInt(0, moves.get(i).size());
			Move move = moves.get(i).get(j);
			b.move(move);
			System.out.println(b.toString());
			oTurn = oTurn == Owner.O ? Owner.X : Owner.O;
		}
		System.out.println(
			"Winner : " + (b.hasWinner() == Owner.O ? "O" : "X")
		);

		// List<Move> moves = b.getMoves(new Position(0, 1));
		// System.out.println(moves.size());
		// for (Move move : moves) {
		// 	System.out.println(move.toString());
		// }
	}
}
