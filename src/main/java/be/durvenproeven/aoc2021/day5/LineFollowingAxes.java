package be.durvenproeven.aoc2021.day5;

import java.util.List;
import java.util.Optional;
import java.util.function.ToIntFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineFollowingAxes {
	private final Coordinates first;
	private final Coordinates second;
	private boolean isHorizontal = false;

	private LineFollowingAxes(Coordinates first, Coordinates second) {
		this.first = first;
		this.second = second;
		isHorizontal = first.getY() == second.getY();
	}

	public static Optional<LineFollowingAxes> createLineFollowingAxes(Coordinates first, Coordinates second) {
		if (first.getX() != second.getX() && first.getY() != second.getY()) {
			return Optional.empty();
		}
		return Optional.of(new LineFollowingAxes(first, second));
	}

	public List<Coordinates> getCoordinatesOnLine() {
		if (isHorizontal){
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
