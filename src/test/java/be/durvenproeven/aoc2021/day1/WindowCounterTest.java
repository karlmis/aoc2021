package be.durvenproeven.aoc2021.day1;

import org.junit.jupiter.api.Test;

import static be.durvenproeven.aoc2021.day1.WindowCounter.startingCounter;
import static org.assertj.core.api.Assertions.assertThat;

class WindowCounterTest {

	@Test
	void withNextValue_StartingSituation() {
		assertThat(startingCounter().getNrOfTimesIncreased()).isZero();

		assertThat(startingCounter().withNextValue(1).getNrOfTimesIncreased()).isZero();
		assertThat(startingCounter().withNextValue(1).withNextValue(2).getNrOfTimesIncreased()).isZero();
		assertThat(startingCounter().withNextValue(1).withNextValue(2).withNextValue(3).getNrOfTimesIncreased()).isZero();
	}

	@Test
	void withNextValue() {
		WindowCounter windowCounter = startingCounter()
				.withNextValue(10)
				.withNextValue(11)
				.withNextValue(12);

		assertThat(windowCounter.withNextValue(10).getNrOfTimesIncreased()).isZero();
		assertThat(windowCounter.withNextValue(13).getNrOfTimesIncreased()).isOne();
	}
}