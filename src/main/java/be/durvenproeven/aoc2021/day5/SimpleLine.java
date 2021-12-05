package be.durvenproeven.aoc2021.day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleLine {
	private final Coordinates first;
	private final Coordinates second;
	private final Direction direction;

	enum Direction {HORIZONTAL, VERTICAL, DIAGONAL}

	;

	private SimpleLine(Coordinates first, Coordinates second, Direction direction) {
		this.first = first;
		this.second = second;
		this.direction = direction;
	}

	public static Optional<SimpleLine> create(Coordinates first, Coordinates second) {
		Direction direction = getDirection(first, second);
		if (direction == null) {
			return Optional.empty();
		}
		return Optional.of(new SimpleLine(first, second, direction));
	}

	private static Direction getDirection(Coordinates first, Coordinates second) {
		if (first.getX() == second.getX()) {
			return Direction.VERTICAL;
		}
		if (first.getY() == second.getY()) {
			return Direction.HORIZONTAL;
		}
		if (Math.abs(first.getX() - second.getX()) == Math.abs(first.getY() - second.getY())) {
			return Direction.DIAGONAL;
		}
		return null;
	}

	public List<Coordinates> getCoordinatesOnLine() {
		if (direction == Direction.HORIZONTAL) {
			return getIntStream(Coordinates::getX)
					.mapToObj(x -> new Coordinates(x, first.getY()))
					.toList();
		}
		if (direction == Direction.VERTICAL) {
			return getIntStream(Coordinates::getY)
					.mapToObj(y -> new Coordinates(first.getX(), y))
					.toList();
		}
		List<Coordinates> coordinates = new ArrayList<>();
		int dif = second.getX() - first.getX();
		int signX = dif > 0 ? 1 : -1;
		int signY = second.getY() > first.getY() ? 1 : -1;
		for (int i = 0; i <= Math.abs(dif); i++) {
			coordinates.add(new Coordinates(first.getX() + (i * signX), first.getY() + (i * signY)));
		}
		return coordinates;

	}

	private IntStream getIntStream(ToIntFunction<Coordinates> a) {
		int[] ints = Stream.of(first, second)
				.mapToInt(a)
				.sorted()
				.toArray();
		return IntStream.rangeClosed(ints[0], ints[1]);
	}

	public Direction getDirection() {
		return direction;
	}
}
