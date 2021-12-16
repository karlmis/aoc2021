package be.durvenproeven.aoc2021.day16;

import java.util.List;
import java.util.Objects;

public class OperatorPacketWitFixedBitSizeArguments implements Packet {
	private int version;
	private int packetTypeId;
	List<Packet> arguments;

	public OperatorPacketWitFixedBitSizeArguments(int version, int packetTypeId, List<Packet> arguments) {
		this.version = version;
		this.packetTypeId = packetTypeId;
		this.arguments = arguments;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public int getPacketTypeId() {
		return packetTypeId;
	}

	@Override
	public long getValue() {
		return switch (packetTypeId) {
			case 0 -> arguments.stream().mapToLong(Packet::getValue).sum();
			case 1 -> arguments.stream().mapToLong(Packet::getValue).reduce(1, (a, b) -> a * b);
			case 2 -> arguments.stream().mapToLong(Packet::getValue).min().orElseThrow();
			case 3 -> arguments.stream().mapToLong(Packet::getValue).max().orElseThrow();
			case 5 -> arguments.get(0).getValue() > arguments.get(1).getValue() ? 1L : 0L;
			case 6 -> arguments.get(0).getValue() < arguments.get(1).getValue() ? 1L : 0L;
			case 7 -> arguments.get(0).getValue() == arguments.get(1).getValue() ? 1L : 0L;
			default -> throw new IllegalArgumentException();
		};
	}

	public List<Packet> getArguments() {
		return arguments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OperatorPacketWitFixedBitSizeArguments that = (OperatorPacketWitFixedBitSizeArguments) o;
		return version == that.version && packetTypeId == that.packetTypeId && Objects.equals(arguments, that.arguments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, packetTypeId, arguments);
	}

	@Override
	public String toString() {
		return "OperatorPacketWitFixedBitSizeArguments{" +
				"version=" + version +
				", packetTypeId=" + packetTypeId +
				", arguments=" + arguments +
				'}';
	}

	@Override
	public int getSumOfVersions() {
		return version + arguments.stream().mapToInt(Packet::getSumOfVersions).sum();
	}
}
