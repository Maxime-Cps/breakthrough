import java.util.ArrayList;
import java.util.List;

class Board {

	private Owner _board[][] = new Owner[8][8];

	public Board() {
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board[i].length; j++) {
				if (i < 2) {
					_board[i][j] = Owner.O;
				} else if (i > 5) {
					_board[i][j] = Owner.X;
				} else {
					_board[i][j] = Owner.EMPTY;
				}
			}
		}
	}

	public List<List<Move>> getMoves(Owner o) {
		ArrayList<List<Move>> moves = new ArrayList<>();
		for (int i = 0; i < _board.length; i++) {
			for (int j = 0; j < _board[i].length; j++) {
				if (_board[i][j] == o) {
					List<Move> currentMoves = getMoves(new Position(j, i));
					if (!currentMoves.isEmpty()) {
						moves.add(currentMoves);
					}
				}
			}
		}
		return moves;
	}

	public List<Move> getMoves(Position p) {
		Owner o = _board[p.y()][p.x()];
		int[][] moves;
		if (o == Owner.O) {
			moves = new int[][] { { -1, 1 }, { 0, 1 }, { 1, 1 } };
		} else {
			moves = new int[][] { { -1, -1 }, { 0, -1 }, { 1, -1 } };
		}
		ArrayList<Move> nextMoves = new ArrayList<>();
		for (int[] move : moves) {
			int next_x = p.x() + move[0];
			int next_y = p.y() + move[1];

			// vérifie si pas outofbound
			if (
				(next_x >= 0 && next_x < _board[next_y].length) &&
				(next_y >= 0 && next_y < _board.length)
			) {
				Owner nextOwner = _board[next_y][next_x];
				// déplacement sur case vide
				if (nextOwner == Owner.EMPTY) {
					nextMoves.add(new Move(p, new Position(next_x, next_y)));
				}
				// déplacement sur case énemie
				else if (nextOwner != o && move[0] != 0) {
					nextMoves.add(new Move(p, new Position(next_x, next_y)));
				}
			}
		}
		return nextMoves;
	}

	private double distanceEuclidienne(Position p1, Position p2) {
		return Math.sqrt(
			Math.powExact(p1.x() - p2.x(), 2) +
				Math.powExact(p1.y() - p2.y(), 2)
		);
	}

	public boolean moveIsValid(Move m) {
		if (
			(m.target().x() < 0 ||
				m.target().x() >= _board[m.target().x()].length) ||
			(m.target().y() < 0 || m.target().y() >= _board.length)
		) {
			System.err.println(m.toString());
			System.err.println("le move sort du plateau il n'est pas valide");
			return false;
		}

		Owner oInit = _board[m.init().y()][m.init().x()];
		Owner oTarg = _board[m.target().y()][m.target().x()];

		if (distanceEuclidienne(m.init(), m.target()) >= 2) {
			System.err.println(m.toString());
			System.err.println("On ne se deplace pas de plus de 1 case");
			return false;
		}

		if (oInit == Owner.EMPTY) {
			System.err.println(m.toString());
			System.err.println("la position initial du move est vide");
			return false;
		} else {
			if (oTarg != Owner.EMPTY) {
				if (oTarg != oInit) {
					if (m.init().x() == m.target().x()) {
						System.err.println(m.toString());
						System.err.println(
							"On ne peu pas manger un adversaire en face de nous"
						);
						return false;
					}
				} else {
					System.err.println(m.toString());
					System.err.println(
						"on ne peu pas se déplacer sur soit même"
					);
					return false;
				}
			}
		}

		if (oInit == Owner.X) {
			if (m.target().y() >= m.init().y()) {
				System.err.println(m.toString());
				System.err.println(
					"la position y du move pour X n'a pas changer dans le bon sens"
				);
				return false;
			}
		} else {
			if (m.target().y() <= m.init().y()) {
				System.err.println(m.toString());
				System.err.println(
					"la position y du move pour O n'a pas changer dans le bon sens"
				);
				return false;
			}
		}
		return true;
	}

	public void move(Move m) {
		if (moveIsValid(m)) {
			Owner oInit = _board[m.init().y()][m.init().x()];
			_board[m.target().y()][m.target().x()] = oInit;
			_board[m.init().y()][m.init().x()] = Owner.EMPTY;
		}
	}

	public Owner hasWinner() {
		for (Owner tile : _board[0]) {
			if (tile == Owner.X) {
				return Owner.X;
			}
		}
		for (Owner tile : _board[7]) {
			if (tile == Owner.O) {
				return Owner.O;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Owner[] line : _board) {
			for (Owner tile : line) {
				sb.append(
					tile == Owner.EMPTY ? "." : tile == Owner.X ? "X" : "O"
				);
				sb.append("|");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
