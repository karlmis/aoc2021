package be.durvenproeven.aoc2021.day11;

import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OctopusesCave {
	private final int[][] nrs;
	private final Coordinates maxCoordinate;


	public OctopusesCave(List<String> input) {
		nrs = new int[input.size()][];
		for (int i = 0; i < input.size(); i++) {
			nrs[i] = NumberUtils.toIntArray(input.get(i));
		}
		maxCoordinate = new Coordinates(input.size(), input.get(0).length());
	}

	public boolean allFlash() {
		for (int[] nr : nrs) {
			for (int i : nr) {
				if (i != 0) {
					return false;
				}
			}
		}
		return true;
	}

	private List<Coordinates> getAllLightenedUp(List<Coordinates> newLightenedUp, List<Coordinates> allLightenedUp) {
		if (newLightenedUp.isEmpty()) {
			return allLightenedUp;
		}
		allLightenedUp.addAll(newLightenedUp);

		Map<Coordinates, Long> neighboursWithNrToAdd = newLightenedUp.stream()
				.flatMap(co -> getNeighbours(co).stream())
				.filter(co -> !allLightenedUp.contains(co))
				.collect(groupingBy(identity(), counting()));

		neighboursWithNrToAdd.forEach(this::addToValue);

		List<Coordinates> newChanged = neighboursWithNrToAdd.keySet().stream()
				.filter(this::isLightingUp)
				.toList();

		return getAllLightenedUp(newChanged, allLightenedUp);
	}

	private boolean isLightingUp(Coordinates co) {
		return getValue(co) > 9;
	}

	private void addToValue(Coordinates coordinates, Long toAdd){
		addToValue(coordinates, toAdd.intValue());
	}

	int nextStep() {
		Coordinates.getAllCoordinatesOld(maxCoordinate)
				.forEach(co -> addToValue(co, 1));

		List<Coordinates> lightningCoordinates = Coordinates.getAllCoordinatesOld(maxCoordinate).stream()
				.filter(this::isLightingUp)
				.toList();

		List<Coordinates> allLightenedUp = getAllLightenedUp(lightningCoordinates, new ArrayList<>());
		allLightenedUp.forEach(this::resetValue);
		return allLightenedUp.size();
	}

	private List<Coordinates> getNeighbours(Coordinates co) {
		return co.getAllNeighbours().stream()
				.filter(this::isValid)
				.toList();
	}

	private boolean isValid(Coordinates coordinates1) {
		return coordinates1.isInFirstQuadrant() && coordinates1.isSmallerThen(maxCoordinate);
	}

	private int getValue(Coordinates co) {
		return nrs[co.getX()][co.getY()];
	}

	private void resetValue(Coordinates co) {
		nrs[co.getX()][co.getY()] = 0;
	}

	private void addToValue(Coordinates co, int i) {
		nrs[co.getX()][co.getY()] += i;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OctopusesCave that = (OctopusesCave) o;
		return Arrays.deepEquals(nrs, that.nrs) && Objects.equals(maxCoordinate, that.maxCoordinate);
	}

	@Override
	public int hashCode() {
		int result = Objects.hash(maxCoordinate);
		result = 31 * result + Arrays.deepHashCode(nrs);
		return result;
	}

	@Override
	public String toString() {
		return "OctopusesCave{" +
				"nrs=" + Arrays.deepToString(nrs) +
				'}';
	}
}
