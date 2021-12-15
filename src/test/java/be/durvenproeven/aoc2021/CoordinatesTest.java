package be.durvenproeven.aoc2021;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class CoordinatesTest {
	@Test
	void isInFirstQuadrant() {
		assertThat(new Coordinates(0, 0).isInFirstQuadrant()).isTrue();

		assertThat(new Coordinates(1, 0).isInFirstQuadrant()).isTrue();
		assertThat(new Coordinates(0, 1).isInFirstQuadrant()).isTrue();
		assertThat(new Coordinates(1, 2).isInFirstQuadrant()).isTrue();

		assertThat(new Coordinates(-1, 0).isInFirstQuadrant()).isFalse();
		assertThat(new Coordinates(0, -1).isInFirstQuadrant()).isFalse();
		assertThat(new Coordinates(-2, -1).isInFirstQuadrant()).isFalse();
	}

	@Test
	void isSmallerThen() {
		assertThat(new Coordinates(0, 1).isSmallerThen(new Coordinates(1, 2))).isTrue();

		assertThat(new Coordinates(1, 2).isSmallerThen(new Coordinates(1, 2))).isFalse();
		assertThat(new Coordinates(1, 0).isSmallerThen(new Coordinates(1, 2))).isFalse();
		assertThat(new Coordinates(0, 2).isSmallerThen(new Coordinates(1, 2))).isFalse();
	}

	@Test
	void getNeighbour() {
		assertThat(new Coordinates(2, 5).getNeighbour(Direction.NORTH)).isEqualTo(new Coordinates(2, 5 + 1));
		assertThat(new Coordinates(2, 5).getNeighbour(Direction.EAST)).isEqualTo(new Coordinates(2 + 1, 5));
		assertThat(new Coordinates(2, 5).getNeighbour(Direction.SOUTH)).isEqualTo(new Coordinates(2, 5 - 1));
		assertThat(new Coordinates(2, 5).getNeighbour(Direction.WEST)).isEqualTo(new Coordinates(2 - 1, 5));
	}

	@Test
	void getNeighbour_Null() {
		assertThatNullPointerException().isThrownBy(() -> new Coordinates(2, 5).getNeighbour(null));
	}

	@Test
	void getCardinalNeighbours() {
		assertThat(new Coordinates(2, 5).getCardinalNeighbours()).containsExactly(
				new Coordinates(2, 5 + 1),
				new Coordinates(2 + 1, 5),
				new Coordinates(2, 5 - 1),
				new Coordinates(2 - 1, 5)
		);

	}

	@Test
	void getAllNeighbours() {
		assertThat(new Coordinates(2, 5).getAllNeighbours()).containsExactly(
				new Coordinates(2, 5 + 1),
				new Coordinates(2 + 1, 5 + 1),
				new Coordinates(2 + 1, 5),
				new Coordinates(2 + 1, 5 - 1),
				new Coordinates(2, 5 - 1),
				new Coordinates(2 - 1, 5 - 1),
				new Coordinates(2 - 1, 5),
				new Coordinates(2 - 1, 5 + 1)
		);

	}

	@Test
	void getSmallerCoordinatesWithDistanceToOrigin() {
		assertThat(new Coordinates(3, 3).getSmallerCoordinatesWithDistanceToOrigin(0))
				.containsExactly(new Coordinates(0, 0));
		assertThat(new Coordinates(3, 3).getSmallerCoordinatesWithDistanceToOrigin(1))
				.containsExactly(new Coordinates(0, 1), new Coordinates(1, 0));

		assertThat(new Coordinates(3, 3).getSmallerCoordinatesWithDistanceToOrigin(4))
				.containsExactly(
						new Coordinates(1, 3),
						new Coordinates(2, 2),
						new Coordinates(3, 1)
				).doesNotContain(
				new Coordinates(0, 4),
				new Coordinates(4, 0)

		);


	}
}