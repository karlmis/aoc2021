package be.durvenproeven.aoc2021.day17;

import be.durvenproeven.aoc2021.CoordinatesXY;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrickShotTest {

	@Test
	void getLaunchCoordinates_MaximalHeights() {
		List<CoordinatesXY> withMinimal4 =
				new TrickShot(new CoordinatesXY(20, -10), new CoordinatesXY(30, -5)).getLaunchCoordinates();
		assertThat(withMinimal4).contains(
				new CoordinatesXY(6, 4),
				new CoordinatesXY(6, 5),
				new CoordinatesXY(6, 6),
				new CoordinatesXY(6, 7),
				new CoordinatesXY(6, 8),
				new CoordinatesXY(6, 9),
				new CoordinatesXY(7, 4),
				new CoordinatesXY(7, 5),
				new CoordinatesXY(7, 6),
				new CoordinatesXY(7, 7),
				new CoordinatesXY(7, 8),
				new CoordinatesXY(7, 9)
		);

	}
	@Test
	void getLaunchCoordinates_MaximalHeights_() {
		List<CoordinatesXY> withMinimal4 =
				new TrickShot(new CoordinatesXY(20, -10), new CoordinatesXY(30, -5)).getLaunchCoordinates();
		assertThat(withMinimal4).contains(
				new CoordinatesXY(6,1),
				new CoordinatesXY(6,2),
				new CoordinatesXY(6,3),
			//	new Coordinates(7,1),
				new CoordinatesXY(7,2),
				new CoordinatesXY(7,3)
		);


	}
}