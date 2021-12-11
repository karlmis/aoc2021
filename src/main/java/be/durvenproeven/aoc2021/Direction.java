package be.durvenproeven.aoc2021;

import java.util.List;

public enum Direction {
	NORTH(0, 1),
	NORTH_EAST(1, 1),
	EAST(1, 0),
	SOUTH_EAST(1, -1),
	SOUTH(0, -1),
	SOUTH_WEST(-1, -1),
	WEST(-1, 0),
	NORTH_WEST(-1, 1);

	private final int xDelta;
	private final int yDelta;

	Direction(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}

	public Direction reverse() {
		return values()[(ordinal() + 4) % values().length];
	}

	public int getxDelta() {
		return xDelta;
	}

	public int getyDelta() {
		return yDelta;
	}

	public static List<Direction> getCardinals(){
		return List.of(NORTH, EAST, SOUTH, WEST);
	}
}
