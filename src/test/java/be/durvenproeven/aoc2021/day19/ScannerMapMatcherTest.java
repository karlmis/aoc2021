package be.durvenproeven.aoc2021.day19;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ScannerMapMatcherTest {

	@Test
	void match() {
		ScannerMap map1 = new ScannerMap(
				List.of(new Coordinates(404, -588, -901),
						new Coordinates(528, -643, 409),
						new Coordinates(-838, 591, 734),
						new Coordinates(390, -675, -793),
						new Coordinates(-537, -823, -458),
						new Coordinates(-485, -357, 347),
						new Coordinates(-345, -311, 381),
						new Coordinates(-661, -816, -575),
						new Coordinates(-876, 649, 763),
						new Coordinates(-618, -824, -621),
						new Coordinates(553, 345, -567),
						new Coordinates(474, 580, 667),
						new Coordinates(-447, -329, 318),
						new Coordinates(-584, 868, -557),
						new Coordinates(544, -627, -890),
						new Coordinates(564, 392, -477),
						new Coordinates(455, 729, 728),
						new Coordinates(-892, 524, 684),
						new Coordinates(-689, 845, -530),
						new Coordinates(423, -701, 434),
						new Coordinates(7, -33, -71),
						new Coordinates(630, 319, -379),
						new Coordinates(443, 580, 662),
						new Coordinates(-789, 900, -551),
						new Coordinates(459, -707, 401))
		);
		ScannerMap map2 = new ScannerMap(
				List.of(
						new Coordinates(686,422,578),
						new Coordinates(605,423,415),
						new Coordinates(515,917,-361),
						new Coordinates(-336,658,858),
						new Coordinates(95,138,22),
						new Coordinates(-476,619,847),
						new Coordinates(-340,-569,-846),
						new Coordinates(567,-361,727),
						new Coordinates(-460,603,-452),
						new Coordinates(669,-402,600),
						new Coordinates(729,430,532),
						new Coordinates(-500,-761,534),
						new Coordinates(-322,571,750),
						new Coordinates(-466,-666,-811),
						new Coordinates(-429,-592,574),
						new Coordinates(-355,545,-477),
						new Coordinates(703,-491,-529),
						new Coordinates(-328,-685,520),
						new Coordinates(413,935,-424),
						new Coordinates(-391,539,-444),
						new Coordinates(586,-435,557),
						new Coordinates(-364,-763,-893),
						new Coordinates(807,-499,-711),
						new Coordinates(755,-354,-619),
						new Coordinates(553,889,-390)
				));

		MatchResult match = ScannerMapMatcher.match(map1, map2, 12);
		assertThat(match.nr()).isEqualTo(12);
		assertThat(match.mutual()).containsExactlyInAnyOrder(
				new Coordinates(-618,-824,-621),
						new Coordinates( -537,-823,-458),
						new Coordinates( -447,-329,318),
						new Coordinates( 404,-588,-901),
						new Coordinates( 544,-627,-890),
						new Coordinates( 528,-643,409),
						new Coordinates( -661,-816,-575),
						new Coordinates( 390,-675,-793),
						new Coordinates( 423,-701,434),
						new Coordinates( -345,-311,381),
						new Coordinates( 459,-707,401),
						new Coordinates( -485,-357,347)
		);
		System.out.println(match.dif());

	}
}