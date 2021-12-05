package be.durvenproeven.aoc2021.day5;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleLineTest {

	private static final Coordinates COORDINATES_22 = new Coordinates(2, 2);
	private static final Coordinates COORDINATES_02 = new Coordinates(0, 2);
	private static final Coordinates COORDINATES_20 = new Coordinates(2, 0);
	private static final Coordinates COORDINATES_00 = new Coordinates(0, 0);

	@Test
	void createLineFollowingAxes() {
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_00)).isEmpty();
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_20)).isPresent();
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_02)).isPresent();
		assertThat(SimpleLine.create(COORDINATES_02, COORDINATES_20)).isEmpty();
	}

	@Test
	void getCoordinatesOnLine() {
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_20).get().getCoordinatesOnLine())
				.containsExactly(COORDINATES_20, new Coordinates(2, 1), COORDINATES_22);
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_02).get().getCoordinatesOnLine())
				.containsExactly(COORDINATES_02, new Coordinates(1, 2), COORDINATES_22);
	}
}