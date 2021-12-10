package be.durvenproeven.aoc2021.day10;

import be.durvenproeven.aoc2021.LineResolver;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static be.durvenproeven.aoc2021.day10.SyntaxScoring.Delimiter.BRACES;
import static be.durvenproeven.aoc2021.day10.SyntaxScoring.Delimiter.BRACKET;
import static be.durvenproeven.aoc2021.day10.SyntaxScoring.Delimiter.PARENTHESIS;
import static be.durvenproeven.aoc2021.day10.SyntaxScoring.Delimiter.SMALLER_GREATER;
import static org.assertj.core.api.Assertions.assertThat;

class SyntaxScoringTest {


	@Test
	void findError() {
		assertThat(new SyntaxScoring("(]").findError()).get()
				.isEqualTo(new SyntaxScoring.ErrorResult(PARENTHESIS, BRACKET));
		assertThat(new SyntaxScoring("{()()()>").findError()).get()
				.isEqualTo(new SyntaxScoring.ErrorResult(BRACES, SMALLER_GREATER));
		assertThat(new SyntaxScoring("(((()))}").findError()).get()
				.isEqualTo(new SyntaxScoring.ErrorResult(PARENTHESIS, BRACES));
		assertThat(new SyntaxScoring("<([]){()}[{}])").findError()).get()
				.isEqualTo(new SyntaxScoring.ErrorResult(SMALLER_GREATER, PARENTHESIS));
	}

	@Test
	void findError_GivenExample() {
		assertThat(new SyntaxScoring("[({(<(())[]>[[{[]{<()<>>").findError()).isEmpty();
		assertThat(new SyntaxScoring("[(()[<>])]({[<{<<[]>>(").findError()).isEmpty();
		assertThat(new SyntaxScoring("{([(<{}[<>[]}>{[]{[(<()>").findError()).get().isEqualTo(new SyntaxScoring.ErrorResult(BRACKET, BRACES));
		assertThat(new SyntaxScoring("(((({<>}<{<{<>}{[]{[]{}").findError()).isEmpty();
		assertThat(new SyntaxScoring("[[<[([]))<([[{}[[()]]]").findError()).get().isEqualTo(new SyntaxScoring.ErrorResult(BRACKET, PARENTHESIS));
		assertThat(new SyntaxScoring("[{[{({}]{}}([{[{{{}}([]").findError()).get().isEqualTo(new SyntaxScoring.ErrorResult(PARENTHESIS, BRACKET));
		assertThat(new SyntaxScoring("{<[[]]>}<{[{[{[]{()[[[]").findError()).isEmpty();
		assertThat(new SyntaxScoring("[<(<(<(<{}))><([]([]()").findError()).get().isEqualTo(new SyntaxScoring.ErrorResult(SMALLER_GREATER, PARENTHESIS));
		assertThat(new SyntaxScoring("<{([([[(<>()){}]>(<<{{").findError()).get().isEqualTo(new SyntaxScoring.ErrorResult(BRACKET, SMALLER_GREATER));
		assertThat(new SyntaxScoring("<{([{{}}[<[[[<>{}]]]>[]]").findError()).isEmpty();
	}

	@Test
	void getScore_GivenExample() {
		assertThat(SyntaxScoring.getScore(List.of(
				"[({(<(())[]>[[{[]{<()<>>",
				"[(()[<>])]({[<{<<[]>>(",
				"{([(<{}[<>[]}>{[]{[(<()>",
				"(((({<>}<{<{<>}{[]{[]{}",
				"[[<[([]))<([[{}[[()]]]",
				"[{[{({}]{}}([{[{{{}}([]",
				"{<[[]]>}<{[{[{[]{()[[[]",
				"[<(<(<(<{}))><([]([]()",
				"<{([([[(<>()){}]>(<<{{",
				"<{([{{}}[<[[[<>{}]]]>[]]"))
		).isEqualTo(26397);
	}

	@Test
	void getScore_RealExample() {
		List<String> strings = LineResolver.getStringStreamOfFile("day10syntax.txt").toList();

		assertThat(SyntaxScoring.getScore(strings)).isEqualTo(323613);
	}

	@Test
	void findMissing() {
		assertThat(new SyntaxScoring("[({(<(())[]>[[{[]{<()<>>").findMissing()).has(closing("}}]])})]"));
		assertThat(new SyntaxScoring("[(()[<>])]({[<{<<[]>>(").findMissing()).has(closing(")}>]})"));
		assertThat(new SyntaxScoring("(((({<>}<{<{<>}{[]{[]{}").findMissing()).has(closing("}}>}>))))"));
		assertThat(new SyntaxScoring("{<[[]]>}<{[{[{[]{()[[[]").findMissing()).has(closing("]]}}]}]}>"));
		assertThat(new SyntaxScoring("<{([{{}}[<[[[<>{}]]]>[]]").findMissing()).has(closing("])}>"));
	}

	@Test
	void getCompletionScore() {
		assertThat(new SyntaxScoring("[({(<(())[]>[[{[]{<()<>>").getCompletionScore()).isEqualTo(288957);
		assertThat(new SyntaxScoring("[(()[<>])]({[<{<<[]>>(").getCompletionScore()).isEqualTo(5566);
		assertThat(new SyntaxScoring("(((({<>}<{<{<>}{[]{[]{}").getCompletionScore()).isEqualTo(1480781);
		assertThat(new SyntaxScoring("{<[[]]>}<{[{[{[]{()[[[]").getCompletionScore()).isEqualTo(995444);
		assertThat(new SyntaxScoring("<{([{{}}[<[[[<>{}]]]>[]]").getCompletionScore()).isEqualTo(294);

	}

	@Test
	void getCompletionScoreOf_GivenExample() {
		assertThat(SyntaxScoring.getCompletionScoreOf(List.of(
				"[({(<(())[]>[[{[]{<()<>>",
				"[(()[<>])]({[<{<<[]>>(",
				"{([(<{}[<>[]}>{[]{[(<()>",
				"(((({<>}<{<{<>}{[]{[]{}",
				"[[<[([]))<([[{}[[()]]]",
				"[{[{({}]{}}([{[{{{}}([]",
				"{<[[]]>}<{[{[{[]{()[[[]",
				"[<(<(<(<{}))><([]([]()",
				"<{([([[(<>()){}]>(<<{{",
				"<{([{{}}[<[[[<>{}]]]>[]]"))
		).isEqualTo(288957);
	}

	@Test
	void getCompletionScoreOf_RealExample() {
		List<String> strings = LineResolver.getStringStreamOfFile("day10syntax.txt").toList();

		assertThat(SyntaxScoring.getCompletionScoreOf(strings)).isEqualTo(3103006161L);

	}

		private Condition<? super Optional<List<SyntaxScoring.Delimiter>>> closing(String s) {
		List<SyntaxScoring.Delimiter> closing = s.chars()
				.mapToObj(Character::toString)
				.map(SyntaxScoring.Delimiter::fromClosing)
				.map(Optional::orElseThrow)
				.toList();
		return new Condition<>(t -> t.isPresent() && t.get().equals(closing), "%s: %s", s, closing);
	}
}