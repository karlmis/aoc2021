package be.durvenproeven.aoc2021.day17;

import be.durvenproeven.aoc2021.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TrickShot {

	private final Coordinates minValue;
	private final Coordinates maxValue;

	public TrickShot(Coordinates minValue, Coordinates maxValue) {
		this.minValue = minValue;
		this.maxValue = maxValue;
	}

	public List<Coordinates> getLaunchCoordinates() {
		List<Integer> xValuesThatEndAtTArget = IntStream.iterate(1, x -> x + 1)
				.limit(20)
				.filter(x -> x * (x + 1) / 2 >= minValue.x())
				.filter(x -> x * (x + 1) / 2 <= maxValue.x())
				.boxed()
				.toList();
		List<Coordinates> res = getTheseWithChanceOfMaximalHeight(xValuesThatEndAtTArget);
		xValuesThatEndAtTArget.stream()
				.flatMap(x -> addExtraForXCoordanatesThatStayInRegion(x).stream())
				.forEach(res::add);


		return res;
	}

	private List<Coordinates> addExtraForXCoordanatesThatStayInRegion(int minimalNr) {
		return IntStream.range(minValue.y(), Math.abs(maxValue.y()) - 1)
				.mapToObj(y1 -> toYRanges(y1, minimalNr))
				.flatMap(List::stream)
				.filter(yRange -> yRange.getValue() <= maxValue.y())
				.filter(yRange -> yRange.getValue() >= minValue.y())
				.map(YRange::getEnd)
				.map(y -> new Coordinates(minimalNr, y))
				.collect(Collectors.toList());
	}

	private List<YRange> toYRanges(int y, int minimalNr) {
		List<YRange> yRanges = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			YRange e = new YRange(y - minimalNr + 1 - i, y);
			yRanges.add(e);
		}
		return yRanges;
	}

	private List<Coordinates> getTheseWithChanceOfMaximalHeight(List<Integer> xValues) {
		List<Integer> yValues = IntStream.rangeClosed(minValue.y(), maxValue.y())
				.map(x -> x * -1)
				.map(x -> x - 1)
				.boxed()
				.toList();
		List<Coordinates> res = new ArrayList<>();
		for (Integer xValue : xValues) {
			for (Integer yValue : yValues) {
				res.add(new Coordinates(xValue, yValue));
			}
		}
		return res;
	}

	private List<XRange> getInitialRanges() {
		if (maxValue.x() == 0) {
			return List.of(XRange.createWithOnly(0));
		}
		int biggestFromZero = Double.valueOf(Math.sqrt(maxValue.x() * 2)).intValue();
		List<XRange> newXRanges = new ArrayList<>();
		for (int i = 1; i < biggestFromZero; i++) {
			newXRanges.add(xRangeAround(maxValue.x(), i));
		}
		newXRanges.add(XRange.createTo(biggestFromZero));
		ArrayList<XRange> res = newXRanges.stream()
				.filter(xr -> xr.getValue() <= maxValue.x() && xr.getValue() >= minValue.x())
				.distinct()
				.collect(Collectors.toCollection(ArrayList::new));
		newXRanges.stream()
				.flatMap(xr -> xr.getPossibleOperations().stream().map(t -> t.apply(xr)))
				.filter(xr -> !res.contains(xr))
				.filter(xr -> xr.getValue() <= maxValue.x() && xr.getValue() >= minValue.x())
				.forEach(res::add);
		return res;
	}

	private XRange xRangeAround(int x, int size) {
		if (size == 1) {
			return XRange.createWithOnly(x);
		}
		int around = (size - 1) / 2;
		if (size % 2 == 0) {
			return XRange.create(x / size - 1 - around, x / size + around);
		} else {
			return XRange.create(x / size - around, x / size + around);
		}
	}

	private void getXRanges(Set<XRange> res, List<XRange> newXRanges) {
		List<XRange> newFoundXRanges = newXRanges.stream()
				.flatMap(xr -> xr.getPossibleOperations().stream().map(t -> t.apply(xr)))
				.filter(xr -> !res.contains(xr))
				.filter(xr -> xr.getValue() <= maxValue.x() && xr.getValue() >= minValue.x())
				.toList();
		res.addAll(newFoundXRanges);
		if (!newFoundXRanges.isEmpty()) {
			getXRanges(res, newFoundXRanges);
		}
	}

	public record LaunchCoordinates(XRange xRange, YRange yRange) {

		public Coordinates getStartingCoordinates() {
			return new Coordinates(xRange().getEnd(), yRange.getEnd());
		}
	}


}
