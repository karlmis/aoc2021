package be.durvenproeven.aoc2021.day1;

import java.util.List;
import java.util.function.BinaryOperator;

public class DepthResolver {
	private static final BinaryOperator<Counter> IGNORED_COMBINER = (c1, c2) -> {
		throw new IllegalArgumentException("do not use parallel");
	};
	private static final BinaryOperator<WindowCounter> OTHER_IGNORED_COMBINER = (c1, c2) -> {
		throw new IllegalArgumentException("do not use parallel");
	};
	private final List<Integer> list;

	public DepthResolver(List<Integer> list) {
		this.list = list;
	}

	public int getNrOfIncreased() {
		return getNrIncreased(list);
	}

	private int getNrIncreased(List<Integer> list) {
		Counter reduce = list.stream()
				.reduce(Counter.startingCounter(), Counter::withNextValue, IGNORED_COMBINER);
		return reduce.getNrOfTimesIncreased();
	}

	public int getNrOfWindowsIncreased() {
		WindowCounter reduce = list.stream()
				.reduce(WindowCounter.startingCounter(), WindowCounter::withNextValue, OTHER_IGNORED_COMBINER);
		return reduce.getNrOfTimesIncreased();
	}
}
