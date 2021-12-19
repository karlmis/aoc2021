package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.LineResolver;
import be.durvenproeven.aoc2021.CoordinatesXY;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HydroThermalVentsTest {
	private static final Predicate<SimpleLine> ONLY_HORIZONTAL_OR_VERTICAL =
			sl -> List.of(Direction.HORIZONTAL, Direction.VERTICAL).contains(sl.direction());

	@Test
	void getNrOfOverlaps_SimpleExample() {
		List<SimpleLine> lines = Stream.of(
				SimpleLine.create(new CoordinatesXY(0, 9), new CoordinatesXY(5, 9)),
				SimpleLine.create(new CoordinatesXY(8, 0), new CoordinatesXY(0, 8)),
				SimpleLine.create(new CoordinatesXY(9, 4), new CoordinatesXY(3, 4)),
				SimpleLine.create(new CoordinatesXY(2, 2), new CoordinatesXY(2, 1)),
				SimpleLine.create(new CoordinatesXY(7, 0), new CoordinatesXY(7, 4)),
				SimpleLine.create(new CoordinatesXY(6, 4), new CoordinatesXY(2, 0)),
				SimpleLine.create(new CoordinatesXY(0, 9), new CoordinatesXY(2, 9)),
				SimpleLine.create(new CoordinatesXY(3, 4), new CoordinatesXY(1, 4)),
				SimpleLine.create(new CoordinatesXY(0, 0), new CoordinatesXY(8, 8)),
				SimpleLine.create(new CoordinatesXY(5, 5), new CoordinatesXY(8, 2))
		)
				.flatMap(Optional::stream)
				.toList();

		HydroThermalVents hydroThermalVents = new HydroThermalVents(lines);

		assertThat(hydroThermalVents.getNrOfOverlaps(ONLY_HORIZONTAL_OR_VERTICAL)).isEqualTo(5);
	}

	@Test
	void getNrOfOverlaps_RealExample() {
		List<SimpleLine> simpleLineFollowingAxes = LineResolver.getStringStreamOfFile("day5.txt")
				.map(this::toOptionalLineFollowingAxes)
				.flatMap(Optional::stream)
				.toList();
		HydroThermalVents hydroThermalVents = new HydroThermalVents(simpleLineFollowingAxes);

		assertThat(hydroThermalVents.getNrOfOverlaps(ONLY_HORIZONTAL_OR_VERTICAL)).isEqualTo(4873);
	}

	@Test
	void getNrOfOverlaps_Part2_SimpleExample() {
		List<SimpleLine> lines = Stream.of(
				SimpleLine.create(new CoordinatesXY(0, 9), new CoordinatesXY(5, 9)),
				SimpleLine.create(new CoordinatesXY(8, 0), new CoordinatesXY(0, 8)),
				SimpleLine.create(new CoordinatesXY(9, 4), new CoordinatesXY(3, 4)),
				SimpleLine.create(new CoordinatesXY(2, 2), new CoordinatesXY(2, 1)),
				SimpleLine.create(new CoordinatesXY(7, 0), new CoordinatesXY(7, 4)),
				SimpleLine.create(new CoordinatesXY(6, 4), new CoordinatesXY(2, 0)),
				SimpleLine.create(new CoordinatesXY(0, 9), new CoordinatesXY(2, 9)),
				SimpleLine.create(new CoordinatesXY(3, 4), new CoordinatesXY(1, 4)),
				SimpleLine.create(new CoordinatesXY(0, 0), new CoordinatesXY(8, 8)),
				SimpleLine.create(new CoordinatesXY(5, 5), new CoordinatesXY(8, 2))
		).flatMap(Optional::stream)
				.toList();

		HydroThermalVents hydroThermalVents = new HydroThermalVents(lines);

		assertThat(hydroThermalVents.getNrOfOverlaps()).isEqualTo(12);
	}

	@Test
	void getNrOfOverlaps_Part2_RealExample() {
		List<SimpleLine> simpleLineFollowingAxes = LineResolver.getStringStreamOfFile("day5.txt")
				.map(this::toOptionalLineFollowingAxes)
				.flatMap(Optional::stream)
				.toList();
		HydroThermalVents hydroThermalVents = new HydroThermalVents(simpleLineFollowingAxes);

		assertThat(hydroThermalVents.getNrOfOverlaps()).isEqualTo(19472);
	}


	private Optional<SimpleLine> toOptionalLineFollowingAxes(String s) {
		//160,751 -> 787,124
		String[] coordinatesParts = s.split("->");
		return SimpleLine.create(getCoordinates(coordinatesParts[0]), getCoordinates(coordinatesParts[1]));
	}

	private CoordinatesXY getCoordinates(String coordinatesPart) {
		List<Integer> integersFirst = Arrays.stream(coordinatesPart.trim().split(","))
				.map(Integer::parseInt)
				.toList();
		return new CoordinatesXY(integersFirst.get(0), integersFirst.get(1));
	}
}