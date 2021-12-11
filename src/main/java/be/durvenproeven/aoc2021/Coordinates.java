package be.durvenproeven.aoc2021;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Coordinates {
	private int x, y;

	public Coordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static List<Coordinates> getAllCoordinates(Coordinates maxCoordinate) {
		return IntStream.range(0, maxCoordinate.getX())
				.mapToObj(xco -> toCoordinates(xco, maxCoordinate))
				.flatMap(Stream::distinct)
				.toList();
	}

	private static Stream<Coordinates> toCoordinates(int xco, Coordinates maxCoordinate) {
		return IntStream.range(0, maxCoordinate.getY())
				.mapToObj(y -> new Coordinates(xco, y));
	}

	public boolean isInFirstQuadrant() {
		return getX() >= 0 && getY() >= 0;
	}

	public boolean isSmallerThen(Coordinates maxCoordinate){
		return x < maxCoordinate.x && y < maxCoordinate.y;
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
