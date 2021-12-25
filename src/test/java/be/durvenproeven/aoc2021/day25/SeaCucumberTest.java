package be.durvenproeven.aoc2021.day25;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SeaCucumberTest {

	@Test
	void move_OneLIne() {
		SeaCucumber seaCucumber = SeaCucumber.fromStrings(List.of("...>>>>>..."));

		assertThat(seaCucumber.move())
				.isEqualTo(SeaCucumber.fromStrings(List.of("...>>>>.>..")));

		assertThat(seaCucumber.move().move())
				.isEqualTo(SeaCucumber.fromStrings(List.of("...>>>.>.>.")));
	}

	@Test
	void move() {
		SeaCucumber seaCucumber = SeaCucumber.fromStrings(List.of(
				"..........",
				".>v....v..",
				".......>..",
				".........."));

		assertThat(seaCucumber.move()).isEqualTo(
				SeaCucumber.fromStrings(List.of(
						"..........",
						".>........",
						"..v....v>.",
						"..........")));

	}

	@Test
	void move_GivenExample() {
		SeaCucumber seaCucumber = SeaCucumber.fromStrings(List.of(
				"...>...",
				".......",
				"......>",
				"v.....>",
				"......>",
				".......",
				"..vvv.."
		));

		assertThat(seaCucumber.move()).isEqualTo(
				SeaCucumber.fromStrings(List.of(
						"..vv>..",
						".......",
						">......",
						"v.....>",
						">......",
						".......",
						"....v.."))
		);
		assertThat(seaCucumber.move().move()).isEqualTo(
				SeaCucumber.fromStrings(List.of(
						"....v>.",
						"..vv...",
						".>.....",
						"......>",
						"v>.....",
						".......",
						"......."))
		);
		assertThat(seaCucumber.move().move().move()).isEqualTo(
				SeaCucumber.fromStrings(List.of(
						"......>",
						"..v.v..",
						"..>v...",
						">......",
						"..>....",
						"v......",
						"......."))
		);
		assertThat(seaCucumber.move().move().move().move()).isEqualTo(
				SeaCucumber.fromStrings(List.of(
						">......",
						"..v....",
						"..>.v..",
						".>.v...",
						"...>...",
						".......",
						"v......"))
		);
	}

	@Test
	void moveAsLongAsYouCan() {
		SeaCucumber seaCucumber = SeaCucumber.fromStrings(List.of(
				"v...>>.vv>",
				".vv>>.vv..",
				">>.>v>...v",
				">>v>>.>.v.",
				"v>v.vv.v..",
				">.>>..v...",
				".vv..>.>v.",
				"v.v..>>v.v",
				"....v..v.>"));

		assertThat(SeaCucumber.moveAsLongAsYouCan(seaCucumber)).isEqualTo(58);
	}

	@Test
	void moveAsLongAsYouCan_RealExample() {
		List<String> strings = LineResolver.getStringStreamOfFile("day25seacucumbers.txt")
				.toList();

		SeaCucumber seaCucumber = SeaCucumber.fromStrings(strings);

		assertThat(SeaCucumber.moveAsLongAsYouCan(seaCucumber)).isEqualTo(458);
	}
}