package be.durvenproeven.aoc2021.day22;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerRangeTest {

	@Test
	void overlapsWith() {
		assertThat(new IntegerRange(1,5).overlapsWith(new IntegerRange(1,5))).isTrue();
		assertThat(new IntegerRange(1,5).overlapsWith(new IntegerRange(4,7))).isTrue();
		assertThat(new IntegerRange(1,5).overlapsWith(new IntegerRange(-3,1))).isTrue();

		assertThat(new IntegerRange(1,5).overlapsWith(new IntegerRange(6,10))).isFalse();
	}

	@Test
	void intersection() {
		assertThat(new IntegerRange(1,5).intersection(new IntegerRange(1,5))).get().isEqualTo(new IntegerRange(1,5));
		assertThat(new IntegerRange(1,5).intersection(new IntegerRange(4,7))).get().isEqualTo(new IntegerRange(4,5));
		assertThat(new IntegerRange(1,5).intersection(new IntegerRange(-3,1))).get().isEqualTo(new IntegerRange(1,1));

		assertThat(new IntegerRange(1,5).intersection(new IntegerRange(6,10))).isEmpty();

	}
}