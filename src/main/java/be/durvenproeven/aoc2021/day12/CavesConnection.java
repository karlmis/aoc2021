package be.durvenproeven.aoc2021.day12;

import org.apache.commons.lang.StringUtils;

public class CavesConnection {
	private final String first;
	private final String second;

	public CavesConnection(String input) {
		this.first = StringUtils.substringBefore(input,"-");
		this.second = StringUtils.substringAfter(input,"-");
	}

	public boolean contains(String s){
		return second.equals(s) || first.equals(s);
	}

	public String getFirst() {
		return first;
	}

	public String getSecond() {
		return second;
	}
}
