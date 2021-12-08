package be.durvenproeven.aoc2021.day8;

import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static be.durvenproeven.aoc2021.day8.StringHelper.normalize;

public class Mapper {

	private final HashMap<String, Integer> map;
	private final DisplayLine displayLine;

	int getOutputValue(){
		return displayLine.getSum(map);
	}

	Mapper(DisplayLine displayLine) {
		this.displayLine = displayLine;
		this.map = new HashMap<>();
		List<String> strings = displayLine.getCodes();
		if (!map.containsValue(8)) {
			strings.stream().filter(s1 -> s1.length() == 7).findFirst()
					.ifPresent(x -> map.put(normalize(x), 8));
		}
		if (!map.containsValue(4)) {
			strings.stream().filter(s1 -> s1.length() == 4).findFirst()
					.ifPresent(x -> map.put(normalize(x), 4));
		}
		if (!map.containsValue(1)) {
			strings.stream().filter(s1 -> s1.length() == 2).findFirst()
					.ifPresent(x -> map.put(normalize(x), 1));
		}
		if (!map.containsValue(7)) {
			strings.stream().filter(s1 -> s1.length() == 3).findFirst()
					.ifPresent(x -> map.put(normalize(x), 7));
		}
		if (!map.containsValue(9) && map.containsValue(1)) {//nr 9
			strings.stream()
					.filter(s1 -> s1.length() == 6)
					.filter(s1 -> matches(s1, getString(map, 4)))
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 9));
		}
		if (!map.containsValue(6) && map.containsValue(1)) {//nr 6
			strings.stream()
					.filter(s1 -> s1.length() == 6)
					.filter(s1 -> !matches(s1, getString(map, 1)))
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 6));
		}
		if (!map.containsValue(0) && map.containsValue(1) && map.containsValue(6) && map.containsValue(4)) {//nr 6
			strings.stream()
					.filter(s1 -> s1.length() == 6)
					.filter(s1 -> !matches(s1, getString(map, 4)))
					.filter(s1 -> !matches(s1, getString(map, 6)))
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 0));
		}
		if (!map.containsValue(3) && map.containsValue(1)) {//nr 3
			strings.stream()
					.filter(s1 -> s1.length() == 5)
					.filter(s1 -> matches(s1, getString(map, 1)))
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 3));
		}
		if (!map.containsValue(5) && map.containsValue(6) && map.containsValue(3)) {//nr 3
			strings.stream()
					.filter(s1 -> s1.length() == 5)
					.filter(s1 -> !matches(s1, getString(map, 3)))
					.filter(s1 -> match(s1, getString(map, 6)) == 5)
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 5));
		}
		if (!map.containsValue(2) && map.containsValue(6) && map.containsValue(3)) {//nr 3
			strings.stream()
					.filter(s1 -> s1.length() == 5)
					.filter(s1 -> !matches(s1, getString(map, 3)))
					.filter(s1 -> match(s1, getString(map, 6)) == 4)
					.findFirst()
					.ifPresent(x -> map.put(normalize(x), 2));
		}

	}

	public HashMap<String, Integer> getMap() {
		return map;
	}

	private boolean matches(String s, String toMatchCharacters) {
		return toMatchCharacters.chars()
				.allMatch(c -> StringUtils.contains(s, (char) c));
	}

	private int match(String s, String toMatchCharacters) {
		return (int) toMatchCharacters.chars()
				.filter(c -> StringUtils.contains(s, (char) c))
				.count();

	}

	private String getString(Map<String, Integer> map, int nr) {
		return map.entrySet().stream()
				.filter(e -> nr == e.getValue())
				.map(Map.Entry::getKey)
				.findFirst().orElseThrow();

	}

}
