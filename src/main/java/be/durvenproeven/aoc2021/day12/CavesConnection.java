package be.durvenproeven.aoc2021.day12;

import org.apache.commons.lang.StringUtils;

public class CavesConnection {
	private String first;
	private String second;

	public CavesConnection(String first, String second) {
		this.first = first;
		this.second = second;
	}

	public CavesConnection(String input) {
		this.first = StringUtils.substringBefore(input,"-");
		this.second = StringUtils.substringAfter(input,"-");
	}

	public CavesConnection reversed(){
		return new CavesConnection(second, first);//mind duplicates
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
