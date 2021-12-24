package be.durvenproeven.aoc2021.day24;

import java.util.List;

public class Registers {
	private int regW;
	private int regX;
	private int regY;
	private int regZ;

	public Registers() {
	}

	private Registers(int regW, int regX, int regY, int regZ) {
		this.regW = regW;
		this.regX = regX;
		this.regY = regY;
		this.regZ = regZ;
	}

	public int getNr(String s){
		if ("w".equals(s)){
			return regW;
		}
		if ("x".equals(s)){
			return regX;
		}
		if ("y".equals(s)){
			return regY;
		}
		if ("z".equals(s)){
			return regZ;
		}
		return Integer.parseInt(s);
	}

	public Registers setNr(String s, int i){
		if ("w".equals(s)){
			regW= i;
			return new Registers(i, regX, regY, regZ);
		}
		if ("x".equals(s)){
			regX= i;
			return new Registers(regW, i, regY, regZ);
		}
		if ("y".equals(s)){
			regY= i;
			return new Registers(regW, regX, i, regZ);
		}
		if ("z".equals(s)){
			regZ= i;
			return new Registers(regW, regX, regY, i);
		}
		throw new IllegalArgumentException("register not allowed: '"+s+"'");
	}

	public List<Integer> getList() {
		return List.of(regW, regX, regY, regZ);
	}


}
