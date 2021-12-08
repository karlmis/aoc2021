package be.durvenproeven.aoc2021.day8;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.durvenproeven.aoc2021.day8.StringHelper.normalize;

public class SevenSegmentSearch {

	private final List<DisplayLine> diplays;

	public SevenSegmentSearch(List<String> inputLines) {
		diplays = inputLines.stream()
				.map(DisplayLine::new)
				.toList();
	}

	public int getKnownNrs() {
		return diplays.stream()
				.mapToInt(displayLine -> displayLine.getKnownNrs(List.of(2, 3, 4, 7)))
				.sum();
	}

	public int getOuputNr(){
		return diplays.stream()
				.mapToInt(d -> d.getSum(new Mapper(d).getMap()))
				.sum();
	}


	//TODO
	public Map<String, Integer> getMapping() {
		for (DisplayLine displayLine : diplays) {
			Map<String, Integer> map = new Mapper(displayLine).getMap();
			if (map != null) return map;
		}
		return null;
	}




}
