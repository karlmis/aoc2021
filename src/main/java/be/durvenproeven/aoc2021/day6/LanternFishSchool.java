package be.durvenproeven.aoc2021.day6;

import java.util.ArrayList;
import java.util.List;

public class LanternFishSchool {
	private List<Integer> list;

	public LanternFishSchool(List<Integer> list) {
		this.list = new ArrayList<>(list);
	}

	public void nextGeneration(){
		List<Integer> newGeneration= new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			Integer nr = list.get(i);
			if (nr == 0){
				list.set(i, 6);
				newGeneration.add(8);
			} else {
				list.set(i, nr-1);
			}
		}
		list.addAll(newGeneration);
	}

	public int getNrOfFish(){
		return list.size();
	}
}
