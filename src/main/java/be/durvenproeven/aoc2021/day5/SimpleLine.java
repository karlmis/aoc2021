package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.Coordinates;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SimpleLine {
	private final Coordinates first;
	private final Coordinates second;
	private final Direction direction;

	private SimpleLine(Coordinates first, Coordinates second, Direction direction) {
		this.first = first;
		this.second = second;
		this.direction = direction;
	}

	public static Optional<SimpleLine> create(Coordinates first, Coordinates second) {
		return getDirection(first, second)
				.map(d -> new SimpleLine(first, second, d));
	}

	private static Optional<Direction> getDirection(Coordinates first, Coordinates second) {
		if (first.getX() == second.getX()) {
			return Optional.of(Direction.VERTICAL);
		}
		if (first.getY() == second.getY()) {
			return Optional.of(Direction.HORIZONTAL);
		}
		if (Math.abs(first.getX() - second.getX()) == Math.abs(first.getY() - second.getY())) {
			return Optional.of(Direction.DIAGONAL);
		}
		return Optional.empty();
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
		return getCoordinatesOnLineForDiagonal();

	}

	private List<Coordinates> getCoordinatesOnLineForDiagonal() {
		int dif = second.getX() - first.getX();
		int signX = dif > 0 ? 1 : -1;
		int signY = second.getY() > first.getY() ? 1 : -1;

		return IntStream.rangeClosed(0, Math.abs(dif))
				.mapToObj(i -> new Coordinates(first.getX() + (i * signX), first.getY() + (i * signY)))
				.collect(toList());
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
