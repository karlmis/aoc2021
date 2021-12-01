package be.durvenproeven.aoc2021.day1;

import java.util.ArrayList;
import java.util.List;

public class DebthResolver {
	private List<Integer> list;

	public DebthResolver(List<Integer> list) {

		this.list = list;
	}

	public int getNrOfIncreased() {
		return getNrIncreased(list);
	}

	private int getNrIncreased(List<Integer> list) {
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

	public int getNrOfWindowsIncreased(){
		List<Integer> windows= new ArrayList<>();
		int firstCounter= list.get(0);
		int secondCounter= list.get(1);
		for (int i = 2; i < list.size(); i++) {
			windows.add(firstCounter+secondCounter+list.get(i));
			firstCounter= secondCounter;
			secondCounter= list.get(i);
		}
		return getNrIncreased(windows);
	}
}
