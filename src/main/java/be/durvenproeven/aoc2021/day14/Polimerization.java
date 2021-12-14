package be.durvenproeven.aoc2021.day14;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Polimerization {

	private final String template;
	private final Map<Pair, String> insertionRules;

	public Polimerization(String template, Map<Pair, String> insertionRules) {
		this.template = template;
		this.insertionRules = insertionRules;
	}

	public Polimerization nextTurn(){
		char[] chars = template.toCharArray();
		StringBuilder sb= new StringBuilder(Character.toString(chars[0]));
		for (int i = 1; i < chars.length; i++) {
			sb.append(getInsertion(chars[i-1], chars[i]))
					.append(chars[i]);
		}
		return new Polimerization(sb.toString(), insertionRules);
	}

	private String getInsertion(char aChar, char aChar1) {
		Pair pair = new Pair(Character.toString(aChar), Character.toString(aChar1));
		return insertionRules.get(pair);
	}

	public String getTemplate() {
		return template;
	}

	public long getDifferenceMostAndLeastCommon(){
		Map<String, Long> collect = template.chars().mapToObj(Character::toString)
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		Long max = collect.values().stream().max(Long::compareTo).orElseThrow();
		Long min = collect.values().stream().min(Long::compareTo).orElseThrow();
		return max-min;
	}
}
