package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.CoordinatesXY;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public record SimpleLine(CoordinatesXY first, CoordinatesXY second, Direction direction) {

	public static Optional<SimpleLine> create(CoordinatesXY first, CoordinatesXY second) {
		return getDirection(first, second)
				.map(d -> new SimpleLine(first, second, d));
	}

	private static Optional<Direction> getDirection(CoordinatesXY first, CoordinatesXY second) {
		if (first.x() == second.x()) {
			return Optional.of(Direction.VERTICAL);
		}
		if (first.y() == second.y()) {
			return Optional.of(Direction.HORIZONTAL);
		}
		if (Math.abs(first.x() - second.x()) == Math.abs(first.y() - second.y())) {
			return Optional.of(Direction.DIAGONAL);
		}
		return Optional.empty();
	}

	public List<CoordinatesXY> getCoordinatesOnLine() {
		if (direction == Direction.HORIZONTAL) {
			return getIntStream(CoordinatesXY::x)
					.mapToObj(x -> new CoordinatesXY(x, first.y()))
					.toList();
		}
		if (direction == Direction.VERTICAL) {
			return getIntStream(CoordinatesXY::y)
					.mapToObj(y -> new CoordinatesXY(first.x(), y))
					.toList();
		}
		return getCoordinatesOnLineForDiagonal();

	}

	private List<CoordinatesXY> getCoordinatesOnLineForDiagonal() {
		int dif = second.x() - first.x();
		int signX = dif > 0 ? 1 : -1;
		int signY = second.y() > first.y() ? 1 : -1;

		return IntStream.rangeClosed(0, Math.abs(dif))
				.mapToObj(i -> new CoordinatesXY(first.x() + (i * signX), first.y() + (i * signY)))
				.collect(toList());
	}

	private IntStream getIntStream(ToIntFunction<CoordinatesXY> a) {
		int[] ints = Stream.of(first, second)
				.mapToInt(a)
				.sorted()
				.toArray();
		return IntStream.rangeClosed(ints[0], ints[1]);
	}

}
