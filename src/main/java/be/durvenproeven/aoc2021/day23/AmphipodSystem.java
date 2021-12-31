package be.durvenproeven.aoc2021.day23;

import be.durvenproeven.aoc2021.CollectionUtils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

public class AmphipodSystem {
	private final int acquiredWeight;
	private final int maxNr;

	private Map<Room, Integer> locationRooms;
	private final SortedMap<Integer, AmphipodType> occupiedPlaces;

	public AmphipodSystem(Map<Room, Integer> locationRooms, int acquiredWeight, Map<Integer, AmphipodType> occupiedPlaces, int maxNr) {
		this.locationRooms = locationRooms;
		this.acquiredWeight = acquiredWeight;
		this.occupiedPlaces = new TreeMap<>(occupiedPlaces);
		this.maxNr = maxNr;
	}

	public static AmphipodSystem createWithFreeSpaces(List<Room> rooms, List<Integer> freespacesBetweenRooms) {
		List<List<AmphipodType>> freeSpacesInHallWay = new ArrayList<>();
		freespacesBetweenRooms.stream()
				.map(AmphipodSystem::arrayListWithNullsAndSize)
				.forEach(freeSpacesInHallWay::add);
		int counter = 0;
		int roomIndex = 0;
		Map<Room, Integer> locationRooms= new LinkedHashMap<>();
		for (List<AmphipodType> amphipodTypes : freeSpacesInHallWay) {
			counter += amphipodTypes.size();
			if (roomIndex < rooms.size()) {
				Room room = rooms.get(roomIndex++);
				locationRooms.put(room, counter++);
			}
		}

		return new AmphipodSystem(locationRooms, 0, new TreeMap<>(), counter);
	}

	private static List<AmphipodType> arrayListWithNullsAndSize(Integer size) {
		return IntStream.range(0, size)
				.mapToObj(ign -> (AmphipodType) null)
				.toList();
	}


	public double getWeight() {
		double weightForInWrongRoom = locationRooms.keySet().stream()
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
		double abs = Math.abs(locationIndexOf(amphipodTypeNeeded) - locationIndexOf(e.type()));
		double v = e.weight() + abs + 1.5;
		return v * e.type().getEnergyRequiredForStep();
	}

	private Integer locationIndexOf(AmphipodType amphipodTypeNeeded) {
		return locationRooms.entrySet().stream()
				.filter(e -> e.getKey().getAmphipodTypeNeeded() == amphipodTypeNeeded)
				.map(Map.Entry::getValue)
				.findFirst()
				.orElseThrow();
	}

	private double getDistanceToCorrectRoom(Map.Entry<Integer, AmphipodType> e) {
		double v = Math.abs(locationIndexOf(e.getValue()) - e.getKey()) + 1.5;
		return v * e.getValue().getEnergyRequiredForStep();
	}

	public List<AmphipodSystem> toNextTurn() {
		List<Integer> freeSpaces = IntStream.range(0, maxNr)
				.filter(o -> !locationRooms.containsValue(o))
				.boxed()
				.toList();
		List<AmphipodSystem> amphipodSystems = new ArrayList<>();
		for (Room room : locationRooms.keySet()) {
			for (Integer freeSpace : freeSpaces) {
				Map<Integer, AmphipodType> newOccupiedPlaces = CollectionUtils.createMapWith(occupiedPlaces, freeSpace, room.getTop());

				amphipodSystems.add(new AmphipodSystem(null, acquiredWeight, newOccupiedPlaces, maxNr));
			}
		}
		return amphipodSystems;
	}


}
