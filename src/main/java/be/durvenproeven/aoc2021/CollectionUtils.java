package be.durvenproeven.aoc2021;

import be.durvenproeven.aoc2021.day9.Coordinates;

import java.util.HashSet;
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
}
