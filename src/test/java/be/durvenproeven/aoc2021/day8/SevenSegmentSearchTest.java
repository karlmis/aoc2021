package be.durvenproeven.aoc2021.day8;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class SevenSegmentSearchTest {

	@Test
	void getKnownNrs() {
		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | ab"))
				.getKnownNrs()).isEqualTo(1);//1

		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | abc"))
				.getKnownNrs()).isEqualTo(1);//7
		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | abcdefg"))
				.getKnownNrs()).isEqualTo(1);//8
		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | abcd"))
				.getKnownNrs()).isEqualTo(1);//4

		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | a"))
				.getKnownNrs()).isEqualTo(0);
	}

	@Test
	void getKnownNrs_Multiple() {
		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | ab a abcde"))
				.getKnownNrs()).isEqualTo(1);//1

		assertThat(new SevenSegmentSearch(List.of("acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | ab a abcde abc"))
				.getKnownNrs()).isEqualTo(2);//1
	}

	@Test
	void getKnownNrs_GivenSimpleExample() {
		assertThat(new SevenSegmentSearch(List.of(
				"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
				"edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
				"fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
				"fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
				"aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
				"fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
				"dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
				"bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
				"egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
				"gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")
		).getKnownNrs()).isEqualTo(26);
	}

	@Test
	void getKnownNrs_RealExample() {
		List<String> stringList = LineResolver.getStringStreamOfFile("day8.txt").toList();

		assertThat(new SevenSegmentSearch(stringList).getKnownNrs()).isEqualTo(274);
	}


	@Test
	void getOutputNr() {
		assertThat(new SevenSegmentSearch(List.of(
				"acedgfb cdfbe gcdfa fbcad dab cefabd cdfgeb eafb cagedb ab | cdfeb fcadb cdfeb cdbaf"
		)).getOuputNr()).isEqualTo(5353);

	}

	@Test
	void getOutputNr_GivenSimpleExample() {
		assertThat(new SevenSegmentSearch(List.of(
				"be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe",
				"edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc",
				"fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg",
				"fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb",
				"aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea",
				"fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb",
				"dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe",
				"bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef",
				"egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb",
				"gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce")
		).getOuputNr()).isEqualTo(61229);
	}


	@Test
	void getOutputNr_RealExample() {
		List<String> stringList = LineResolver.getStringStreamOfFile("day8.txt").toList();

		assertThat(new SevenSegmentSearch(stringList).getOuputNr()).isEqualTo(1012089);
	}

}