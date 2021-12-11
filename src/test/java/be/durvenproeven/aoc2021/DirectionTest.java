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
	}
}