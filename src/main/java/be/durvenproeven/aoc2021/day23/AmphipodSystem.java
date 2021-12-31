package be.durvenproeven.aoc2021.day23;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmphipodSystem {
	private final int acquiredWeight;

	private final LinkedHashMap<AmphipodType, Integer> amphipodTypeWithIndexOfRoom;
	private final LinkedHashMap<AmphipodType, Room> amphipodTypeWithRoom;

	private final SortedMap<Integer, AmphipodType> occupiedPlaces;
	private final int maxNr;


	AmphipodSystem(List<Room> rooms, List<List<AmphipodType>> freeSpacesInHallWay, int acquiredWeight) {
		this.acquiredWeight = acquiredWeight;

		int counter = 0;
		int roomIndex = 0;
		amphipodTypeWithIndexOfRoom = new LinkedHashMap<>();
		amphipodTypeWithRoom = new LinkedHashMap<>();
		for (List<AmphipodType> amphipodTypes : freeSpacesInHallWay) {
			counter += amphipodTypes.size();
			if (roomIndex < rooms.size()) {
				Room room = rooms.get(roomIndex++);
				amphipodTypeWithIndexOfRoom.put(room.getAmphipodTypeNeeded(), counter++);
				amphipodTypeWithRoom.put(room.getAmphipodTypeNeeded(), room);
			}
		}
		maxNr = counter;
		occupiedPlaces = new TreeMap<>();
	}

	public AmphipodSystem(Map<Room, Integer> locationRooms, int acquiredWeight, Map<Integer, AmphipodType> occupiedPlaces, int maxNr) {
		amphipodTypeWithIndexOfRoom = locationRooms.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().getAmphipodTypeNeeded(), Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
		amphipodTypeWithRoom = locationRooms.entrySet().stream()
				.collect(Collectors.toMap(e -> e.getKey().getAmphipodTypeNeeded(), Map.Entry::getKey, (a, b) -> a, LinkedHashMap::new));

		this.acquiredWeight = acquiredWeight;
		this.occupiedPlaces = new TreeMap<>(occupiedPlaces);
		this.maxNr = maxNr;


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
		double weightForInWrongRoom = amphipodTypeWithRoom.values().stream()
				.mapToDouble(this::getWeightForWrongLocated)
				.sum();
		double weightForBetweenRooms = occupiedPlaces.entrySet().stream()
				.mapToDouble(this::getDistanceToCorrectRoom)
				.sum();


		return weightForInWrongRoom + weightForBetweenRooms + acquiredWeight;
	}

	private double getWeightForWrongLocated(Room room) {
		return room.getWrongLocated().stream()
				.mapToDouble(aw -> getDistanceBetweenRooms(aw, room.getAmphipodTypeNeeded()))
				.sum();
	}

	private double getDistanceBetweenRooms(Room.AmphipodWithWeight e, AmphipodType amphipodTypeNeeded) {
		double abs = Math.abs(amphipodTypeWithIndexOfRoom.get(amphipodTypeNeeded) - amphipodTypeWithIndexOfRoom.get(e.type()));
		double v = e.weight() + abs + 1.5;
		return v * e.type().getEnergyRequiredForStep();
	}

	private double getDistanceToCorrectRoom(Map.Entry<Integer, AmphipodType> e) {
		double v = Math.abs(amphipodTypeWithIndexOfRoom.get(e.getValue()) - e.getKey()) + 1.5;
		return v * e.getValue().getEnergyRequiredForStep();
	}


}
