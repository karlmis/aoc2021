package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.CoordinatesXY;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleLineTest {

	private static final CoordinatesXY COORDINATES_22 = new CoordinatesXY(2, 2);
	private static final CoordinatesXY COORDINATES_02 = new CoordinatesXY(0, 2);
	private static final CoordinatesXY COORDINATES_20 = new CoordinatesXY(2, 0);
	private static final CoordinatesXY COORDINATES_00 = new CoordinatesXY(0, 0);

	@Test
	void createLineFollowingAxes() {
		assertThat(SimpleLine.create(COORDINATES_22, new CoordinatesXY(0, 1))).isEmpty();

		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_00)).isPresent();
		assertThat(SimpleLine.create(COORDINATES_00, COORDINATES_22)).isPresent();

		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_20)).isPresent();
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_02)).isPresent();

		assertThat(SimpleLine.create(COORDINATES_02, COORDINATES_20)).isPresent();
	}

	@Test
	void getCoordinatesOnLine() {
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_20)).get()
				.extracting(SimpleLine::getCoordinatesOnLine).asList()
				.containsExactly(COORDINATES_20, new CoordinatesXY(2, 1), COORDINATES_22);
		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_02)).get()
				.extracting(SimpleLine::getCoordinatesOnLine).asList()
				.containsExactly(COORDINATES_02, new CoordinatesXY(1, 2), COORDINATES_22);
	}

	@Test
	void getCoordinatesOnLine_Diagonal() {
		assertThat(SimpleLine.create(COORDINATES_00, COORDINATES_22)).get()
				.extracting(SimpleLine::getCoordinatesOnLine).asList()
				.containsExactly(COORDINATES_00, new CoordinatesXY(1, 1), COORDINATES_22);

		assertThat(SimpleLine.create(COORDINATES_22, COORDINATES_00)).get()
				.extracting(SimpleLine::getCoordinatesOnLine).asList()
				.containsExactly(COORDINATES_22, new CoordinatesXY(1, 1), COORDINATES_00);

		assertThat(SimpleLine.create(COORDINATES_02, COORDINATES_20)).get()
				.extracting(SimpleLine::getCoordinatesOnLine).asList()
				.containsExactly(COORDINATES_02, new CoordinatesXY(1, 1), COORDINATES_20);
	}
}