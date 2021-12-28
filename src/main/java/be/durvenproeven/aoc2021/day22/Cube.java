package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.day19.Coordinates;
import be.durvenproeven.aoc2021.day19.Coordinates.Axes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class Cube {
	private static final BinaryOperator<Map<IntegerRange.Position, IntegerRange>> NO_MERGE_ALLOWED = (i1, i2) ->
	{
		throw new IllegalArgumentException("no merge allowed");
	};

	private Coordinates first, second;

	public Cube(Coordinates first, Coordinates second) {
		this.first = first;
		this.second = second;
	}

	public Cube(IntegerRange xRange, IntegerRange yRange, IntegerRange zRange) {
		this(new Coordinates(xRange.getMin(), yRange.getMin(), zRange.getMin()),
				new Coordinates(xRange.getMax(), yRange.getMax(), zRange.getMax()));
	}

	public long getSize(){
		return Arrays.stream(Axes.values())
				.map(this::getIntegerRange)
				.mapToLong(IntegerRange::getSize)
				.reduce(1, (a, b) -> a * b);
	}


	public boolean overlaps(Cube other) {
		return Arrays.stream(Axes.values())
				.allMatch(ax -> getIntegerRange(ax).overlapsWith(other.getIntegerRange(ax)));
	}

	private IntegerRange getIntegerRange(Axes ax) {
		return first.getIntegerRange(second, ax);
	}

	public Optional<Cube> intersection(Cube other) {
		List<IntegerRange> integerRanges = Arrays.stream(Axes.values())
				.map(ax -> getIntegerRange(ax).intersection(other.getIntegerRange(ax)))
				.flatMap(Optional::stream)
				.toList();
		if (integerRanges.size() != 3) {
			return Optional.empty();
		}
		return Optional.of(new Cube(
				new Coordinates(integerRanges.get(0).getMin(), integerRanges.get(1).getMin(), integerRanges.get(2).getMin()),
				new Coordinates(integerRanges.get(0).getMax(), integerRanges.get(1).getMax(), integerRanges.get(2).getMax())
		));
	}

	public List<Cube> difference(Cube other) {
		if (!this.overlaps(other)) {
			return List.of(this);
		}
		Map<Axes, Map<IntegerRange.Position, IntegerRange>> overlappings = getOverlappings(other);
		ArrayList<Cube> cubes = new ArrayList<>();
		for (Map.Entry<IntegerRange.Position, IntegerRange> entry : overlappings.get(Axes.X).entrySet()) {
			if (entry.getKey()== IntegerRange.Position.OVERLAPPING){
				cubes.addAll(getCubes(overlappings, entry.getValue()));
			} else {
				cubes.add(new Cube(entry.getValue(), getIntegerRange(Axes.Y), getIntegerRange(Axes.Z)));
			}
		}
		return cubes;
	}

	public boolean contains(Cube other){
		return Arrays.stream(Axes.values())
				.allMatch(ax -> getIntegerRange(ax).contains(other.getIntegerRange(ax)));
	}

	private List<Cube> getCubes(Map<Axes, Map<IntegerRange.Position, IntegerRange>> overlappings, IntegerRange integerRangeForX){
		ArrayList<Cube> cubes = new ArrayList<>();
		for (Map.Entry<IntegerRange.Position, IntegerRange> entry : overlappings.get(Axes.Y).entrySet()) {
			if (entry.getKey()== IntegerRange.Position.OVERLAPPING){
				for (Map.Entry<IntegerRange.Position, IntegerRange> entryI : overlappings.get(Axes.Z).entrySet()) {
					if (entryI.getKey()!= IntegerRange.Position.OVERLAPPING) {
						cubes.add(new Cube(integerRangeForX, entry.getValue(), entryI.getValue()));
					}
				}
			} else {
				cubes.add(new Cube(integerRangeForX, entry.getValue(), getIntegerRange(Axes.Z)));
			}
		}
		return cubes;
	}

	Map<Axes, Map<IntegerRange.Position, IntegerRange>> getOverlappings(Cube other) {
		return Arrays.stream(Axes.values())
				.collect(toMap(
						Function.identity(),
						ax -> getIntegerRange(ax).getPartsComparedTo(other.getIntegerRange(ax)),
						NO_MERGE_ALLOWED,
						() -> new EnumMap<>(Axes.class)
				));
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cube cube = (Cube) o;
		return Arrays.stream(Axes.values())
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
