package be.durvenproeven.aoc2021.day8;

import com.google.common.base.Preconditions;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

class DisplayLine {
	private final List<String> firstCodes;
	private final List<String> secondCodes;

	public DisplayLine(String s) {
		String[] split = s.split("\\|");
		Preconditions.checkArgument(split.length == 2);

		//remove and make sure alphabetic
		firstCodes = Arrays.stream((split[0].trim().split(" ")))
				.map(StringHelper::normalize)
				.toList();
		secondCodes = Arrays.stream((split[1].trim().split(" ")))
				.map(StringHelper::normalize)
				.toList();
	}

	List<String> getCodes() {
		return Stream.concat(firstCodes.stream(), secondCodes.stream()).toList();
	}

	public int getSum(Map<String, Integer> map) {
		return secondCodes.stream()
				.map(map::get)
				.mapToInt(Integer::intValue)
				.reduce(0, (a, b) -> a * 10 + b);
	}

	public int getKnownNrs(List<Integer> integers) {
		return (int) secondCodes.stream()
				.mapToInt(String::length)
				.filter(integers::contains)
				.count();
	}
}
