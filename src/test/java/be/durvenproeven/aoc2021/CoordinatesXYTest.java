package be.durvenproeven.aoc2021;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class CoordinatesXYTest {
	@Test
	void isInFirstQuadrant() {
		assertThat(new CoordinatesXY(0, 0).isInFirstQuadrant()).isTrue();

		assertThat(new CoordinatesXY(1, 0).isInFirstQuadrant()).isTrue();
		assertThat(new CoordinatesXY(0, 1).isInFirstQuadrant()).isTrue();
		assertThat(new CoordinatesXY(1, 2).isInFirstQuadrant()).isTrue();

		assertThat(new CoordinatesXY(-1, 0).isInFirstQuadrant()).isFalse();
		assertThat(new CoordinatesXY(0, -1).isInFirstQuadrant()).isFalse();
		assertThat(new CoordinatesXY(-2, -1).isInFirstQuadrant()).isFalse();
	}

	@Test
	void isSmallerThen() {
		assertThat(new CoordinatesXY(0, 1).isSmallerThen(new CoordinatesXY(1, 2))).isTrue();

		assertThat(new CoordinatesXY(1, 2).isSmallerThen(new CoordinatesXY(1, 2))).isFalse();
		assertThat(new CoordinatesXY(1, 0).isSmallerThen(new CoordinatesXY(1, 2))).isFalse();
		assertThat(new CoordinatesXY(0, 2).isSmallerThen(new CoordinatesXY(1, 2))).isFalse();
	}

	@Test
	void getNeighbour() {
		assertThat(new CoordinatesXY(2, 5).getNeighbour(Direction.NORTH)).isEqualTo(new CoordinatesXY(2, 5 + 1));
		assertThat(new CoordinatesXY(2, 5).getNeighbour(Direction.EAST)).isEqualTo(new CoordinatesXY(2 + 1, 5));
		assertThat(new CoordinatesXY(2, 5).getNeighbour(Direction.SOUTH)).isEqualTo(new CoordinatesXY(2, 5 - 1));
		assertThat(new CoordinatesXY(2, 5).getNeighbour(Direction.WEST)).isEqualTo(new CoordinatesXY(2 - 1, 5));
	}

	@Test
	void getNeighbour_Null() {
		assertThatNullPointerException().isThrownBy(() -> new CoordinatesXY(2, 5).getNeighbour(null));
	}

	@Test
	void getCardinalNeighbours() {
		assertThat(new CoordinatesXY(2, 5).getCardinalNeighbours()).containsExactly(
				new CoordinatesXY(2, 5 + 1),
				new CoordinatesXY(2 + 1, 5),
				new CoordinatesXY(2, 5 - 1),
				new CoordinatesXY(2 - 1, 5)
		);

	}

	@Test
	void getAllNeighbours() {
		assertThat(new CoordinatesXY(2, 5).getAllNeighbours()).containsExactly(
				new CoordinatesXY(2, 5 + 1),
				new CoordinatesXY(2 + 1, 5 + 1),
				new CoordinatesXY(2 + 1, 5),
				new CoordinatesXY(2 + 1, 5 - 1),
				new CoordinatesXY(2, 5 - 1),
				new CoordinatesXY(2 - 1, 5 - 1),
				new CoordinatesXY(2 - 1, 5),
				new CoordinatesXY(2 - 1, 5 + 1)
		);

	}

	@Test
	void getSmallerCoordinatesWithDistanceToOrigin() {
		assertThat(new CoordinatesXY(3, 3).getSmallerCoordinatesWithDistanceToOrigin(0))
				.containsExactly(new CoordinatesXY(0, 0));
		assertThat(new CoordinatesXY(3, 3).getSmallerCoordinatesWithDistanceToOrigin(1))
				.containsExactly(new CoordinatesXY(0, 1), new CoordinatesXY(1, 0));

		assertThat(new CoordinatesXY(3, 3).getSmallerCoordinatesWithDistanceToOrigin(4))
				.containsExactly(
						new CoordinatesXY(1, 3),
						new CoordinatesXY(2, 2),
						new CoordinatesXY(3, 1)
				).doesNotContain(
				new CoordinatesXY(0, 4),
				new CoordinatesXY(4, 0)

		);


	}
}