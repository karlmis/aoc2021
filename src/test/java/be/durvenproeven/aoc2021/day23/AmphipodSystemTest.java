package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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
						new Room(Bronze, asList(null, Amber)),3
				),
				0,
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
						new Room(Bronze, asList(null, Amber)),3
				),
				1000,
				Map.of(2, Amber),
				5
		);

		assertThat(amphipodSystem.getWeight()).isEqualTo(1000 + 10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)),3
				),
				0,
				Map.of(2, Bronze),
				5);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)),4
				),
				0,
				Map.of(2, Amber),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)),4
				),
				0,
				Map.of(3, Amber),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight_ExtraPlacesBetweenRooms_After_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)),4
				),
				0,
				Map.of(2, Bronze),
				6);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_After_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(null, Bronze)), 1,
						new Room(Bronze, asList(Amber, Amber)),4
				),
				0,
				Map.of(3, Bronze),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayLeft() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(//TODO vanaf hier & kijken naar de indexen
				Map.of(
						new Room(Amber, asList(null, Bronze)), 2,
						new Room(Bronze, asList(Amber, Amber)),5
				),
				0,
				Map.of(0, Bronze),
				7);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 5.5 + 1));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayRight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				Map.of(
						new Room(Amber, asList(Bronze, Bronze)), 1,
						new Room(Bronze, asList(null, Amber)),4
				),
				0,
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


}