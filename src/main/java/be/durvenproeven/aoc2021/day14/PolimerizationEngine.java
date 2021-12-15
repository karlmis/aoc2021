package be.durvenproeven.aoc2021.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolimerizationEngine {

	private final Map<Pair, String> insertionRules;
	private final Map<Pair, List<Pair>> pairRules;

	public PolimerizationEngine(Map<Pair, String> insertionRules) {
		this.insertionRules = insertionRules;
		pairRules = new HashMap<>();
		insertionRules.forEach((pair, value)-> pairRules.put(pair, toPairs(pair, value)));
	}

	private List<Pair> toPairs(Pair pair, String value) {
		return List.of(new Pair(pair.first(), value), new Pair(value, pair.second()));
	}

	public PairCollection pairsAfterNrOfSteps(String input, int nr){
		char[] chars = input.toCharArray();
		List<Pair> pairs= new ArrayList<>();
		for (int i = 1; i < chars.length; i++) {
			pairs.add(new Pair(Character.toString(chars[i-1]), Character.toString(chars[i])));
		}
		PairCollection pairCollection = new PairCollection(pairs);
		for (int i = 0; i < nr; i++) {
			pairCollection= pairCollection.nextTurn(pairRules);
		}
		return pairCollection;
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
