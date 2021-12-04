package be.durvenproeven.aoc2021.day4;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BingoBoardGameTest {
	@Test
	void scoreNrs_SimpleInput() {
		BingoBoardGame bingoBoardGame = new BingoBoardGame(List.of(
				new BingoBoard(List.of(22, 13, 17, 11, 0,
						8, 2, 23, 4, 24,
						21, 9, 14, 16, 7,
						6, 10, 3, 18, 5,
						1, 12, 20, 15, 19), 5),
				new BingoBoard(List.of(3, 15, 0, 2, 22,
						9, 18, 13, 17, 5,
						19, 8, 7, 25, 23,
						20, 11, 10, 24, 4,
						14, 21, 16, 12, 6), 5),
				new BingoBoard(List.of(14, 21, 17, 24, 4,
						10, 16, 15, 9, 19,
						18, 8, 23, 26, 20,
						22, 11, 13, 6, 5,
						2, 0, 12, 3, 7), 5)
		));

		assertThat(bingoBoardGame.scoreNrs(List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)))
				.isEqualTo(4512);
	}

	@Test
	void scoreLastOne_SimpleInput() {
		BingoBoardGame bingoBoardGame = new BingoBoardGame(List.of(
				new BingoBoard(List.of(22, 13, 17, 11, 0,
						8, 2, 23, 4, 24,
						21, 9, 14, 16, 7,
						6, 10, 3, 18, 5,
						1, 12, 20, 15, 19), 5),
				new BingoBoard(List.of(3, 15, 0, 2, 22,
						9, 18, 13, 17, 5,
						19, 8, 7, 25, 23,
						20, 11, 10, 24, 4,
						14, 21, 16, 12, 6), 5),
				new BingoBoard(List.of(14, 21, 17, 24, 4,
						10, 16, 15, 9, 19,
						18, 8, 23, 26, 20,
						22, 11, 13, 6, 5,
						2, 0, 12, 3, 7), 5)
		));

		assertThat(bingoBoardGame.scoreLastOne(List.of(7, 4, 9, 5, 11, 17, 23, 2, 0, 14, 21, 24, 10, 16, 13, 6, 15, 25, 12, 22, 18, 20, 8, 19, 3, 26, 1)))
				.isEqualTo(1924);
	}


	@Test
	void scoreNrs_RealGivenInput() {
		List<BingoBoard> bingoBoards = LineResolver.groupToList("day4boards.txt",  strings -> BingoBoard.from(strings, 5));

		BingoBoardGame bingoBoardGame = new BingoBoardGame(bingoBoards);
		String s = LineResolver.getStringStreamOfFile("day4numbers.txt")
				.findFirst().orElseThrow();
		List<Integer> nrs = Arrays.stream(s.split(","))
				.map(Integer::parseInt)
				.toList();

		assertThat(bingoBoardGame.scoreNrs(nrs)).isEqualTo(8580);
	}

	@Test
	void scoreLastOne_RealGivenInput() {
		List<BingoBoard> bingoBoards = LineResolver.groupToList("day4boards.txt",  strings -> BingoBoard.from(strings, 5));

		BingoBoardGame bingoBoardGame = new BingoBoardGame(bingoBoards);
		String s = LineResolver.getStringStreamOfFile("day4numbers.txt")
				.findFirst().orElseThrow();
		List<Integer> nrs = Arrays.stream(s.split(","))
				.map(Integer::parseInt)
				.toList();

		assertThat(bingoBoardGame.scoreLastOne(nrs)).isEqualTo(9576);
	}

}