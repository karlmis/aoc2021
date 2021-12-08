package be.durvenproeven.aoc2021.day8;

import java.util.List;

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
				.mapToInt(d -> new Mapper(d).getOutputValue())
				.sum();
	}


}
