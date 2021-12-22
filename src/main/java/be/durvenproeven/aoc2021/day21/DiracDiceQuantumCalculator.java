package be.durvenproeven.aoc2021.day21;

public class DiracDiceQuantumCalculator {
	private int scoreToReach = 12;

	public long calculate(DiracDice diracDice) {
		if (diracDice.getScoreCurrentPlayer() == scoreToReach - 1) {
			return 27;
		}
		if (diracDice.getScoreCurrentPlayer() == scoreToReach - 2) {
			switch (diracDice.getLocationCurrentPlayer()) {
				case 1, 10, 9:
					return 27;
				case 2:
					//return 26 + calculate(new DiracDice())
			}
			return 27;
		}
		throw new UnsupportedOperationException("implement me!");
	}
}
