package be.durvenproeven.aoc2021.day8;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StringHelperTest {

	@Test
	void normalize() {
		assertThat(StringHelper.normalize("a")).isEqualTo("a");
		assertThat(StringHelper.normalize("ba")).isEqualTo("ab");
	}
}