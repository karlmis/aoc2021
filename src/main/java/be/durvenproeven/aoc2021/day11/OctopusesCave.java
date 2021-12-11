package be.durvenproeven.aoc2021.day11;

import be.durvenproeven.aoc2021.NumberUtils;
import be.durvenproeven.aoc2021.Coordinates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

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
				if (i!= 0){
					return false;
				}
			}
		}
		return true;
	}

	int nextStep() {
		Coordinates.getAllCoordinates(maxCoordinate)
				.forEach(co -> setValue(co, getValue(co)+1));
		List<Coordinates> lightningCoordinates = new ArrayList<>();
		List<Coordinates> allChanged = new ArrayList<>();
		Coordinates.getAllCoordinates(maxCoordinate).stream()
				.filter(co -> getValue(co) > 9)
				.forEach(lightningCoordinates::add);
		allChanged.addAll(lightningCoordinates);
		while (!lightningCoordinates.isEmpty()) {
			Map<Coordinates, Long> neighbours = lightningCoordinates.stream()
					.flatMap(co -> getNeighbours(co).stream())
					.filter(o -> !allChanged.contains(o))
					.filter(co -> getValue(co)<=9)
					.collect(groupingBy(Function.identity(), counting()));

			lightningCoordinates.clear();
			for (Map.Entry<Coordinates, Long> entry : neighbours.entrySet()) {
				int newValue = getValue(entry.getKey()) + entry.getValue().intValue();
				setValue(entry.getKey(), newValue);
				if (newValue > 9 && !allChanged.contains(entry.getKey())) {
					lightningCoordinates.add(entry.getKey());
				}
			}
			allChanged.addAll(lightningCoordinates);
		}
		Coordinates.getAllCoordinates(maxCoordinate).stream()
				.filter(co -> getValue(co) > 9)
				.forEach(co -> setValue(co, 0));
		return allChanged.size();
	}

	private List<Coordinates> getNeighbours(Coordinates co) {
		return Stream.of(
				getCoordinate(co.getX() + 1, co.getY() - 1),
				getCoordinate(co.getX() + 1, co.getY()),
				getCoordinate(co.getX() + 1, co.getY() + 1),
				getCoordinate(co.getX(), co.getY() + 1),
				getCoordinate(co.getX() - 1, co.getY() - 1),
				getCoordinate(co.getX() - 1, co.getY()),
				getCoordinate(co.getX() - 1, co.getY() + 1),
				getCoordinate(co.getX(), co.getY() - 1))
				.flatMap(Optional::stream)
				.toList();
	}

	private Optional<Coordinates> getCoordinate(int x, int y) {
		Coordinates coordinates = new Coordinates(x, y);
		if (isValid(coordinates)) {
			return Optional.of(coordinates);
		}
		return Optional.empty();
	}

	private boolean isValid(Coordinates coordinates1) {
		return coordinates1.isInFirstQuadrant() && coordinates1.isSmallerThen(maxCoordinate);
	}

	private int getValue(Coordinates co) {
		return nrs[co.getX()][co.getY()];
	}

	private int setValue(Coordinates co, int i) {
		return nrs[co.getX()][co.getY()] = i;
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
		result = 31 * result + Arrays.hashCode(nrs);
		return result;
	}

	@Override
	public String toString() {
		return "OctopusesCave{" +
				"nrs=" + Arrays.deepToString(nrs) +
				'}';
	}
}
