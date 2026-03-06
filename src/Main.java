import java.util.List;

class Main {

	public static void main(String[] args) {

		Client client = new Client();
		client.run();
		

		// Board b = new Board();
		// System.out.println(b.toString());
		// Mark oTurn = Mark.B;
		// Bot bot = new Bot(Mark.B);
		// while (b.hasWinner() == null) {
		// 	Move move;
		// 	if (oTurn == Mark.B) {
		// 		List<Move> moves = bot.getNextMoveAB(b);
		// 		move = moves.get(0);
		// 	} else {
		// 		List<Move> moves = b.getMoves(oTurn);
		// 		int i = (int) (Math.random() * moves.size());
		// 		move = moves.get(i);
		// 	}
		// 	System.out.println(
		// 		(oTurn == Mark.B ? "B" : "R") + " " + move.toString()
		// 	);
		// 	b.move(move);
		// 	System.out.println(b.toString());
		// 	oTurn = oTurn == Mark.B ? Mark.R : Mark.B;
		// }
		// System.out.println("Winner : " + (b.hasWinner() == Mark.B ? "B" : "R"));
	}
}
