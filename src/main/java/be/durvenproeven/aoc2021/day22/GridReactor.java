package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.day19.Coordinates;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class GridReactor {
	List<List<BitSet>> content;
	private final int size;

	public GridReactor(int size) {
		this.size = size;
		content = new ArrayList<>(size * 2 + 1);
		for (int i = 0; i < size * 2 + 1; i++) {
			List<BitSet> e =
					IntStream.rangeClosed(0, size*2+1).mapToObj(ign -> new BitSet()).toList();
			content.add(e);
		}
	}

	public void turnOn(Coordinates start, Coordinates end) {
		if (Coordinates.hasOneSizeOutsideRange(start, end, -size, size) ){
			return;
		}
		for (int i = start.x() + size; i <= end.x() + size; i++) {
			for (int j = start.y() + size; j <= end.y() + size; j++) {
				BitSet bitSet = content.get(i).get(j);
				bitSet.set(start.z() + size, end.z() + 1 + size);
			}
		}
	}

	public void turnOff(Coordinates start, Coordinates end) {
		if (Coordinates.hasOneSizeOutsideRange(start, end, -size, size) ){
			return;
		}
		for (int i = start.x() + size; i <= end.x() + size; i++) {
			for (int j = start.y() + size; j <= end.y() + size; j++) {
				BitSet bitSet = content.get(i).get(j);
				bitSet.clear(start.z() + size, end.z() + 1 + size);
			}
		}

	}

	public int getNrOfCubesOn() {
		return content.stream()
				.flatMap(Collection::stream)
				.mapToInt(BitSet::cardinality)
				.sum();
	}

}
