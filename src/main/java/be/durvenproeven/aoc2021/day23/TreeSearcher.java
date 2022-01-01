package be.durvenproeven.aoc2021.day23;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class TreeSearcher {

	private final Function<WeightedAmphipodeSystem, List<WeightedAmphipodeSystem>> function;
	private final Comparator<WeightedAmphipodeSystem> comparator;
	private final Predicate<WeightedAmphipodeSystem> isFinishedPredicate;
	private ToDoubleFunction<WeightedAmphipodeSystem> toDoubleFunction;

	public TreeSearcher(Function<WeightedAmphipodeSystem, List<WeightedAmphipodeSystem>> function, Comparator<WeightedAmphipodeSystem> comparator, Predicate<WeightedAmphipodeSystem> isFinishedPredicate, ToDoubleFunction<WeightedAmphipodeSystem> toDoubleFunction)  {
		this.function = function;
		this.comparator = comparator;
		this.isFinishedPredicate = isFinishedPredicate;
		this.toDoubleFunction = toDoubleFunction;
	}

	public WeightedAmphipodeSystem calculate(WeightedAmphipodeSystem starting){
		Set<WeightedAmphipodeSystem> alreadyDone= new HashSet<>();
		HashMap<AmphipodSystem, WeightedAmphipodeSystem> mapping = new HashMap<>();
		PriorityQueue<WeightedAmphipodeSystem> pq = new PriorityQueue<>(comparator);
		pq.add(starting);
		mapping.put(starting.getAmphipodSystem(), starting);
		WeightedAmphipodeSystem nextitem = pq.poll();
		while (nextitem != null){
			System.out.println(nextitem.getAmphipodSystem().toPrettyString());
			if (isFinishedPredicate.test(nextitem)){
				return nextitem;
			}
			alreadyDone.add(nextitem);
			List<WeightedAmphipodeSystem> newItems = function.apply(nextitem);
			if (!newItems.isEmpty()){
				Map<Boolean, List<WeightedAmphipodeSystem>> collect = newItems.stream()
						.filter(o -> !alreadyDone.contains(o))
						.collect(Collectors.groupingBy(o1 -> mapping.containsKey(o1.getAmphipodSystem())));
				for (WeightedAmphipodeSystem weightedAmphipodeSystem : collect.getOrDefault(true, Collections.emptyList())) {
					WeightedAmphipodeSystem savedWeighted = mapping.get(weightedAmphipodeSystem.getAmphipodSystem());
					if (weightedAmphipodeSystem.getWeight() < savedWeighted.getWeight()){
						pq.remove(savedWeighted);
						pq.add(weightedAmphipodeSystem);
						mapping.put(weightedAmphipodeSystem.getAmphipodSystem(), weightedAmphipodeSystem);
					}
				}

				pq.addAll(collect.getOrDefault(false, Collections.emptyList()));
				collect.getOrDefault(false, Collections.emptyList()).stream()
						.forEach(wa -> mapping.put(wa.getAmphipodSystem(), wa));
			}
			System.out.println(pq.size());
			nextitem= pq.poll();
		}
		throw new IllegalArgumentException("not found");
	}
}
