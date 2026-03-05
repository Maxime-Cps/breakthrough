public record Move(Position init, Position target) {
	@Override
	public String toString() {
		return (
			"depart : {x:" +
			init.x() +
			",y:" +
			init.y() +
			"}, arriver : {x:" +
			target.x() +
			",y:" +
			target.y() +
			"}"
		);
	}
}
