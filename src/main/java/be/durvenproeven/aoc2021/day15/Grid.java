package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.NumberUtils;

import java.util.List;
import java.util.stream.Collectors;

public class Grid {
	private final List<List<Integer>> riskLevels;
	private final Coordinates maxCoordinates;


	public Grid(List<String> listRiskLevels) {
		riskLevels = listRiskLevels.stream()
				.map(NumberUtils::toIntList)
				.collect(Collectors.toList());
		maxCoordinates = new Coordinates(riskLevels.size() - 1, riskLevels.get(0).size() - 1);
	}

	private boolean isValid(Coordinates coordinates) {
		return coordinates.isInFirstQuadrant() && coordinates.isEqualOrSmallerThen(maxCoordinates);
	}

	public int getValue(Coordinates coordinates) {
		if (isValid(coordinates)) {
			return riskLevels.get(coordinates.getX()).get(coordinates.getY());
		}
		throw new IllegalArgumentException(coordinates.toString());
	}

	public List<Coordinates> getCardinalNeighbours(Coordinates coordinates) {
		return coordinates.getCardinalNeighbours().stream()
				.filter(this::isValid)
				.toList();
	}

	public int getSize() {
		return (maxCoordinates.getX() + 1) * (maxCoordinates.getY() + 1);
	}

	public Coordinates getMaxCoordinates() {
		return maxCoordinates;
	}
}
