package be.durvenproeven.aoc2021.day23;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AmphipodSystem {
	private final List<Room> rooms;
	private final List<List<AmphipodType>> freeSpacesInHallWay;
	private final int acquiredWeight;

	AmphipodSystem(List<Room> rooms, List<List<AmphipodType>> freeSpacesInHallWay, int acquiredWeight) {
		this.rooms = rooms;
		this.freeSpacesInHallWay = freeSpacesInHallWay;
		this.acquiredWeight = acquiredWeight;
	}

	public static AmphipodSystem createWithFreeSpaces(List<Room> rooms, List<Integer> freespacesBetweenRooms) {
		List<List<AmphipodType>> freeSpacesInHallWay = new ArrayList<>();
		freespacesBetweenRooms.stream()
				.map(AmphipodSystem::arrayListWithNullsAndSize)
				.forEach(freeSpacesInHallWay::add);
		return new AmphipodSystem(rooms, freeSpacesInHallWay, 0);
	}

	private static List<AmphipodType> arrayListWithNullsAndSize(Integer size) {
		return IntStream.range(0, size)
				.mapToObj(ign -> (AmphipodType) null)
				.toList();
	}


	public double getWeight() {
		double res = acquiredWeight;
		for (Room room : rooms) {
			res += room.getWrongLocated().stream()
					.mapToDouble(e -> weightForLocatedInRoom(room, e))
					.sum();
		}
		for (int indexOfFreeSpaces = 0, freeSpacesInHallWaySize = freeSpacesInHallWay.size(); indexOfFreeSpaces < freeSpacesInHallWaySize; indexOfFreeSpaces++) {
			List<AmphipodType> amphipodTypes = freeSpacesInHallWay.get(indexOfFreeSpaces);
			for (int i = 0; i < amphipodTypes.size(); i++) {
				if (amphipodTypes.get(i) != null) {
					res += weightForLocatedNotInRoom(indexOfFreeSpaces, amphipodTypes, i);
				}
			}
		}
		return res;
	}

	

	private double weightForLocatedNotInRoom(int indexOfFreeSpaces, List<AmphipodType> amphipodTypesBlock, int indexInAmpiPodTypesBlock) {
		AmphipodType amphipodType = amphipodTypesBlock.get(indexInAmpiPodTypesBlock);
		int indexOfRoom = indexOfRoom(amphipodType);
		if (indexOfFreeSpaces <= indexOfRoom) {//next
			int distanceInRooms = IntStream.rangeClosed(indexOfFreeSpaces + 1, indexOfRoom)
					.map(i -> freeSpacesInHallWay.get(i).size() + 1)
					.sum();

			double distance = distanceInRooms + 1 + 1.5 + (amphipodTypesBlock.size() - 1 - indexInAmpiPodTypesBlock);
			return distance * amphipodType.getEnergyRequiredForStep();
		}
		if (indexOfFreeSpaces > indexOfRoom ) { //previous
			//look at the +1
			int distanceInRooms = IntStream.range(indexOfRoom+1, indexOfFreeSpaces)
					.map(i -> freeSpacesInHallWay.get(i).size() + 1)
					.sum();

			double distance = distanceInRooms + 1 + 1.5 + indexInAmpiPodTypesBlock;
			return distance * amphipodType.getEnergyRequiredForStep();
		}

		throw new IllegalArgumentException();
	}

	private double weightForLocatedInRoom(Room room, Room.AmphipodWithWeight e) {
		double v = e.weight() + distanceFromTo(room.getAmphipodTypeNeeded(), e.type()) + 1.5;
		return v * e.type().getEnergyRequiredForStep();
	}

	private double distanceFromTo(AmphipodType toRoom, AmphipodType fromRoom) {
		int from = indexOfRoom(fromRoom);
		int to = indexOfRoom(toRoom);
		if (from == to) {
			return 0;
		}
		return IntStream.rangeClosed(Math.min(from, to) + 1, Math.max(from, to))
				.map(i -> freeSpacesInHallWay.get(i).size() + 1)
				.sum();
	}

	private int indexOfRoom(AmphipodType fromRoom) {
		return IntStream.range(0, rooms.size())
				.filter(i -> rooms.get(i).getAmphipodTypeNeeded() == fromRoom)
				.findFirst().orElseThrow();
	}
}
