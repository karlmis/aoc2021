package be.durvenproeven.aoc2021.day23;

import java.util.Collections;
import java.util.Comparator;
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
		PriorityQueue<WeightedAmphipodeSystem> ts = new PriorityQueue<>(comparator);
		ts.add(starting);
		WeightedAmphipodeSystem nextitem = ts.poll();
		while (nextitem != null){
			System.out.println(nextitem.getAmphipodSystem().toPrettyString());
			if (isFinishedPredicate.test(nextitem)){
				return nextitem;
			}
			alreadyDone.add(nextitem);
			List<WeightedAmphipodeSystem> newItems = function.apply(nextitem);
			if (!newItems.isEmpty()){
				Map<Boolean, List<WeightedAmphipodeSystem>> collect = newItems.stream()
						.collect(Collectors.groupingBy(alreadyDone::contains));

				ts.addAll(collect.getOrDefault(false, Collections.emptyList()));
			}
			System.out.println(ts.size());
			nextitem= ts.poll();
		}
		throw new IllegalArgumentException("not found");
	}
}
