package be.durvenproeven.aoc2021.day16;

public interface Packet {

	int getVersion() ;

	int getPacketTypeId() ;

	int getSumOfVersions();

	long getValue();
}
