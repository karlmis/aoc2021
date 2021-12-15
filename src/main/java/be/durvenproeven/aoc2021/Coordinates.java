package be.durvenproeven.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record Coordinates(int x, int y) {

	@Deprecated
	public static List<Coordinates> getAllCoordinatesOld(Coordinates maxCoordinate) {
		return IntStream.range(0, maxCoordinate.x())
				.mapToObj(xco -> toCoordinatesOld(xco, maxCoordinate))
				.flatMap(Stream::distinct)
				.toList();
	}

	public static List<Coordinates> getAllCoordinates(Coordinates maxCoordinate) {
		return IntStream.rangeClosed(0, maxCoordinate.x())
				.mapToObj(xco -> toCoordinates(xco, maxCoordinate))
				.flatMap(Stream::distinct)
				.toList();
	}

	@Deprecated
	private static Stream<Coordinates> toCoordinatesOld(int xco, Coordinates maxCoordinate) {
		return IntStream.range(0, maxCoordinate.y())
				.mapToObj(y -> new Coordinates(xco, y));
	}

	private static Stream<Coordinates> toCoordinates(int xco, Coordinates maxCoordinate) {
		return IntStream.rangeClosed(0, maxCoordinate.y())
				.mapToObj(y -> new Coordinates(xco, y));
	}

	public boolean isInFirstQuadrant() {
		return this.x() >= 0 && this.y() >= 0;
	}

	public boolean isSmallerThen(Coordinates maxCoordinate) {
		return x < maxCoordinate.x && y < maxCoordinate.y;
	}

	public boolean isEqualOrSmallerThen(Coordinates maxCoordinate) {
		return x <= maxCoordinate.x && y <= maxCoordinate.y;
	}

	public Coordinates getNeighbour(Direction direction) {
		return new Coordinates(x + direction.getxDelta(), y + direction.getyDelta());
	}

	public List<Coordinates> getCardinalNeighbours() {
		return Direction.getCardinals().stream()
				.map(this::getNeighbour)
				.toList();
	}

	public List<Coordinates> getAllNeighbours() {
		return Arrays.stream(Direction.values())
				.map(this::getNeighbour)
				.toList();
	}

	public Coordinates withHorizontalReflection(int yReflection) {
		if (y > yReflection) {
			return new Coordinates(x, y - 2 * (y - yReflection));
		}
		return this;
	}

	public Coordinates withVerticalReflection(int xReflection) {
		if (x > xReflection) {
			return new Coordinates(x - 2 * (x - xReflection), y);
		}
		return this;
	}

	public List<Coordinates> getSmallerCoordinatesWithDistanceToOrigin(int distance) {
		return IntStream.rangeClosed(0, distance)
				.mapToObj(i -> new Coordinates(i, distance - i))
				.filter(co -> co.isEqualOrSmallerThen(this))
				.toList();
	}

}
