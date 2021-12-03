package be.durvenproeven.aoc2021.day3;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BinaryDiagnosticTest {

	@Test
	void getGammaRate() {
		assertThat(new BinaryDiagnostic(List.of("001","001"),3).getGammaRate()).isEqualTo(1);
		assertThat(new BinaryDiagnostic(List.of("100","001","001"),3).getGammaRate()).isEqualTo(1);
	}

	@Test
	void getRates_SimpleGivenExample() {
		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(List.of("00100",
				"11110",
				"10110",
				"10111",
				"10101",
				"01111",
				"00111",
				"11100",
				"10000",
				"11001",
				"00010",
				"01010"), 5);
		assertThat(binaryDiagnostic.getGammaRate()).isEqualTo(22);
		assertThat(binaryDiagnostic.getEpsilonRate()).isEqualTo(9);
		assertThat(binaryDiagnostic.getEpsilonRate()* binaryDiagnostic.getGammaRate()).isEqualTo(198);
	}


	@Test
	void getRates_RealExample() {
		List<String> inputNrs = LineResolver.getStringStreamOfFile("day3.txt")
				.toList();

		BinaryDiagnostic binaryDiagnostic = new BinaryDiagnostic(inputNrs, 12);
		assertThat(binaryDiagnostic.getEpsilonRate()* binaryDiagnostic.getGammaRate()).isEqualTo(3923414);
	}
}