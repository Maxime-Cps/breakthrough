enum Mark {
	R,
	B,
	EMPTY;

	@Override
	public String toString() {
		switch (this) {
			case R:
				return "R";
			case B:
				return "B";
			default:
				return " ";
		}
	}
}
