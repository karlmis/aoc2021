package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.CoordinatesXY;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.google.common.base.Preconditions.checkArgument;

public class ChitonPath {
	private final LinkedHashMap<CoordinatesXY, Integer> steps;
	private final int startValue;
	private final CoordinatesXY lastCoordinates;

	public ChitonPath(CoordinatesXY coordinates, int startValue) {
		steps = new LinkedHashMap<>(Map.of(coordinates, startValue));
		this.startValue = startValue;
		lastCoordinates = coordinates;
	}

	private ChitonPath(LinkedHashMap<CoordinatesXY, Integer> steps, int startValue, CoordinatesXY lastCoordinates) {
		this.steps = steps;
		this.startValue = startValue;
		this.lastCoordinates = lastCoordinates;
	}

	public ChitonPath addStep(CoordinatesXY coordinates, int value) {
		checkArgument(!steps.containsKey(coordinates));

		LinkedHashMap<CoordinatesXY, Integer> newMap = new LinkedHashMap<>(steps);
		newMap.put(coordinates, value);
		return new ChitonPath(newMap, startValue, coordinates);
	}

	public int getRiskAfterFirst() {
		return steps.values().stream()
				.mapToInt(Integer::intValue)
				.sum() - startValue;
	}

	public CoordinatesXY getLastCoordinates() {
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
