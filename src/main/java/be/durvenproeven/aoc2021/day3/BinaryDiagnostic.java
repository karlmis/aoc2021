package be.durvenproeven.aoc2021.day3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

public class BinaryDiagnostic {
	private final int size;
	private int gammaRate;
	private int ogygenGeneratorRating;
	private final List<Integer> nrs;

	public BinaryDiagnostic(List<String> binaries, int size) {
		this.size = size;
		nrs = binaries.stream()
				.map(s -> Integer.parseInt(s, 2))
				.toList();
		for (int i = 0; i < size; i++) {
			int powerOf2 = i;
			long count = nrs.stream()
					.filter(nr -> BigInteger.valueOf(nr).testBit(powerOf2))
					.count();
			if (count > binaries.size() / 2) {
				gammaRate += powerOf2(powerOf2);
			}
		}


	}

	private int getSize(List<Integer> integers) {
		if (integers == null) {
			return 0;
		}
		return integers.size();
	}

	public int getOgyGeneratorRating() {
		List<Integer> nrs1 = new ArrayList<>(nrs);
		int downwardCounter = size - 1;
		while (nrs1.size() > 1) {
			Map<Boolean, List<Integer>> mappedByBit = groupByBitIndex(nrs1, downwardCounter);
			nrs1 = selectMostFrequentOrTrue(mappedByBit);
			downwardCounter--;
		}
		return nrs1.get(0);
	}

	private Map<Boolean, List<Integer>> groupByBitIndex(List<Integer> nrs1, int downwardCounter) {
		return nrs1.stream()
				.collect(groupingBy(nr -> BigInteger.valueOf(nr).testBit(downwardCounter)));
	}

	private List<Integer> selectMostFrequentOrTrue(Map<Boolean, List<Integer>> collect) {
		if (getSize(collect.get(false)) > getSize(collect.get(true))) {
			return collect.get(false);
		}
		return collect.get(true);
	}

	public int getCo2ScrubberRating() {
		List<Integer> nrs1 = new ArrayList<>(nrs);
		int downwardCounter = size - 1;
		while (nrs1.size() > 1) {
			Map<Boolean, List<Integer>> mappedByBit = groupByBitIndex(nrs1, downwardCounter);
			nrs1 = selectLeastCommonOrFalse(mappedByBit);
			downwardCounter--;
		}
		return nrs1.get(0);
	}

	private List<Integer> selectLeastCommonOrFalse(Map<Boolean, List<Integer>> collect) {
		if (getSize(collect.get(true)) < getSize(collect.get(false))) {
			return collect.get(true);
		}
		return collect.get(false);
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
