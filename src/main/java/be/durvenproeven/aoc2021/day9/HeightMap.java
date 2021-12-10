package be.durvenproeven.aoc2021.day9;


import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HeightMap {

	private static final int MAX_HEIGHT = 9;
	private static final Comparator<Integer> INTEGER_COMPARATOR = Integer::compare;
	private final int[][] nrs;
	private final List<Coordinates> lowPoints;
	private final Coordinates maxCoordinate;

	public HeightMap(List<String> input) {
		nrs = new int[input.size()][];
		for (int i = 0; i < input.size(); i++) {
			nrs[i] = toArray(input.get(i));
		}
		maxCoordinate = new Coordinates(input.size(), input.get(0).length());
		lowPoints = calculateLowPoints();
	}

	private int[] toArray(String l) {
		return l.chars()
				.mapToObj(Character::toString)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	private List<Coordinates> calculateLowPoints() {
		return IntStream.range(0, maxCoordinate.getX())
				.mapToObj(this::toCoordinates)
				.flatMap(Stream::distinct)
				.filter(co -> getValue(co) < minOfNeighbours(co))
				.toList();

	}

	private Stream<Coordinates> toCoordinates(int xco) {
		return IntStream.range(0, maxCoordinate.getY())
				.mapToObj(y -> new Coordinates(xco, y));
	}

	public Collection<Integer> getLowPoints() {
		return lowPoints.stream()
				.map(this::getValue)
				.toList();
	}

	public List<Integer> getBasinSizes() {
		return lowPoints.stream()
				.map(entry -> getBasin(entry, mutableSetOf(entry)).size())
				.toList();
	}

	private Set<Coordinates> mutableSetOf(Coordinates value) {
		Set<Coordinates> basin = new HashSet<>();
		basin.add(value);
		return basin;
	}

	private Set<Coordinates> getBasin(Coordinates coordinates, Set<Coordinates> alreadyDone) {
		if (getValue(coordinates) == MAX_HEIGHT) {
			return alreadyDone;
		}
		HashSet<Coordinates> newAlreadyDone = new HashSet<>(alreadyDone);
		newAlreadyDone.add(coordinates);

		Arrays.stream(Direction.values())
				.map(coordinates::getNeighbour)
				.filter(this::isValid)
				.filter(neighbour -> !newAlreadyDone.contains(neighbour))
				.forEach(neighbour -> newAlreadyDone.addAll(getBasin(neighbour, newAlreadyDone)));
		return newAlreadyDone;
	}

	private int getValue(Coordinates coordinates) {
		return nrs[coordinates.getX()][coordinates.getY()];
	}

	private boolean isValid(Coordinates coordinates1) {
		return coordinates1.isInFirstQuadrant() && coordinates1.isSmallerThen(maxCoordinate);
	}

	public int getBasinNumber() {
		return getBasinSizes().stream()
				.sorted(INTEGER_COMPARATOR.reversed())
				.limit(3)
				.reduce(1, (a, b) -> a * b);
	}

	public int getSumRiskLevel() {
		return lowPoints.stream()
				.mapToInt(this::getValue)
				.map(i -> i + 1)
				.sum();
	}

	private int minOfNeighbours(Coordinates co) {
		return getNeighbours(co).stream()
				.mapToInt(this::getValue)
				.min().orElseThrow();
	}

	private List<Coordinates> getNeighbours(Coordinates co) {
		return Stream.of(getCoordinate(co.getX() + 1, co.getY()), getCoordinate(co.getX(), co.getY() + 1),
				getCoordinate(co.getX() - 1, co.getY()), getCoordinate(co.getX(), co.getY() - 1))
				.flatMap(Optional::stream)
				.toList();
	}

	Optional<Coordinates> getCoordinate(int x, int y) {
		Coordinates coordinates = new Coordinates(x, y);
		if (isValid(coordinates)) {
			return Optional.of(coordinates);
		}
		return Optional.empty();
	}


}
