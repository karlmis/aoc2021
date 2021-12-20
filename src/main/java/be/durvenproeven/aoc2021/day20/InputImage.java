package be.durvenproeven.aoc2021.day20;

import be.durvenproeven.aoc2021.CoordinatesXY;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class InputImage {
	private List<String> input;
	private String infinityString;


	public InputImage(List<String> input, String infinityString) {
		this.input = input;
		this.infinityString = infinityString;
	}

	public String translate(CoordinatesXY coordinatesXY, String algortihm) {
		int nr = 0;
		for (int yDelta = -1; yDelta <= 1; yDelta++) {
			for (int xDelta = -1; xDelta <= 1; xDelta++) {
				int nr1 = getBitAt(new CoordinatesXY(coordinatesXY.x() + xDelta, coordinatesXY.y() + yDelta));
				nr = nr * 2 + nr1;
			}
		}
		//System.out.println("nr = " + nr + "," + algortihm.charAt(nr)+"for "+coordinatesXY);
		return getAlgorithmString(algortihm, nr);
	}

	private String getAlgorithmString(String algortihm, int nr) {
		return algortihm.substring(nr, nr + 1);
	}

	private int getBitAt(CoordinatesXY coordinatesXY) {
		if (coordinatesXY.x() < 0 || coordinatesXY.y() < 0) {
			return "#".equals(infinityString) ? 1 : 0;
		}
		if (coordinatesXY.y() >= input.size() || coordinatesXY.x() >= input.get(coordinatesXY.y()).length()) {
			return "#".equals(infinityString) ? 1 : 0;
		}
		return "#".equals(getAlgorithmString(input.get(coordinatesXY.y()), coordinatesXY.x())) ? 1 : 0;
	}

	public int getLightenedUp() {
		return input.stream()
				.mapToInt(s -> StringUtils.countMatches(s, "#"))
				.sum();
	}

	public CoordinatesXY getSize() {
		return new CoordinatesXY(input.get(0).length(), input.size());
	}

	public String toPrettyString() {
		return input.stream().collect(Collectors.joining("\n"));
	}

	public InputImage enhance(String algorithm) {
		Preconditions.checkArgument(algorithm.length() == 512, algorithm.length());
		List<String> res = new ArrayList<>();
		for (int y = -1; y <= input.size(); y++) {
			StringBuilder b = new StringBuilder();
			for (int x = -1; x <= input.get(0).length(); x++) {
				b.append(translate(new CoordinatesXY(x, y), algorithm));
			}
			res.add(b.toString());
		}
		String substring = getAlgorithmString(algorithm, ".".equals(infinityString) ? 0 : 511);
		return new InputImage(res, substring);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		InputImage that = (InputImage) o;
		return Objects.equals(input, that.input);
	}

	@Override
	public int hashCode() {
		return Objects.hash(input);
	}

	@Override
	public String toString() {
		return "InputImage{" +
				"input=" + input +
				'}';
	}
}
