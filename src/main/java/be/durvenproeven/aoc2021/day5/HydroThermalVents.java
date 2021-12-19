package be.durvenproeven.aoc2021.day5;

import be.durvenproeven.aoc2021.CoordinatesXY;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class HydroThermalVents {
	private final List<SimpleLine> lines;

	public HydroThermalVents(List<SimpleLine> lines) {
		this.lines = lines;
	}

	public int getNrOfOverlaps() {
		return getNrOfOverlaps(sl -> true);
	}

	public int getNrOfOverlaps(Predicate<SimpleLine> predicate) {
		Map<CoordinatesXY, Long> collect = lines.stream()
				.filter(predicate)
				.flatMap(l -> l.getCoordinatesOnLine().stream())
				.collect(groupingBy(Function.identity(), counting()));
		return (int) collect.entrySet().stream()
				.filter(e -> e.getValue() > 1)
				.count();
	}


}
