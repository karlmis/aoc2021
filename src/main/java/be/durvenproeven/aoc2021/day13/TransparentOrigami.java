package be.durvenproeven.aoc2021.day13;

import be.durvenproeven.aoc2021.Coordinates;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TransparentOrigami {
	private final Set<Coordinates> coordinatesSet = new HashSet<>();

	public TransparentOrigami(List<Coordinates> coordinatesList) {
		this.coordinatesSet.addAll(coordinatesList);
	}

	public int getVisibleDots() {
		return coordinatesSet.size();
	}

	public TransparentOrigami foldHorizontal(int y) {
		List<Coordinates> newCoordinates = coordinatesSet.stream()
				.filter(co -> co.y() != y)
				.map(co -> co.withHorizontalReflection(y))
				.toList();
		return new TransparentOrigami(newCoordinates);
	}


	public TransparentOrigami foldVertical(int x) {
		List<Coordinates> newCoordinates = coordinatesSet.stream()
				.filter(co -> co.x() != x)
				.map(co -> co.withVerticalReflection(x))
				.toList();
		return new TransparentOrigami(newCoordinates);
	}

	public void toPrettyString(){
		Map<Integer, List<Coordinates>> collect = coordinatesSet.stream()
				.collect(Collectors.groupingBy(coordinates2 -> coordinates2.y()));
		int maxY = collect.keySet().stream()
				.mapToInt(Integer::intValue)
				.max().orElse(-1);
		int maxX = collect.values().stream()
				.flatMap(List::stream)
				.mapToInt(coordinates1 -> coordinates1.x())
				.max().orElse(-1);
		for (int i = 0; i <= maxY; i++) {
			List<Integer> xCoordinates = collect.get(i).stream()
					.mapToInt(coordinates -> coordinates.x())
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
