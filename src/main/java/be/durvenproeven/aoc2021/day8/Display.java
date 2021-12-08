package be.durvenproeven.aoc2021.day8;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class Display {
	private static List<String> codes;
	private final String firstPart;
	private final String secondPart;
	private final List<String> firstCodes;
	private final List<String> secondCodes;

	public Display(String s) {
		String[] split = s.split("\\|");
		Preconditions.checkArgument(split.length == 2);
		firstPart = split[0].trim();
		secondPart = split[1].trim();//remove and make sure alphabetic
		firstCodes = Arrays.stream((firstPart.split(" "))).toList();
		secondCodes = Arrays.stream((secondPart.split(" "))).toList();
	}

	List<String> getCodes() {
		return Stream.concat(firstCodes.stream(), secondCodes.stream()).toList();
	}

	public String getSecondPart() {
		return secondPart;
	}

	public int getSum(Map<String, Integer> map) {
		return Arrays.stream((secondPart.split(" ")))
				.map(StringHelper::normalize)
				.map(map::get)
				.mapToInt(Integer::intValue)
				.reduce(0, (a, b) -> a * 10 + b);
	}
}
