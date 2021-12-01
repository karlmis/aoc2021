package be.durvenproeven.aoc2021.day1;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DebthResolverTest {
	@Test
	void getNrOfIncreased() {
		assertThat(new DebthResolver(List.of(1, 2)).getNrOfIncreased()).isEqualTo(2 - 1);
		assertThat(new DebthResolver(List.of(2, 1)).getNrOfIncreased()).isEqualTo(0);
	}

	@Test
	void getNrOfIncreased_GivenSimpleTest() {
		assertThat(new DebthResolver(List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)).getNrOfIncreased())
				.isEqualTo(7);
	}

	@Test
	void getNrOfIncreased_GivenInput() {
		List<Integer> inputNrs = LineResolver.getStringStreamOfFile("day1.txt")
				.map(Integer::parseInt)
				.toList();
		assertThat(new DebthResolver(inputNrs).getNrOfIncreased()).isEqualTo(1233);
	}

	@Test
	void getNrOfWindowsIncreased() {
		assertThat(new DebthResolver(List.of(1,2,3,4)).getNrOfWindowsIncreased())
				.isEqualTo(1);
	}

	@Test
	void getNrOfWindowsIncreased_GivenSimpleTest() {
		assertThat(new DebthResolver(List.of(199, 200, 208, 210, 200, 207, 240, 269, 260, 263)).getNrOfWindowsIncreased())
				.isEqualTo(5);
	}

	@Test
	void getNrOfWindowsIncreased_GivenInput() {
		List<Integer> inputNrs = LineResolver.getStringStreamOfFile("day1.txt")
				.map(Integer::parseInt)
				.toList();
		assertThat(new DebthResolver(inputNrs).getNrOfWindowsIncreased()).isEqualTo(1275);
	}

}