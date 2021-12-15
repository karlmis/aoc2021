package be.durvenproeven.aoc2021.day10;

import java.util.Objects;

class ErrorResult {
	private final Delimiter expected, found;

	public ErrorResult(Delimiter expected, Delimiter found) {
		this.expected = expected;
		this.found = found;
	}

	public int getScore() {
		return found.getErrorScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ErrorResult that = (ErrorResult) o;
		return expected == that.expected && found == that.found;
	}

	@Override
	public int hashCode() {
		return Objects.hash(expected, found);
	}

	@Override
	public String toString() {
		return "ErrorResult{" +
				"expected=" + expected +
				", found=" + found +
				'}';
	}
}
