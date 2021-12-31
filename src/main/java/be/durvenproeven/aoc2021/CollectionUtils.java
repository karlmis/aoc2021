package be.durvenproeven.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CollectionUtils {
	public static Set<CoordinatesXY> mutableSetOf(CoordinatesXY value) {
		Set<CoordinatesXY> basin = new HashSet<>();
		basin.add(value);
		return basin;
	}

	public static Set<CoordinatesXY> createMutableSetWith(Set<CoordinatesXY> alreadyDone, CoordinatesXY coordinates) {
		HashSet<CoordinatesXY> newAlreadyDone = new HashSet<>(alreadyDone);
		newAlreadyDone.add(coordinates);
		return newAlreadyDone;
	}

	public static <T> List<T> createListWith(List<T> alreadyDone, T coordinates) {
		List<T> newAlreadyDone = new ArrayList<>(alreadyDone);
		newAlreadyDone.add(coordinates);
		return newAlreadyDone;
	}

	public static <K,V> Map<K,V> createMapWith(Map<K,V> alreadyDone, K keyToAdd, V valueToAdd) {
		LinkedHashMap<K, V> res = new LinkedHashMap<>(alreadyDone);
		res.put(keyToAdd, valueToAdd);
		return res;
	}

}
