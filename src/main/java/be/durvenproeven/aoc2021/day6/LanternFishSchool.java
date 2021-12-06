package be.durvenproeven.aoc2021.day6;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class LanternFishSchool {
	private Map<Integer, Long> mapByTimer;

	public LanternFishSchool(List<Integer> list) {
		mapByTimer = list.stream().collect(Collectors.groupingBy(Function.identity(), counting()));
	}

	public void nextGeneration(){
		Map<Integer, Long> newMapByTimer= new HashMap<>();

		for (Map.Entry<Integer, Long> integerLongEntry : mapByTimer.entrySet()) {
			if (integerLongEntry.getKey() != 0){
				newMapByTimer.put(integerLongEntry.getKey()-1, integerLongEntry.getValue());
			}
		}
		Long newGenerations = mapByTimer.get(0);
		if (newGenerations != null) {
			newMapByTimer.put(8, newGenerations);
			newMapByTimer.put(6, newMapByTimer.getOrDefault(6,0L)+  newGenerations);
		}

		mapByTimer= newMapByTimer;
	}

	public long getNrOfFish(){
		//return list.size();
		return mapByTimer.values().stream()
				.mapToLong(Long::longValue)
				.sum();
	}
}
