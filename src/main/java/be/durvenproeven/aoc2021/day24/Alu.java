package be.durvenproeven.aoc2021.day24;

import java.util.List;
import java.util.function.BiFunction;

public class Alu {
	private final List<String> instructionStrings;

	private int regW;
	private int regX;
	private int regY;
	private int regZ;
	private final List<BiFunction<Registers, InputValues, Registers>> biConsumers;
	private final BiFunction<Registers, InputValues, Registers> bif;

	public Alu(List<String> instructionStrings) {
		this.instructionStrings = instructionStrings;
		biConsumers = instructionStrings.stream()
				.map(Instruction::findBiConsumer)
				.toList();
		bif = apply(biConsumers);


	}

	private BiFunction<Registers, InputValues, Registers> apply(List<BiFunction<Registers, InputValues, Registers>> biConsumers) {
		return (registers, input) -> biConsumers.stream()
				.reduce((bif1, bif2) -> bif1.andThen(reg -> reg == null ? null : bif2.apply(reg, input)))
				.orElseThrow()
				.apply(registers, input);
	}

	public List<Integer> calculate(InputValues inputValues) {
		Registers registers1 = bif.apply(new Registers(), inputValues);

		return registers1.getList();
		// wrong
	}

	public int getNr(String s) {
		if ("w".equals(s)) {
			return regW;
		}
		if ("x".equals(s)) {
			return regX;
		}
		if ("y".equals(s)) {
			return regY;
		}
		if ("z".equals(s)) {
			return regZ;
		}
		return Integer.parseInt(s);
	}

	public void setNr(String s, int i) {
		if ("w".equals(s)) {
			regW = i;
			return;
		}
		if ("x".equals(s)) {
			regX = i;
			return;
		}
		if ("y".equals(s)) {
			regY = i;
			return;
		}
		if ("z".equals(s)) {
			regZ = i;
			return;
		}
		throw new IllegalArgumentException("register not allowed: '" + s + "'");
	}

	@Override
	public String toString() {
		return "Alu{" +
				"regW=" + regW +
				", regX=" + regX +
				", regY=" + regY +
				", regZ=" + regZ +
				'}';
	}
}
