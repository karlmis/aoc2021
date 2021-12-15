package be.durvenproeven.aoc2021.day9;


import be.durvenproeven.aoc2021.CollectionUtils;
import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.Grid;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class HeightMap {

	private static final int MAX_HEIGHT = 9;
	private static final Comparator<Integer> INTEGER_COMPARATOR = Integer::compare;
	private final List<Coordinates> lowPoints;
	private final Grid grid;

	public HeightMap(List<String> input) {
		grid = new Grid(input);
		lowPoints = calculateLowPoints();
	}

	private List<Coordinates> calculateLowPoints() {
		return grid.withLevel(t-> true).stream()
				.filter(co -> grid.getValue(co) < minOfNeighbours(co))
				.toList();
	}

	private int minOfNeighbours(Coordinates co) {
		return grid.getCardinalNeighbours(co).stream()
				.mapToInt(grid::getValue)
				.min().orElseThrow();
	}

	public Collection<Integer> getLowPoints() {
		return lowPoints.stream()
				.map(grid::getValue)
				.toList();
	}

	public List<Integer> getBasinSizes() {
		return lowPoints.stream()
				.map(entry -> getBasin(entry, CollectionUtils.mutableSetOf(entry)).size())
				.toList();
	}

	private Set<Coordinates> getBasin(Coordinates coordinates, Set<Coordinates> alreadyDone) {
		if (grid.getValue(coordinates) == MAX_HEIGHT) {
			return alreadyDone;
		}
		Set<Coordinates> newAlreadyDone = CollectionUtils.createMutableSetWith(alreadyDone, coordinates);

		grid.getCardinalNeighbours(coordinates).stream()
				.filter(neighbour -> !newAlreadyDone.contains(neighbour))
				.forEach(neighbour -> newAlreadyDone.addAll(getBasin(neighbour, newAlreadyDone)));
		return newAlreadyDone;
	}

	public int getBasinNumber() {
		return getBasinSizes().stream()
				.sorted(INTEGER_COMPARATOR.reversed())
				.limit(3)
				.reduce(1, (a, b) -> a * b);
	}

	public int getSumRiskLevel() {
		return lowPoints.stream()
				.mapToInt(grid::getValue)
				.map(i -> i + 1)
				.sum();
	}

}
