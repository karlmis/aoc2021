package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class RoomTest {

	@Test
	void getWrongLocated() {
		Room room = new Room(Amber, List.of(Bronze, Copper));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithWeight(Bronze,1),
				new Room.AmphipodWithWeight(Copper,2));
	}

	@Test
	void getWrongLocated_TwoTheSameWrong() {
		Room room = new Room(Amber, List.of(Bronze, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithWeight(Bronze,1),
				new Room.AmphipodWithWeight(Bronze,2)
		);
	}

	@Test
	void getWrongLocated_OneCorrect() {
		Room room = new Room(Amber, List.of(Bronze, Amber));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithWeight(Bronze,1));
	}

	@Test
	void getWrongLocated_TwoCorrect() {
		Room room = new Room(Amber, List.of(Amber, Amber));

		assertThat(room.getWrongLocated()).isEmpty();
	}
}