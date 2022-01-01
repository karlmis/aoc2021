package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.List;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class RoomTest {

	@Test
	void getWrongLocated() {
		Room room = new Room(Amber, List.of(Bronze, Copper));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze,1),
				new Room.AmphipodWithDistance(Copper,2));
	}

	@Test
	void getWrongLocated_TwoTheSameWrong() {
		Room room = new Room(Amber, List.of(Bronze, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze,1),
				new Room.AmphipodWithDistance(Bronze,2)
		);
	}

	@Test
	void getWrongLocated_FirstIsCorrect() {
		Room room = new Room(Amber, List.of(Amber, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Amber,1),
				new Room.AmphipodWithDistance(Bronze,2)
		);
	}
	@Test
	void getWrongLocated_FirstIsEmpty() {
		Room room = new Room(Amber, asList(null, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze,2)
		);
	}

	@Test
	void getWrongLocated_OneCorrect() {
		Room room = new Room(Amber, List.of(Bronze, Amber));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze,1));
	}

	@Test
	void getWrongLocated_TwoCorrect() {
		Room room = new Room(Amber, List.of(Amber, Amber));

		assertThat(room.getWrongLocated()).isEmpty();
	}
}