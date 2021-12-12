package be.durvenproeven.aoc2021.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;

public class CavesPathFinder {
	private final List<CavesConnection> connections;
	private final BiPredicate<SmallCaves, String> smallCaveNotAllowed;

	public CavesPathFinder(List<CavesConnection> connections, BiPredicate<SmallCaves, String> smallCaveNotAllowed) {
		this.connections = connections;
		this.smallCaveNotAllowed = smallCaveNotAllowed;
	}

	public List<List<String>> getPaths(String from, String to){
		List<CavesPath> startingCavePaths = connections.stream().filter(c -> c.contains(from))
				.map(c -> new CavesPath(from, to, c))
				.toList();

		List<CavesPath> res= getPaths(startingCavePaths, new ArrayList<>());
		return res.stream().map(CavesPath::getConnectionStrings).toList();
	}

	private List<CavesPath> getPaths(List<CavesPath> paths, List<CavesPath> alreadyFound) {
		if (paths.isEmpty()){
			return alreadyFound;
		}
		Map<Boolean, List<CavesPath>> collect = paths.stream()
				.flatMap(path -> connections.stream().flatMap(con -> path.addConnection(con, smallCaveNotAllowed).stream()))
				.collect(Collectors.groupingBy(CavesPath::isFinished));

		alreadyFound.addAll(collect.getOrDefault(true, emptyList()));
		return getPaths(collect.getOrDefault(false, emptyList()), alreadyFound);
	}

}
