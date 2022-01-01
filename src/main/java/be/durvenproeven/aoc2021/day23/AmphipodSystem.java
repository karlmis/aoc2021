package be.durvenproeven.aoc2021.day23;

import be.durvenproeven.aoc2021.CollectionUtils;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AmphipodSystem {
	private final int maxNr;
	private final int roomSize;

	private SortedMap<Room, Integer> locationRooms;
	private final SortedMap<Integer, AmphipodType> occupiedPlaces;

	public AmphipodSystem(Map<Room, Integer> locationRooms, Map<Integer, AmphipodType> occupiedPlaces, int maxNr) {
		this.locationRooms = new TreeMap<>(locationRooms);
		roomSize = locationRooms.keySet().iterator().next().getSize();
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
		Map<Room, Integer> locationRooms = new LinkedHashMap<>();
		for (List<AmphipodType> amphipodTypes : freeSpacesInHallWay) {
			counter += amphipodTypes.size();
			if (roomIndex < rooms.size()) {
				Room room = rooms.get(roomIndex++);
				locationRooms.put(room, counter++);
			}
		}

		return new AmphipodSystem(locationRooms, new TreeMap<>(), counter);
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


		return weightForInWrongRoom + weightForBetweenRooms;
	}

	private double getWeightForWrongLocated(Room room) {
		double sum = room.getWrongLocated().stream()
				.mapToDouble(aw -> getDistanceBetweenRooms(aw, room.getAmphipodTypeNeeded(), room))
				.sum();
		return sum;
	}

	private double getDistanceBetweenRooms(Room.AmphipodWithDistance e, AmphipodType amphipodTypeNeeded, Room room) {
		Room targetRoom = locationRooms.keySet().stream()
				.filter(r -> r.getAmphipodTypeNeeded() == e.type())
				.findFirst().orElseThrow();
		double abs = Math.abs(locationIndexOf(amphipodTypeNeeded) - locationIndexOf(e.type()));
		double v = e.weight() + abs + targetRoom.getAverageValue();
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
		Room room = locationRooms.keySet().stream()
				.filter(r -> r.getAmphipodTypeNeeded() == e.getValue())
				.findFirst().orElseThrow();

		double v = Math.abs(locationIndexOf(e.getValue()) - e.getKey()) + room.getAverageValue();
		return v * e.getValue().getEnergyRequiredForStep();
	}

	public Map<AmphipodSystem,Double> toNextTurn() {
		Map<AmphipodSystem, Double> amphipodSystems = new HashMap<>();//assumpiton no duplicates possible
		for (Map.Entry<Room, Integer> entry : locationRooms.entrySet()) {
			Room room = entry.getKey();
			if (!room.hasWrongValues()) {
				continue;
			}
			for (Integer freeSpace : getFreeSpaces(entry.getValue())) {
				Map<Integer, AmphipodType> newOccupiedPlaces = CollectionUtils.createMapWith(occupiedPlaces, freeSpace, room.getTop());
				Map<Room, Integer> newLocationRooms = locationRooms.entrySet().stream()
						.map(e -> removeTopFromCorrectRoom(e, room))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
				AmphipodSystem key = new AmphipodSystem(newLocationRooms, newOccupiedPlaces, maxNr);
				amphipodSystems.put(key, (double) weightedDistanceForTop(room, freeSpace));
			}
		}
//		for (Map.Entry<Room, Integer> entry : locationRooms.entrySet()) {
//			Room room = entry.getKey();
//			if (!room.hasWrongValues()) {
//				continue;
//			}
//
//		}

		for (Map.Entry<Integer, AmphipodType> entry : occupiedPlaces.entrySet()) {
			Room destinationRoom = locationRooms.keySet().stream()
					.filter(r -> r.getAmphipodTypeNeeded() == entry.getValue())
					.findFirst().orElseThrow();
			if (noOccupiedPlacesBetweenLocationAndCorrectRoom(entry) && !destinationRoom.hasWrongValues()) {
				Map.Entry<Room, Integer> roomWithIndex = locationRooms.entrySet().stream()
						.filter(r -> r.getKey().getAmphipodTypeNeeded() == entry.getValue())
						.findFirst().orElseThrow();
				OptionalInt distanceToFreePlace = roomWithIndex.getKey().getDistanceToFreePlace();
				if (distanceToFreePlace.isEmpty()) {
					continue;
				}
				Map<Room, Integer> newLocationRooms = locationRooms.entrySet().stream()
						.map(e -> addToCorrectRoom(e, entry.getValue()))
						.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

				Map<Integer, AmphipodType> newOccupiedPlaces =
						CollectionUtils.createMapWithout(occupiedPlaces, entry.getKey(), entry.getValue());
				int distanceBetweenIndexes = Math.abs(roomWithIndex.getValue() - entry.getKey());
				double newExtraWeight = entry.getValue().getEnergyRequiredForStep() * (distanceToFreePlace.getAsInt() + distanceBetweenIndexes);//TODO
				amphipodSystems.put(new AmphipodSystem(newLocationRooms, newOccupiedPlaces, maxNr), newExtraWeight);
			}
		}
		return amphipodSystems;
	}

	private boolean noOccupiedPlacesBetweenLocationAndCorrectRoom(Map.Entry<Integer, AmphipodType> entry) {
		Integer roomLocation = locationIndexOf(entry.getValue());
		Integer occupationLocation = entry.getKey();
		if (roomLocation < occupationLocation) {
			return occupiedPlaces.subMap(roomLocation, occupationLocation).isEmpty();
		} else return occupiedPlaces.subMap(occupationLocation + 1, roomLocation).isEmpty();
	}

	public boolean isComplete(){
		return occupiedPlaces.isEmpty() && locationRooms.keySet().stream().allMatch(Room::isComplete);
	}

	public String toPrettyString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(IntStream.range(0,maxNr)
				.mapToObj(occupiedPlaces::get)
				.map(t -> Optional.ofNullable(t).map(AmphipodType::getSymbol).orElse("."))
				.collect(Collectors.joining())).append("\n");
		IntStream.range(0, roomSize).forEach( i ->
			stringBuilder.append("  ").append(locationRooms.keySet().stream()
					.map(r -> r.getAtLocation(i).map(AmphipodType::getSymbol).orElse("."))
					.collect(Collectors.joining("#"))).append("+\n"));


		return stringBuilder.toString();
	}

	private List<Integer> getFreeSpaces(Integer index) {
		List<Integer> res = new ArrayList<>();

		for (int i = index + 1; i < maxNr && !occupiedPlaces.containsKey(i); i++) {
			if (!locationRooms.containsValue(i)) {
				res.add(i);
			}
		}
		for (int i = index - 1; i >= 0 && !occupiedPlaces.containsKey(i); i--) {
			if (!locationRooms.containsValue(i)) {
				res.add(i);
			}
		}
		return res;
	}

	private int weightedDistanceForTop(Room room, Integer freeSpace) {
		Room.AmphipodWithDistance amphipodWithDistance = room.getWrongLocated().get(0);
		int distanceBetweenLocations = Math.abs(locationIndexOf(room.getAmphipodTypeNeeded()) - freeSpace);
		int weight = amphipodWithDistance.weight() + distanceBetweenLocations;
		return weight * amphipodWithDistance.type().getEnergyRequiredForStep();
	}

	private Map.Entry<Room, Integer> removeTopFromCorrectRoom(Map.Entry<Room, Integer> e, Room room) {
		if (e.getKey() != room) {
			return e;
		}
		return new AbstractMap.SimpleImmutableEntry<>(room.withoutTop(), e.getValue());
	}

	private Map.Entry<Room, Integer> addToCorrectRoom(Map.Entry<Room, Integer> e, AmphipodType amphipodType) {
		if (e.getKey().getAmphipodTypeNeeded() != amphipodType) {
			return e;
		}
		return new AbstractMap.SimpleImmutableEntry<>(e.getKey().withCorrectTop(), e.getValue());
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		AmphipodSystem that = (AmphipodSystem) o;
		return maxNr == that.maxNr && Objects.equals(locationRooms, that.locationRooms) && Objects.equals(occupiedPlaces, that.occupiedPlaces);
	}

	@Override
	public int hashCode() {
		return Objects.hash(maxNr, locationRooms, occupiedPlaces);
	}

	@Override
	public String toString() {
		return "AmphipodSystem{" +
				"maxNr=" + maxNr +
				", locationRooms=" + locationRooms +
				", occupiedPlaces=" + occupiedPlaces +
				'}';
	}
}