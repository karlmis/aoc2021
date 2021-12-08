package be.durvenproeven.aoc2021.day8;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;

public class SevenSegmentSearch {

	private final List<String> nrsPart;

	public SevenSegmentSearch(List<String> inputLines) {
		nrsPart = inputLines.stream()
				.map(s -> StringUtils.substringAfter(s, "|".trim()))
				.toList();

	}

	public int getKnownNrs(){
		return (int) nrsPart.stream()
				.flatMap(s -> Arrays.stream(s.split(" ")))
				.filter(s -> List.of(2,3,4,7).contains(s.length()))
				.count();
	}

}
