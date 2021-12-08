package be.durvenproeven.aoc2021.day8;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static be.durvenproeven.aoc2021.day8.StringHelper.normalize;
import static org.assertj.core.api.Assertions.assertThat;

class MapperTest {

	@Test
	void getMapping_SimpleExample() {
		assertThat(new Mapper(new DisplayLine(
				"acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
		)
		).getMap()).containsAllEntriesOf(Map.of(
				normalize("acedgfb"), 8,
				normalize("cdfbe"), 5,
				normalize("gcdfa"), 2,
				normalize("fbcad"), 3,
				normalize("dab"), 7,
				normalize("cefabd"), 9,
				normalize("cdfgeb"), 6,
				normalize("eafb"), 4,
				normalize("cagedb"), 0,
				normalize("ab"), 1
				)
		);
	}
}