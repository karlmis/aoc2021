package be.durvenproeven.aoc2021.day4;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BingoBoardTest {

	@Test
	void chooseNr_Horizontal() {
		BingoBoard bingoBoard = new BingoBoard(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);

		assertThat(bingoBoard.chooseNr(1)).isFalse();
		assertThat(bingoBoard.chooseNr(2)).isFalse();
		assertThat(bingoBoard.chooseNr(3)).isTrue();
	}

	@Test
	void chooseNr_HorizontalSecondRow() {
		BingoBoard bingoBoard = new BingoBoard(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);

		assertThat(bingoBoard.chooseNr(4)).isFalse();
		assertThat(bingoBoard.chooseNr(5)).isFalse();
		assertThat(bingoBoard.chooseNr(6)).isTrue();
	}

	@Test
	void chooseNr_Vertical() {
		BingoBoard bingoBoard = new BingoBoard(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);

		assertThat(bingoBoard.chooseNr(1)).isFalse();
		assertThat(bingoBoard.chooseNr(4)).isFalse();
		assertThat(bingoBoard.chooseNr(7)).isTrue();
	}

	@Test
	void chooseNr_Vertical2ndColumn() {
		BingoBoard bingoBoard = new BingoBoard(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);

		assertThat(bingoBoard.chooseNr(2)).isFalse();
		assertThat(bingoBoard.chooseNr(5)).isFalse();
		assertThat(bingoBoard.chooseNr(8)).isTrue();
	}

	@Test
	void getScore() {
		BingoBoard bingoBoard = new BingoBoard(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9), 3);

		bingoBoard.chooseNr(2);
		bingoBoard.chooseNr(5);
		bingoBoard.chooseNr(8);

		assertThat(bingoBoard.getScore(8)).isEqualTo(8 * (1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 - 2 - 5 - 8));
	}

	@Test
	void simpleGivenTest() {
		BingoBoard bingoBoard = new BingoBoard(List.of(14, 21, 17, 24, 4,
				10, 16, 15, 9, 19,
				18, 8, 23, 26, 20,
				22, 11, 13, 6, 5,
				2, 0, 12, 3, 7), 5);

		assertThat(bingoBoard.chooseNr(7)).isFalse();
		assertThat(bingoBoard.chooseNr(4)).isFalse();
		assertThat(bingoBoard.chooseNr(9)).isFalse();
		assertThat(bingoBoard.chooseNr(5)).isFalse();
		assertThat(bingoBoard.chooseNr(11)).isFalse();
		assertThat(bingoBoard.chooseNr(17)).isFalse();
		assertThat(bingoBoard.chooseNr(23)).isFalse();
		assertThat(bingoBoard.chooseNr(2)).isFalse();
		assertThat(bingoBoard.chooseNr(0)).isFalse();
		assertThat(bingoBoard.chooseNr(14)).isFalse();
		assertThat(bingoBoard.chooseNr(21)).isFalse();
		assertThat(bingoBoard.chooseNr(24)).isTrue();

		assertThat(bingoBoard.getScore(24)).isEqualTo(4512);
	}

	@Test
	void fromStrings() {
		BingoBoard fromStringsBoard = BingoBoard.from(List.of(
				"22 13 17 11  0",
				" 8  2 23  4 24",
				"21  9 14 16  7",
				" 6 10  3 18  5",
				" 1 12 20 15 19"
		), 5);

		assertThat(fromStringsBoard).isEqualTo(
				new BingoBoard(List.of(22, 13, 17, 11, 0,
						8, 2, 23, 4, 24,
						21, 9, 14, 16, 7,
						6, 10, 3, 18, 5,
						1, 12, 20, 15, 19), 5));
	}
}