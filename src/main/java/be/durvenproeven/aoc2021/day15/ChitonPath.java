package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.Coordinates;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class ChitonPath {
	private final LinkedHashMap<Coordinates, Integer> steps;
	private final int startValue;
	private final Coordinates lastCoordinates;

	public ChitonPath(Coordinates coordinates, int startValue) {
		steps = new LinkedHashMap<>(Map.of(coordinates, startValue));
		this.startValue = startValue;
		lastCoordinates = coordinates;
	}

	private ChitonPath(LinkedHashMap<Coordinates, Integer> steps, int startValue, Coordinates lastCoordinates) {
		this.steps = steps;
		this.startValue = startValue;
		this.lastCoordinates = lastCoordinates;
	}

	public ChitonPath addStep(Coordinates coordinates, int value) {
		checkArgument(!steps.containsKey(coordinates));

		LinkedHashMap<Coordinates, Integer> newMap = new LinkedHashMap<>(steps);
		newMap.put(coordinates, value);
		return new ChitonPath(newMap, startValue, coordinates);
	}

	public int getRiskAfterFirst() {
		return steps.values().stream()
				.mapToInt(Integer::intValue)
				.sum() - startValue;
	}

	public int getSize() {
		return steps.size();
	}

	public Coordinates getLastCoordinates() {
		return lastCoordinates;
	}

	@Override
	public String toString() {
		return "ChitonPath{" +
				getRiskAfterFirst()+","+
				"lastCoordinates=" + lastCoordinates +
				'}';
	}
}
