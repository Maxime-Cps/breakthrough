enum Owner {
	X,
	O,
	EMPTY;

	public boolean isEnemy(Owner other) {
		if (this == EMPTY || other == EMPTY) {
			return false;
		}
		return this != other;
	}
}
