package be.durvenproeven.aoc2021.day9;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesTest {
	@Test
	void isInFirstQuadrant() {
		assertThat(new Coordinates(0,0).isInFirstQuadrant()).isTrue();

		assertThat(new Coordinates(1,0).isInFirstQuadrant()).isTrue();
		assertThat(new Coordinates(0,1).isInFirstQuadrant()).isTrue();
		assertThat(new Coordinates(1,2).isInFirstQuadrant()).isTrue();

		assertThat(new Coordinates(-1,0).isInFirstQuadrant()).isFalse();
		assertThat(new Coordinates(0,-1).isInFirstQuadrant()).isFalse();
		assertThat(new Coordinates(-2,-1).isInFirstQuadrant()).isFalse();
	}

	@Test
	void isSmallerThen() {
		assertThat(new Coordinates(0,1).isSmallerThen(new Coordinates(1,2))).isTrue();

		assertThat(new Coordinates(1,2).isSmallerThen(new Coordinates(1,2))).isFalse();
		assertThat(new Coordinates(1,0).isSmallerThen(new Coordinates(1,2))).isFalse();
		assertThat(new Coordinates(0,2).isSmallerThen(new Coordinates(1,2))).isFalse();

	}
}