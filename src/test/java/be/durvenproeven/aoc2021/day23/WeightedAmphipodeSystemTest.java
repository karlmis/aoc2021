package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Desert;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class WeightedAmphipodeSystemTest {

	@Disabled
	@Test
	void toNextTurn() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(Bronze, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Collections.emptyMap(),
				11
		), 0, null);
		System.out.println(amphipodSystem.getWeight());

		WeightedAmphipodeSystem toSearchFor = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Map.of(3, Bronze),
				11
		), 40, null);
		assertThat(amphipodSystem.toNextTurn()).contains(toSearchFor);
		assertThat(toSearchFor.getWeight()).isEqualTo(10549.5+20.);

	}

	@Test
	void toNextTurn_WeightShouldNeverIncrease() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(null, Desert)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Map.of(3, Bronze, 5, Copper),
				11
		), 0, null);
		System.out.println(amphipodSystem.getWeight());

		assertThat(amphipodSystem.toNextTurn())
				.extracting(WeightedAmphipodeSystem::getWeight)
				.allMatch(w-> w >= amphipodSystem.getWeight());
	}

	@Test
	void name() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(null, Copper)), 4,
						new Room(Copper, asList(null, null)), 6,
						new Room(Desert, asList(Desert, Desert)), 8
				),
				Map.of(0, Amber, 1, Amber, 5, Copper, 9, Bronze),
				11
		), 14236, null);
		assertThat( amphipodSystem.getWeight()).isEqualTo(15162);

		WeightedAmphipodeSystem toCheck = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(null, Copper)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Desert)), 8
				),
				Map.of(0, Amber, 1, Amber, 9, Bronze),
				11
		), 14236 + 300., null);
		assertThat(amphipodSystem.toNextTurn())
				.hasSize(3)
				.contains(toCheck);

		assertThat(toCheck.getWeight()).isEqualTo(15162.);
	}
}