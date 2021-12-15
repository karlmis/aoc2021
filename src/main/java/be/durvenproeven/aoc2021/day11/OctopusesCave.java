package be.durvenproeven.aoc2021.day11;

import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class OctopusesCave {
	private final Grid grid;


	public OctopusesCave(List<String> input) {
		grid = new Grid(input);
	}

	public boolean allFlash() {
		return grid.allHaveLevel(0);
	}

	int nextStep() {
		grid.apply(nr -> nr+1);

		List<Coordinates> lightningCoordinates = grid.withLevel(i-> i>9);

		List<Coordinates> allLightenedUp = getAllLightenedUp(lightningCoordinates, new ArrayList<>());
		allLightenedUp.forEach(this::resetValue);
		return allLightenedUp.size();
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
				.filter(co -> getValue(co) > 9)
				.toList();

		return getAllLightenedUp(newChanged, allLightenedUp);
	}

	private void addToValue(Coordinates coordinates, Long toAdd){
		grid.updateValue(coordinates, nr -> nr+ toAdd.intValue());
	}

	private List<Coordinates> getNeighbours(Coordinates co) {
		return grid.getAllNeighbours(co);
	}

	private int getValue(Coordinates co) {
		return grid.getValue(co);
	}

	private void resetValue(Coordinates co) {
		grid.setValue(co, 0);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OctopusesCave that = (OctopusesCave) o;
		return Objects.equals(grid, that.grid);
	}

	@Override
	public int hashCode() {
		return Objects.hash(grid);
	}
}
