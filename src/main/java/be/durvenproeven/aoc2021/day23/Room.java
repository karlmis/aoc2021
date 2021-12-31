package be.durvenproeven.aoc2021.day23;

import java.util.List;
import java.util.stream.IntStream;

public class Room {
	private AmphipodType amphipodTypeNeeded;

	private List<AmphipodType> occupations;

	public Room(AmphipodType amphipodTypeNeeded, List<AmphipodType> occupations) {
		this.amphipodTypeNeeded = amphipodTypeNeeded;
		this.occupations = occupations;
	}



	List<AmphipodWithWeight> getWrongLocated() {
		//assumption: first element in list is never correct if second is not
		return IntStream.range(0, occupations.size())
				.filter(i -> occupations.get(i) != amphipodTypeNeeded)
				.filter(i -> occupations.get(i) != null)
				.boxed()
				.map(i -> new AmphipodWithWeight(occupations.get(i), i+1))
				.toList();


	}
	//public boolean isAvailable

	public static record AmphipodWithWeight(AmphipodType type, double weight){}

	public AmphipodType getAmphipodTypeNeeded() {
		return amphipodTypeNeeded;
	}
}
