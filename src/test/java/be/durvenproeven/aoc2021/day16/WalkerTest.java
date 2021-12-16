package be.durvenproeven.aoc2021.day16;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WalkerTest {

	@Test
	void getNextBits_OneHalfByte() {
		assertThat(new Walker("F").getNextBits(1)).isEqualTo("1");
		assertThat(new Walker("F").getNextBits(2)).isEqualTo("11");
		assertThat(new Walker("F").getNextBits(3)).isEqualTo("111");
		assertThat(new Walker("F").getNextBits(4)).isEqualTo("1111");

		assertThat(new Walker("6").getNextBits(1)).isEqualTo("0");
		assertThat(new Walker("6").getNextBits(2)).isEqualTo("01");
		assertThat(new Walker("6").getNextBits(3)).isEqualTo("011");
		assertThat(new Walker("6").getNextBits(4)).isEqualTo("0110");
	}

	@Test
	void getNextBits_MultipleInOneHalfByte() {
		Walker walker = new Walker("A");
		assertThat(walker.getNextBits(1)).isEqualTo("1");
		assertThat(walker.getNextBits(2)).isEqualTo("01");
	}

	@Test
	void getNextBits_MultipleHalfByte() {
		Walker walker = new Walker("F0");
		assertThat(walker.getNextBits(1)).isEqualTo("1");
		assertThat(walker.getNextBits(4)).isEqualTo("1110");
		assertThat(walker.getNextBits(3)).isEqualTo("000");
	}

	@Test
	void getNextBits_MultipleHalfByte_Longer() {
		Walker walker = new Walker("F0F0");
		assertThat(walker.getNextBits(1)).isEqualTo("1");
		assertThat(walker.getNextBits(4)).isEqualTo("1110");
		assertThat(walker.getNextBits(10)).isEqualTo("0001111000");
		assertThat(walker.getNextBits(1)).isEqualTo("0");
	}
}