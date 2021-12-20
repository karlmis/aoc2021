package be.durvenproeven.aoc2021.day19;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

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
		LinkedHashMap<ScannerMap, List<Pair>> map = new LinkedHashMap<>();
		for (int i = 0; i < scannerMaps.size(); i++) {
			List<Pair> pairs = new ArrayList<>();
			for (int i1 = i+1; i1 < scannerMaps.size(); i1++) {
				MatchResult match = match(scannerMaps.get(i), scannerMaps.get(i1), minNr);
				if (match.hasMatch()) {
					pairs.add(new Pair(scannerMaps.get(i1), match));
				}
			}
			map.put(scannerMaps.get(i), pairs);
		}
		System.out.println(map);
		int total=0;
		for (ScannerMap scannerMap : map.keySet()) {
			List<Pair> pairs = map.values().stream()
					.flatMap(Collection::stream)
					.filter(p -> p.map == scannerMap)
					.toList();
			Preconditions.checkArgument(pairs.size()<2);
			if (pairs.size()==0){
				total+= scannerMap.getSize();
			}
			if (pairs.size()==1){
				total += scannerMap.getSize()-pairs.get(0).matchResult.nr();
			}
		}
		return total;
//		throw new UnsupportedOperationException("implement me!");
	}

	private static record Pair(ScannerMap map, MatchResult matchResult) {

	}
}
