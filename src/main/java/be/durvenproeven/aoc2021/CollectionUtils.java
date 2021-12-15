package be.durvenproeven.aoc2021;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionUtils {
	public static Set<Coordinates> mutableSetOf(Coordinates value) {
		Set<Coordinates> basin = new HashSet<>();
		basin.add(value);
		return basin;
	}

	public static Set<Coordinates> createMutableSetWith(Set<Coordinates> alreadyDone, Coordinates coordinates) {
		HashSet<Coordinates> newAlreadyDone = new HashSet<>(alreadyDone);
		newAlreadyDone.add(coordinates);
		return newAlreadyDone;
	}

	public static <T> List<T> createListWith(List<T> alreadyDone, T coordinates) {
		List<T> newAlreadyDone = new ArrayList<>(alreadyDone);
		newAlreadyDone.add(coordinates);
		return newAlreadyDone;
	}

}
