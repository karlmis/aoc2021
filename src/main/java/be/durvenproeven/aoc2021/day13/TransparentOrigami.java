package be.durvenproeven.aoc2021.day13;

import be.durvenproeven.aoc2021.CoordinatesXY;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransparentOrigami {
	private final Set<CoordinatesXY> coordinatesSet = new HashSet<>();

	public TransparentOrigami(List<CoordinatesXY> coordinatesList) {
		this.coordinatesSet.addAll(coordinatesList);
	}

	public int getVisibleDots() {
		return coordinatesSet.size();
	}

	public TransparentOrigami foldHorizontal(int y) {
		List<CoordinatesXY> newCoordinates = coordinatesSet.stream()
				.filter(co -> co.y() != y)
				.map(co -> co.withHorizontalReflection(y))
				.toList();
		return new TransparentOrigami(newCoordinates);
	}


	public TransparentOrigami foldVertical(int x) {
		List<CoordinatesXY> newCoordinates = coordinatesSet.stream()
				.filter(co -> co.x() != x)
				.map(co -> co.withVerticalReflection(x))
				.toList();
		return new TransparentOrigami(newCoordinates);
	}

	public void toPrettyString(){
		Map<Integer, List<CoordinatesXY>> collect = coordinatesSet.stream()
				.collect(Collectors.groupingBy(CoordinatesXY::y));
		int maxY = collect.keySet().stream()
				.mapToInt(Integer::intValue)
				.max().orElse(-1);
		int maxX = collect.values().stream()
				.flatMap(List::stream)
				.mapToInt(CoordinatesXY::x)
				.max().orElse(-1);
		for (int i = 0; i <= maxY; i++) {
			List<Integer> xCoordinates = collect.get(i).stream()
					.mapToInt(CoordinatesXY::x)
					.sorted()
					.boxed()
					.toList();
			StringBuilder sb= new StringBuilder();
			for (int j = 0; j <= maxX; j++) {
				if (xCoordinates.contains(j)){
					sb.append("#");
				}
				else {
					sb.append(".");
				}
			}
			System.out.println(sb);
		}

	}
}
