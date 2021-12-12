package be.durvenproeven.aoc2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

public record CavesPathFinder(List<CavesConnection> connections,
                              BiPredicate<SmallCaves, String> smallCaveNotAllowed) {

	public List<List<String>> getPaths(String from, String to) {
		List<CavesPath> startingCavePaths = connections.stream()
				.filter(c -> c.contains(from))
				.map(c -> new CavesPath(from, to, c))
				.toList();

		List<CavesPath> res = getPaths(startingCavePaths, new ArrayList<>());

		return res.stream()
				.map(CavesPath::getConnectionStrings)
				.toList();
	}

	private List<CavesPath> getPaths(List<CavesPath> paths, List<CavesPath> alreadyDone) {
		if (paths.isEmpty()) {
			return alreadyDone;
		}
		Map<Boolean, List<CavesPath>> collect = paths.stream()
				.flatMap(path -> connections.stream().flatMap(con -> path.addConnection(con, smallCaveNotAllowed).stream()))
				.collect(groupingBy(CavesPath::isFinished));

		alreadyDone.addAll(collect.getOrDefault(true, emptyList()));
		return getPaths(collect.getOrDefault(false, emptyList()), alreadyDone);
	}

}
