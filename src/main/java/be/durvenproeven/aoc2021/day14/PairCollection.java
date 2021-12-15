package be.durvenproeven.aoc2021.day14;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;

public class PairCollection {
	private Map<Pair, Long> pairs;
	private Pair first;
	private Pair last;

	public PairCollection(List<Pair> pairs) {
		this.pairs = pairs.stream().collect(Collectors.groupingBy(identity(), counting()));
		first = pairs.get(0);
		last = pairs.get(pairs.size() - 1);
	}

	private PairCollection(Map<Pair, Long> pairs, Pair first, Pair last) {
		this.pairs = pairs;
		this.first = first;
		this.last = last;
	}

	public PairCollection nextTurn(Map<Pair, List<Pair>> pairRules){
		Map<Pair, Long> newPairs = new HashMap<>();
		pairs.forEach((key, value) -> {
			List<Pair> resultingPairs = pairRules.get(key);
			resultingPairs.forEach(pair -> newPairs.compute(pair, (k, v) -> v == null ? value : v + value));
		});

		return new PairCollection(newPairs, first, last);
	}

	public Long getDiffInOccurences(){
		Map<String, Long> count= new HashMap<>();
		pairs.forEach((key, value) -> {
			count.compute(key.getFirst(), (k,v)-> v== null? value: v+value);
			count.compute(key.getSecond(), (k,v)-> v== null? value: v+value);
		});

		// all others are counted twice, so adding 1 extra will make sure that everything is counted twice
		count.computeIfPresent(first.getFirst(), (k,v)-> v+1);
		count.computeIfPresent(last.getSecond(), (k,v)-> v+1);

		Long max = count.values().stream().max(Long::compareTo).orElseThrow()/2;
		Long min = count.values().stream().min(Long::compareTo).orElseThrow()/2;
		return max-min;

	}
}