package be.durvenproeven.aoc2021.day1;

record WindowCounter(int nrOfTimesIncreased, int value0, int value1, int value2) {

	static WindowCounter startingCounter() {
		return new WindowCounter(-1, -1, -1, -1);
	}

	WindowCounter withNextValue(int newValue) {
		if (value0 < 0) {
			return new WindowCounter(nrOfTimesIncreased, newValue, -1, -1);
		}
		if (value1 < 0) {
			return new WindowCounter(nrOfTimesIncreased, value0, newValue, -1);
		}
		if (value2 < 0) {
			return new WindowCounter(0, value0, value1, newValue);
		}
		if (value1 + value2 + newValue > sum()) {
			return new WindowCounter(nrOfTimesIncreased + 1, value1, value2, newValue);
		}
		return new WindowCounter(nrOfTimesIncreased, value1, value2, newValue);
	}

	int getNrOfTimesIncreased() {
		if (isStartingCounter()) {
			return 0;
		}
		return nrOfTimesIncreased;
	}

	private boolean isStartingCounter() {
		return nrOfTimesIncreased < 0;
	}

	private int sum() {
		return value0 + value1 + value2;
	}
}
