package be.durvenproeven.aoc2021.day10;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
