package be.durvenproeven.aoc2021.day16;

import java.util.ArrayList;
import java.util.List;

public class Decoder {

	static Packet getPacket(Walker walker) {
		Packet packet = readPacket(walker);
		walker.skipZerosInHalfByte();
		return packet;
	}

	private static Packet readPacket(Walker walker) {
		int version = Integer.parseInt(walker.getNextBits(3), 2);
		int typeId = Integer.parseInt(walker.getNextBits(3), 2);

		if (typeId == 4) {
			long value = 0;
			boolean hasEncounteredZero = false;
			while (!hasEncounteredZero) {
				hasEncounteredZero = "0".equals(walker.getNextBits(1));
				int nextHex = Integer.parseInt(walker.getNextBits(4), 2);
				value = value * 16 + nextHex;
			}
			return new LiteralValuePacket(version, value);
		}
		boolean fixedSize = Integer.parseInt(walker.getNextBits(1), 2) == 0;
		if (fixedSize) {
			int nrOfBits = Integer.parseInt(walker.getNextBits(15), 2);
			List<Packet> packets = getPackets(walker, nrOfBits);
			return new OperatorPacketWitFixedBitSizeArguments(version, typeId, packets);
		} else {
			int nrOfArguments = Integer.parseInt(walker.getNextBits(11), 2);
			OperatorPacketWithNrOfArguments packet = new OperatorPacketWithNrOfArguments(version, typeId, nrOfArguments);
			for (int i = 0; i < nrOfArguments; i++) {
				packet.addArgument(readPacket(walker));
			}
			return packet;

		}
	}

	static List<Packet> getPackets(Walker walker, int bitSize) {
		List<Packet> res = new ArrayList<>();
		int startIndex = walker.getIndex();
		int index = startIndex;
		while (index < startIndex + bitSize) {
			res.add(readPacket(walker));
			index = walker.getIndex();
		}
		if (index < startIndex + bitSize) {
			throw new IllegalArgumentException("expected to pass " + (startIndex + bitSize) + " bits, but instead passed " + index);
		}
		return res;
	}
}
