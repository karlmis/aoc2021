package be.durvenproeven.aoc2021.day19;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScannerMapTest {

	private static final Coordinates COORDINATES_A = new Coordinates(0, 4, 7);
	private static final Coordinates COORDINATES_B = new Coordinates(1, 2, 2);
	private static final Coordinates COORDINATES1 = new Coordinates(2, 3, 6);
	private static final Coordinates COORDINATES_C = COORDINATES1;
	private static final Coordinates DIRECTION = new Coordinates(-1, 1, -1);

	@Test
	void getCoordinatesWithOrientation_X() {
		ScannerMap scannerMap = new ScannerMap(List.of(
						new Coordinates(0+1, 0, 0),
						new Coordinates(1+1, 2, 0),
						new Coordinates(2+1, 2, 0))
		);

		assertThat(scannerMap.getCoordinatesWithOrientation(new Coordinates(-1, 1, 1))).containsExactly(
				new Coordinates(0, 2, 0),
				new Coordinates(1, 2, 0),
				new Coordinates(2, 0, 0)
		);

	}

	@Test
	void getCoordinatesWithOrientation_Y() {
		ScannerMap scannerMap = new ScannerMap(List.of(
						new Coordinates(0, 0+2, 0),
						new Coordinates(1, 2+2, 0),
						new Coordinates(2, 2+2, 0))
		);

		assertThat(scannerMap.getCoordinatesWithOrientation(new Coordinates(1, -1, 1))).containsExactly(
				new Coordinates(0, 2, 0),
				new Coordinates(1, 0, 0),
				new Coordinates(2, 0, 0)
		);

	}

	@Test
	void getCoordinatesWithOrientation_Z_NoY() {
		ScannerMap scannerMap = new ScannerMap(List.of(
				new Coordinates(0, 0, 0+3),
				new Coordinates(1, 0, 2+3),
				new Coordinates(2, 0, 2+3))
		);

		assertThat(scannerMap.getCoordinatesWithOrientation(new Coordinates(1, 1, -1))).containsExactly(
				new Coordinates(0, 0, 2),
				new Coordinates(1, 0, 0),
				new Coordinates(2, 0, 0)
		);

	}

	@Test
	void getMatching() {
		ScannerMap scannerMap = new ScannerMap(List.of(COORDINATES_A, COORDINATES_B, COORDINATES_C)
		);

		MatchResult matching = scannerMap.getMatching(
				List.of(
						COORDINATES_A.plus(DIRECTION),
						COORDINATES_B.plus(DIRECTION),
						COORDINATES_C.plus(DIRECTION)),
				3
		);
		assertThat(matching)
				.isEqualTo(new MatchResult(3, DIRECTION.reverse(), List.of(COORDINATES_A, COORDINATES_B, COORDINATES_C)));
	}

	@Disabled
	@Test
	void getNrOfBeacons_GivenInput() {
		List<ScannerMap> scannerMaps = LineResolver.groupToList("day19giveninput.txt", this::toScannerMap);
		int nrOfBeacons = ScannerMapMatcher.getNrOfBeacons(scannerMaps, 12);

		assertThat(nrOfBeacons).isEqualTo(79);
	}

	private ScannerMap toScannerMap(List<String> l) {
		List<Coordinates> coordinates = l.subList(1, l.size()).stream()
				.map(this::toCoordinates)
				.toList();
		return new ScannerMap(coordinates);
	}

	private Coordinates toCoordinates(String s) {
		List<Integer> integers = Arrays.stream(s.split(","))
				.map(Integer::parseInt).toList();
		return new Coordinates(integers.get(0), integers.get(1), integers.get(2));
	}
}