package be.durvenproeven.aoc2021.day9;

import java.util.Objects;

public class Coordinates {
	private int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Coordinates getNeighbour(Direction direction) {
		switch (direction) {
			case NORTH -> {
				return new Coordinates(x, y + 1);
			}
			case EAST -> {
				return new Coordinates(x + 1, y);
			}
			case SOUTH -> {
				return new Coordinates(x, y - 1);
			}
			case WEST -> {
				return new Coordinates(x - 1, y);
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordinates that = (Coordinates) o;
		return x == that.x && y == that.y;
	}

	@Override
	public int hashCode() {
		return Objects.hash(x, y);
	}

	@Override
	public String toString() {
		return "Coordinates{" +
				"x=" + x +
				", y=" + y +
				'}';
	}
}
