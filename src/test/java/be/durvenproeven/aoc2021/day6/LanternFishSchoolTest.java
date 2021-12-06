package be.durvenproeven.aoc2021.day6;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class LanternFishSchoolTest {

	@Test
	void nextGeneration() {
		LanternFishSchool lanternFishSchool = new LanternFishSchool(List.of(3, 4, 3, 1, 2));
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(5);

		lanternFishSchool.nextGeneration();

		lanternFishSchool.nextGeneration();
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(6);

		lanternFishSchool.nextGeneration();
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(7);

		lanternFishSchool.nextGeneration();
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(9);
	}

	@Test
	void nextGeneration_SimpleExample() {
		LanternFishSchool lanternFishSchool = new LanternFishSchool(List.of(3, 4, 3, 1, 2));

		for (int i = 0; i < 18; i++) {
			lanternFishSchool.nextGeneration();
		}
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(26);

		for (int i = 18; i < 80; i++) {
			lanternFishSchool.nextGeneration();
		}
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(5934);


	}

	@Test
	void nextGeneration_RealExample() {
		String line = LineResolver.getStringStreamOfFile("day6.txt").findFirst().orElseThrow();
		List<Integer> integers = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();

		LanternFishSchool lanternFishSchool = new LanternFishSchool(integers);

		for (int i = 0; i < 80; i++) {
			lanternFishSchool.nextGeneration();
		}
		assertThat(lanternFishSchool.getNrOfFish()).isEqualTo(374994);
	}
}