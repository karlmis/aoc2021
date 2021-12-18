package be.durvenproeven.aoc2021.day17;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

import static com.google.common.base.Preconditions.checkArgument;

public class XRange {
	private final int start, end;

	private XRange(int startInclusive, int endInclusive) {
		this.start = startInclusive;
		this.end = endInclusive;
	}

	public static XRange create(int startInclusive, int endInclusive) {
		return new XRange(startInclusive, endInclusive);
	}

	public static XRange createTo(int endInclusive) {
		return new XRange(1, endInclusive);
	}

	public static XRange createWithOnly(int endInclusive) {
		return new XRange(endInclusive, endInclusive);
	}



	public int getEnd() {
		return end;
	}

	public int getValue(){
		return IntStream.rangeClosed(start, end).sum();
	}

	public XRange higher(){
		return create(start+1, end+1);
	}

	public XRange lower(){
		return create(Math.max(start-1,0), end-1);
	}

	public XRange expandToHigher(){
		return create(start, end+1);
	}

	public XRange expandToLower(){
		checkArgument(isExpandToLowerPossible());
		return create(start-1, end);
	}

	private boolean isExpandToLowerPossible() {
		return start > 0;
	}

	public XRange reduceFromHigher(){
		checkArgument(reduceIsPossible());
		return create(start, end-1);
	}

	public XRange reduceFromLower(){
		checkArgument(reduceIsPossible());
		return create(start+1, end);
	}

	public List<UnaryOperator<XRange>> getPossibleOperations(){
		List<UnaryOperator<XRange>> unaryOperators = new ArrayList<>();
		unaryOperators.add(XRange::higher);
		unaryOperators.add(XRange::expandToHigher);
		unaryOperators.add(XRange::lower);
		if(isExpandToLowerPossible()){
			unaryOperators.add(XRange::expandToLower);
		}
		if (reduceIsPossible()){
			unaryOperators.add(XRange::reduceFromLower);
			unaryOperators.add(XRange::reduceFromHigher);
		}
		return unaryOperators;
	}

	private boolean reduceIsPossible() {
		return end> start;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		XRange xRange = (XRange) o;
		return start == xRange.start && end == xRange.end;
	}

	@Override
	public int hashCode() {
		return Objects.hash(start, end);
	}

	@Override
	public String toString() {
		return "XRange{" +
				"start=" + start +
				", end=" + end +
				'}';
	}
}
