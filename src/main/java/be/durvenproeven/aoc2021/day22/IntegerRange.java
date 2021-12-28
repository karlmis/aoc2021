package be.durvenproeven.aoc2021.day22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

	public boolean contains(IntegerRange other) {
		return min <=other.min  && other.max <= max;
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

	public int getSize(){
		return max-min+1;
	}

	@Deprecated
	public List<IntegerRange> difference(IntegerRange other) {
		Optional<IntegerRange> intersectionOpt = this.intersection(other);
		if (intersectionOpt.isEmpty()){
			return List.of(this);
		}
		IntegerRange intersection = intersectionOpt.get();
		if ( intersection.equals(this)){
			return Collections.emptyList();
		}
		List<IntegerRange> res = new ArrayList<>();
		if (other.min> min){
			res.add(new IntegerRange(min, other.min-1));
		}
		if (other.max< max){
			res.add(new IntegerRange(other.max+1, max));
		}
		return res;
	}

	public Map<Position, IntegerRange> getPartsComparedTo(IntegerRange other){
		Optional<IntegerRange> intersection = intersection(other);
		if (intersection.isPresent()){
			Map<Position, IntegerRange> res = new EnumMap<>(Position.class);
			res.put(Position.OVERLAPPING, intersection.get());
			if (min< other.min) {
				res.put(Position.BEFORE, new IntegerRange(min, other.min-1));
			}
			if (max> other.max) {
				res.put(Position.AFTER, new IntegerRange(other.max+1, max));
			}
			return res;
		} else {
			if (min< other.min) {
				return Map.of(Position.BEFORE, this);
			}
			return Map.of(Position.AFTER, this);
		}
	}

	public enum Position{
		BEFORE, OVERLAPPING, AFTER
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
