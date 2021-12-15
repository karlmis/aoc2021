package be.durvenproeven.aoc2021.day14;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class CharacterCounter {

	private final Map<String, Long> collect;
	private String input;


	public CharacterCounter(String s) {
		collect = s.chars().mapToObj(Character::toString)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		input = s;
	}

	public String getInput() {
		return input;
	}

	Long getMaxOccurences(){
		return collect.values().stream().max(Long::compareTo).orElseThrow();
	}

	Long getMinOccurences(){
		return collect.values().stream().min(Long::compareTo).orElseThrow();
	}

}
