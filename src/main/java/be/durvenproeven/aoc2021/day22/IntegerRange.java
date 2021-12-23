package be.durvenproeven.aoc2021.day22;

import java.util.Objects;
import java.util.Optional;

public class IntegerRange {
	private int min, max;

	public IntegerRange(int first, int second) {
		this.min = Math.min(first, second);
		this.max = Math.max(first, second);
	}

	public boolean overlapsWith(IntegerRange other) {
		return other.min <= max && other.max >= min;
	}

	public Optional<IntegerRange> intersection(IntegerRange other) {
		int newMin = Math.max(min, other.min);
		int newMax = Math.min(max, other.max);
		if (newMin <= newMax) {
			return Optional.of(new IntegerRange(newMin, newMax));
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		IntegerRange that = (IntegerRange) o;
		return min == that.min && max == that.max;
	}

	@Override
	public int hashCode() {
		return Objects.hash(min, max);
	}

	@Override
	public String toString() {
		return "IntegerRange{" +
				"min=" + min +
				", max=" + max +
				'}';
	}

	public int getMin() {
		return min;
	}

	public int getMax() {
		return max;
	}
}
