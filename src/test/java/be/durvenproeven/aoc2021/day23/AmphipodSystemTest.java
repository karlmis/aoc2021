package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

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
				0,
				Map.of(2, Amber),
				5,
				null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_AlsoAcquired() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 3
				),
				1000,
				Map.of(2, Amber),
				5,
				null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(1000 + 10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 3
				),
				0,
				Map.of(2, Bronze),
				5, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				0,
				Map.of(2, Amber),
				6, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				0,
				Map.of(3, Amber),
				6, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight_ExtraPlacesBetweenRooms_After_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 4
				),
				0,
				Map.of(2, Bronze),
				6, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_After_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)), 4
				),
				0,
				Map.of(3, Bronze),
				0, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayLeft() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(//TODO vanaf hier & kijken naar de indexen
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(Amber, Amber)), 5
				),
				0,
				Map.of(0, Bronze),
				7, null);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 5.5 + 1));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayRight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)), 4
				),
				0,
				Map.of(5, Amber),
				7, null);

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
		AmphipodSystem amphipodSystem = AmphipodSystem.createWithFreeSpaces(
				List.of(new Room(Amber, List.of(Bronze, Bronze)),
						new Room(Bronze, List.of(Amber, Amber))),
				List.of(1, 1, 1)
		);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						), 20,
						Map.of(0, Bronze),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						), 20,
						Map.of(2, Bronze),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						), 40,
						Map.of(4, Bronze),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						), 4,
						Map.of(0, Amber),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						), 2,
						Map.of(2, Amber),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, List.of(Bronze, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						), 2,
						Map.of(4, Amber),
						5,
						null)

		);
	}

	@Test
	void nextTurn_OneBetween() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 3
				), 20,
				Map.of(2, Bronze),
				5,
				null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 3
						), 20 + 30,
						Map.of(2, Bronze, 0, Bronze),
						5,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 3
						), 20 + 2,
						Map.of(2, Bronze, 4, Amber),
						5,
						null)
		);
	}

	@Test
	void nextTurn_OneMoreBetween() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 5
				), 20,
				Map.of(3, Bronze),
				7,
				null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 5
						), 20 + 30,
						Map.of(3, Bronze, 0, Bronze),
						7,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, List.of(Amber, Amber)), 5
						), 20 + 30,
						Map.of(3, Bronze, 2, Bronze),
						7,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						), 20 + 2,
						Map.of(3, Bronze, 6, Amber),
						7,
						null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Bronze)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						), 20 + 2,
						Map.of(3, Bronze, 4, Amber),
						7,
						null)
		);
	}

	@Test
	void nextTurn_RoomsWithoutAmphipodesAreIgnored() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, null)), 1,
						new Room(Bronze, List.of(Amber, Amber)), 5
				), 20 + 30,
				Map.of(3, Bronze, 2, Bronze),
				7,
				null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						), 20 + 30 + 2,
						Map.of(3, Bronze, 2, Bronze, 4, Amber),
						7, null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, null)), 1,
								new Room(Bronze, asList(null, Amber)), 5
						), 20 + 30 + 2,
						Map.of(3, Bronze, 2, Bronze, 6, Amber),
						7, null)
		);
	}

	@Test
	void nextTurn_OccupiedPlaceGoesToTheCorrectLocation() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Amber)), 1,
						new Room(Bronze, asList(null, Bronze)), 3
				), 50,
				Map.of(0, Bronze, 4, Amber),
				5,
				null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Amber, Amber)), 1,
								new Room(Bronze, asList(null, Bronze)), 3
						), 50 + 4,
						Map.of(0, Bronze),
						5, null),
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Amber)), 1,
								new Room(Bronze, asList(Bronze, Bronze)), 3
						), 50 + 40,
						Map.of(4, Amber),
						5, null)
		);
	}

	@Test
	void nextTurn_OccupiedPlaceGoesToTheCorrectLocation_ButOneInMiddle() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Amber)), 1,
						new Room(Bronze, asList(null, Bronze)), 3
				), 50,
				Map.of(2, Bronze, 4, Amber),
				5,
				null);

		assertThat(amphipodSystem.toNextTurn()).containsExactlyInAnyOrder(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(null, Amber)), 1,
								new Room(Bronze, asList(Bronze, Bronze)), 3
						), 50 + 20,
						Map.of(4, Amber),
						5, null)
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
				), 0,
				Map.of(),
				11,
				null);
		List<AmphipodSystem> actual = amphipodSystem.toNextTurn();
		assertThat(actual).contains(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Bronze, Amber)), 2,
								new Room(Bronze, asList(Copper, Desert)), 4,
								new Room(Copper, asList(Bronze, Copper)), 6,
								new Room(Desert, asList(null, Amber)), 8
						), 0,
						Map.of(9, Desert),
						11,
						null)//equals werkt niet meer
		);
	}

	@Disabled // does not matter
	@Test
	void nextTurn_FromRoomToRoom() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Amber)), 2,
						new Room(Bronze, asList(Copper, Desert)), 4,
						new Room(Copper, asList(null, Copper)), 6,
						new Room(Desert, asList(Desert, Amber)), 8
				), 0,
				Map.of(3, Bronze),
				11,
				null);

		assertThat(amphipodSystem.toNextTurn()).contains(
				new AmphipodSystem(
						Map.of(
								new Room(Amber, asList(Bronze, Amber)), 2,
								new Room(Bronze, asList(null, Desert)), 4,
								new Room(Copper, asList(Copper, Copper)), 6,
								new Room(Desert, asList(Desert, Amber)), 8
						), 0,
						Map.of(3, Bronze),
						11,
						null)

		);

	}
}