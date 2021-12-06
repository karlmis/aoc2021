package be.durvenproeven.aoc2021.day6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class LanternFishSchool {
	private static final int RESET_VALUE = 6;
	private static final int VALUE_FOR_NEW = 8;
	private static final int VALUE_BEFORE_SPAWN = 0;
	private Map<Integer, Long> mapByTimer;

	public LanternFishSchool(List<Integer> list) {
		mapByTimer = list.stream()
				.collect(groupingBy(Function.identity(), counting()));
	}

	public void nextGeneration() {
		Map<Integer, Long> newMapByTimer = new HashMap<>();

		for (Map.Entry<Integer, Long> integerLongEntry : mapByTimer.entrySet()) {
			if (integerLongEntry.getKey() != VALUE_BEFORE_SPAWN) {
				newMapByTimer.put(integerLongEntry.getKey() - 1, integerLongEntry.getValue());
			}
		}
		Long newGenerations = mapByTimer.get(VALUE_BEFORE_SPAWN);
		if (newGenerations != null) {
			newMapByTimer.put(VALUE_FOR_NEW, newGenerations);
			newMapByTimer.compute(RESET_VALUE, (k, v) -> v == null ? newGenerations : v + newGenerations);
		}

		mapByTimer = newMapByTimer;
	}

	public long getNrOfFish() {
		return mapByTimer.values().stream()
				.mapToLong(Long::longValue)
				.sum();
	}
}
