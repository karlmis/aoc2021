package be.durvenproeven.aoc2021.day21;

public class DeterministicDice {
	private int maxNr;
	private int lastRoll = 0;

	public DeterministicDice(int maxNr) {
		this.maxNr = maxNr;
	}

	public int getNextRolls(int nrOfTimes) {
		int sum = 0;
		for (int i = 0; i < nrOfTimes; i++) {
			sum += nextRoll();
		}
		return sum;
	}

	private int nextRoll() {
		return 1 + (lastRoll++ % maxNr);
	}

	public int getNrOfTimesRolled() {
		return lastRoll;
	}
}
