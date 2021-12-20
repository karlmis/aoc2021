package be.durvenproeven.aoc2021.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CoordinatesTest {

	@Test
	void getBisections() {
		assertThat(Coordinates.getBisections()).containsExactlyInAnyOrder(
			new Coordinates(1,1,1),
			new Coordinates(1,1,-1),
			new Coordinates(1,-1,1),
			new Coordinates(1,-1,-1),
			new Coordinates(-1,1,1),
			new Coordinates(-1,1,-1),
			new Coordinates(-1,-1,1),
			new Coordinates(-1,-1,-1)
		);
	}

}