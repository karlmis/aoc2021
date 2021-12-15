package be.durvenproeven.aoc2021;

import java.util.List;

public class NumberUtils {
	public static int[] toIntArray(String withOnlyDigits) {
		return withOnlyDigits.chars()
				.mapToObj(Character::toString)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	public static List<Integer> toIntList(String withOnlyDigits) {
		return withOnlyDigits.chars()
				.mapToObj(Character::toString)
				.map(Integer::parseInt)
				.toList();
	}
}
