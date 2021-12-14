package be.durvenproeven.aoc2021.day13;

import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransparentOrigamiTest {

	@Test
	void foldHorizontal() {
		TransparentOrigami transparentOrigami = new TransparentOrigami(List.of(
				new Coordinates(6, 10),
				new Coordinates(0, 14),
				new Coordinates(9, 10),
				new Coordinates(0, 3),
				new Coordinates(10, 4),
				new Coordinates(4, 11),
				new Coordinates(6, 0),
				new Coordinates(6, 12),
				new Coordinates(4, 1),
				new Coordinates(0, 13),
				new Coordinates(10, 12),
				new Coordinates(3, 4),
				new Coordinates(3, 0),
				new Coordinates(8, 4),
				new Coordinates(1, 10),
				new Coordinates(2, 14),
				new Coordinates(8, 10),
				new Coordinates(9, 0)
		));

		TransparentOrigami afterHorizontalFold = transparentOrigami.foldHorizontal(7);
		assertThat(afterHorizontalFold.getVisibleDots()).isEqualTo(17);
		//afterHorizontalFold.toPrettyString();

		TransparentOrigami afterVerticalFold = afterHorizontalFold.foldVertical(5);
		assertThat(afterVerticalFold.getVisibleDots()).isEqualTo(16);
		//afterVerticalFold.toPrettyString();

	}

	@Test
	void folding_FirstInput() {
		List<Coordinates> coordinatesList = LineResolver.getStringStreamOfFile("day13origami.txt")
				.map(s -> s.split(","))
				.map(s -> new Coordinates(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
				.toList();
		TransparentOrigami transparentOrigami = new TransparentOrigami(coordinatesList);

		TransparentOrigami afterHorizontalFold = transparentOrigami.foldVertical(655);
		assertThat(afterHorizontalFold.getVisibleDots()).isEqualTo(689);
	}

	@Test
	void folding_SecondPart() {
		List<Coordinates> coordinatesList = LineResolver.getStringStreamOfFile("day13origami.txt")
				.map(s -> s.split(","))
				.map(s -> new Coordinates(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
				.toList();
		TransparentOrigami transparentOrigami = new TransparentOrigami(coordinatesList);

		TransparentOrigami afterFolding = transparentOrigami
				.foldVertical(655)
				.foldHorizontal(447)
				.foldVertical(327)
				.foldHorizontal(223)
				.foldVertical(163)
				.foldHorizontal(111)
				.foldVertical(81)
				.foldHorizontal(55)
				.foldVertical(40)
				.foldHorizontal(27)
				.foldHorizontal(13)
				.foldHorizontal(6);
		//RLBCJGLU

		afterFolding.toPrettyString();
	}
}