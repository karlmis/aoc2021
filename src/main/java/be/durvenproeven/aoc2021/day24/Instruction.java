package be.durvenproeven.aoc2021.day24;

import java.util.Arrays;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkArgument;

public enum Instruction {
	INP("inp") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			return registers.setNr(s[1], inputValues.readInput());
		}
	},
	ADD("add") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			return registers.setNr(s[1], registers.getNr(s[1])+ registers.getNr(s[2]));
		}
	},
	MUL("mul") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			return registers.setNr(s[1], registers.getNr(s[1])* registers.getNr(s[2]));
		}
	},
	DIV("div") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			int divider = registers.getNr(s[2]);
			if(divider ==0){
				return null;
			};
			return registers.setNr(s[1], registers.getNr(s[1])/ divider);
		}
	},
	MOD("mod") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			int first = registers.getNr(s[1]);
			if(first <0){
				return null;
			}
			int second = registers.getNr(s[2]);
			if(second <=0){
				return null;
			}
			return registers.setNr(s[1], first % second);
		}
	},
	EQL("eql") {
		@Override
		public Registers calculate(String[] s, InputValues inputValues, Registers registers) {
			return registers.setNr(s[1], registers.getNr(s[1])== registers.getNr(s[2])?1:0);
		}
	};

	private String code;

	Instruction(String code) {
		this.code = code;
	}

	abstract Registers calculate(String[] s, InputValues inputValues, Registers registers);

	public BiFunction<Registers, InputValues, Registers> getBiConsumer(String[] s){
		return (registers, iv)-> {
			if( registers==null){
				return null;
			}
			return calculate(s, iv, registers);
		};
	}


	public static BiFunction<Registers, InputValues, Registers> findBiConsumer(String s){
		String[] split = s.split(" ");
		Instruction instruction = Arrays.stream(values())
				.filter(inst -> inst.code.equals(split[0]))
				.findFirst()
				.orElseThrow();
		return instruction.getBiConsumer(split);

	}




}
