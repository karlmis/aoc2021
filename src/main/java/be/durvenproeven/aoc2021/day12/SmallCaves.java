package be.durvenproeven.aoc2021.day12;

import java.util.HashMap;
import java.util.Map;

public class SmallCaves {
	private Map<String, Integer> cavesMap = new HashMap<>();

	public SmallCaves(String s) {
		cavesMap.put(s, 1);
	}

	public SmallCaves() {
	}

	private SmallCaves(Map<String, Integer> cavesMap) {
		this.cavesMap = cavesMap;
	}

	boolean contains(String s){
		return cavesMap.containsKey(s);
	}

	int getNrOfOccurences(String s){
		return cavesMap.getOrDefault(s,0);
	}

	int getMaximumNrOfOccurences(){
		return cavesMap.values().stream()
				.mapToInt(Integer::intValue)
				.max().orElse(0);
	}

	SmallCaves add(String s){
		Map<String,Integer> map = new HashMap<>(cavesMap);
		map.merge(s, 1, Integer::sum);
		return new SmallCaves(map);
	}
}
