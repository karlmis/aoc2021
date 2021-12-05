package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HydroThermalVentsTest {

	@Test
	void getNrOfOverlaps_SimpleExample() {
		List<LineFollowingAxes> lines = Stream.of(
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(0, 9), new Coordinates(5, 9)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(8, 0), new Coordinates(0, 8)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(9, 4), new Coordinates(3, 4)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(2, 2), new Coordinates(2, 1)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(7, 0), new Coordinates(7, 4)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(6, 4), new Coordinates(2, 0)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(0, 9), new Coordinates(2, 9)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(3, 4), new Coordinates(1, 4)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(0, 0), new Coordinates(8, 8)),
				LineFollowingAxes.createLineFollowingAxes(new Coordinates(5, 5), new Coordinates(8, 2))
		).filter(Optional::isPresent).map(Optional::get).toList();

		HydroThermalVents hydroThermalVents = new HydroThermalVents(lines);

		assertThat(hydroThermalVents.getNrOfOverlaps()).isEqualTo(5);
	}

	@Test
	void getNrOfOverlaps_RealExample() {
		List<LineFollowingAxes> lineFollowingAxes = LineResolver.getStringStreamOfFile("day5.txt")
				.map(this::toOptionalLineFollowingAxes)
				.filter(Optional::isPresent)
				.map(Optional::get)
				.toList();
		HydroThermalVents hydroThermalVents = new HydroThermalVents(lineFollowingAxes);

		assertThat(hydroThermalVents.getNrOfOverlaps()).isEqualTo(4873);
	}

	private Optional<LineFollowingAxes> toOptionalLineFollowingAxes(String s) {
		//160,751 -> 787,124
		String[] coordinatesParts = s.split("->");
		return LineFollowingAxes.createLineFollowingAxes(getCoordinates(coordinatesParts[0]), getCoordinates(coordinatesParts[1]));
	}

	private Coordinates getCoordinates(String coordinatesPart) {
		List<Integer> integersFirst = Arrays.stream(coordinatesPart.trim().split(","))
				.map(Integer::parseInt)
				.toList();
		return new Coordinates(integersFirst.get(0), integersFirst.get(1));
	}
}