package be.durvenproeven.aoc2021.day7;

public class CrabCalculator {
	private final CrabSwarm crabSwarm;

	public CrabCalculator(CrabSwarm crabSwarm) {
		this.crabSwarm = crabSwarm;
	}

	public int getLeastFuelPosition(){
		int minDistance= Integer.MAX_VALUE;
		int index= -1;
		for (int i = crabSwarm.getMin(); i < crabSwarm.getMax(); i++) {
			int distance = crabSwarm.getDistance(i);
			if (distance < minDistance){
				minDistance= distance;
				index= i;
			}
		}
		return index;
	}

	public int getLeastFuelPart2Position(){
		int minDistance= Integer.MAX_VALUE;
		int index= -1;
		for (int i = crabSwarm.getMin(); i < crabSwarm.getMax(); i++) {
			int distance = crabSwarm.getSecondDistance(i);
			if (distance < minDistance){
				minDistance= distance;
				index= i;
			}
		}
		return index;
	}
}
