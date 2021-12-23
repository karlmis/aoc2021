package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.day19.Coordinates;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class Cube {
	private Coordinates first, second;

	public Cube(Coordinates first, Coordinates second) {
		this.first = first;
		this.second = second;
	}



	public boolean overlaps(Cube other){
		return Arrays.stream(Coordinates.Axes.values())
				.allMatch(ax -> getIntegerRange(ax).overlapsWith(other.getIntegerRange(ax)));
	}

	private IntegerRange getIntegerRange(Coordinates.Axes ax) {
		return first.getIntegerRange(second, ax);
	}

	public Optional<Cube> intersection(Cube other){
		List<IntegerRange> integerRanges = Arrays.stream(Coordinates.Axes.values())
				.map(ax -> getIntegerRange(ax).intersection(other.getIntegerRange(ax)))
				.flatMap(Optional::stream)
				.toList();
		if (integerRanges.size()!= 3){
			return Optional.empty();
		}
		return Optional.of(new Cube(
				new Coordinates(integerRanges.get(0).getMin(),integerRanges.get(1).getMin(),integerRanges.get(2).getMin()),
				new Coordinates(integerRanges.get(0).getMax(),integerRanges.get(1).getMax(),integerRanges.get(2).getMax())
				));
	}

	public List<Cube> difference(Cube other){
		throw new UnsupportedOperationException("implement me!");
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cube cube = (Cube) o;
		return  Arrays.stream(Coordinates.Axes.values())
				.allMatch(ax -> getIntegerRange(ax).equals(cube.getIntegerRange(ax)));

	}

	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

	@Override
	public String toString() {
		return "Cube{" +
				"first=" + first +
				", second=" + second +
				'}';
	}
}
