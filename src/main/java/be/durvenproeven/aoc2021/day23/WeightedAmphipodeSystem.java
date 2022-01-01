package be.durvenproeven.aoc2021.day23;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class WeightedAmphipodeSystem {
	private AmphipodSystem amphipodSystem;
	private double acquiredWeight;

	public WeightedAmphipodeSystem(AmphipodSystem amphipodSystem, double acquiredWeight) {
		this.amphipodSystem = amphipodSystem;
		this.acquiredWeight = acquiredWeight;
	}

	public AmphipodSystem getAmphipodSystem() {
		return amphipodSystem;
	}

	public double getAcquiredWeight() {
		return acquiredWeight;
	}

	public double getWeight(){
		return acquiredWeight+ amphipodSystem.getWeight();
	}

	public List<WeightedAmphipodeSystem> toNextTurn(){
		Map<AmphipodSystem, Double> amphipodSystemDoubleMap = amphipodSystem.toNextTurn();
		return amphipodSystemDoubleMap.entrySet()
				.stream().map(e -> new WeightedAmphipodeSystem(e.getKey(), acquiredWeight+ e.getValue()))
				.toList();
	}

	public boolean isComplete() {
		return amphipodSystem.isComplete();
	}

	public String toPrettyStringComplete() {
		return amphipodSystem.toPrettyStringComplete()+"\n"+acquiredWeight;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		WeightedAmphipodeSystem that = (WeightedAmphipodeSystem) o;
		return Double.compare(that.acquiredWeight, acquiredWeight) == 0 && Objects.equals(amphipodSystem, that.amphipodSystem);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amphipodSystem, acquiredWeight);
	}

	@Override
	public String toString() {
		return "WeightedAmphipodeSystem{" +
				"amphipodSystem=" + amphipodSystem +
				", acquiredWeight=" + acquiredWeight +
				'}';
	}
}
