class Main {

	public static void main(String[] args) {
		String host = "localhost";
        int port = 8888;

        if (args.length >= 1) {
            host = args[0];
        }

        if (args.length >= 2) {
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Invalid port number, using default 8888");
            }
        }

        System.out.println("Host: " + host);
        System.out.println("Port: " + port);

		Client client = new Client(host, port);
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
