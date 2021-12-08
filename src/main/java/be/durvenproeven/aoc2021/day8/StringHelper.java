package be.durvenproeven.aoc2021.day8;

import static java.util.stream.Collectors.joining;

public class StringHelper {

	public static String normalize(String s){
		return s.chars().sorted()
				.mapToObj(nr -> Character.toString((char) nr))
				.collect(joining());
	}
}
