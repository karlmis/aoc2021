package be.durvenproeven.aoc2021.day3;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;

public class BinaryDiagnostic {
	private final int size;
	private final int gammaRate;
	private final List<Integer> nrs;

	public BinaryDiagnostic(List<String> binaries, int size) {
		this.size = size;
		nrs = binaries.stream()
				.map(s -> Integer.parseInt(s, 2))
				.toList();
		gammaRate= calculateGammaRate(binaries, size);
	}

	private int calculateGammaRate(List<String> binaries, int size) {
		int nrToBuildUp=0;
		for (int i = 0; i < size; i++) {
			int bitIndex = i;
			long count = nrs.stream()
					.filter(nr -> BigInteger.valueOf(nr).testBit(bitIndex))
					.count();
			if (count > binaries.size() / 2) {
				nrToBuildUp += powerOf2(bitIndex);
			}
		}
		return nrToBuildUp;
	}

	private int getSize(List<Integer> integers) {
		if (integers == null) {
			return 0;
		}
		return integers.size();
	}

	public int getOxyGeneratorRating() {
		Function<Map<Boolean, List<Integer>>, List<Integer>> selector = l -> selectMostFrequent(l, true);
		return reduceListTillOne(selector, size - 1, nrs);
	}

	public int getCo2ScrubberRating() {
		Function<Map<Boolean, List<Integer>>, List<Integer>> selector = l -> selectLeastCommon(l, false);
		return reduceListTillOne(selector, size - 1, nrs);
	}

	private int reduceListTillOne(Function<Map<Boolean, List<Integer>>, List<Integer>> selector, int bitIndex, List<Integer> nrs1) {
		if (nrs1.size() == 1) {
			return nrs1.get(0);
		}
		if (bitIndex < 0) {
			throw new IllegalArgumentException("bitIndex <0 and still too many items "+ nrs1);
		}
		Map<Boolean, List<Integer>> mappedByBit = groupByBitIndex(nrs1, bitIndex);
		List<Integer> remainingItems = selector.apply(mappedByBit);
		return reduceListTillOne(selector, bitIndex - 1, remainingItems);
	}

	private Map<Boolean, List<Integer>> groupByBitIndex(List<Integer> nrs1, int downwardCounter) {
		return nrs1.stream()
				.collect(groupingBy(nr -> BigInteger.valueOf(nr).testBit(downwardCounter)));
	}

	private List<Integer> selectMostFrequent(Map<Boolean, List<Integer>> collect, boolean preferenceWhenSameSize) {
		if (getSize(collect.get(preferenceWhenSameSize)) < getSize(collect.get(!preferenceWhenSameSize))) {
			return collect.get(!preferenceWhenSameSize);
		}
		return collect.get(preferenceWhenSameSize);
	}

	private List<Integer> selectLeastCommon(Map<Boolean, List<Integer>> collect, boolean preferenceWhenSameSize) {
		if (getSize(collect.get(!preferenceWhenSameSize)) < getSize(collect.get(preferenceWhenSameSize))) {
			return collect.get(!preferenceWhenSameSize);
		}
		return collect.get(preferenceWhenSameSize);
	}

	private int powerOf2(int powerOf2) {
		int res = 1;
		for (int i = 0; i < powerOf2; i++) {
			res *= 2;
		}
		return res;
	}

	public int getGammaRate() {
		return gammaRate;
	}

	public int getEpsilonRate() {
		return powerOf2(size) - 1 - gammaRate;
	}
}
