package be.durvenproeven.aoc2021.day19;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;

public record MatchResult(int nr, Coordinates dif, List<Coordinates> mutual){

	public static MatchResult noMatch(){
		return new MatchResult(-1, null, emptyList());
	}

	public boolean hasMatch(){
		return nr!= -1;
	}

}
