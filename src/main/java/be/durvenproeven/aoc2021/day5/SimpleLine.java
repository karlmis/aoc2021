package be.durvenproeven.aoc2021.day5;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SimpleLine {
	private final Coordinates first;
	private final Coordinates second;
	private Direction direction;

	private enum Direction{HORIZONTAL, VERTICAL, DIAGONAL};

	private SimpleLine(Coordinates first, Coordinates second, Direction direction) {
		this.first = first;
		this.second = second;
		this.direction = direction;
	}

	public static Optional<SimpleLine> create(Coordinates first, Coordinates second) {
		Direction direction = getDirection(first, second);
		if (direction== null){
			return Optional.empty();
		}
		return Optional.of(new SimpleLine(first, second, direction));
	}

	private static Direction getDirection(Coordinates first, Coordinates second) {
		Direction direction= null;
		if (first.getX() == second.getX()){
			direction= Direction.VERTICAL;
		}
		if (first.getY() == second.getY()) {
			direction= Direction.HORIZONTAL;
		}
		return direction;
	}

	public List<Coordinates> getCoordinatesOnLine() {
		if (direction== Direction.HORIZONTAL){
			return getIntStream(Coordinates::getX)
					.mapToObj(x -> new Coordinates(x, first.getY()))
					.toList();
		}
		return getIntStream(Coordinates::getY)
				.mapToObj(y -> new Coordinates(first.getX(), y))
				.toList();
	}

	private IntStream getIntStream(ToIntFunction<Coordinates> a) {
		int[] ints = Stream.of(first, second)
				.mapToInt(a)
				.sorted()
				.toArray();
		return IntStream.rangeClosed(ints[0], ints[1]);
	}
}
