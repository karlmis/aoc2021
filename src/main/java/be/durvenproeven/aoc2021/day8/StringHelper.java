package be.durvenproeven.aoc2021.day8;

import org.apache.commons.lang.StringUtils;

import static java.util.stream.Collectors.joining;

public class StringHelper {

	public static String normalize(String s){
		return s.chars().sorted()
				.mapToObj(nr -> Character.toString((char) nr))
				.collect(joining());
	}

	static boolean matches(String s, String toMatchCharacters) {
		return toMatchCharacters.chars()
				.allMatch(c -> StringUtils.contains(s, (char) c));
	}

	static int countMatch(String s, String toMatchCharacters) {
		return (int) toMatchCharacters.chars()
				.filter(c -> StringUtils.contains(s, (char) c))
				.count();

	}
}
