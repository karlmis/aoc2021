package be.durvenproeven.aoc2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NumberUtils {
	public static List<Integer> toIntList(String withOnlyDigits) {
		return withOnlyDigits.chars()
				.mapToObj(Character::toString)
				.map(Integer::parseInt)
				.collect(Collectors.toCollection(ArrayList::new));
	}
}
