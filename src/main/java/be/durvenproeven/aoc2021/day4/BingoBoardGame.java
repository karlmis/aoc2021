package be.durvenproeven.aoc2021.day4;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BingoBoardGame {
	private final List<BingoBoard> boards;

	public BingoBoardGame(List<BingoBoard> boards) {
		this.boards = boards;
	}

	public int scoreNrs(List<Integer> nrs){
		for (Integer nr : nrs) {
			for (BingoBoard board : boards) {
				if (board.chooseNr(nr)){
					return board.getScore(nr);
				}
			}
		}
		return -1;
	}

	public int scoreLastOne(List<Integer> nrs){
		Map<BingoBoard, Boolean> boardMap = boards.stream()
				.collect(Collectors.toMap(Function.identity(), i -> false));
		int counter= 0;
		for (Integer nr : nrs) {//TODO should be better
			for (Map.Entry<BingoBoard, Boolean> boardEntry : boardMap.entrySet()) {
				if (!boardEntry.getValue() &&  boardEntry.getKey().chooseNr(nr)){
					boardEntry.setValue(true);
					counter++;
				}
				if (counter== boardMap.size()){
					return boardEntry.getKey().getScore(nr);
				}

			}
		}
		return -1;
	}
}
