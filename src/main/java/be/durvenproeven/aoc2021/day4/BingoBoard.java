package be.durvenproeven.aoc2021.day4;


import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class BingoBoard {
	private final List<Integer> nrs;
	private final TreeSet<Integer> chosenNrs = new TreeSet<>();
	private final int nrsInaRow;

	public BingoBoard(List<Integer> nrs, int nrsInaRow) {
		Preconditions.checkArgument(nrs.size() == nrsInaRow * nrsInaRow);
		this.nrs = nrs;
		this.nrsInaRow = nrsInaRow;
	}

	public static BingoBoard from(List<String> strings, int nrsInaRow) {
		List<Integer> nrs = strings.stream()
				.flatMap(s -> getNrsFrom(s).stream())
				.toList();
		return new BingoBoard(nrs, nrsInaRow);
	}

	private static List<Integer> getNrsFrom(String s) {
		String[] split = s.split(" ");
		return Arrays.stream(split)
				.filter(s1 -> !s1.isEmpty())
				.map(Integer::parseInt)
				.toList();
	}

	public boolean chooseNr(int i) {
		int index = nrs.indexOf(i);
		chosenNrs.add(index);
		return isHorizontalFilled(index / nrsInaRow) || isVerticalField(index % nrsInaRow);
	}

	private boolean isVerticalField(int column) {
		return IntStream.range(0, nrsInaRow)
				.map(i -> i * nrsInaRow + column)
				.boxed()
				.allMatch(chosenNrs::contains);
	}

	private boolean isHorizontalFilled(int row) {
		return chosenNrs.subSet(row * nrsInaRow, (row + 1) * nrsInaRow).size() == nrsInaRow;
	}

	public int getScore(int lastChosenNr) {
		return IntStream.range(0, nrsInaRow * nrsInaRow)
				.filter(index -> !chosenNrs.contains(index))
				.map(nrs::get)
				.sum() * lastChosenNr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		BingoBoard that = (BingoBoard) o;
		return nrsInaRow == that.nrsInaRow && Objects.equals(nrs, that.nrs) && Objects.equals(chosenNrs, that.chosenNrs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nrs, chosenNrs, nrsInaRow);
	}
}
