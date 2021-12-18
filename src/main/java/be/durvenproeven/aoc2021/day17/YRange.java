package be.durvenproeven.aoc2021.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class YRange {
	private final int start, end;

	public YRange(int start, int end) {
		checkArgument(start <= end);
		this.start = start;
		this.end = end;
	}

	public static YRange create(int startInclusive, int endInclusive) {
		return new YRange(startInclusive, endInclusive);
	}

	public static YRange createTo(int endInclusive) {
		return new YRange(1, endInclusive);
	}

	public static YRange createWithOnly(int endInclusive) {
		return new YRange(endInclusive, endInclusive);
	}


	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public int getValue(){
		return IntStream.rangeClosed(start, end).sum();
	}

	public YRange higher(){
		return new YRange(start+1, end+1);
	}

	public YRange lower(){
		return new YRange(Math.max(start-1,0), end-1);
	}

	public YRange expandToHigher(){
		return new YRange(start, end+1);
	}

	public YRange expandToLower(){
		return new YRange(start-1, end);
	}

	public YRange reduceFromHigher(){
		checkArgument(reduceIsPossible());
		return new YRange(start, end-1);
	}

	public YRange reduceFromLower(){
		checkArgument(reduceIsPossible());
		return new YRange(start+1, end);
	}

	public List<UnaryOperator<YRange>> getPossibleOperations(){
		List<UnaryOperator<YRange>> unaryOperators = new ArrayList<>();
		unaryOperators.add(YRange::higher);
		unaryOperators.add(YRange::expandToHigher);
		unaryOperators.add(YRange::lower);
		unaryOperators.add(YRange::expandToLower);
		if (reduceIsPossible()){
			unaryOperators.add(YRange::reduceFromLower);
			unaryOperators.add(YRange::reduceFromHigher);
		}
		return unaryOperators;
	}

	private boolean reduceIsPossible() {
		return end> start;
	}

}
