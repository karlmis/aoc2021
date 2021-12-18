package be.durvenproeven.aoc2021.day17;

import be.durvenproeven.aoc2021.Coordinates;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TrickShotTest {

	@Test
	void getLaunchCoordinates_MaximalHeights() {
		List<Coordinates> withMinimal4 =
				new TrickShot(new Coordinates(20, -10), new Coordinates(30, -5)).getLaunchCoordinates();
		assertThat(withMinimal4).contains(
				new Coordinates(6, 4),
				new Coordinates(6, 5),
				new Coordinates(6, 6),
				new Coordinates(6, 7),
				new Coordinates(6, 8),
				new Coordinates(6, 9),
				new Coordinates(7, 4),
				new Coordinates(7, 5),
				new Coordinates(7, 6),
				new Coordinates(7, 7),
				new Coordinates(7, 8),
				new Coordinates(7, 9)
		);

	}
	@Test
	void getLaunchCoordinates_MaximalHeights_() {
		List<Coordinates> withMinimal4 =
				new TrickShot(new Coordinates(20, -10), new Coordinates(30, -5)).getLaunchCoordinates();
		assertThat(withMinimal4).contains(
				new Coordinates(6,1),
				new Coordinates(6,2),
				new Coordinates(6,3),
			//	new Coordinates(7,1),
				new Coordinates(7,2),
				new Coordinates(7,3)
		);


	}
}