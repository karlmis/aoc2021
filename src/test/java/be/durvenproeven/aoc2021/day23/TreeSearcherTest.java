package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Disabled;
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
		TreeSearcher treeSearcher = new TreeSearcher(WeightedAmphipodeSystem::toNextTurn, Comparator.comparing(WeightedAmphipodeSystem::getWeight), WeightedAmphipodeSystem::isComplete, WeightedAmphipodeSystem::getWeight);
		/*
		#############
		#...........#
		###B#C#B#D###
		  #A#D#C#A#
		  #########
		 */
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
		assertThat(result.getWeight()).isEqualTo(12521);
	}

	@Test
	void calculate_Part2() {
		TreeSearcher treeSearcher = new TreeSearcher(WeightedAmphipodeSystem::toNextTurn, Comparator.comparing(WeightedAmphipodeSystem::getWeight), WeightedAmphipodeSystem::isComplete, WeightedAmphipodeSystem::getWeight);
		/*
		#############
		#...........#
		###B#C#B#D###
		  #A#D#C#A#
		  #########
		 */
		WeightedAmphipodeSystem result = treeSearcher.calculate(new WeightedAmphipodeSystem( new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Desert, Desert, Amber)), 2,
						new Room(Bronze, asList(Copper, Copper, Bronze, Desert)), 4,
						new Room(Copper, asList(Bronze, Bronze, Amber, Copper)), 6,
						new Room(Desert, asList(Desert, Amber, Copper, Amber)), 8
				),
				Collections.emptyMap(),
				11), 0, null));
		System.out.println("result = " + result);
		System.out.println("result weight = \n" + result.toPrettyStringComplete());
		assertThat(result.getWeight()).isEqualTo(44169);
	}

	@Test
	void calculate_RealTEst() {
		TreeSearcher treeSearcher = new TreeSearcher(WeightedAmphipodeSystem::toNextTurn, Comparator.comparing(WeightedAmphipodeSystem::getWeight), WeightedAmphipodeSystem::isComplete, WeightedAmphipodeSystem::getWeight);
		WeightedAmphipodeSystem starting = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Desert, Bronze)), 2,
						new Room(Bronze, asList(Copper, Copper)), 4,
						new Room(Copper, asList(Amber, Desert)), 6,
						new Room(Desert, asList(Bronze, Amber)), 8
				),
				Collections.emptyMap(),
				11), 0, null);
		System.out.println("starting.getWeight() = " + starting.getWeight());
		WeightedAmphipodeSystem result = treeSearcher.calculate(starting);
		System.out.println("result = " + result);
		System.out.println("result weight = \n" + result.toPrettyStringComplete());
		assertThat(result.getWeight()).isEqualTo(15160);
	}

	@Test
	void calculate_Part2_RealTEst() {
		TreeSearcher treeSearcher = new TreeSearcher(WeightedAmphipodeSystem::toNextTurn, Comparator.comparing(WeightedAmphipodeSystem::getWeight), WeightedAmphipodeSystem::isComplete, WeightedAmphipodeSystem::getWeight);
		WeightedAmphipodeSystem starting = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Desert, Desert, Desert, Bronze)), 2,
						new Room(Bronze, asList(Copper, Copper, Bronze, Copper)), 4,
						new Room(Copper, asList(Amber, Bronze, Amber, Desert)), 6,
						new Room(Desert, asList(Bronze, Amber, Copper,Amber)), 8
				),
				Collections.emptyMap(),
				11), 0, null);
		System.out.println("starting.getWeight() = " + starting.getWeight());
		WeightedAmphipodeSystem result = treeSearcher.calculate(starting);
		System.out.println("result = " + result);
		System.out.println("result weight = \n" + result.toPrettyStringComplete());
		assertThat(result.getWeight()).isEqualTo(46772);
	}
}