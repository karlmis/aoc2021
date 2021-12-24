package be.durvenproeven.aoc2021.day24;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class InputValues {
	private List<Integer> inputs;
	private int inputCounter = 0;
	private long inputNr;

	public InputValues(long inputNr) {
		this.inputNr = inputNr;
		inputs = new ArrayList<>();
		while (inputNr > 0) {
			int nextNr = (int) (inputNr % 10);
			inputs.add(0, nextNr);
			inputNr /= 10;
		}
	}

	public InputValues(List<Integer> inputs) {
		this.inputCounter = 0;
		this.inputs = inputs;
	}

	public int nrOfDecimalNrs() {
		return inputs.size();
	}

	public InputValues next() {
		int index = lastIndexToChange();
		if (index == 7) {
			System.out.println(inputs);
		}
		List<Integer> newInput = IntStream.range(0, 14)
				.map(i -> toNr(inputs, index, i))
				.boxed()
				.toList();
		return new InputValues(newInput);
	}

	private int toNr(List<Integer> inputs, int index, int i) {
		if (i< index){
			return inputs.get(i);
		}
		if (i== index){
			return inputs.get(i)-1;
		}
		return 9;
	}

	private int lastIndexToChange() {
		int index = 13;
		while (inputs.get(index) == 1) {
			index--;
		}
		return index;
	}

	public int readInput() {
		Preconditions.checkState(inputCounter < inputs.size());
		return inputs.get(inputCounter++);
	}

	@Override
	public String toString() {
		return "InputValues{" +
				"inputs=" + inputs +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InputValues that = (InputValues) o;
		return inputCounter == that.inputCounter && inputs.equals(that.inputs);
	}

	@Override
	public int hashCode() {
		return Objects.hash(inputs, inputCounter);
	}
}
