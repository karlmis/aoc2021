package be.durvenproeven.aoc2021;

public class NumberUtils {
	public static int[] toIntArray(String withOnlyDigits) {
		return withOnlyDigits.chars()
				.mapToObj(Character::toString)
				.mapToInt(Integer::parseInt)
				.toArray();
	}
}
