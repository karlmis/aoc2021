package be.durvenproeven.aoc2021;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.Collectors;

public class Grid {
	private final List<List<Integer>> riskLevels;
	private final CoordinatesXY maxCoordinates;


	public Grid(List<String> listRiskLevels) {
		riskLevels = listRiskLevels.stream()
				.map(NumberUtils::toIntList)
				.collect(Collectors.toList());
		maxCoordinates = new CoordinatesXY(riskLevels.size() - 1, riskLevels.get(0).size() - 1);
	}

	private Grid(List<List<Integer>> riskLevels, CoordinatesXY maxCoordinates) {
		this.riskLevels = riskLevels;
		this.maxCoordinates = maxCoordinates;
	}

	private boolean isValid(CoordinatesXY coordinates) {
		return coordinates.isInFirstQuadrant() && coordinates.isEqualOrSmallerThen(maxCoordinates);
	}

	public int getValue(CoordinatesXY coordinates) {
		if (isValid(coordinates)) {
			return riskLevels.get(coordinates.x()).get(coordinates.y());
		}
		throw new IllegalArgumentException(coordinates.toString());
	}

	public List<CoordinatesXY> getCardinalNeighbours(CoordinatesXY coordinates) {
		return coordinates.getCardinalNeighbours().stream()
				.filter(this::isValid)
				.toList();
	}

	public List<CoordinatesXY> getAllNeighbours(CoordinatesXY coordinates) {
		return coordinates.getAllNeighbours().stream()
				.filter(this::isValid)
				.toList();
	}

	public int getSize() {
		return (maxCoordinates.x() + 1) * (maxCoordinates.y() + 1);
	}

	public CoordinatesXY getMaxCoordinates() {
		return maxCoordinates;
	}

	public Grid increase(int horizontal, int vertical) {
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < riskLevels.size() * vertical; i++) {


			List<Integer> integers = new ArrayList<>();
			for (int j = 0; j < horizontal; j++) {
				integers.addAll(riskLevels.get(i % riskLevels.size()));
			}
			res.add(integers);

		}
		return new Grid(res, new CoordinatesXY((maxCoordinates.x() + 1) * horizontal - 1, (maxCoordinates.y() + 1) * vertical - 1));
	}

	public Grid increase(int horizontal, int vertical, IntUnaryOperator intUnaryOperator) {
		List<List<Integer>> res = new ArrayList<>();
		for (int i = 0; i < riskLevels.size() * vertical; i++) {


			List<Integer> integers = new ArrayList<>();
			for (int j = 0; j < horizontal; j++) {
				List<Integer> l = riskLevels.get(i % riskLevels.size())
						.stream()
						.map(times(intUnaryOperator,j+(i/ riskLevels.size())))
						.toList();
				integers.addAll(l);
			}
			res.add(integers);

		}
		return new Grid(res, new CoordinatesXY((maxCoordinates.x() + 1) * horizontal - 1, (maxCoordinates.y() + 1) * vertical - 1));
	}

	private Function<Integer, Integer> times(IntUnaryOperator intUnaryOperator, int times) {
		if (times==0){
			return i -> i;
		}
		IntUnaryOperator res= intUnaryOperator;
		for (int i = 1; i < times; i++) {
			res= res.andThen(intUnaryOperator);
		}
		return res::applyAsInt;
	}

	List<List<Integer>> getRiskLevels() {
		return riskLevels;
	}

	public boolean allHaveLevel(int nr){
		return riskLevels.stream()
				.flatMap(Collection::stream)
				.mapToInt(Integer::intValue)
				.allMatch(i -> i==nr);
	}

	public List<CoordinatesXY> withLevel(IntPredicate predicate){
		return CoordinatesXY.getAllCoordinates(maxCoordinates).stream()
				.filter(c -> predicate.test(getValue(c)))
				.toList();
	}

	public void apply(IntUnaryOperator operator){
		for (List<Integer> riskLevel : riskLevels) {
			for (int i = 0; i < riskLevel.size(); i++) {
				riskLevel.set(i,operator.applyAsInt(riskLevel.get(i)));
			}
		}
	}

	public void setValue(CoordinatesXY co, int nr){
		if (!isValid(co)) {
			throw new IllegalArgumentException();
		}
		riskLevels.get(co.x()).set(co.y(), nr);
	}

	public void updateValue(CoordinatesXY co, IntUnaryOperator operator){
		if (!isValid(co)) {
			throw new IllegalArgumentException();
		}
		Integer oldValue = riskLevels.get(co.x()).get(co.y());
		int newValue = operator.applyAsInt(oldValue);
		riskLevels.get(co.x()).set(co.y(), newValue);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Grid grid = (Grid) o;
		return Objects.equals(riskLevels, grid.riskLevels) && Objects.equals(maxCoordinates, grid.maxCoordinates);
	}

	@Override
	public int hashCode() {
		return Objects.hash(riskLevels, maxCoordinates);
	}
}
