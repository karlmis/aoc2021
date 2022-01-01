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
import static org.assertj.core.api.Assertions.assertThat;

class TreeSearcherTest {

	@Test
	void calculate() {
		TreeSearcher<AmphipodSystem> treeSearcher = new TreeSearcher<>(AmphipodSystem::toNextTurn, Comparator.comparing(AmphipodSystem::getWeight), AmphipodSystem::isComplete, AmphipodSystem::getWeight);

		AmphipodSystem result = treeSearcher.calculate(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(Bronze, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				), 0,
				Collections.emptyMap(),
				11, null));
		System.out.println("result = " + result);
		System.out.println("result weight = " + result.toPrettyStringComplete());
	}
}