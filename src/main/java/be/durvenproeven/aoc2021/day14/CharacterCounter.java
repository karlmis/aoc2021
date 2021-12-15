package be.durvenproeven.aoc2021.day14;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharacterCounter {

	private final Map<String, Long> collect;
	private final String input;


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
