package be.durvenproeven.aoc2021.day23;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.DoubleFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToDoubleBiFunction;
import java.util.function.ToDoubleFunction;
import java.util.stream.Collectors;

public class TreeSearcher<T> {

	private final Function<T, List<T>> function;
	private final Comparator<T> comparator;
	private final Predicate<T> isFinishedPredicate;
	private ToDoubleFunction<T> toDoubleFunction;

	public TreeSearcher(Function<T, List<T>> function, Comparator<T> comparator, Predicate<T> isFinishedPredicate, ToDoubleFunction<T> toDoubleFunction)  {
		this.function = function;
		this.comparator = comparator;
		this.isFinishedPredicate = isFinishedPredicate;
		this.toDoubleFunction = toDoubleFunction;
	}

	public T calculate(T starting){
		Set<T> alreadyDone= new HashSet<>();
		PriorityQueue<T> ts = new PriorityQueue<>(comparator);
		ts.add(starting);
		T nextitem = ts.poll();
		while (nextitem != null){
			System.out.println(((WeightedAmphipodeSystem) nextitem).getAmphipodSystem().toPrettyString());
			if (isFinishedPredicate.test(nextitem)){
				return nextitem;
			}
			alreadyDone.add(nextitem);
			List<T> newItems = function.apply(nextitem);
			if (!newItems.isEmpty()){
				Map<Boolean, List<T>> collect = newItems.stream()
						.collect(Collectors.groupingBy(alreadyDone::contains));

				ts.addAll(collect.getOrDefault(false, Collections.emptyList()));
			}
			System.out.println(ts.size());
			nextitem= ts.poll();
		}
		throw new IllegalArgumentException("not found");
	}
}
