package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.Coordinates;
import be.durvenproeven.aoc2021.NumberUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChitonPathFinder {
	private final Coordinates maxCoordinate;
	private final List<List<Integer>> riskLevels;
	private final Coordinates coordinatesToReach;

	public ChitonPathFinder(List<String> listRiskLevels) {
		riskLevels= listRiskLevels.stream()
				.map(NumberUtils::toIntList)
				.collect(Collectors.toList());
		maxCoordinate = new Coordinates(riskLevels.size(), riskLevels.get(0).size());
		coordinatesToReach = new Coordinates(riskLevels.size() - 1, riskLevels.get(0).size() - 1);
	}

	public ChitonPath findShortestPathFromStartToEnd(){
		ChitonPath chitonPath = new ChitonPath(new Coordinates(0, 0), getValue(new Coordinates(0, 0)));

		Map<Coordinates, ChitonPath> shortestPath= new HashMap<>();
		shortestPath.put(new Coordinates(0,0), chitonPath);

		PriorityQueue<ChitonPath> queue = new PriorityQueue<>(Comparator.comparing(ChitonPath::getRiskAfterFirst));
		queue.add(chitonPath);


		while (shortestPath.size() < (coordinatesToReach.getY()+1)* (coordinatesToReach.getX()+1) && !shortestPath.containsKey(coordinatesToReach)) {
			ChitonPath nextShortestPath = queue.poll();
			Map<Coordinates, ChitonPath> map = nextShortestPath.getLastCoordinates().getCardinalNeighbours().stream()
					.filter(this::isValid)
					.filter(o -> !shortestPath.containsKey(o))
					.collect(Collectors.toMap(Function.identity(), co -> getShortestPath(co, shortestPath), this::getSmallest));

			shortestPath.putAll(map);
			queue.addAll(map.values());
		}

		return shortestPath.get(coordinatesToReach);
	}

	private ChitonPath getSmallest(ChitonPath cp1, ChitonPath cp2) {
		return Stream.of(cp1, cp2).min(Comparator.comparing(ChitonPath::getRiskAfterFirst)).orElseThrow();
	}

	private ChitonPath getShortestPath(Coordinates co, Map<Coordinates, ChitonPath> shortestPath) {
		ChitonPath chitonPath = co.getCardinalNeighbours().stream()
				.map(shortestPath::get)
				.filter(Objects::nonNull)
				.min(Comparator.comparing(ChitonPath::getRiskAfterFirst))
				.orElseThrow();

		return chitonPath.addStep(co, getValue(co));
	}

	private int getValue(Coordinates coordinates){
		if (isValid(coordinates)){
			return riskLevels.get(coordinates.getX()).get(coordinates.getY());
		}
		throw new IllegalArgumentException(coordinates.toString());
	}

	private boolean isValid(Coordinates coordinates) {
		return coordinates.isInFirstQuadrant() && coordinates.isSmallerThen(maxCoordinate);
	}
}
