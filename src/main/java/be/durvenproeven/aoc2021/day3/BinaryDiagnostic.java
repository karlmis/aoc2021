package be.durvenproeven.aoc2021.day3;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BinaryDiagnostic {
	private final int size;
	private int gammaRate;
	private int ogygenGeneratorRating;
	private final List<Integer> nrs;

	public BinaryDiagnostic(List<String> binaries, int size) {
		this.size = size;
		nrs = binaries.stream()
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

	private int getSize(List<Integer> integers) {
		if (integers==null){
			return 0;
		}
		return integers.size();
	}

	public int getOgyGeneratorRating(){
		List<Integer> nrs1= new ArrayList<>(nrs);
		int downwardCounter= size-1;
		while(nrs1.size()>1) {
			int bitNr= downwardCounter;
			Map<Boolean, List<Integer>> collect = nrs1.stream().collect(Collectors.groupingBy(nr -> BigInteger.valueOf(nr).testBit(bitNr)));
			if ( getSize(collect.get(false)) > getSize(collect.get(true))){
				nrs1= collect.get(false);
			} else {
				nrs1= collect.get(true);
			}
			downwardCounter--;
		}
		return nrs1.get(0);
	}

	public int getCo2ScrubberRating(){
		List<Integer> nrs1= new ArrayList<>(nrs);
		int downwardCounter= size-1;
		while(nrs1.size()>1) {
			int bitNr= downwardCounter;
			Map<Boolean, List<Integer>> collect = nrs1.stream().collect(Collectors.groupingBy(nr -> BigInteger.valueOf(nr).testBit(bitNr)));
			if ( getSize(collect.get(true)) < getSize(collect.get(false))){
				nrs1= collect.get(true);
			} else {
				nrs1= collect.get(false);
			}
			downwardCounter--;
		}
		return nrs1.get(0);
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
