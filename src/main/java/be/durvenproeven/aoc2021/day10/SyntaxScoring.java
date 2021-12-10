package be.durvenproeven.aoc2021.day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class SyntaxScoring {
	private final String line;

	public SyntaxScoring(String line) {
		this.line = line;
	}

	long getCompletionScore() {
		return findMissing()
				.map(Delimiter::getCompletionScore)
				.orElse(0L);

	}

	static long getCompletionScoreOf(List<String> l) {
		long[] longs = l.stream()
				.map(SyntaxScoring::new)
				.mapToLong(SyntaxScoring::getCompletionScore)
				.filter(i -> i != 0)
				.sorted().toArray();
		return longs[longs.length / 2];
	}

	public Optional<List<Delimiter>> findMissing() {
		if (findError().isPresent()) {
			return Optional.empty();
		}
		List<String> strings = getStringListWithoutCouples();
		Collections.reverse(strings);
		return Optional.of(strings.stream()
				.map(s -> Delimiter.fromOpening(s).orElseThrow(() -> new IllegalArgumentException("nothing found for " + s)))
				.toList());
	}

	public Optional<ErrorResult> findError() {
		List<String> strings = getStringListWithoutCouples();
		for (int i = 1; i < strings.size(); i++) {
			Optional<Delimiter> closing = Delimiter.fromClosing(strings.get(i));
			if (closing.isPresent()) {
				return Optional.of(new ErrorResult(Delimiter.fromOpening(strings.get(i - 1)).get(), closing.get()));
			}
		}
		return Optional.empty();
	}

	private List<String> getStringListWithoutCouples() {
		List<String> strings = line.chars()
				.mapToObj(Character::toString)
				.collect(Collectors.toCollection(ArrayList::new));
		int previousSize = strings.size();
		removePairs(strings);
		while (strings.size() < previousSize) {
			previousSize = strings.size();
			removePairs(strings);
			System.out.println("after:" + strings);
		}
		return strings;
	}

	static int getScore(List<String> list) {
		return list.stream()
				.map(SyntaxScoring::new)
				.map(SyntaxScoring::findError)
				.flatMap(Optional::stream)
				.mapToInt(ErrorResult::getScore)
				.sum();
	}

	private void removePairs(List<String> strings) {
		System.out.println("before:" + strings);
		List<Integer> toRemove = new ArrayList<>();
		for (int i = 1; i < strings.size(); i++) {
			if (Delimiter.isPair(strings.get(i - 1), strings.get(i))) {
				toRemove.add(0, i - 1);
				toRemove.add(0, i);
			}
		}
		toRemove.forEach(index -> strings.remove(index.intValue()));
	}

	static class ErrorResult {
		Delimiter expected, found;

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

	enum Delimiter {
		PARENTHESIS("(", ")", 3, 1),
		BRACKET("[", "]", 57, 2),
		BRACES("{", "}", 1197, 3),
		SMALLER_GREATER("<", ">", 25137, 4);

		private final String leftSign;
		private final String rightSign;
		private final int errorScore;
		private final int completionScore;

		Delimiter(String leftSign, String rightSign, int errorScore, int completionScore) {
			this.leftSign = leftSign;
			this.rightSign = rightSign;
			this.errorScore = errorScore;
			this.completionScore = completionScore;
		}

		public int getErrorScore() {
			return errorScore;
		}

		public int getCompletionScore() {
			return completionScore;
		}

		public static boolean isPair(String first, String second) {
			return Arrays.stream(values())
					.anyMatch(v -> v.leftSign.equals(first) && v.rightSign.equals(second));
		}

		public static Optional<Delimiter> fromClosing(String first) {
			return Arrays.stream(values())
					.filter(v -> v.rightSign.equals(first))
					.findFirst();
		}

		public static Optional<Delimiter> fromOpening(String first) {
			return Arrays.stream(values())
					.filter(v -> v.leftSign.equals(first))
					.findFirst();
		}

		public static long getCompletionScore(List<Delimiter> list) {
			return list.stream()
					.mapToLong(Delimiter::getCompletionScore)
					.reduce(0, (pr, nr) -> pr * 5 + nr);

		}
	}
}
