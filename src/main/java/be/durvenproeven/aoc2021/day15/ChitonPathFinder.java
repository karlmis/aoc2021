package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.Coordinates;

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
	private final Grid riskLevels;

	public ChitonPathFinder(List<String> listRiskLevels) {
		riskLevels= new Grid(listRiskLevels);
	}

	public ChitonPath findShortestPathFromStartToEnd(){
		ChitonPath chitonPath = new ChitonPath(new Coordinates(0, 0), riskLevels.getValue(new Coordinates(0, 0)));

		Map<Coordinates, ChitonPath> shortestPath= new HashMap<>();
		shortestPath.put(new Coordinates(0,0), chitonPath);

		PriorityQueue<ChitonPath> queue = new PriorityQueue<>(Comparator.comparing(ChitonPath::getRiskAfterFirst));
		queue.add(chitonPath);


		while (shortestPath.size() < riskLevels.getSize() && !shortestPath.containsKey(riskLevels.getMaxCoordinates())) {
			ChitonPath nextShortestPath = queue.poll();
			Map<Coordinates, ChitonPath> map = riskLevels.getCardinalNeighbours(nextShortestPath.getLastCoordinates()).stream()
					.filter(o -> !shortestPath.containsKey(o))
					.collect(Collectors.toMap(Function.identity(), co -> getShortestPath(co, shortestPath), this::getSmallest));

			shortestPath.putAll(map);
			queue.addAll(map.values());
		}

		return shortestPath.get(riskLevels.getMaxCoordinates());
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

		return chitonPath.addStep(co, riskLevels.getValue(co));
	}

}
