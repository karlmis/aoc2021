package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.day19.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CubeTest {

	@Test
	void overlaps() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.overlaps(new Cube(new Coordinates(6, 6, 6), new Coordinates(8, 8, 8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(5, 5, 5), new Coordinates(8, 8, 8)))).isTrue();

		assertThat(cube.overlaps(new Cube(new Coordinates(5, 5, 6), new Coordinates(8, 8, 8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(5, 6, 5), new Coordinates(8, 8, 8)))).isFalse();
		assertThat(cube.overlaps(new Cube(new Coordinates(6, 5, 5), new Coordinates(8, 8, 8)))).isFalse();
	}

	@Test
	void intersection() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.intersection(new Cube(new Coordinates(6, 6, 6), new Coordinates(8, 8, 8)))).isEmpty();
		assertThat(cube.intersection(new Cube(new Coordinates(4, 4, 4), new Coordinates(8, 8, 8)))).get()
				.isEqualTo(new Cube(new Coordinates(4, 4, 4), new Coordinates(5, 5, 5)));

		assertThat(cube.intersection(new Cube(new Coordinates(3, 5, 4), new Coordinates(8, 8, 8)))).get()
				.isEqualTo(new Cube(new Coordinates(3, 5, 4), new Coordinates(5, 5, 5)));
	}

	@Test
	void difference_NoOverlapping() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));

		assertThat(cube.difference(new Cube(new Coordinates(6, 6, 6), new Coordinates(8, 8, 8))))
				.containsExactly(cube);
	}

	@Test
	void difference() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));
		Cube other = new Cube(new Coordinates(4, 4, 4), new Coordinates(8, 8, 8));

		List<Cube> difference = cube.difference(other);
		assertThat(difference.stream().mapToLong(Cube::getSize).sum()).isEqualTo(cube.getSize() -
				new Cube(new Coordinates(4, 4, 4), new Coordinates(5, 5, 5)).getSize());
		assertThat(difference).allMatch(cube::contains);
		assertThat(difference).allMatch(dif -> other.intersection(dif).isEmpty());
		for (Cube cube1 : difference) {
			for (Cube cube2 : difference) {
				if (cube1 != cube2) {
					assertThat(cube1.intersection(cube2)).isEmpty();
				}
			}
		}

	}
	@Test
	void difference_Other() {
		Cube cube = new Cube(new Coordinates(1, 1, 1), new Coordinates(5, 5, 5));
		Cube other = new Cube(new Coordinates(3, 4, 5), new Coordinates(8, 8, 8));

		List<Cube> difference = cube.difference(other);
		assertThat(difference.stream().mapToLong(Cube::getSize).sum()).isEqualTo(cube.getSize() -
				new Cube(new Coordinates(3, 4, 5), new Coordinates(5, 5, 5)).getSize());
		assertThat(difference).allMatch(cube::contains);
		assertThat(difference).allMatch(dif -> other.intersection(dif).isEmpty());
		for (Cube cube1 : difference) {
			for (Cube cube2 : difference) {
				if (cube1 != cube2) {
					assertThat(cube1.intersection(cube2)).isEmpty();
				}
			}
		}

	}

}