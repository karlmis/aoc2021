package be.durvenproeven.aoc2021.day3;

import java.math.BigInteger;
import java.util.List;

public class BinaryDiagnostic {
	private final int size;
	private int gammaRate;

	public BinaryDiagnostic(List<String> binaries, int size) {
		this.size = size;
		List<Integer> nrs = binaries.stream()
				.map(s -> Integer.parseInt(s, 2))
				.toList();
		for (int i = 0; i < size; i++) {
			int powerOf2 = i;
			long count = nrs.stream()
					.filter(nr -> BigInteger.valueOf(nr).testBit(powerOf2))
					.count();
			if (count > binaries.size()/2){
				gammaRate+=  powerOf2(powerOf2);
			}
		}
	}

	private int powerOf2(int powerOf2) {
		int res= 1;
		for (int i = 0; i < powerOf2; i++) {
			res*=2;
		}
		return res;
	}

	public int getGammaRate() {
		return gammaRate;
	}

	public int getEpsilonRate(){
		return powerOf2(size)-1-gammaRate;
	}
}
