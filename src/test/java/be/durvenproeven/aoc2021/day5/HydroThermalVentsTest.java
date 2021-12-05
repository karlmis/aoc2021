package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class HydroThermalVentsTest {
	private static final Predicate<SimpleLine> ONLY_HORIZONTAL_OR_VERTICAL =
			sl -> List.of(Direction.HORIZONTAL, Direction.VERTICAL).contains(sl.getDirection());

	@Test
	void getNrOfOverlaps_SimpleExample() {
		List<SimpleLine> lines = Stream.of(
				SimpleLine.create(new Coordinates(0, 9), new Coordinates(5, 9)),
				SimpleLine.create(new Coordinates(8, 0), new Coordinates(0, 8)),
				SimpleLine.create(new Coordinates(9, 4), new Coordinates(3, 4)),
				SimpleLine.create(new Coordinates(2, 2), new Coordinates(2, 1)),
				SimpleLine.create(new Coordinates(7, 0), new Coordinates(7, 4)),
				SimpleLine.create(new Coordinates(6, 4), new Coordinates(2, 0)),
				SimpleLine.create(new Coordinates(0, 9), new Coordinates(2, 9)),
				SimpleLine.create(new Coordinates(3, 4), new Coordinates(1, 4)),
				SimpleLine.create(new Coordinates(0, 0), new Coordinates(8, 8)),
				SimpleLine.create(new Coordinates(5, 5), new Coordinates(8, 2))
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
				SimpleLine.create(new Coordinates(0, 9), new Coordinates(5, 9)),
				SimpleLine.create(new Coordinates(8, 0), new Coordinates(0, 8)),
				SimpleLine.create(new Coordinates(9, 4), new Coordinates(3, 4)),
				SimpleLine.create(new Coordinates(2, 2), new Coordinates(2, 1)),
				SimpleLine.create(new Coordinates(7, 0), new Coordinates(7, 4)),
				SimpleLine.create(new Coordinates(6, 4), new Coordinates(2, 0)),
				SimpleLine.create(new Coordinates(0, 9), new Coordinates(2, 9)),
				SimpleLine.create(new Coordinates(3, 4), new Coordinates(1, 4)),
				SimpleLine.create(new Coordinates(0, 0), new Coordinates(8, 8)),
				SimpleLine.create(new Coordinates(5, 5), new Coordinates(8, 2))
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

	private Coordinates getCoordinates(String coordinatesPart) {
		List<Integer> integersFirst = Arrays.stream(coordinatesPart.trim().split(","))
				.map(Integer::parseInt)
				.toList();
		return new Coordinates(integersFirst.get(0), integersFirst.get(1));
	}
}