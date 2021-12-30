package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.List;

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
				List.of(new Room(Amber, asList(Bronze, Bronze)),
						new Room(Bronze, asList(null, Amber))),
				List.of(singletonList(null), singletonList(Amber), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_AlsoAcquired() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(Bronze, Bronze)),
						new Room(Bronze, asList(null, Amber))),
				List.of(singletonList(null), singletonList(Amber), singletonList(null)),
				1000);

		assertThat(amphipodSystem.getWeight()).isEqualTo(1000 + 10 * 10 + (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(null, Bronze)),
						new Room(Bronze, List.of(Amber, Amber))),
				List.of(singletonList(null), singletonList(Bronze), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(10 + 10 * (5.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(Bronze, Bronze)),
						new Room(Bronze, asList(null, Amber))),
				List.of(singletonList(null), asList(Amber, null), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_Before_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(Bronze, Bronze)),
						new Room(Bronze, asList(null, Amber))),
				List.of(singletonList(null), asList(null, Amber), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 * 10 + (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_Weight_ExtraPlacesBetweenRooms_After_Left() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(null, Bronze)),
						new Room(Bronze, asList(Amber, Amber))),
				List.of(singletonList(null), asList(Bronze, null), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 1 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_ExtraPlacesBetweenRooms_After_Right() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(null, Bronze)),
						new Room(Bronze, asList(Amber, Amber))),
				List.of(singletonList(null), asList(null, Bronze), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 2.5));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayLeft() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(null, Bronze)),
						new Room(Bronze, asList(Amber, Amber))),
				List.of(asList(Bronze, null), asList(null, null), singletonList(null)),
				0);

		assertThat(amphipodSystem.getWeight()).isEqualTo(12 + 10 * (6.5 + 5.5 + 1));
	}

	@Test
	void getWeight_PartsInBetween_FurtherAwayRight() {
		AmphipodSystem amphipodSystem = new AmphipodSystem(
				List.of(new Room(Amber, asList(Bronze, Bronze)),
						new Room(Bronze, asList(null, Amber))),
				List.of(singletonList(null), asList(null, null), asList(Amber, null)),
				0);

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