package be.durvenproeven.aoc2021.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BinaryOperator;

public class DebtResolver {
	private static final BinaryOperator<Counter> IGNORED_COMBINER = (c1, c2) -> {
		throw new IllegalArgumentException("do not use parallel");
	};
	private List<Integer> list;

	public DebtResolver(List<Integer> list) {
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
		List<Integer> windows = new ArrayList<>();
		int firstCounter = list.get(0);
		int secondCounter = list.get(1);
		for (int i = 2; i < list.size(); i++) {
			windows.add(firstCounter + secondCounter + list.get(i));
			firstCounter = secondCounter;
			secondCounter = list.get(i);
		}
		return getNrIncreased(windows);
	}
}
