package be.durvenproeven.aoc2021.day20;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;

class InputImageTest {

	@Test
	void enhance() {
		String algorithm =
				"..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#." +
						".#..##..###..######.###...####..#..#####..##..#.#####...##.#.#.." +
						"#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####....." +
						"#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####" +
						".#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##." +
						"#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#.." +
						"#.##.#....##..#.####....##...##..#...#......#.#.......#.......##" +
						"..####..#...#.#.#...##..#.#..###..#####........#..####......#..#";

		InputImage inputImage = new InputImage(List.of(
				"#..#.",
				"#....",
				"##..#",
				"..#..",
				"..###"), ".");

		InputImage enhancedImage = inputImage.enhance(algorithm);
		assertThat(enhancedImage)
				.isEqualTo(new InputImage(List.of(
						".##.##.",
						"#..#.#.",
						"##.#..#",
						"####..#",
						".#..##.",
						"..##..#",
						"...#.#."
				), "."));

		InputImage enhancedTwice = enhancedImage.enhance(algorithm);
		assertThat(enhancedTwice)
				.isEqualTo(new InputImage(List.of(
						".......#.",
						".#..#.#..",
						"#.#...###",
						"#...##.#.",
						"#.....#.#",
						".#.#####.",
						"..#.#####",
						"...##.##.",
						"....###.."
				), "."));
		assertThat(enhancedTwice.getLightenedUp()).isEqualTo(35);
	}

	@Test
	void enhance_50() {
		String algorithm =
				"..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#." +
						".#..##..###..######.###...####..#..#####..##..#.#####...##.#.#.." +
						"#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####....." +
						"#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####" +
						".#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##." +
						"#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#.." +
						"#.##.#....##..#.####....##...##..#...#......#.#.......#.......##" +
						"..####..#...#.#.#...##..#.#..###..#####........#..####......#..#";

		InputImage inputImage = new InputImage(List.of(
				"#..#.",
				"#....",
				"##..#",
				"..#..",
				"..###"), ".");

		for (int i = 0; i < 50; i++) {
			inputImage= inputImage.enhance(algorithm);
		}
		assertThat(inputImage.getLightenedUp()).isEqualTo(3351);
	}

	@Test
	void enhance_RealExample() {
		String algorithm = LineResolver.getStringStreamOfFile("day20InputAlgortithmImage.txt")
				.collect(Collectors.joining());

		List<String> strings = LineResolver.getStringStreamOfFile("day20inputimage.txt").toList();
		InputImage inputImage = new InputImage(strings, ".");
		InputImage enhance = inputImage.enhance(algorithm);

		InputImage enhancedTwice = enhance
				.enhance(algorithm);
		System.out.println("second:"+enhancedTwice.getLightenedUp()+","+enhancedTwice.getSize());

		assertThat(enhancedTwice.getLightenedUp()).isEqualTo(4964);
	}

	@Test
	void enhance50_RealExample() {
		String algorithm = LineResolver.getStringStreamOfFile("day20InputAlgortithmImage.txt")
				.collect(Collectors.joining());

		List<String> strings = LineResolver.getStringStreamOfFile("day20inputimage.txt").toList();
		InputImage inputImage = new InputImage(strings, ".");
		for (int i = 0; i < 50; i++) {
			inputImage= inputImage.enhance(algorithm);
		}
		assertThat(inputImage.getLightenedUp()).isEqualTo(13202);
	}
}