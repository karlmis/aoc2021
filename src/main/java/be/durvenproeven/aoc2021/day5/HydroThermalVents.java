package be.durvenproeven.aoc2021.day5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;

public class HydroThermalVents {


	private final Map<Coordinates, Long> collect;

	public HydroThermalVents(List<LineFollowingAxes> lines) {
		collect = lines.stream().flatMap(l -> l.getCoordinatesOnLine().stream())
				.collect(Collectors.groupingBy(Function.identity(), counting()));
	}

	public int getNrOfOverlaps(){
		return (int) collect.entrySet().stream()
				.filter(e -> e.getValue()>1)
				.count();

	}
}
