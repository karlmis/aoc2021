package be.durvenproeven.aoc2021.day23;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Room implements Comparable<Room> {
	private AmphipodType amphipodTypeNeeded;

	private List<AmphipodType> occupations;

	public Room(AmphipodType amphipodTypeNeeded, List<AmphipodType> occupations) {
		this.amphipodTypeNeeded = amphipodTypeNeeded;
		this.occupations = occupations;
	}

	int getSize(){
		return occupations.size();
	}

	Optional<AmphipodType> getAtLocation(int nr) {
		return Optional.ofNullable(occupations.get(nr));
	}

	public boolean isComplete() {
		return occupations.stream().allMatch(at -> at == amphipodTypeNeeded);
	}

	public OptionalInt getDistanceToFreePlace() {
		return IntStream.range(0, occupations.size())
				.map(this::reverse)
				.filter(i -> occupations.get(i) == null)
				.map(i -> i + 1)
				.findFirst();
	}

	private int reverse(int i) {
		return occupations.size() - 1 - i;
	}

	public boolean hasWrongValues() {
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
		int indexFirstNonNull = IntStream.range(0, occupations.size())
				.filter(i -> occupations.get(i) != null)
				.findFirst().orElseThrow();
		List<AmphipodType> newOccupations = new ArrayList<>(occupations);
		newOccupations.set(indexFirstNonNull, null);
		return new Room(amphipodTypeNeeded, newOccupations);
	}

	public Room withCorrectTop() {
		int indexLastNull = IntStream.range(0, occupations.size())
				.map(this::reverse)
				.filter(i -> occupations.get(i) == null)
				.findFirst().orElseThrow();

		List<AmphipodType> newOccupations = new ArrayList<>(occupations);
		newOccupations.set(indexLastNull, amphipodTypeNeeded);
		return new Room(amphipodTypeNeeded, newOccupations);

	}

	List<AmphipodWithDistance> getWrongLocated() {
		int indexFurthestIncorrect = IntStream.range(0, occupations.size())
				.map(this::reverse)
				.filter(i -> occupations.get(i) != null && occupations.get(i) != amphipodTypeNeeded)
				.findFirst().orElse(-1);
		if (indexFurthestIncorrect == -1) {
			return Collections.emptyList();
		}
		int indexStartIncorrect = IntStream.rangeClosed(0, indexFurthestIncorrect)
				.filter(i -> occupations.get(i) != null)
				.findFirst().orElse(0);

		return IntStream.rangeClosed(indexStartIncorrect, indexFurthestIncorrect)
				.mapToObj(i -> new AmphipodWithDistance(occupations.get(i), i + 1))
				.toList();
	}

	double getAverageValue() {
		int lastNotCorrect = IntStream.range(0, occupations.size())
				.map(this::reverse)
				.filter(i -> occupations.get(i) != amphipodTypeNeeded)
				.findFirst().orElse(0);
		return (lastNotCorrect+2.)/2;
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
