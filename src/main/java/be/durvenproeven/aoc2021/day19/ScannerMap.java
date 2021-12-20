package be.durvenproeven.aoc2021.day19;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ScannerMap {
	private static final Comparator<Coordinates> COMPARATOR = Comparator.comparing(Coordinates::x).thenComparing(Coordinates::y).thenComparing(Coordinates::z);
	private final IntSummaryStatistics xSummaryStatistics;
	private final IntSummaryStatistics ySummaryStatistics;
	private final IntSummaryStatistics zSummaryStatistics;
	private List<Coordinates> coordinatesList;

	public List<Coordinates> getCoordinatesList() {
		return coordinatesList;
	}

	public ScannerMap(List<Coordinates> coordinatesList) {
		this.coordinatesList = coordinatesList;
		xSummaryStatistics = coordinatesList.stream()
				.mapToInt(Coordinates::x)
				.summaryStatistics();
		ySummaryStatistics = coordinatesList.stream()
				.mapToInt(Coordinates::y)
				.summaryStatistics();
		zSummaryStatistics = coordinatesList.stream()
				.mapToInt(Coordinates::z)
				.summaryStatistics();
	}

	public List<Coordinates> getCoordinatesWithOrientation(Coordinates bisection) {
		return coordinatesList.stream()
				.map(co -> changeX(co, bisection.x()))
				.map(co -> changeY(co, bisection.y()))
				.map(co -> changeZ(co, bisection.z()))
				.sorted(COMPARATOR)
				.toList();
	}//moet een CoordinatesWithPositionMeegeven

	private Coordinates changeX(Coordinates coordinates, int xChange) {
		if (xChange > 0) {
			return coordinates;
		}
		return new Coordinates(xSummaryStatistics.getMax() - coordinates.x(), coordinates.y(), coordinates.z());

	}

	private Coordinates changeY(Coordinates coordinates, int yChange) {
		if (yChange > 0) {
			return coordinates;
		}
		return new Coordinates(coordinates.x(), ySummaryStatistics.getMax() - coordinates.y(), coordinates.z());

	}
	private Coordinates changeZ(Coordinates coordinates, int zChange) {
		if (zChange > 0) {
			return coordinates;
		}
		return new Coordinates(coordinates.x(), coordinates.y(), zSummaryStatistics.getMax() - coordinates.z());
	}

	public int getSize(){
		return coordinatesList.size();
	}

	//List<CoordinatesWithPosition
	public MatchResult getMatching(List<Coordinates> otherCo, int minNr){
		Map<Coordinates, List<Pair>> diffWithPairs = coordinatesList.stream()
				.flatMap(co -> otherCo.stream().map(other -> new Pair(co, other)))
				.collect(Collectors.groupingBy(Pair::getDifference));

		//van beide ook de index
		return diffWithPairs.entrySet().stream()
				.filter(e -> e.getValue().size() >= minNr)
				.map(e -> new MatchResult(e.getValue().size(), e.getKey(), e.getValue().stream().map(Pair::left).toList()))
				.findFirst().orElse(MatchResult.noMatch());
	}

	private record Pair(Coordinates left, Coordinates right){
		Coordinates getDifference(){
			return right.minus(left);
		}
	};
}
