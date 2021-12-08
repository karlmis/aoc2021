package be.durvenproeven.aoc2021.day7;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CrabCalculatorTest {

	@Test
	void getLeastFuelPosition() {
		CrabSwarm crabSwarm = new CrabSwarm(List.of(16, 1, 2, 0, 4, 2, 7, 1, 2, 14));

		CrabCalculator crabCalculator = new CrabCalculator(crabSwarm);
		assertThat(crabCalculator.getLeastFuelPosition()).isEqualTo(2);
	}

	@Test
	void getLeastFuelPosition_RealInput() {
		String line = LineResolver.getStringStreamOfFile("day7.txt").findFirst().orElseThrow();
		List<Integer> integers = Arrays.stream(line.split(","))
				.map(Integer::parseInt)
				.toList();
		CrabSwarm crabSwarm = new CrabSwarm(integers);

		CrabCalculator crabCalculator = new CrabCalculator(crabSwarm);
		assertThat(crabCalculator.getLeastFuelPosition()).isEqualTo(337);
		assertThat(crabSwarm.getDistance(337)).isEqualTo(342641);
	}

	@Test
	void getLeastFuelPart2Position_RealInput() {
		String line = LineResolver.getStringStreamOfFile("day7.txt").findFirst().orElseThrow();
		List<Integer> integers = Arrays.stream(line.split(","))
				.map(Integer::parseInt)
				.toList();
		CrabSwarm crabSwarm = new CrabSwarm(integers);

		CrabCalculator crabCalculator = new CrabCalculator(crabSwarm);
		assertThat(crabCalculator.getLeastFuelPart2Position()).isEqualTo(470);
		assertThat(crabSwarm.getSecondDistance(470)).isEqualTo(93006301);
	}
}