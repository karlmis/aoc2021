package be.durvenproeven.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Coordinates {
	private final int x;
	private final int y;

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
		return new Coordinates(x+ direction.getxDelta(), y+ direction.getyDelta());
	}

	public List<Coordinates> getCardinalNeighbours(){
		return Arrays.stream(Direction.values())
				.map(this::getNeighbour)
				.toList();
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
