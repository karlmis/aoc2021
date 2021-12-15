package be.durvenproeven.aoc2021;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class GridTest {

	@Test
	void increase() {
		Grid grid = new Grid(List.of("01", "23"));

		Grid increased_Horizontal = grid.increase(2, 1);
		assertThat(increased_Horizontal.getMaxCoordinates()).isEqualTo(new Coordinates(3, 1));
		assertThat(increased_Horizontal.getRiskLevels()).containsExactly(
				List.of(0, 1, 0, 1),
				List.of(2, 3, 2, 3)
		);


		Grid increased_Vertical = grid.increase(1, 2);
		assertThat(increased_Vertical.getMaxCoordinates()).isEqualTo(new Coordinates(1, 3));
		assertThat(increased_Vertical.getRiskLevels()).containsExactly(
				List.of(0, 1),
				List.of(2, 3),
				List.of(0, 1),
				List.of(2, 3)
		);

	}

	@SuppressWarnings("PointlessArithmeticExpression")
	@Test
	void increase_unaryOperator() {
		Grid grid = new Grid(List.of("01", "23"));

		Grid increased_Horizontal = grid.increase(3, 1, i -> i + 1);
		assertThat(increased_Horizontal.getMaxCoordinates()).isEqualTo(new Coordinates(5, 1));
		assertThat(increased_Horizontal.getRiskLevels()).containsExactly(
				List.of(0, 1, 0 + 1, 1 + 1, 0 + 2, 1 + 2),
				List.of(2, 3, 2 + 1, 3 + 1, 2 + 2, 3 + 2)
		);


		Grid increased_Vertical = grid.increase(1, 3, i -> i + 1);
		assertThat(increased_Vertical.getMaxCoordinates()).isEqualTo(new Coordinates(1, 5));
		assertThat(increased_Vertical.getRiskLevels()).containsExactly(
				List.of(0, 1),
				List.of(2, 3),
				List.of(0 + 1, 1 + 1),
				List.of(2 + 1, 3 + 1),
				List.of(0 + 2, 1 + 2),
				List.of(2 + 2, 3 + 2)
		);

		Grid increased_Both = grid.increase(2, 2, i -> i + 1);
		assertThat(increased_Both.getMaxCoordinates()).isEqualTo(new Coordinates(3, 3));
		assertThat(increased_Both.getRiskLevels()).containsExactly(
				List.of(0, 1, 0 + 1, 1 + 1),
				List.of(2, 3, 2 + 1, 3 + 1),
				List.of(0 + 1, 1 + 1, 0 + 2, 1 + 2),
				List.of(2 + 1, 3 + 1, 2 + 2, 3 + 2)
		);


	}
}