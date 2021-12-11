package be.durvenproeven.aoc2021;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

	@Test
	void reverse() {
		assertThat(Direction.NORTH.reverse()).isEqualTo(Direction.SOUTH);
		assertThat(Direction.SOUTH.reverse()).isEqualTo(Direction.NORTH);

		assertThat(Direction.EAST.reverse()).isEqualTo(Direction.WEST);
		assertThat(Direction.WEST.reverse()).isEqualTo(Direction.EAST);

		assertThat(Direction.NORTH_EAST.reverse()).isEqualTo(Direction.SOUTH_WEST);
		assertThat(Direction.SOUTH_WEST.reverse()).isEqualTo(Direction.NORTH_EAST);

		assertThat(Direction.NORTH_WEST.reverse()).isEqualTo(Direction.SOUTH_EAST);
		assertThat(Direction.SOUTH_EAST.reverse()).isEqualTo(Direction.NORTH_WEST);
	}
}