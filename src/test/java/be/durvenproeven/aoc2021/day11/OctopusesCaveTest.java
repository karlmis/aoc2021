package be.durvenproeven.aoc2021.day11;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class OctopusesCaveTest {

	@Test
	void nextStep_SimpleExample() {
		OctopusesCave octopusesCave = new OctopusesCave(List.of("11111",
				"19991",
				"19191",
				"19991",
				"11111"));

		assertThat(octopusesCave.nextStep()).isEqualTo(9);
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"34543",
				"40004",
				"50005",
				"40004",
				"34543")));

		assertThat(octopusesCave.nextStep()).isEqualTo(0);
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"45654",
				"51115",
				"61116",
				"51115",
				"45654")));
	}

	@Test
	void nextStep_GivenExample() {
		OctopusesCave octopusesCave = new OctopusesCave(List.of(
				"5483143223",
				"2745854711",
				"5264556173",
				"6141336146",
				"6357385478",
				"4167524645",
				"2176841721",
				"6882881134",
				"4846848554",
				"5283751526"));
		int nr = 0;//1
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"6594254334",
				"3856965822",
				"6375667284",
				"7252447257",
				"7468496589",
				"5278635756",
				"3287952832",
				"7993992245",
				"5957959665",
				"6394862637")
		));
//2
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"8807476555",
				"5089087054",
				"8597889608",
				"8485769600",
				"8700908800",
				"6600088989",
				"6800005943",
				"0000007456",
				"9000000876",
				"8700006848")
		));
//3
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"0050900866",
				"8500800575",
				"9900000039",
				"9700000041",
				"9935080063",
				"7712300000",
				"7911250009",
				"2211130000",
				"0421125000",
				"0021119000")
		));
//4
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"2263031977",
				"0923031697",
				"0032221150",
				"0041111163",
				"0076191174",
				"0053411122",
				"0042361120",
				"5532241122",
				"1532247211",
				"1132230211")
		));
//5
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"4484144000",
				"2044144000",
				"2253333493",
				"1152333274",
				"1187303285",
				"1164633233",
				"1153472231",
				"6643352233",
				"2643358322",
				"2243341322")
		));
//6
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"5595255111",
				"3155255222",
				"3364444605",
				"2263444496",
				"2298414396",
				"2275744344",
				"2264583342",
				"7754463344",
				"3754469433",
				"3354452433")
		));
//7
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"6707366222",
				"4377366333",
				"4475555827",
				"3496655709",
				"3500625609",
				"3509955566",
				"3486694453",
				"8865585555",
				"4865580644",
				"4465574644")
		));
//8
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"7818477333",
				"5488477444",
				"5697666949",
				"4608766830",
				"4734946730",
				"4740097688",
				"6900007564",
				"0000009666",
				"8000004755",
				"6800007755")
		));
//9
		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"9060000644",
				"7800000976",
				"6900000080",
				"5840000082",
				"5858000093",
				"6962400000",
				"8021250009",
				"2221130009",
				"9111128097",
				"7911119976")
		));

		nr += octopusesCave.nextStep();
		assertThat(octopusesCave).isEqualTo(new OctopusesCave(List.of(
				"0481112976",
				"0031112009",
				"0041112504",
				"0081111406",
				"0099111306",
				"0093511233",
				"0442361130",
				"5532252350",
				"0532250600",
				"0032240000")
		));

		assertThat(nr).isEqualTo(204);
	}

	@Test
	void nextStep_GivenExampleCount() {
		OctopusesCave octopusesCave = new OctopusesCave(List.of(
				"5483143223",
				"2745854711",
				"5264556173",
				"6141336146",
				"6357385478",
				"4167524645",
				"2176841721",
				"6882881134",
				"4846848554",
				"5283751526"));
		int sum = IntStream.range(0, 100)
				.map(i -> octopusesCave.nextStep())
				.sum();
		assertThat(sum).isEqualTo(1656);
	}

	@Test
	void nextStep_RealInput() {
		List<String> strings = LineResolver.getStringStreamOfFile("day11lumniscentoctopuses.txt").toList();
		OctopusesCave octopusesCave = new OctopusesCave(strings);
		int sum = IntStream.range(0, 100)
				.map(i -> octopusesCave.nextStep())
				.sum();
		assertThat(sum).isEqualTo(1681);
	}

	@Test
	void allFlashes_GivenExample() {
		OctopusesCave octopusesCave = new OctopusesCave(List.of(
				"5483143223",
				"2745854711",
				"5264556173",
				"6141336146",
				"6357385478",
				"4167524645",
				"2176841721",
				"6882881134",
				"4846848554",
				"5283751526"));
		assertThat(getStepWhenAllFlashes(octopusesCave)).isEqualTo(195);
	}

	@Test
	void allFlashes_RealExample() {
		List<String> strings = LineResolver.getStringStreamOfFile("day11lumniscentoctopuses.txt").toList();
		OctopusesCave octopusesCave = new OctopusesCave(strings);

		assertThat(getStepWhenAllFlashes(octopusesCave)).isEqualTo(276);
	}

	private int getStepWhenAllFlashes(OctopusesCave octopusesCave) {
		int counter = 0;
		while (!octopusesCave.allFlash()) {
			counter++;
			octopusesCave.nextStep();
		}
		return counter;
	}

}