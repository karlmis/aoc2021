package be.durvenproeven.aoc2021;

public enum Direction {
	NORTH, EAST, SOUTH, WEST;

	public Direction reverse() {
		return values()[(ordinal() + 2) % values().length];
	}
}
