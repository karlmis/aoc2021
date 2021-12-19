package be.durvenproeven.aoc2021.day13;

import be.durvenproeven.aoc2021.CoordinatesXY;
import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class TransparentOrigamiTest {

	@Test
	void foldHorizontal() {
		TransparentOrigami transparentOrigami = new TransparentOrigami(List.of(
				new CoordinatesXY(6, 10),
				new CoordinatesXY(0, 14),
				new CoordinatesXY(9, 10),
				new CoordinatesXY(0, 3),
				new CoordinatesXY(10, 4),
				new CoordinatesXY(4, 11),
				new CoordinatesXY(6, 0),
				new CoordinatesXY(6, 12),
				new CoordinatesXY(4, 1),
				new CoordinatesXY(0, 13),
				new CoordinatesXY(10, 12),
				new CoordinatesXY(3, 4),
				new CoordinatesXY(3, 0),
				new CoordinatesXY(8, 4),
				new CoordinatesXY(1, 10),
				new CoordinatesXY(2, 14),
				new CoordinatesXY(8, 10),
				new CoordinatesXY(9, 0)
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
		List<CoordinatesXY> coordinatesList = LineResolver.getStringStreamOfFile("day13origami.txt")
				.map(s -> s.split(","))
				.map(s -> new CoordinatesXY(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
				.toList();
		TransparentOrigami transparentOrigami = new TransparentOrigami(coordinatesList);

		TransparentOrigami afterHorizontalFold = transparentOrigami.foldVertical(655);
		assertThat(afterHorizontalFold.getVisibleDots()).isEqualTo(689);
	}

	@Test
	void folding_SecondPart() {
		List<CoordinatesXY> coordinatesList = LineResolver.getStringStreamOfFile("day13origami.txt")
				.map(s -> s.split(","))
				.map(s -> new CoordinatesXY(Integer.parseInt(s[0]), Integer.parseInt(s[1])))
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