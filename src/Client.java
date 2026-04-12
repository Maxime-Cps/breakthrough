import java.io.*;
import java.net.*;
import java.util.List;

class Client {
	
	private Socket MyClient = null;
	private BufferedInputStream input = null;
	private BufferedOutputStream output = null;
	private Board b = new Board();
	private Bot bot = null;
	private Move move = null;
	private String moveStr = null;

	private String host;
	private int port;

	public Client(String host, int port) {
		this.host = host;
		this.port = port;
	}

	public void run() {
		try {
			MyClient = new Socket(host, port);

			input = new BufferedInputStream(MyClient.getInputStream());
			output = new BufferedOutputStream(MyClient.getOutputStream());
			BufferedReader console = new BufferedReader(
				new InputStreamReader(System.in)
			);
			// Test if the connection is established
			if (MyClient.isConnected()) {
				System.out.println(
					"Connection to server established successfully."
				);
			} else {
				System.out.println("Failed to establish connection to server.");
				System.exit(1);
			}

			while (true) {
				char cmd = (char) input.read();

				// Debut de la partie en joueur rouge
				if (cmd == '1') {
					bot = new Bot(Mark.R);
					byte[] aBuffer = new byte[1024];

					int size = input.available();
					input.read(aBuffer, 0, size);

					System.out.println(
						"New game! You play Red"
					);

					moveBot();
				}

				// Debut de la partie en joueur Noir
				if (cmd == '2') {
					bot = new Bot(Mark.B);
					System.out.println(
						"New game! You play Black"
					);
					byte[] aBuffer = new byte[1024];

					int size = input.available();
					//System.out.println("size " + size);
					input.read(aBuffer, 0, size);
				}

				// Le serveur demande le prochain coup
				// Le message contient aussi le dernier coup joue.
				if (cmd == '3') {
					moveOthers();
					
					moveBot();
				}

				// Le dernier coup est invalide
				if (cmd == '4') {
					System.out.println(
						"Coup invalide, entrez un nouveau coup : "
					);
					String move = null;
					move = console.readLine();
					output.write(move.getBytes(), 0, move.length());
					output.flush();
				}

				// La partie est terminée
				if (cmd == '5') {
					byte[] aBuffer = new byte[16];
					int size = input.available();
					input.read(aBuffer, 0, size);
					String s = new String(aBuffer);
					System.out.println(
						"Partie Terminé. Le gagnant est : " + s.trim().charAt(0)
					);

					String move = null;
					move = console.readLine();
					output.write(move.getBytes(), 0, move.length());
					output.flush();
				}
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	private void moveOthers() throws IOException {
		byte[] aBuffer = new byte[16];

		int size = input.available();
		input.read(aBuffer, 0, size);

		moveStr = new String(aBuffer);
		move = new Move(moveStr.trim());
		b.move(move);
		System.out.println(
			bot.getEnnemieMark().toString()  + " " + move.toString() + "\n" + b.toString()
		);
	}

	private void moveBot() throws IOException {
		List<Move> moves = bot.getNextMoveAB(b);
		move = moves.get(0);
		moveStr = move.toString();

		b.move(move);
		System.out.println(
			bot.getPlayerMark().toString()  + " " + move.toString() + "\n" + b.toString()
		);

		output.write(moveStr.getBytes(), 0, moveStr.length());
		output.flush();
	}
}
