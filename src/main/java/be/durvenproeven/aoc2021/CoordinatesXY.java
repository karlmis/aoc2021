package be.durvenproeven.aoc2021;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public record CoordinatesXY(int x, int y) {

	public static List<CoordinatesXY> getAllCoordinates(CoordinatesXY maxCoordinate) {
		return IntStream.rangeClosed(0, maxCoordinate.x())
				.mapToObj(xco -> toCoordinates(xco, maxCoordinate))
				.flatMap(Stream::distinct)
				.toList();
	}

	private static Stream<CoordinatesXY> toCoordinates(int xco, CoordinatesXY maxCoordinate) {
		return IntStream.rangeClosed(0, maxCoordinate.y())
				.mapToObj(y -> new CoordinatesXY(xco, y));
	}

	public boolean isInFirstQuadrant() {
		return this.x() >= 0 && this.y() >= 0;
	}

	public boolean isSmallerThen(CoordinatesXY maxCoordinate) {
		return x < maxCoordinate.x && y < maxCoordinate.y;
	}

	public boolean isEqualOrSmallerThen(CoordinatesXY maxCoordinate) {
		return x <= maxCoordinate.x && y <= maxCoordinate.y;
	}

	public CoordinatesXY getNeighbour(Direction direction) {
		return new CoordinatesXY(x + direction.getxDelta(), y + direction.getyDelta());
	}

	public List<CoordinatesXY> getCardinalNeighbours() {
		return Direction.getCardinals().stream()
				.map(this::getNeighbour)
				.toList();
	}

	public List<CoordinatesXY> getAllNeighbours() {
		return Arrays.stream(Direction.values())
				.map(this::getNeighbour)
				.toList();
	}

	public CoordinatesXY withHorizontalReflection(int yReflection) {
		if (y > yReflection) {
			return new CoordinatesXY(x, y - 2 * (y - yReflection));
		}
		return this;
	}

	public CoordinatesXY withVerticalReflection(int xReflection) {
		if (x > xReflection) {
			return new CoordinatesXY(x - 2 * (x - xReflection), y);
		}
		return this;
	}

	public List<CoordinatesXY> getSmallerCoordinatesWithDistanceToOrigin(int distance) {
		return IntStream.rangeClosed(0, distance)
				.mapToObj(i -> new CoordinatesXY(i, distance - i))
				.filter(co -> co.isEqualOrSmallerThen(this))
				.toList();
	}

}
