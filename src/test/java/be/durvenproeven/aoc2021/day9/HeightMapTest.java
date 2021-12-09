package be.durvenproeven.aoc2021.day9;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class HeightMapTest {

	@Test
	void getLowPoints() {
		HeightMap heightMap = new HeightMap(List.of(
				"2199943210" ,
				"3987894921" ,
				"9856789892" ,
				"8767896789" ,
				"9899965678"));

		assertThat(heightMap.getLowPoints())
				.containsExactlyInAnyOrder(1,0,5,5);
		assertThat(heightMap.getSumRiskLevel()).isEqualTo(15);
	}

	@Test
	void getLowPoints_RealExample() {
		List<String> lines = LineResolver.getStringStreamOfFile("day9lowpoints.txt").collect(Collectors.toList());
		HeightMap heightMap = new HeightMap(lines);

		assertThat(heightMap.getSumRiskLevel()).isEqualTo(530);
	}

	@Test
	void getBasinSizes() {
		HeightMap heightMap = new HeightMap(List.of(
				"2199943210" ,
				"3987894921" ,
				"9856789892" ,
				"8767896789" ,
				"9899965678"));

		assertThat(heightMap.getBasinSizes())
				.containsExactlyInAnyOrder(3,9,14,9);
		assertThat(heightMap.getBasinNumber()).isEqualTo(1134);
	}

	@Test
	void getBasinSizes_RealExample() {
		List<String> lines = LineResolver.getStringStreamOfFile("day9lowpoints.txt").collect(Collectors.toList());
		HeightMap heightMap = new HeightMap(lines);

		assertThat(heightMap.getBasinNumber()).isEqualTo(1019494);
	}
}