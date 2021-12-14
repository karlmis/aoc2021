package be.durvenproeven.aoc2021.day14;

import java.util.Map;

public class PolimerizationEngine {

	private final Map<Pair, String> insertionRules;

	public PolimerizationEngine(Map<Pair, String> insertionRules) {
		this.insertionRules = insertionRules;
	}

	public CharacterCounter afterNrOfSteps(String polymer, int nr){
		String input= polymer;
		for (int i = 0; i < nr; i++) {
			input= getNextString(input);
		}
		return new CharacterCounter(input);
	}

	private String getNextString(String input) {
		char[] chars = input.toCharArray();
		StringBuilder sb= new StringBuilder(Character.toString(chars[0]));
		for (int i = 1; i < chars.length; i++) {
			sb.append(getInsertion(chars[i-1], chars[i]))
					.append(chars[i]);
		}
		return sb.toString();
	}

	private String getInsertion(char aChar, char aChar1) {
		Pair pair = new Pair(Character.toString(aChar), Character.toString(aChar1));
		return insertionRules.get(pair);
	}

}
