package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.day19.Coordinates;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CubeTest {

	@Test
	void overlaps() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.overlaps(new Cube(new Coordinates(6,6,6), new Coordinates(8,8,8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(5,5,5), new Coordinates(8,8,8)))).isTrue();

		assertThat(cube.overlaps(new Cube(new Coordinates(5,5,6), new Coordinates(8,8,8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(5,6,5), new Coordinates(8,8,8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(6,5,5), new Coordinates(8,8,8)))).isFalse();
	}

	@Test
	void intersection() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.intersection(new Cube(new Coordinates(6,6,6), new Coordinates(8,8,8)))).isEmpty();
		assertThat(cube.intersection(new Cube(new Coordinates(4,4,4), new Coordinates(8,8,8)))).get()
				.isEqualTo(new Cube(new Coordinates(4,4,4), new Coordinates(5,5,5)));

		assertThat(cube.intersection(new Cube(new Coordinates(3,5,4), new Coordinates(8,8,8)))).get()
				.isEqualTo(new Cube(new Coordinates(3,5,4), new Coordinates(5,5,5)));
	}

	@Disabled
	@Test
	void difference() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.difference(new Cube(new Coordinates(6,6,6), new Coordinates(8,8,8)))).isEmpty();
		assertThat(cube.difference(new Cube(new Coordinates(4,4,4), new Coordinates(8,8,8)))).containsExactlyInAnyOrder(
				new Cube(new Coordinates(1,1,1), new Coordinates(3,3,5)),
		//TODO		new Cube(new Coordinates(4,5,1), new Coordinates(5,5,5)),
				new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5)),
				new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5)),
				new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5)),
				new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5)),
				new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5))
		);

		//assertThat(cube.difference(new Cube(new Coordinates(3,5,4), new Coordinates(8,8,8)))).containsExactlyInAnyOrder(
		//		new Cube(new Coordinates(1,1,1), new Coordinates(5,5,5)));
	}


}