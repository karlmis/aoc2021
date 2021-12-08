package be.durvenproeven.aoc2021.day8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

import static be.durvenproeven.aoc2021.day8.StringHelper.countMatch;
import static be.durvenproeven.aoc2021.day8.StringHelper.matches;
import static java.util.Collections.emptyList;


public class Mapper {

	private final Map<String, Integer> map;
	private final DisplayLine displayLine;

	int getOutputValue() {
		return displayLine.getSum(map);
	}

	Mapper(DisplayLine displayLine) {
		this.displayLine = displayLine;
		map = calculateMap(displayLine);
	}

	private Map<String, Integer> calculateMap(DisplayLine displayLine) {
		Map<String, Integer> map = new HashMap<>();
		Arrays.stream(Digits.values())
				.filter(digits -> digits.canCalculate(map))
				.forEach(digits -> addDigitToMapIfPossible(map, displayLine, digits));
		return map;
	}

	private void addDigitToMapIfPossible(Map<String, Integer> map, DisplayLine displayLine, Digits digits) {
		digits.getCodeFrom(displayLine.getCodes(), map)
				.ifPresent(code -> map.put(code, digits.getNr()));
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	private static String getString(Map<String, Integer> map, int nr) {
		return map.entrySet().stream()
				.filter(e -> nr == e.getValue())
				.map(Map.Entry::getKey)
				.findFirst().orElseThrow();
	}

	private enum Digits {
		ONE(1, 2, emptyList()),
		FOUR(4, 4, emptyList()),
		SEVEN(7, 3, emptyList()),
		EIGHT(8, 7, emptyList()),
		SIX(6, 6, List.of(1)) {
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> !matches(s1, getString(map, 1));
			}
		},
		NINE(9, 6, List.of(4)) {
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> matches(s1, getString(map, 4));
			}
		},
		ZERO(0, 6, List.of(4,6)){
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> !matches(s1, getString(map, 4))
						&& !matches(s1, getString(map, 6));
			}
		},
		THREE(3, 5, List.of(1)){
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> matches(s1, getString(map, 1));
			}
		},
		TWO(2, 5, List.of(3,6)){
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> !matches(s1, getString(map, 3))
						&& countMatch(s1, getString(map, 6)) == 4;
			}
		},
		FIVE(5, 5, List.of(3,6)){
			@Override
			public Predicate<String> getPredicate(Map<String, Integer> map) {
				return s1 -> !matches(s1, getString(map, 3))
						&& countMatch(s1, getString(map, 6)) == 5;
			}
		};

		private final int nr;
		private final int nrOfSegments;
		private final List<Integer> needsValues;

		Digits(int nr, int nrOfSegments, List<Integer> needsValues) {
			this.nr = nr;
			this.nrOfSegments = nrOfSegments;
			this.needsValues = needsValues;
		}

		public Predicate<String> getPredicate(Map<String, Integer> map){
			return t-> true;
		}

		public int getNr() {
			return nr;
		}

		boolean canCalculate(Map<String, Integer> map) {
			return needsValues.stream().allMatch(map::containsValue);
		}

		Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
			return codes.stream().filter(s -> s.length() == nrOfSegments)
					.filter(getPredicate(map))
					.findFirst();
		}
	}

}
