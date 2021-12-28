package be.durvenproeven.aoc2021.day22;

import org.junit.jupiter.api.Test;

import static be.durvenproeven.aoc2021.day22.IntegerRange.Position.AFTER;
import static be.durvenproeven.aoc2021.day22.IntegerRange.Position.BEFORE;
import static be.durvenproeven.aoc2021.day22.IntegerRange.Position.OVERLAPPING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

class IntegerRangeTest {

	@Test
	void overlapsWith() {
		assertThat(new IntegerRange(1, 5).overlapsWith(new IntegerRange(1, 5))).isTrue();
		assertThat(new IntegerRange(1, 5).overlapsWith(new IntegerRange(4, 7))).isTrue();
		assertThat(new IntegerRange(1, 5).overlapsWith(new IntegerRange(-3, 1))).isTrue();

		assertThat(new IntegerRange(1, 5).overlapsWith(new IntegerRange(6, 10))).isFalse();
	}

	@Test
	void intersection() {
		assertThat(new IntegerRange(1, 5).intersection(new IntegerRange(1, 5))).get().isEqualTo(new IntegerRange(1, 5));
		assertThat(new IntegerRange(1, 5).intersection(new IntegerRange(4, 7))).get().isEqualTo(new IntegerRange(4, 5));
		assertThat(new IntegerRange(1, 5).intersection(new IntegerRange(-3, 1))).get().isEqualTo(new IntegerRange(1, 1));

		assertThat(new IntegerRange(1, 5).intersection(new IntegerRange(6, 10))).isEmpty();
	}

	@Test
	void difference() {
		IntegerRange range_5_10 = new IntegerRange(5, 10);

		assertThat(range_5_10.difference(new IntegerRange(-1, 4))).containsExactly(range_5_10);

		assertThat(range_5_10.difference(new IntegerRange(-1, 6))).containsExactly(new IntegerRange(7, 10));
		assertThat(range_5_10.difference(new IntegerRange(8, 12))).containsExactly(new IntegerRange(5, 7));
		assertThat(range_5_10.difference(new IntegerRange(12, 16))).containsExactly(range_5_10);

		assertThat(range_5_10.difference(new IntegerRange(4, 11))).isEmpty();
	}

	@Test
	void difference_inside() {

		IntegerRange range_5_10 = new IntegerRange(5, 10);

		assertThat(range_5_10.difference(new IntegerRange(6, 8))).containsExactly(new IntegerRange(5, 5), new IntegerRange(9, 10));
		assertThat(range_5_10.difference(new IntegerRange(8, 10))).containsExactly(new IntegerRange(5, 7));
		assertThat(range_5_10.difference(new IntegerRange(5, 7))).containsExactly(new IntegerRange(8, 10));
	}

	@Test
	void getPartsComparedTo() {
		IntegerRange range_5_10 = new IntegerRange(5, 10);

		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(6, 8))).containsExactly(
				entry(BEFORE, new IntegerRange(5, 5)),
				entry(OVERLAPPING, new IntegerRange(6, 8)),
				entry(AFTER, new IntegerRange(9, 10))
		);
		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(8, 10))).containsExactly(
				entry(BEFORE, new IntegerRange(5, 7)),
				entry(OVERLAPPING, new IntegerRange(8, 10))
		);
		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(5, 7))).containsExactly(
				entry(OVERLAPPING, new IntegerRange(5, 7)),
				entry(AFTER, new IntegerRange(8, 10))
		);
		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(3, 10))).containsExactly(
				entry(OVERLAPPING, new IntegerRange(5, 10))
		);
	}

	@Test
	void getPartsComparedTo_NoOverlapping() {
		IntegerRange range_5_10 = new IntegerRange(5, 10);

		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(11, 20))).containsExactly(
				entry(BEFORE, new IntegerRange(5, 10))
		);
		assertThat(range_5_10.getPartsComparedTo(new IntegerRange(1, 4))).containsExactly(
				entry(AFTER, new IntegerRange(5 , 10))
		);
	}

	@Test
	void getSize() {
		assertThat(new IntegerRange(5, 10).getSize()).isEqualTo(6);
		assertThat(new IntegerRange(-10, -5).getSize()).isEqualTo(6);
		assertThat(new IntegerRange(-2, 5).getSize()).isEqualTo(8);
	}
}