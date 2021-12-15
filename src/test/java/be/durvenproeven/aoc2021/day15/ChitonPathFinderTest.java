package be.durvenproeven.aoc2021.day15;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ChitonPathFinderTest {

	@Test
	void findShortestPathFromStartToEnd() {
		ChitonPathFinder chitonPathFinder = new ChitonPathFinder(List.of(
				"1163751742",
				"1381373672",
				"2136511328",
				"3694931569",
				"7463417111",
				"1319128137",
				"1359912421",
				"3125421639",
				"1293138521",
				"2311944581"));

		ChitonPath shortestPath = chitonPathFinder.findShortestPathFromStartToEnd();
		assertThat(shortestPath.getRiskAfterFirst()).isEqualTo(40);
	}
	@Test
	void findShortestPathFromStartToEnd_RealExample() {
		List<String> listRiskLevels = LineResolver.getStringStreamOfFile("day15chitons.txt").toList();
		ChitonPathFinder chitonPathFinder = new ChitonPathFinder(listRiskLevels);

		ChitonPath shortestPath = chitonPathFinder.findShortestPathFromStartToEnd();
		assertThat(shortestPath.getRiskAfterFirst()).isEqualTo(720);//not 722
	}
}