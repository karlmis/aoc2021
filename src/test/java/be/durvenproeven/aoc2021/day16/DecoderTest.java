package be.durvenproeven.aoc2021.day16;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DecoderTest {

	@Test
	void getPacket_Literal() {
		Packet packet = Decoder.getPacket(new Walker("D2FE28"));

		assertThat(packet.getPacketTypeId()).isEqualTo(4);
		assertThat(packet.getVersion()).isEqualTo(6);
		assertThat(packet).isExactlyInstanceOf(LiteralValuePacket.class);
		assertThat((LiteralValuePacket) packet).returns(2021L, LiteralValuePacket::getValue);

	}

	@Test
	void getPacket_fixedBitSize() {
		Packet packet = Decoder.getPacket(new Walker("38006F45291200"));

		assertThat(packet.getPacketTypeId()).isEqualTo(6);
		assertThat(packet.getVersion()).isEqualTo(1);
		assertThat(packet).isExactlyInstanceOf(OperatorPacketWitFixedBitSizeArguments.class);
		assertThat(((OperatorPacketWitFixedBitSizeArguments) packet).getArguments()).containsExactly(
				new LiteralValuePacket(6, 10),
				new LiteralValuePacket(2, 20)
		);
	}

	@Test
	void getPacket_fixedArgumentSize() {
		Packet packet = Decoder.getPacket(new Walker("EE00D40C823060"));

		assertThat(packet.getPacketTypeId()).isEqualTo(3);
		assertThat(packet.getVersion()).isEqualTo(7);
		assertThat(packet).isExactlyInstanceOf(OperatorPacketWithNrOfArguments.class);
		assertThat(((OperatorPacketWithNrOfArguments) packet).getArguments()).containsExactly(
				new LiteralValuePacket(2, 1),
				new LiteralValuePacket(4, 2),
				new LiteralValuePacket(1, 3)
		);
	}


	@Test
	void getPackets_List() {
		List<Packet> packets = Decoder.getPackets(new Walker("D14A448"), 27);

		assertThat(packets).containsExactly(
				new LiteralValuePacket(6, 10),
				new LiteralValuePacket(2, 20)
		);
	}

	@Test
	void getPacket_GivenExamples() {
		assertThat(Decoder.getPacket(new Walker("8A004A801A8002F478"))).isEqualTo(
				new OperatorPacketWithNrOfArguments(4,2,List.of(
						new OperatorPacketWithNrOfArguments(1,2, List.of(
								new OperatorPacketWitFixedBitSizeArguments(5,2, List.of(
										new LiteralValuePacket(6,15)
								)
						))
				))));

		assertThat(Decoder.getPacket(new Walker("8A004A801A8002F478")).getSumOfVersions()).isEqualTo(16);

		assertThat(Decoder.getPacket(new Walker("620080001611562C8802118E34")).getSumOfVersions()).isEqualTo(12);
		assertThat(Decoder.getPacket(new Walker("C0015000016115A2E0802F182340")).getSumOfVersions()).isEqualTo(23);
		assertThat(Decoder.getPacket(new Walker("A0016C880162017C3686B18A3D4780")).getSumOfVersions()).isEqualTo(31);
	}

	@Test
	void getPacket_RealExample() {
		String input = LineResolver.getStringStreamOfFile("day16bits.txt").toList().get(0);
		Packet packet = Decoder.getPacket(new Walker(input));

		assertThat(packet.getSumOfVersions()).isEqualTo(927);
	}

	@Test
	void getValue() {
		assertThat(Decoder.getPacket(new Walker("C200B40A82")).getValue()).isEqualTo(3);
		assertThat(Decoder.getPacket(new Walker("04005AC33890")).getValue()).isEqualTo(54);
		assertThat(Decoder.getPacket(new Walker("880086C3E88112")).getValue()).isEqualTo(7);
		assertThat(Decoder.getPacket(new Walker("CE00C43D881120")).getValue()).isEqualTo(9);
		assertThat(Decoder.getPacket(new Walker("D8005AC2A8F0")).getValue()).isEqualTo(1);
		assertThat(Decoder.getPacket(new Walker("F600BC2D8F")).getValue()).isEqualTo(0);
		assertThat(Decoder.getPacket(new Walker("9C005AC2F8F0")).getValue()).isEqualTo(0);
		assertThat(Decoder.getPacket(new Walker("9C0141080250320F1802104A08")).getValue()).isEqualTo(1);
	}

	@Test
	void getValue_RealExample() {
		String input = LineResolver.getStringStreamOfFile("day16bits.txt").toList().get(0);
		Packet packet = Decoder.getPacket(new Walker(input));

		System.out.println(packet.getValue());
		assertThat(packet.getValue()).isNotEqualTo(27249018106L);//too low
	}
}