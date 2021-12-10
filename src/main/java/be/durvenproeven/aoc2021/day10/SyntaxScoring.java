package be.durvenproeven.aoc2021.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SyntaxScoring {
	private final String line;
	private final List<String> strings;
	private Optional<ErrorResult> error;

	public SyntaxScoring(String line) {
		this.line = line;
		strings = getStringListWithoutCouples();
		error = getError();
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
		if (error.isPresent()) {
			return Optional.empty();
		}
		return Optional.of(
				reverse(strings).stream()
						.map(s -> Delimiter.fromOpening(s).orElseThrow(() -> new IllegalArgumentException("nothing found for " + s)))
						.toList());
	}

	private List<String> reverse(List<String> strings) {
		List<String> strings1 = new ArrayList<>(strings);
		Collections.reverse(strings1);
		return strings1;
	}

	public Optional<ErrorResult> findError() {
		return error;
	}

	private Optional<ErrorResult> getError() {
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

}
