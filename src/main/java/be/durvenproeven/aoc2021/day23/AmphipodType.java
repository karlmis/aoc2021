package be.durvenproeven.aoc2021.day23;

public enum AmphipodType {
	Amber("A", 1),
	Bronze("B", 10),
	Copper("C", 100),
	Desert("D", 1000);

	private String symbol;
	private int energyRequiredForStep;

	AmphipodType(String symbol, int energyRequiredForStep) {
		this.symbol = symbol;
		this.energyRequiredForStep = energyRequiredForStep;
	}

	public int getEnergyRequiredForStep() {
		return energyRequiredForStep;
	}
}
