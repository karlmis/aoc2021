package be.durvenproeven.aoc2021.day24;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class InputValuesTest {

	@Test
	void next_first() {
		InputValues inputValues = new InputValues(99_999_999_999_999L);
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_999L));
		inputValues= inputValues.next();
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_998L));

		assertThat(inputValues.nrOfDecimalNrs()).isEqualTo(14);
	}
	@Test
	void next() {
		InputValues inputValues = new InputValues(99_999_999_999_999L);

		inputValues = doNext(inputValues, 9 - 1);
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_991L));
		inputValues = inputValues.next();
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_989L));

		inputValues = doNext(inputValues, 8*9-1);
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_911L));
		inputValues = inputValues.next();
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_899L));

		inputValues = doNext(inputValues,9*9-1);
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_811L));
		inputValues= inputValues.next();
		assertThat(inputValues).isEqualTo(new InputValues(99_999_999_999_799L));


	}

	private InputValues doNext(InputValues inputValues, int nrOfTimes) {
		for (int i = 0; i < nrOfTimes; i++) {
			inputValues = inputValues.next();
		}
		return inputValues;
	}
}