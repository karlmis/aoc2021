package be.durvenproeven.aoc2021.day8;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static be.durvenproeven.aoc2021.day8.StringHelper.normalize;

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

	private enum Digits {
		ONE(1, 2),
		FOUR(4, 4),
		SEVEN(7, 3),
		EIGHT(8, 7),
		SIX(6, 6) {
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(1);
			}

			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> !matches(s1, getString(map, 1))).findFirst();
			}
		},
		NINE(9, 6) {
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(4);
			}
			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> matches(s1, getString(map, 4)))
						.findFirst();
			}
		},
		ZERO(0, 6){
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(4) && map.containsValue(6);
			}

			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> !matches(s1, getString(map, 4)))
						.filter(s1 -> !matches(s1, getString(map, 6)))
						.findFirst();
			}
		},
		THREE(3, 5){
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(1);
			}

			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> matches(s1, getString(map, 1))).findFirst();
			}
		},
		TWO(2, 5){
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(3) && map.containsValue(6);
			}

			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> !matches(s1, getString(map, 3)))
						.filter(s1 -> match(s1, getString(map, 6)) == 4)
						.findFirst();
			}
		},
		FIVE(5, 5){
			@Override
			boolean canCalculate(Map<String, Integer> map) {
				return map.containsValue(3) && map.containsValue(6);
			}

			@Override
			Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
				return getCodesWithCorrectLength(codes)
						.filter(s1 -> !matches(s1, getString(map, 3)))
						.filter(s1 -> match(s1, getString(map, 6)) == 5)
						.findFirst();
			}
		};

		private final int nr;
		private final int nrOfSegments;

		Digits(int nr, int nrOfSegments) {
			this.nr = nr;
			this.nrOfSegments = nrOfSegments;
		}

		public int getNr() {
			return nr;
		}

		boolean canCalculate(Map<String, Integer> map) {
			return true;
		}

		Stream<String> getCodesWithCorrectLength(List<String> codes){
			return codes.stream().filter(s -> s.length() == nrOfSegments);
		}

		Optional<String> getCodeFrom(List<String> codes, Map<String, Integer> map) {
			return getCodesWithCorrectLength(codes).findFirst();
		}
	}

	private Map<String, Integer> calculateMap(DisplayLine displayLine) {
		Map<String, Integer> map = new HashMap<>();
		for (Digits digits : Digits.values()) {
			if (digits.canCalculate(map)) {
				digits.getCodeFrom(displayLine.getCodes(), map).ifPresent(code -> map.put(normalize(code), digits.getNr()));
			}
		}
		return map;
	}

	public Map<String, Integer> getMap() {
		return map;
	}

	private static boolean matches(String s, String toMatchCharacters) {
		return toMatchCharacters.chars()
				.allMatch(c -> StringUtils.contains(s, (char) c));
	}

	private static int match(String s, String toMatchCharacters) {
		return (int) toMatchCharacters.chars()
				.filter(c -> StringUtils.contains(s, (char) c))
				.count();

	}

	private static String getString(Map<String, Integer> map, int nr) {
		return map.entrySet().stream()
				.filter(e -> nr == e.getValue())
				.map(Map.Entry::getKey)
				.findFirst().orElseThrow();

	}

}
