package be.durvenproeven.aoc2021.day9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HeightMap {

	private static final int MAX_HEIGHT = 9;
	private final int[][] nrs;
	private final Map<Coordinates, Integer> lowPoints;
	private final Coordinates maxCoordinate;

	public HeightMap(List<String> input) {
		nrs = new int[input.size()][];
		for (int i = 0; i < input.size(); i++) {
			nrs[i] = toArray(input.get(i));
		}
		lowPoints = calculateLowPoints();
		maxCoordinate = new Coordinates(input.size(), input.get(0).length());
	}

	private int[] toArray(String l) {
		return l.chars()
				.mapToObj(Character::toString)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	private Map<Coordinates, Integer> calculateLowPoints() {
		Map<Coordinates, Integer> res = new HashMap<>();
		for (int i = 0; i < nrs.length; i++) {
			for (int i1 = 0; i1 < nrs[i].length; i1++) {
				if (nrs[i][i1] < minOfNeighbours(i, i1)) {
					res.put(new Coordinates(i, i1), nrs[i][i1]);
				}
			}
		}
		return res;
	}

	public Collection<Integer> getLowPoints() {
		return lowPoints.values();
	}

	public List<Integer> getBasinSizes() {
		return lowPoints.keySet().stream()
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
		Comparator<Integer> compare = Integer::compare;
		return getBasinSizes().stream()
				.sorted(compare.reversed())
				.limit(3)
				.reduce(1, (a, b) -> a * b);
	}

	public int getSumRiskLevel() {
		return lowPoints.values().stream()
				.mapToInt(Integer::intValue)
				.map(i -> i + 1)
				.sum();
	}

	private int minOfNeighbours(int i, int i1) {
		List<Integer> neighourValues = new ArrayList<>();
		if (i > 0) {
			neighourValues.add(nrs[i - 1][i1]);
		}
		if (i < nrs.length - 1) {
			neighourValues.add(nrs[i + 1][i1]);
		}
		if (i1 > 0) {
			neighourValues.add(nrs[i][i1 - 1]);
		}
		if (i1 < nrs[i].length - 1) {
			neighourValues.add(nrs[i][i1 + 1]);
		}
		return neighourValues.stream()
				.mapToInt(Integer::intValue)
				.min().orElseThrow();
	}


}
