package be.durvenproeven.aoc2021.day24;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.atIndex;

class AluTest {

	@Test
	void calculate_FirstExample() {
		Alu alu = new Alu(List.of(
				"inp x",
				"mul x -1")
		);
		assertThat(alu.calculate(new InputValues(1L))).contains(-1, atIndex(1));
		assertThat(alu.calculate(new InputValues(2L))).contains(-2, atIndex(1));

	}

	@Test
	void calculate_SecondExample() {
		Alu alu = new Alu(List.of(
				"inp z",
				"inp x",
				"mul z 3",
				"eql z x")
		);
		assertThat(alu.calculate(new InputValues(13L))).contains(1, atIndex(3));
		assertThat(alu.calculate(new InputValues(14L))).contains(0, atIndex(3));
	}

	@Test
	void calculate_ThirdExample() {
		Alu alu = new Alu(List.of(
				"inp w",
				"add z w",
				"mod z 2",
				"div w 2",
				"add y w",
				"mod y 2",
				"div w 2",
				"add x w",
				"mod x 2",
				"div w 2",
				"mod w 2")
		);
		assertThat(alu.calculate(new InputValues(4L))).containsExactly(0, 1, 0, 0);

		assertThat(alu.calculate(new InputValues(9L))).containsExactly(1, 0, 0, 1);
	}

	@Disabled
	@Test
	void calculate_RealExample() {
		List<String> inputs = LineResolver.getStringStreamOfFile("day24monad.txt").toList();
		Alu alu = new Alu(inputs);

		InputValues inputValues = new InputValues(99_999_999_999_999L);
		InputValues inputValues1 = Stream.iterate(inputValues, InputValues::next).parallel()
				.filter(iv -> iv.nrOfDecimalNrs() == 14)
				.filter(iv -> Optional.ofNullable(alu.calculate(iv)).map(l-> l.get(3) == 0).orElse(false))
				.findAny().orElseThrow();
		assertThat(inputValues1).isNull();
	}

	@Test
	void calculate_RealExample_wrongoutput() {
		List<String> inputs = LineResolver.getStringStreamOfFile("day24monad.txt").toList();
		Alu alu = new Alu(inputs);

		InputValues inputValues = new InputValues(99_999_999_995_814L);
		List<Integer> values = alu.calculate(inputValues);
//		assertThat(values.get(3)).isEqualTo(-1000);
	}

	private boolean containsZero(long counter) {
		if (counter % 10000 == 0) {
			System.out.println(counter);
		}
		long toCompare = 10;
		for (int i = 0; i < 13; i++) {
			if (counter % toCompare == 0) {
				return true;
			}
			toCompare *= 10;
		}
		return false;
	}


}