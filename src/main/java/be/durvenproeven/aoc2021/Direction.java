package be.durvenproeven.aoc2021;

public enum Direction {
	NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

	private final int xDelta;
	private final int yDelta;

	Direction(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	public Direction reverse() {
		return values()[(ordinal() + 2) % values().length];
	}

	public int getxDelta() {
		return xDelta;
	}

	public int getyDelta() {
		return yDelta;
	}
}
