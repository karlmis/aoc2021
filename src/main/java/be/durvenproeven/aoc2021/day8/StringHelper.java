package be.durvenproeven.aoc2021.day8;

import java.util.stream.Collectors;

public class StringHelper {

	public static String normalize(String s){
		return s.chars().sorted()
				.mapToObj(nr -> Character.toString((char) nr))
				.collect(Collectors.joining());
	}
}
