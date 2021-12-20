package be.durvenproeven.aoc2021.day19;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScannerMapMatcher {

	public static MatchResult match(ScannerMap first, ScannerMap second, int minNr) {
		for (Coordinates bisection : Coordinates.getBisections()) {
			MatchResult matching = first.getMatching(second.getCoordinatesWithOrientation(bisection), minNr);
			if (matching.hasMatch()) {
				return matching;
			}
		}
		return MatchResult.noMatch();
	}

	public static int getNrOfBeacons(List<ScannerMap> scannerMaps, int minNr) {
		Map<ScannerMap, List<Pair>> map = new HashMap<>();
		for (int i = 0; i < scannerMaps.size(); i++) {
			List<Pair> pairs = new ArrayList<>();
			for (int i1 = i; i1 < scannerMaps.size(); i1++) {
				MatchResult match = match(scannerMaps.get(i), scannerMaps.get(i1), minNr);
				if (match.hasMatch()) {
					pairs.add(new Pair(scannerMaps.get(i1), match));
				}
			}
			map.put(scannerMaps.get(i), pairs);
		}

		throw new UnsupportedOperationException("implement me!");
	}

	private static record Pair(ScannerMap map, MatchResult matchResult) {

	}
}
