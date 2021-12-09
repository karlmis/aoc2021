package be.durvenproeven.aoc2021.day9;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HeightMap {

	private final int[][] nrs;
	private final Map<Coordinates, Integer> lowPoints;

	public HeightMap(List<String> input) {
		nrs = new int[input.size()][];
		for (int i = 0; i < input.size(); i++) {
			nrs[i]= toArray(input.get(i));
		}
		lowPoints = calculateLowPoints();
	}

	private int[] toArray(String l) {
		return l.chars()
				.mapToObj(Character::toString)
				.mapToInt(Integer::parseInt)
				.toArray();
	}

	private Map<Coordinates, Integer> calculateLowPoints() {
		Map<Coordinates, Integer> res = new HashMap<>();
		for (int i = 0; i < nrs.length; i++) {
			for (int i1 = 0; i1 < nrs[i].length; i1++) {
				if (nrs[i][i1]< minOfNeighbours(i, i1)){
					res.put(new Coordinates(i, i1), nrs[i][i1]);
				}
			}
		}
		return res;
	}

	public Collection<Integer> getLowPoints(){
		return lowPoints.values();
	}

	public List<Integer> getBasinSizes(){
		List<Integer> res = new ArrayList<>();
		for (Map.Entry<Coordinates, Integer> entry : lowPoints.entrySet()) {
			HashSet<Coordinates> basin = new HashSet<>();

			for (Direction value : Direction.values()) {
				basin.addAll(getBasinSize(entry.getKey().getNeighbour(value), Set.of(entry.getKey())));
			}
			res.add(basin.size());
		}
		return res;
	}

	public int getBasinNumber(){
		Comparator<Integer> compare = Integer::compare;
		return getBasinSizes().stream()
				.sorted(compare.reversed())
				.limit(3)
				.reduce(1, (a, b) -> a * b);
	}

	private Set<Coordinates> getBasinSize(Coordinates coordinates, Set<Coordinates> alreadyDone){
		if (coordinates.getX()<0 || coordinates.getY()<0 ||
				coordinates.getX() >= nrs.length || coordinates.getY() >= nrs[coordinates.getX()].length){
			return alreadyDone;
		}
		int value= nrs[coordinates.getX()][coordinates.getY()];
		if (value ==9){
			return alreadyDone;
		}
		HashSet<Coordinates> newAlreadyDone = new HashSet<>(alreadyDone);
		newAlreadyDone.add(coordinates);
		for (Direction dir : Direction.values()) {

			Coordinates neighbour = coordinates.getNeighbour(dir);
			if (!newAlreadyDone.contains(neighbour)){
				newAlreadyDone.addAll(getBasinSize(neighbour, newAlreadyDone));
			}

		}
		return newAlreadyDone;

	}

	public int getSumRiskLevel(){
		return lowPoints.values().stream()
				.mapToInt(Integer::intValue)
				.map(i -> i+1)
				.sum();
	}

	private int minOfNeighbours(int i, int i1) {
		List<Integer> neighourValues= new ArrayList<>();
		if (i >0){
			neighourValues.add(nrs[i-1][i1]);
		}
		if (i < nrs.length-1){
			neighourValues.add(nrs[i+1][i1]);
		}
		if (i1 >0){
			neighourValues.add(nrs[i][i1-1]);
		}
		if (i1 < nrs[i].length-1){
			neighourValues.add(nrs[i][i1+1]);
		}
		return neighourValues.stream()
				.mapToInt(Integer::intValue)
				.min().orElseThrow();
	}




}
