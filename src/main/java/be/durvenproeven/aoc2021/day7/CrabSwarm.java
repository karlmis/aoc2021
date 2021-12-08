package be.durvenproeven.aoc2021.day7;

import java.util.List;
import java.util.function.ToIntFunction;

public class CrabSwarm {
	private List<Integer> positions;

	public CrabSwarm(List<Integer> positions) {
		this.positions = positions;
	}

	public int getDistance(int toNr){
		return getDistance(x -> Math.abs(x - toNr));
	}

	public int getSecondDistance(int toNr){
		return getDistance(x-> neededFule(x, toNr));
	}

	private int neededFule(Integer x, int toNr) {
		int abs = Math.abs(toNr - x);
		return abs*(abs+1)/2;
	}

	private int getDistance(ToIntFunction<Integer> integerToIntFunction) {
		return positions.stream()
				.mapToInt(integerToIntFunction)
				.sum();
	}

	public int getMax(){
		return positions.stream()
				.mapToInt(Integer::intValue)
				.max().orElseThrow();
	}

	public int getMin(){
		return positions.stream()
				.mapToInt(Integer::intValue)
				.min().orElseThrow();
	}
}
