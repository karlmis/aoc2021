package be.durvenproeven.aoc2021.day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Room implements Comparable<Room>{
	private AmphipodType amphipodTypeNeeded;

	private List<AmphipodType> occupations;

	public Room(AmphipodType amphipodTypeNeeded, List<AmphipodType> occupations) {
		this.amphipodTypeNeeded = amphipodTypeNeeded;
		this.occupations = occupations;
	}

	Optional<AmphipodType> getAtLocation(int nr){
		return Optional.ofNullable(occupations.get(nr));
	}

	public boolean isComplete() {
		return occupations.size() == 2 && occupations.stream().allMatch(at -> at == amphipodTypeNeeded);
	}

	public OptionalInt getDistanceToFreePlace(){
		if (occupations.get(1)==null){
			return OptionalInt.of(2);
		}
		if (occupations.get(0)==null){
			return OptionalInt.of(1);
		}
		return OptionalInt.empty();
	}

	public boolean hasWrongValues(){
		return occupations.stream()
				.filter(Objects::nonNull)
				.anyMatch(at -> at != amphipodTypeNeeded);

	}

	public AmphipodType getTop() {
		return occupations.stream()
				.filter(Objects::nonNull)
				.findFirst().orElseThrow();
	}

	public Room withoutTop() {
		List<AmphipodType> newOccupations = new ArrayList<>(occupations);
		if (occupations.get(0) != null) {
			newOccupations.set(0, null);
			return new Room(amphipodTypeNeeded, newOccupations);
		}
		if (occupations.get(1) != null) {
			newOccupations.set(1, null);
			return new Room(amphipodTypeNeeded, newOccupations);
		}
		throw new IllegalArgumentException();
	}

	public Room withCorrectTop() {
		List<AmphipodType> newOccupations = new ArrayList<>(occupations);
		if (occupations.get(1) == null) {
			newOccupations.set(1, amphipodTypeNeeded);
			return new Room(amphipodTypeNeeded, newOccupations);
		}
		if (occupations.get(0) == null) {
			newOccupations.set(0, amphipodTypeNeeded);
			return new Room(amphipodTypeNeeded, newOccupations);
		}
		throw new IllegalArgumentException();
	}

	List<AmphipodWithDistance> getWrongLocated() {
		if (occupations.get(1)== amphipodTypeNeeded){
			if (occupations.get(0)== amphipodTypeNeeded || occupations.get(0)==null){
				return Collections.emptyList();
			}
			return List.of(new AmphipodWithDistance(occupations.get(0),1));
		}
		return IntStream.range(0,2)
				.filter(i -> occupations.get(i)!= null)
				.mapToObj(i -> new AmphipodWithDistance(occupations.get(i), i+1))
				.toList();
	}

	@Override
	public int compareTo(Room o) {
		return Comparator.comparing(Room::getAmphipodTypeNeeded)
				.thenComparing(r -> r.occupations.get(0), Comparator.nullsLast(AmphipodType::compareTo))
				.thenComparing(r -> r.occupations.get(1), Comparator.nullsLast(AmphipodType::compareTo))
				.compare(this, o);
	}
	//public boolean isAvailable

	public static record AmphipodWithDistance(AmphipodType type, int weight) {
	}

	public AmphipodType getAmphipodTypeNeeded() {
		return amphipodTypeNeeded;
	}

	double getAverageValue(){
		if (occupations.get(1)== amphipodTypeNeeded){
			return 1;
		}
		return 1.5;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Room room = (Room) o;
		return amphipodTypeNeeded == room.amphipodTypeNeeded && Objects.equals(occupations, room.occupations);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amphipodTypeNeeded, occupations);
	}

	@Override
	public String toString() {
		return "Room{" +
				"amphipodTypeNeeded=" + amphipodTypeNeeded +
				", occupations=" + occupations +
				'}';
	}
}
