package be.durvenproeven.aoc2021.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AmphipodSystem {
	private List<Room> rooms;
	private List<List<AmphipodType>> freeSpacesInHallWay;

	public AmphipodSystem(List<Room> rooms, List<Integer> freespacesBetweenRooms) {
		this.rooms = rooms;
		freeSpacesInHallWay= new ArrayList<>();
		freespacesBetweenRooms.stream()
				.map(this::arrayListWithNullsAndSize)
				.forEach(freeSpacesInHallWay::add);
	}

	private List<AmphipodType> arrayListWithNullsAndSize(Integer size) {
		return IntStream.range(0,size)
				.mapToObj(ign -> (AmphipodType)null)
				.toList();
	}
}
