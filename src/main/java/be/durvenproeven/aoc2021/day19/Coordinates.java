package be.durvenproeven.aoc2021.day19;

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

	private static int nrOf(int nr, int divider) {
		int i = nr % (divider * 2);
		if (i > divider) {
			return 1;
		}
		return -1;
	}


}
