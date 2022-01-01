package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Desert;
import static java.util.Arrays.asList;

class TreeSearcherTest {

	@Test
	void calculate() {
		TreeSearcher treeSearcher = new TreeSearcher(WeightedAmphipodeSystem::toNextTurn, Comparator.comparing(WeightedAmphipodeSystem::getWeight), WeightedAmphipodeSystem::isComplete, WeightedAmphipodeSystem::getWeight);

		WeightedAmphipodeSystem result = treeSearcher.calculate(new WeightedAmphipodeSystem( new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(Bronze, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Collections.emptyMap(),
				11), 0, null));
		System.out.println("result = " + result);
		System.out.println("result weight = \n" + result.toPrettyStringComplete());
	}
}