package be.durvenproeven.aoc2021.day23;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.OptionalInt;

import static be.durvenproeven.aoc2021.day23.AmphipodType.Amber;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Bronze;
import static be.durvenproeven.aoc2021.day23.AmphipodType.Copper;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

class RoomTest {

	@Test
	void getDistanceToFreePlace() {
		assertThat(new Room(Amber, List.of(Bronze, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.empty());
		assertThat(new Room(Amber, asList(null, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(1));
		assertThat(new Room(Amber, asList(null, null)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(2));
	}

	@Test
	void getDistanceToFreePlace_4() {
		assertThat(new Room(Amber, List.of(Bronze, Bronze, Bronze, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.empty());
		assertThat(new Room(Amber, asList(null, Bronze, Bronze, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(1));
		assertThat(new Room(Amber, asList(null, null, Bronze, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(2));
		assertThat(new Room(Amber, asList(null, null, null, Bronze)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(3));
		assertThat(new Room(Amber, asList(null, null, null, null)).getDistanceToFreePlace())
				.isEqualTo(OptionalInt.of(4));
	}

	@Test
	void withoutTop() {
		assertThat(new Room(Amber, asList(Bronze, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, Bronze)));
		assertThat(new Room(Amber, asList(null, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, null)));
	}

	@Test
	void withoutTop_4() {
		assertThat(new Room(Amber, asList(Bronze, Bronze, Bronze, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, Bronze, Bronze, Bronze)));
		assertThat(new Room(Amber, asList(null, Bronze, Bronze, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, null, Bronze, Bronze)));
		assertThat(new Room(Amber, asList(null, null, Bronze, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, null, null, Bronze)));
		assertThat(new Room(Amber, asList(null, null, null, Bronze)).withoutTop()).isEqualTo(
				new Room(Amber, asList(null, null, null, null)));
	}

	@Test
	void withCorrectTop() {
		assertThat(new Room(Amber, asList(null, Bronze)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(Amber, Bronze)));
		assertThat(new Room(Amber, asList(null, null)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(null, Amber)));
	}

	@Test
	void withCorrectTop_4() {
		assertThat(new Room(Amber, asList(null, Bronze, Bronze, Bronze)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(Amber, Bronze, Bronze, Bronze)));
		assertThat(new Room(Amber, asList(null, null, Bronze, Bronze)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(null, Amber, Bronze, Bronze)));
		assertThat(new Room(Amber, asList(null, null, null, Bronze)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(null, null, Amber, Bronze)));
		assertThat(new Room(Amber, asList(null, null, null, null)).withCorrectTop()).isEqualTo(
				new Room(Amber, asList(null, null, null, Amber)));
	}


	@Test
	void getWrongLocated() {
		Room room = new Room(Amber, List.of(Bronze, Copper));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze, 1),
				new Room.AmphipodWithDistance(Copper, 2));
	}

	@Test
	void getWrongLocated_TwoTheSameWrong() {
		Room room = new Room(Amber, List.of(Bronze, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze, 1),
				new Room.AmphipodWithDistance(Bronze, 2)
		);
	}

	@Test
	void getWrongLocated_FirstIsCorrect() {
		Room room = new Room(Amber, List.of(Amber, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Amber, 1),
				new Room.AmphipodWithDistance(Bronze, 2)
		);
	}

	@Test
	void getWrongLocated_FirstIsEmpty() {
		Room room = new Room(Amber, asList(null, Bronze));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze, 2)
		);
	}

	@Test
	void getWrongLocated_OneCorrect() {
		Room room = new Room(Amber, List.of(Bronze, Amber));

		assertThat(room.getWrongLocated()).containsExactly(
				new Room.AmphipodWithDistance(Bronze, 1));
	}

	@Test
	void getWrongLocated_TwoCorrect() {
		Room room = new Room(Amber, List.of(Amber, Amber));

		assertThat(room.getWrongLocated()).isEmpty();
	}

	@Test
	void getAverageValue() {
		assertThat(new Room(Amber, List.of(Amber, Amber)).getAverageValue()).isEqualTo(1.);
		assertThat(new Room(Amber, List.of(Amber, Bronze)).getAverageValue()).isEqualTo(1.5);
	}

	@Test
	void getAverageValue_4() {
		assertThat(new Room(Amber, List.of(Amber, Amber, Amber, Amber)).getAverageValue()).isEqualTo(1.);
		assertThat(new Room(Amber, List.of(Amber, Bronze, Amber, Amber)).getAverageValue()).isEqualTo(1.5);
		assertThat(new Room(Amber, List.of(Amber, Amber, Bronze, Amber)).getAverageValue()).isEqualTo(2);
		assertThat(new Room(Amber, List.of(Amber, Amber, Amber, Bronze)).getAverageValue()).isEqualTo(2.5);
	}

	@Test
	void isComplete() {

	}
}