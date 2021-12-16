package be.durvenproeven.aoc2021.day16;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OperatorPacketWithNrOfArguments implements Packet {
	private int version;
	private int packetTypeId;
	private int nrOfArguments;
	private List<Packet> arguments= new ArrayList<>();

	public OperatorPacketWithNrOfArguments(int version, int packetTypeId, int nrOfArguments) {
		this.version = version;
		this.packetTypeId = packetTypeId;
		this.nrOfArguments = nrOfArguments;
	}

	public OperatorPacketWithNrOfArguments(int version, int packetTypeId, List<Packet> arguments) {
		this.version = version;
		this.packetTypeId = packetTypeId;
		this.arguments = arguments;
		this.nrOfArguments= arguments.size();
	}

	public void addArgument(Packet otherPacket) {
		Preconditions.checkArgument(arguments.size() < nrOfArguments);
		arguments.add(otherPacket);
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

	public int getNrOfArguments() {
		return nrOfArguments;
	}

	public List<Packet> getArguments() {
		return arguments;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		OperatorPacketWithNrOfArguments that = (OperatorPacketWithNrOfArguments) o;
		return version == that.version && packetTypeId == that.packetTypeId && nrOfArguments == that.nrOfArguments && Objects.equals(arguments, that.arguments);
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, packetTypeId, nrOfArguments, arguments);
	}

	@Override
	public String toString() {
		return "OperatorPacketWithNrOfArguments{" +
				"version=" + version +
				", packetTypeId=" + packetTypeId +
				", nrOfArguments=" + nrOfArguments +
				", arguments=" + arguments +
				'}';
	}

	@Override
	public int getSumOfVersions() {
		return version + arguments.stream().mapToInt(Packet::getSumOfVersions).sum();
	}


}
