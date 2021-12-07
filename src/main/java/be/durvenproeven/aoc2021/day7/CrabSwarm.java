package be.durvenproeven.aoc2021.day7;

import java.util.List;

public class CrabSwarm {
	private List<Integer> positions;

	public CrabSwarm(List<Integer> positions) {
		this.positions = positions;
	}

	public int getDistance(int toNr){
		return positions.stream()
				.mapToInt(x -> Math.abs(x-toNr))
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
