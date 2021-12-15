package be.durvenproeven.aoc2021.day12;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CavesPathFinderTest {

	@Test
	void getPaths_SmallCavesOnce() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("start-A"),
				new CavesConnection("start-b"),
				new CavesConnection("A-c"),
				new CavesConnection("A-b"),
				new CavesConnection("b-d"),
				new CavesConnection("A-end"),
				new CavesConnection("b-end")
		), SmallCaves::contains);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(10);
	}

	@Test
	void getPaths_SmallCavesOnce_SecondGivenExample() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("dc-end"),
				new CavesConnection("HN-start"),
				new CavesConnection("start-kj"),
				new CavesConnection("dc-start"),
				new CavesConnection("dc-HN"),
				new CavesConnection("LN-dc"),
				new CavesConnection("HN-end"),
				new CavesConnection("kj-sa"),
				new CavesConnection("kj-HN"),
				new CavesConnection("kj-dc")
		), SmallCaves::contains);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(19);
	}

	@Test
	void getPaths_SmallCavesOnce_ThirdGivenExample() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("fs-end"),
				new CavesConnection("he-DX"),
				new CavesConnection("fs-he"),
				new CavesConnection("start-DX"),
				new CavesConnection("pj-DX"),
				new CavesConnection("end-zg"),
				new CavesConnection("zg-sl"),
				new CavesConnection("zg-pj"),
				new CavesConnection("pj-he"),
				new CavesConnection("RW-he"),
				new CavesConnection("fs-DX"),
				new CavesConnection("pj-RW"),
				new CavesConnection("zg-RW"),
				new CavesConnection("start-pj"),
				new CavesConnection("he-WI"),
				new CavesConnection("zg-he"),
				new CavesConnection("pj-fs"),
				new CavesConnection("start-RW")
		), SmallCaves::contains);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(226);
	}

	@Test
	void getPaths_SmallCavesOnce_RealExample() {
		List<CavesConnection> cavesConnections = LineResolver.getStringStreamOfFile("day12caves.txt")
				.map(CavesConnection::new)
				.toList();

		CavesPathFinder cavesPathFinder = new CavesPathFinder(cavesConnections, SmallCaves::contains);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(4186);
	}

	@Test
	void getPaths() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("start-A"),
				new CavesConnection("start-b"),
				new CavesConnection("A-c"),
				new CavesConnection("A-b"),
				new CavesConnection("b-d"),
				new CavesConnection("A-end"),
				new CavesConnection("b-end")
		), this::allowedOnceInASmallCaveExceptOnce2Times);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(36);
	}

	@Test
	void getPaths_SecondGivenExample() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("dc-end"),
				new CavesConnection("HN-start"),
				new CavesConnection("start-kj"),
				new CavesConnection("dc-start"),
				new CavesConnection("dc-HN"),
				new CavesConnection("LN-dc"),
				new CavesConnection("HN-end"),
				new CavesConnection("kj-sa"),
				new CavesConnection("kj-HN"),
				new CavesConnection("kj-dc")
		), this::allowedOnceInASmallCaveExceptOnce2Times);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(103);
	}

	@Test
	void getPaths_ThirdGivenExample() {
		CavesPathFinder cavesPathFinder = new CavesPathFinder(List.of(
				new CavesConnection("fs-end"),
				new CavesConnection("he-DX"),
				new CavesConnection("fs-he"),
				new CavesConnection("start-DX"),
				new CavesConnection("pj-DX"),
				new CavesConnection("end-zg"),
				new CavesConnection("zg-sl"),
				new CavesConnection("zg-pj"),
				new CavesConnection("pj-he"),
				new CavesConnection("RW-he"),
				new CavesConnection("fs-DX"),
				new CavesConnection("pj-RW"),
				new CavesConnection("zg-RW"),
				new CavesConnection("start-pj"),
				new CavesConnection("he-WI"),
				new CavesConnection("zg-he"),
				new CavesConnection("pj-fs"),
				new CavesConnection("start-RW")
		), this::allowedOnceInASmallCaveExceptOnce2Times);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(3509);
	}

	@Test
	void getPaths_RealExample() {
		List<CavesConnection> cavesConnections = LineResolver.getStringStreamOfFile("day12caves.txt")
				.map(CavesConnection::new)
				.toList();

		CavesPathFinder cavesPathFinder = new CavesPathFinder(cavesConnections, this::allowedOnceInASmallCaveExceptOnce2Times);

		assertThat(cavesPathFinder.getPaths("start", "end")).hasSize(92111);
	}


	private boolean allowedOnceInASmallCaveExceptOnce2Times(SmallCaves sc, String s) {
		if (sc.getNrOfOccurences(s) == 2) {
			return true;
		}
		return sc.getNrOfOccurences(s) == 1 && sc.getMaximumNrOfOccurences() == 2;
	}
}