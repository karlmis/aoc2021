package be.durvenproeven.aoc2021.day16;

import org.apache.commons.lang.StringUtils;

public class Walker {
	private String s;
	private int index = 0;
	private int bitIndex = 4;
	private String currentPart;

	public Walker(String s) {
		this.s = s;
	}

	public String getNextBits(int nr) {
		if (bitIndex > 3) {
			readNextPart();
			bitIndex = 0;
		}
		if (bitIndex + nr > 4) {
			int remaining = nr - (4 - bitIndex);
			String res = currentPart.substring(bitIndex);
			bitIndex = 0;
			while (remaining > 0) {
				if (remaining > 4) {
					readNextPart();
					res += currentPart;
					remaining -= 4;
				} else {
					readNextPart();
					bitIndex = remaining;
					res += currentPart.substring(0, remaining);
					remaining = 0;
				}
			}

			return res;
		} else {
			String res = currentPart.substring(bitIndex, bitIndex + nr);
			bitIndex += nr;
			return res;
		}
	}

	private void readNextPart() {
		String substring = s.substring(index, index + 1);
		currentPart = Integer.toBinaryString(Integer.parseInt(substring, 16));
		currentPart = StringUtils.leftPad(currentPart, 4, '0');
		index++;
	}

	public boolean hasOnlyZerosLeft() {
		throw new UnsupportedOperationException("implement me!");
	}

	public boolean hasNext() {
		throw new UnsupportedOperationException("implement me!");
	}

	public int getIndex() {
		return index * 4 + bitIndex;
	}

	public void skipZerosInHalfByte() {

	}

}
