package be.durvenproeven.aoc2021.day16;

import java.util.Objects;

public class LiteralValuePacket implements Packet{
	private int version;
	private long value;

	public LiteralValuePacket(int version, long value) {
		this.version = version;
		this.value = value;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public int getPacketTypeId() {
		return 4;
	}

	public long getValue() {
		return value;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		LiteralValuePacket that = (LiteralValuePacket) o;
		return version == that.version && value == that.value;
	}

	@Override
	public int hashCode() {
		return Objects.hash(version, value);
	}

	@Override
	public String toString() {
		return "LiteralValuePacket{" +
				"version=" + version +
				", value=" + value +
				'}';
	}

	@Override
	public int getSumOfVersions() {
		return version;
	}
}
