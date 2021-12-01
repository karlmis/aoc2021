package be.durvenproeven.aoc2021.day1;

class Counter {
	private final int nrOfTimesIncreased;
	private final int value;

	private Counter(int nrOfTimesIncreased, int valueToCompareWith) {
		this.nrOfTimesIncreased = nrOfTimesIncreased;
		this.value = valueToCompareWith;
	}

	static Counter startingCounter() {
		return new Counter(-1, 0);
	}

	Counter withNextValue(int newValue) {
		if (isStartingCounter()) {
			return new Counter(0, newValue);
		}
		if (newValue > value) {
			return increasedWith(newValue);
		}
		return sameWith(newValue);
	}

	private boolean isStartingCounter() {
		return nrOfTimesIncreased == -1;
	}

	private Counter sameWith(int newValue) {
		return new Counter(nrOfTimesIncreased, newValue);
	}

	private Counter increasedWith(int newValue) {
		return new Counter(nrOfTimesIncreased + 1, newValue);
	}

	int getNrOfTimesIncreased() {
		if( isStartingCounter()){
			return 0;
		}
		return nrOfTimesIncreased;
	}
}
