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

	List<Coordinates> getAllLightenedUp(List<Coordinates> newLightenedUp, List<Coordinates> allLightenedUp) {
		if (newLightenedUp.isEmpty()) {
			return allLightenedUp;
		}
		allLightenedUp.addAll(newLightenedUp);

		Map<Coordinates, Long> neighboursWithNrToAdd = newLightenedUp.stream()
				.flatMap(co -> getNeighbours(co).stream())
				.filter(o -> !allLightenedUp.contains(o))
				.collect(groupingBy(identity(), counting()));

		List<Coordinates> newChanged = new ArrayList<>();
		for (Map.Entry<Coordinates, Long> entry : neighboursWithNrToAdd.entrySet()) {
			int newValue = getValue(entry.getKey()) + entry.getValue().intValue();
			setValue(entry.getKey(), newValue);
			if (newValue > 9) {
				newChanged.add(entry.getKey());
			}
		}
		return getAllLightenedUp(newChanged, allLightenedUp);
	}

	int nextStep() {
		Coordinates.getAllCoordinates(maxCoordinate)
				.forEach(co -> setValue(co, getValue(co) + 1));

		List<Coordinates> lightningCoordinates = new ArrayList<>();
		Coordinates.getAllCoordinates(maxCoordinate).stream()
				.filter(co -> getValue(co) > 9)
				.forEach(lightningCoordinates::add);

		List<Coordinates> allChanged = getAllLightenedUp(lightningCoordinates, new ArrayList<>());

		allChanged.forEach(co -> setValue(co, 0));
		return allChanged.size();
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

	private void setValue(Coordinates co, int i) {
		nrs[co.getX()][co.getY()] = i;
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
