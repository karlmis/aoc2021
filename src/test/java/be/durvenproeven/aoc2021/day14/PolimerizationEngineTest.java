package be.durvenproeven.aoc2021.day14;

import be.durvenproeven.aoc2021.LineResolver;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PolimerizationEngineTest {

	@Test
	void nextTurn() {
		Map<Pair, String> insertionRules = new HashMap<>();
		insertionRules.put(new Pair("C", "H"), "B");
		insertionRules.put(new Pair("H", "H"), "N");
		insertionRules.put(new Pair("C", "B"), "H");
		insertionRules.put(new Pair("N", "H"), "C");
		insertionRules.put(new Pair("H", "B"), "C");
		insertionRules.put(new Pair("H", "C"), "B");
		insertionRules.put(new Pair("H", "N"), "C");
		insertionRules.put(new Pair("N", "N"), "C");
		insertionRules.put(new Pair("B", "H"), "H");
		insertionRules.put(new Pair("N", "C"), "B");
		insertionRules.put(new Pair("N", "B"), "B");
		insertionRules.put(new Pair("B", "N"), "B");
		insertionRules.put(new Pair("B", "B"), "N");
		insertionRules.put(new Pair("B", "C"), "B");
		insertionRules.put(new Pair("C", "C"), "N");
		insertionRules.put(new Pair("C", "N"), "C");


		PolimerizationEngine polimerizationEngine = new PolimerizationEngine(insertionRules);
		assertThat(polimerizationEngine.afterNrOfSteps("NNCB",1).getInput())
				.isEqualTo("NCNBCHB");

		assertThat(polimerizationEngine.afterNrOfSteps("NNCB",2).getInput())
				.isEqualTo("NBCCNBBBCBHCB");

		assertThat(polimerizationEngine.afterNrOfSteps("NNCB",3).getInput())
				.isEqualTo("NBBBCNCCNBBNBNBBCHBHHBCHB");

		assertThat(polimerizationEngine.afterNrOfSteps("NNCB",4).getInput())
				.isEqualTo("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB");

	}

	@Test
	void getDifferenceMostAndLeastCommon() {
		Map<Pair, String> insertionRules = new HashMap<>();
		insertionRules.put(new Pair("C", "H"), "B");
		insertionRules.put(new Pair("H", "H"), "N");
		insertionRules.put(new Pair("C", "B"), "H");
		insertionRules.put(new Pair("N", "H"), "C");
		insertionRules.put(new Pair("H", "B"), "C");
		insertionRules.put(new Pair("H", "C"), "B");
		insertionRules.put(new Pair("H", "N"), "C");
		insertionRules.put(new Pair("N", "N"), "C");
		insertionRules.put(new Pair("B", "H"), "H");
		insertionRules.put(new Pair("N", "C"), "B");
		insertionRules.put(new Pair("N", "B"), "B");
		insertionRules.put(new Pair("B", "N"), "B");
		insertionRules.put(new Pair("B", "B"), "N");
		insertionRules.put(new Pair("B", "C"), "B");
		insertionRules.put(new Pair("C", "C"), "N");
		insertionRules.put(new Pair("C", "N"), "C");


		PolimerizationEngine polimerizationEngine = new PolimerizationEngine(
				insertionRules);

		CharacterCounter nncb = polimerizationEngine.afterNrOfSteps("NNCB", 10);
		assertThat(nncb.getMaxOccurences()- nncb.getMinOccurences())
				.isEqualTo(1588);
	}

	@Test
	void getDifferenceMostAndLeastCommon_GivenExample40() {
		Map<Pair, String> insertionRules = new HashMap<>();
		insertionRules.put(new Pair("C", "H"), "B");
		insertionRules.put(new Pair("H", "H"), "N");
		insertionRules.put(new Pair("C", "B"), "H");
		insertionRules.put(new Pair("N", "H"), "C");
		insertionRules.put(new Pair("H", "B"), "C");
		insertionRules.put(new Pair("H", "C"), "B");
		insertionRules.put(new Pair("H", "N"), "C");
		insertionRules.put(new Pair("N", "N"), "C");
		insertionRules.put(new Pair("B", "H"), "H");
		insertionRules.put(new Pair("N", "C"), "B");
		insertionRules.put(new Pair("N", "B"), "B");
		insertionRules.put(new Pair("B", "N"), "B");
		insertionRules.put(new Pair("B", "B"), "N");
		insertionRules.put(new Pair("B", "C"), "B");
		insertionRules.put(new Pair("C", "C"), "N");
		insertionRules.put(new Pair("C", "N"), "C");


		PolimerizationEngine polimerizationEngine = new PolimerizationEngine(
				insertionRules);

		PairCollection nncb = polimerizationEngine.pairsAfterNrOfSteps("NNCB", 40);

		assertThat(nncb.getDiffInOccurences()).isEqualTo(2188189693529L);
	}

	@Test
	void realExample() {
		HashMap<Pair, String> insertionRules = new HashMap<>();
		LineResolver.getStringStreamOfFile("day14polymerization.txt")
				.forEach(s -> insertionRules.put(pairOf(s), outputOf(s)));


		PolimerizationEngine polimerizationEngine = new PolimerizationEngine(
				insertionRules);

		PairCollection cpsssfcfofvfnvpkbfvn = polimerizationEngine.pairsAfterNrOfSteps("CPSSSFCFOFVFNVPKBFVN", 10);
		assertThat(cpsssfcfofvfnvpkbfvn.getDiffInOccurences()).isEqualTo(3697L);

		CharacterCounter ccCounter = polimerizationEngine.afterNrOfSteps("CPSSSFCFOFVFNVPKBFVN", 10);
		assertThat(ccCounter.getMaxOccurences()- ccCounter.getMinOccurences()).isEqualTo(3697L);
	}

	@Test
	void realExample40() {
		HashMap<Pair, String> insertionRules = new HashMap<>();
		LineResolver.getStringStreamOfFile("day14polymerization.txt")
				.forEach(s -> insertionRules.put(pairOf(s), outputOf(s)));


		PolimerizationEngine polimerizationEngine = new PolimerizationEngine(
				insertionRules);

		PairCollection pairCollection = polimerizationEngine.pairsAfterNrOfSteps("CPSSSFCFOFVFNVPKBFVN", 40);
		assertThat(pairCollection.getDiffInOccurences()).isEqualTo(4371307836157L);

	}

	private String outputOf(String s) {
		return StringUtils.right(s,1);
	}

	private Pair pairOf(String s) {
		return new Pair(s.substring(0,1), s.substring(1,2));
	}
}