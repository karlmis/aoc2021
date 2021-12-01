package be.durvenproeven.aoc2021.day1;

import org.junit.jupiter.api.Test;

import static be.durvenproeven.aoc2021.day1.Counter.startingCounter;
import static org.assertj.core.api.Assertions.assertThat;

class CounterTest {

	@Test
	void withNextValue_StartingCounter() {
		assertThat(startingCounter().getNrOfTimesIncreased()).isZero();
	}

	@Test
	void withNextValue_OnlyOne() {
		assertThat(startingCounter().withNextValue(10).getNrOfTimesIncreased()).isZero();
	}

	@Test
	void withNextValue_Multiple() {
		Counter counter0Value10 = startingCounter().withNextValue(10);

		assertThat(counter0Value10.withNextValue(1).getNrOfTimesIncreased()).isZero();
		assertThat(counter0Value10.withNextValue(11).getNrOfTimesIncreased()).isOne();
	}


}