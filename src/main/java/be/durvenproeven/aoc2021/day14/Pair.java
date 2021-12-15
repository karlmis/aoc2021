package be.durvenproeven.aoc2021.day14;

import com.google.common.base.Preconditions;

import java.util.Objects;

public class Pair {
	private String first, second;

	public Pair(String first, String second) {
		this.first = first;
		this.second = second;
	}

	public Pair(String s) {
		Preconditions.checkArgument(s.length()==2);
		this.first = s.substring(0,1);
		this.second= s.substring(1);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Pair pair = (Pair) o;
		return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}

	@Override
	public String toString() {
		return "["+first +second+"]" ;
	}
}
