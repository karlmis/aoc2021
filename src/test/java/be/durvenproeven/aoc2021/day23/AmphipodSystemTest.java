package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Desert;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class AmphipodSystemTest {

	@Test
	void getWeight() {
		AmphipodSystem amphipodSystem = AmphipodSystem.createWithFreeSpaces(
				List.of(new Room(Amber, List.of(Bronze, Bronze)),
						new Room(Bronze, List.of(Amber, Amber))),
				List.of(1, 1, 1)
		);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * 10);
	}

	@Test
	void getWeight_PartsInBetween() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 3
				),
				Map.of(2, Amber),
				5
		);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_AlsoAcquired() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 3
				),
				Map.of(2, Amber),
				5
		);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 3
				),
				Map.of(2, Bronze),
				5);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				Map.of(2, Amber),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				Map.of(3, Amber),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight_ExtraPlacesBetweenRooms_After_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 4
				),
				Map.of(2, Bronze),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_After_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 4
				),
				Map.of(3, Bronze),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayLeft() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(//TODO vanaf hier & kijken naar de indexen
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(Amber, Amber)), 5
				),
				Map.of(0, Bronze),
				7);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 5.5 + 1));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayRight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				Map.of(5, Amber),
				7);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 5.5));
	}

	@Test
	void getWeight_MoreRoomsBetween() {
		AmphipodSystem amphipodSystem = AmphipodSystem.createWithFreeSpaces(
				List.of(new Room(Amber, List.of(Bronze, Bronze)),
						new Room(Bronze, List.of(Amber, Amber))),
				List.of(1, 1, 1)
		);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * 10);
	}


	@Test
	void toNextTurn_FirstTurn() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(AmphipodSystem.createWithFreeSpaces(
				List.of(new Room(Amber, List.of(Bronze, Bronze)),
						new Room(Bronze, List.of(Amber, Amber))),
				List.of(1, 1, 1)
		), 0, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						),
						Map.of(0, Bronze),
						5
				), 20, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						),
						Map.of(2, Bronze),
						5
				), 20, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						),
						Map.of(4, Bronze),
						5
				), 40, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						),
						Map.of(0, Amber),
						5
				), 4, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						),
						Map.of(2, Amber),
						5
				), 2, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						),
						Map.of(4, Amber),
						5
				)
						, 2, null)
		);
	}

	@Test
	void nextTurn_OneBetween() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 3
				),
				Map.of(2, Bronze),
				5
		), 20, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						),
						Map.of(2, Bronze, 0, Bronze),
						5
				), 20 + 30, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						),
						Map.of(2, Bronze, 4, Amber),
						5
				), 20 + 2, null)
		);
	}

	@Test
	void nextTurn_OneMoreBetween() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 5
				),
				Map.of(3, Bronze),
				7
		), 20, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 5
						),
						Map.of(3, Bronze, 0, Bronze),
						7
				), 20 + 30, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 5
						),
						Map.of(3, Bronze, 2, Bronze),
						7
				), 20 + 30, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						),
						Map.of(3, Bronze, 6, Amber),
						7
				), 20 + 2, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						),
						Map.of(3, Bronze, 4, Amber),
						7
				)
						, 20 + 2, null)
		);
	}

	@Test
	void nextTurn_RoomsWithoutAmphipodesAreIgnored() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, null)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 5
				),
				Map.of(3, Bronze, 2, Bronze),
				7
		), 20+30, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						),
						Map.of(3, Bronze, 2, Bronze, 4, Amber),
						7), 20 + 30 + 2, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						),
						Map.of(3, Bronze, 2, Bronze, 6, Amber),
						7), 20 + 30 + 2, null)
		);
	}

	@Test
	void nextTurn_OccupiedPlaceGoesToTheCorrectLocation() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Amber)), 1,
						new Room(Bronze, asList(null, Bronze)), 3
				),
				Map.of(0, Bronze, 4, Amber),
				5
		), 50, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Amber, Amber)), 1,
								new Room(Bronze, asList(null, Bronze)), 3
						),
						Map.of(0, Bronze),
						5), 50 + 4, null),
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Amber)), 1,
								new Room(Bronze, asList(Bronze, Bronze)), 3
						),
						Map.of(4, Amber),
						5), 50 + 40, null)
		);
	}

	@Test
	void nextTurn_OccupiedPlaceGoesToTheCorrectLocation_ButOneInMiddle() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Amber)), 1,
						new Room(Bronze, asList(null, Bronze)), 3
				),
				Map.of(2, Bronze, 4, Amber),
				5
		), 50, null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Amber)), 1,
								new Room(Bronze, asList(Bronze, Bronze)), 3
						),
						Map.of(4, Amber),
						5), 50 + 20, null)
		);
	}

	@Test
	void nextTurn_1() {

		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(Bronze, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Map.of(),
				11
		);
		Map<AmphipodSystem, Double> amphipodSystemDoubleMap = amphipodSystem.toNextTurn();
		assertThat(amphipodSystemDoubleMap)
				.hasSize(7*4)
				.containsKey(new AmphipodSystem(
								Map.of(
										new Room(Amber, asList(Bronze, Amber)), 2,
										new Room(Bronze, asList(Copper, Desert)), 4,
										new Room(Copper, asList(Bronze, Copper)), 6,
										new Room(Desert, asList(null, Amber)), 8
								),
						Map.of(9, Desert),
						11
				)
		);
	}

	@Disabled // does not matter one step will happen 2 steps
	@Test
	void nextTurn_FromRoomToRoom() {
		WeightedAmphipodeSystem amphipodSystem = new WeightedAmphipodeSystem(new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Map.of(3, Bronze),
				11
		), 0, null);

		assertThat(amphipodSystem.toNextTurn()).contains(
				new WeightedAmphipodeSystem(new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Bronze, Amber)), 2,
								new Room(Bronze, asList(null, Desert)), 4,
								new Room(Copper, asList(Copper, Copper)), 6,
								new Room(Desert, asList(Desert, Amber)), 8
						),
						Map.of(3, Bronze),
						11
				),0, null)

		);

	}

	@Test
	void nextTurn_OnlyGoToCorrectRoomIfAllAreOk() {
		/*
		.......B...
          B#.#C#D+
          A#D#C#A-
		 */
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(null, Desert)), 4,
						new Room(Copper, asList(Copper, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				),
				Map.of(7, Bronze),
				11
		);

		assertThat(amphipodSystem.toNextTurn()).doesNotContainKey(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Bronze, Amber)), 2,
								new Room(Bronze, asList(Bronze, Desert)), 4,
								new Room(Copper, asList(Copper, Copper)), 6,
								new Room(Desert, asList(Desert, Amber)), 8
						),
						Collections.emptyMap(),
						11
				)

		);

	}

	@Test
	void equals() {
		Map<Room, Integer> map0 = Map.of(
				new Room(Amber, asList(Bronze, Amber)), 2,
				new Room(Bronze, asList(Copper, Desert)), 4,
				new Room(Copper, asList(null, Copper)), 6,
				new Room(Desert, asList(Desert, Amber)), 8
		);
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				map0,
				Map.of(3, Bronze),
				11
		);

		Map<Room, Integer> map1 = Map.of(
				new Room(Amber, asList(null, Amber)), 2,
				new Room(Bronze, asList(Copper, Desert)), 4,
				new Room(Copper, asList(Bronze, Copper)), 6,
				new Room(Desert, asList(Desert, Amber)), 8
		);
		AmphipodSystem amphipodSystem1 = new AmphipodSystem(
				map1,
				Map.of(3, Bronze),
				11
		);
		assertThat(map0).isNotEqualTo(map1);
		assertThat(new TreeMap<>(map0)).isNotEqualTo(new TreeMap<>(map1));

		assertThat(amphipodSystem).isNotEqualTo(amphipodSystem1);
	}

	@Test
	void getWeight2() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(null, Copper)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Desert)), 8
				),
				Map.of(0, Amber, 1, Amber, 9, Bronze),
				11
		);
		assertThat(amphipodSystem.getWeight()).isEqualTo(626);
	}
}