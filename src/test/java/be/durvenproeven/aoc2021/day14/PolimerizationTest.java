package be.durvenproeven.aoc2021.day14;

import be.durvenproeven.aoc2021.LineResolver;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PolimerizationTest {

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


		Polimerization polimerization = new Polimerization("NNCB",
				insertionRules);

		Polimerization polimerization_Turn1 = polimerization.nextTurn();
		assertThat(polimerization_Turn1.getTemplate()).isEqualTo("NCNBCHB");

		Polimerization polimerization_Turn2 = polimerization_Turn1.nextTurn();
		assertThat(polimerization_Turn2.getTemplate()).isEqualTo("NBCCNBBBCBHCB");

		Polimerization polimerization_Turn3 = polimerization_Turn2.nextTurn();
		assertThat(polimerization_Turn3.getTemplate()).isEqualTo("NBBBCNCCNBBNBNBBCHBHHBCHB");

		Polimerization polimerization_Turn4 = polimerization_Turn3.nextTurn();
		assertThat(polimerization_Turn4.getTemplate()).isEqualTo("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB");

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


		Polimerization polimerization = new Polimerization("NNCB",
				insertionRules);

		for (int i = 0; i < 10; i++) {
			polimerization= polimerization.nextTurn();
		}


		assertThat(polimerization.getDifferenceMostAndLeastCommon()).isEqualTo(1588);
	}

	@Disabled
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


		Polimerization polimerization = new Polimerization("NNCB",
				insertionRules);

		for (int i = 0; i < 40; i++) {
			polimerization= polimerization.nextTurn();
		}


		assertThat(polimerization.getDifferenceMostAndLeastCommon()).isEqualTo(2188189693529L);
	}

	@Test
	void realExample() {
		HashMap<Pair, String> insertionRules = new HashMap<>();
		LineResolver.getStringStreamOfFile("day14polymerization.txt")
				.forEach(s -> insertionRules.put(pairOf(s), outputOf(s)));


		Polimerization polimerization = new Polimerization("CPSSSFCFOFVFNVPKBFVN",
				insertionRules);

		Polimerization polimerization10 = polimerization
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn()
				.nextTurn();

		assertThat(polimerization10.getDifferenceMostAndLeastCommon()).isEqualTo(3697L);

	}

	private String outputOf(String s) {
		return StringUtils.right(s,1);
	}

	private Pair pairOf(String s) {
		return new Pair(s.substring(0,1), s.substring(1,2));
	}
}