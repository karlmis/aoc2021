package be.durvenproeven.aoc2021.day19;

import be.durvenproeven.aoc2021.day22.IntegerRange;

import java.util.ArrayList;
import java.util.List;

public record Coordinates(int x, int y, int z) {
	public Coordinates minus(Coordinates other) {
		return new Coordinates(other.x - x, other.y - y, other.z() - z);
	}

	public Coordinates plus(Coordinates other) {
		return new Coordinates(other.x + x, other.y + y, other.z() + z);
	}

	public Coordinates reverse() {
		return new Coordinates((-1) * x, (-1) * y, (-1) * z);
	}

	public static List<Coordinates> getBisections() {
		ArrayList<Coordinates> coordinates = new ArrayList<>();
		for (int i = -1; i <= 1; i += 2) {
			for (int j = -1; j <= 1; j += 2) {
				for (int k = -1; k <= 1; k += 2) {
					coordinates.add(new Coordinates(i, j, k));
				}
			}
		}
		return coordinates;
	}

	public Coordinates limitTo(int min, int max){
		return new Coordinates(
				limit(min, max, x),
				limit(min, max, y),
				limit(min, max, z)
		);
	}

	public static boolean hasOneSizeOutsideRange(Coordinates co1, Coordinates co2, int min, int max){
		return (co1.x() < min && co2.x() < min)
				|| (co1.y() < min && co2.y() < min)
				|| (co1.z() < min && co2.z() < min) ||
				(co1.x() > max && co2.x() > max)
				|| (co1.y() > max && co2.y() > max)
				|| (co1.z() > max && co2.z() > max);
	}

	private int limit(int min, int max, int nr){
		if (nr > max){
			return max;
		}
		if (nr < min){
			return min;
		}
		return nr;
	}

	private static int nrOf(int nr, int divider) {
		int i = nr % (divider * 2);
		if (i > divider) {
			return 1;
		}
		return -1;
	}

	public enum Axes{X,Y,Z}

	public IntegerRange getIntegerRange(Coordinates other, Axes ax){
		return switch (ax) {
			case X -> new IntegerRange(x(), other.x());
			case Y -> new IntegerRange(y(), other.y());
			case Z -> new IntegerRange(z(), other.z());
		};
	}

}
