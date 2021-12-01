package be.durvenproeven.aoc2021.day1;

import java.util.List;

public class DebthResolver {
	private List<Integer> list;

	public DebthResolver(List<Integer> list) {

		this.list = list;
	}

	public int getNrOfIncreased() {
		int previous = -1;
		int counter = -1; //first one is never an increase
		for (Integer integer : list) {
			if (integer > previous) {
				counter++;
			}
			previous = integer;
		}
		return counter;
	}
}
