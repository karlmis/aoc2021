package be.durvenproeven.aoc2021.day18;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SnailFishSumTest {

	@Test
	void add() {
		SnailFishSum first = SnailFishSum.create(1, 2);
		SnailFishSum second = SnailFishSum.create(3, 4);

		assertThat(first.add(second)).isEqualTo(SnailFishSum.create(first, second));
	}

	@Test
	void reduce_OnlyRight() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[1,2],4]");
		snailFishSum.reducePairs(1);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[0,6]"));
	}

	@Test
	void reduce_2_OnlyRight() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[[1,2],3],4]");
		snailFishSum.reducePairs(2);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[0,5],4]"));
	}

	@Test
	void reduce_2_OnlyRight_PairAfter() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[[1,2],[3,4]],5]");
		snailFishSum.reducePairs(2);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[0,[5,4]],5]"));
	}

	@Test
	void reduce_2_OnlyLeft() {
		SnailFishSum snailFishSum = SnailFishSum.create("[1,[2,[3,4]]]");
		snailFishSum.reducePairs(2);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[1,[5,0]]"));
	}

	@Test
	void reduce_2_Both() {
		SnailFishSum snailFishSum = SnailFishSum.create("[1,[[2,3],4]]");
		snailFishSum.reducePairs(2);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[3,[0,7]]"));
	}

	@Test
	void reduce_2_Both_PairBefore() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[1,5],[[2,3],4]]");
		snailFishSum.reducePairs(2);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[1,7],[0,7]]"));
	}

	@Test
	void reduce_Pair_GivenExamples_1() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[[[[9,8],1],2],3],4]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[0,9],2],3],4]"));
	}

	@Test
	void reduce_Pair_GivenExamples_2() {
		SnailFishSum snailFishSum = SnailFishSum.create("[7,[6,[5,[4,[3,2]]]]]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[7,[6,[5,[7,0]]]]"));
	}

	@Test
	void reduce_Pair_GivenExamples_3() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[6,[5,[4,[3,2]]]],1]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[6,[5,[7,0]]],3]"));
	}

	@Test
	void reduce_Pair_GivenExamples_4() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]"));
	}

	@Test
	void reduce_Pair_GivenExamples_5() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[3,[2,[8,0]]],[9,[5,[7,0]]]]"));
	}

	@Test
	void reduce_OnlyRight_PairAfter() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[1,2],[3,4]]");
		snailFishSum.reducePairs(1);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[0,[5,4]]"));
	}

	@Test
	void reduce_OnlyLeft() {
		SnailFishSum snailFishSum = SnailFishSum.create("[1,[2,3]]");
		snailFishSum.reducePairs(1);

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[3,0]"));
	}

	@Test
	void reduce_Explode() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[[[0,7],4],[15,[0,13]]],[1,1]]");
		snailFishSum.reduce();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"));

		snailFishSum.reduce();
		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[0,7],4],[[7,8],[0,[6,7]]]],[1,1]]"));
	}

	@Test
	void reduce_givenExample() {
		SnailFishSum snailFishSum = SnailFishSum.create("[[[[[4,3],4],4],[7,[[8,4],9]]],[1,1]]");

		snailFishSum.reduceCompletely();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]"));
	}

	@Test
	void sum_Reduce() {
		SnailFishSum to4 = IntStream.rangeClosed(1, 4)
				.mapToObj(i -> SnailFishSum.create(i, i))
				.reduce(SnailFishSum::add).orElseThrow();
		to4.reduceCompletely();
		assertThat(to4).isEqualTo(SnailFishSum.create("[[[[1,1],[2,2]],[3,3]],[4,4]]"));

		SnailFishSum to5 = IntStream.rangeClosed(1, 5)
				.mapToObj(i -> SnailFishSum.create(i, i))
				.reduce(SnailFishSum::add).orElseThrow();
		to5.reduceCompletely();
		assertThat(to5).isEqualTo(SnailFishSum.create("[[[[3,0],[5,3]],[4,4]],[5,5]]"));

		SnailFishSum to6 = IntStream.rangeClosed(1, 6)
				.mapToObj(i -> SnailFishSum.create(i, i))
				.reduce(SnailFishSum::add).orElseThrow();
		to6.reduceCompletely();
		assertThat(to6).isEqualTo(SnailFishSum.create("[[[[5,0],[7,4]],[5,5]],[6,6]]"));
	}

	@Test
	void sum_Reduce_1() {
		SnailFishSum snailFishSum = Stream.of(
				"[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]",
				"[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]",
				"[[2,[[0,8],[3,4]]],[[[6,7],1],[7,[1,6]]]]",
				"[[[[2,4],7],[6,[0,5]]],[[[6,8],[2,8]],[[2,1],[4,5]]]]",
				"[7,[5,[[3,8],[1,4]]]]",
				"[[2,[2,2]],[8,[8,1]]]",
				"[2,9]",
				"[1,[[[9,3],9],[[9,0],[0,7]]]]",
				"[[[5,[7,4]],7],1]",
				"[[[[4,2],2],6],[8,7]]")
				.map(SnailFishSum::create)
				.reduce(SnailFishSum::add).orElseThrow();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]"));
	}
	@Test
	void sum_Reduce_2() {
		SnailFishSum sum1 = SnailFishSum.create("[[[0,[4,5]],[0,0]],[[[4,5],[2,6]],[9,5]]]");
		SnailFishSum sum = sum1.add(SnailFishSum.create("[7,[[[3,7],[4,3]],[[6,3],[8,8]]]]"));


		assertThat(sum).isEqualTo(SnailFishSum.create("[[[[4,0],[5,4]],[[7,7],[6,0]]],[[8,[7,7]],[[7,9],[5,0]]]]"));
	}

	@Test
	void create_FromString() {
		assertThat(SnailFishSum.create("[1,2]")).isEqualTo(SnailFishSum.create(1, 2));

		assertThat(SnailFishSum.create("[[[[[9,8],1],2],3],4]")).isEqualTo(
				SnailFishSum.create(
						SnailFishSum.create(
								SnailFishSum.create(
										SnailFishSum.create(
												SnailFishSum.create(
														9,
														8),
												1),
										2),
								3),
						4)
		);
		assertThat(SnailFishSum.create("[[[[1,2],[3,4]],[[5,6],[7,8]]],9]")).isEqualTo(SnailFishSum.create(
				SnailFishSum.create("[[[1,2],[3,4]],[[5,6],[7,8]]]"),
				9));

		assertThat(SnailFishSum.create("[[[[1,3],[5,3]],[[1,3],[8,7]]],[[[4,9],[6,9]],[[8,2],[7,3]]]]")).isEqualTo(SnailFishSum.create(
				SnailFishSum.create("[[[1,3],[5,3]],[[1,3],[8,7]]]"),
				SnailFishSum.create("[[[4,9],[6,9]],[[8,2],[7,3]]]")
		));

		assertThat(SnailFishSum.create("[0,[5,4]]")).isEqualTo(
				SnailFishSum.create(
						0,
						SnailFishSum.create(5, 4))
		);

	}

	@Test
	void toPrettyString() {
		System.out.println(SnailFishSum.create("[1,2]").toPrettyString(0));
		System.out.println(SnailFishSum.create("[1,[2,3]]").toPrettyString(0));
	}

	@Test
	void getValue() {
		assertThat(SnailFishSum.create("[[1,2],[[3,4],5]]").getMagnitude()).isEqualTo(143);
		assertThat(SnailFishSum.create("[[[[0,7],4],[[7,8],[6,0]]],[8,1]]").getMagnitude()).isEqualTo(1384);
		assertThat(SnailFishSum.create("[[[[1,1],[2,2]],[3,3]],[4,4]]").getMagnitude()).isEqualTo(445);
		assertThat(SnailFishSum.create("[[[[3,0],[5,3]],[4,4]],[5,5]]").getMagnitude()).isEqualTo(791);
		assertThat(SnailFishSum.create("[[[[5,0],[7,4]],[5,5]],[6,6]]").getMagnitude()).isEqualTo(1137);
		assertThat(SnailFishSum.create("[[[[8,7],[7,7]],[[8,6],[7,7]]],[[[0,7],[6,6]],[8,7]]]").getMagnitude()).isEqualTo(3488);
	}

	@Test
	void getValue_GivenExample() {
		SnailFishSum snailFishSum = Stream.of("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]",
				"[[[5,[2,8]],4],[5,[[9,9],0]]]",
				"[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]",
				"[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]",
				"[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]",
				"[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]",
				"[[[[5,4],[7,7]],8],[[8,3],8]]",
				"[[9,3],[[9,9],[6,[4,9]]]]",
				"[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]",
				"[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]")
				.map(SnailFishSum::create)
				.reduce(SnailFishSum::add).orElseThrow();

		assertThat(snailFishSum).isEqualTo(SnailFishSum.create("[[[[6,6],[7,6]],[[7,7],[7,0]]],[[[7,7],[7,7]],[[7,8],[9,9]]]]"));
		assertThat(snailFishSum.getMagnitude()).isEqualTo(4140);
	}

	@Test
	void getValue_RealInput() {
		SnailFishSum snailFishSum =
				LineResolver.getStringStreamOfFile("day18snailfishsums.txt")
				.map(SnailFishSum::create)
				.reduce(SnailFishSum::add).orElseThrow();

		assertThat(snailFishSum.getMagnitude()).isEqualTo(4435L);
	}

	@Test
	void getValue_RealInput_Part2() {
		List<SnailFishSum> snailFishSums = LineResolver.getStringStreamOfFile("day18snailfishsums.txt")
				.map(SnailFishSum::create)
				.toList();

		long biggestValue= 0;
		for (int i = 0; i < snailFishSums.size(); i++) {
			for (int j = 0; j < snailFishSums.size(); j++) {
				if (i != j){
					SnailFishSum res = new SnailFishSum(snailFishSums.get(i)).add(new SnailFishSum(snailFishSums.get(j)));
					if (res.getMagnitude()> biggestValue){
						biggestValue= res.getMagnitude();

					}
				}
			}
		}
		assertThat(biggestValue).isEqualTo(4802L);
	}

	@Test
	void getValue_RealInput_Part1() {
		List<SnailFishSum> snailFishSums = Stream.of("[[[0,[5,8]],[[1,7],[9,6]]],[[4,[1,2]],[[1,4],2]]]" ,
				"[[[5,[2,8]],4],[5,[[9,9],0]]]" ,
				"[6,[[[6,2],[5,6]],[[7,6],[4,7]]]]" ,
				"[[[6,[0,7]],[0,9]],[4,[9,[9,0]]]]" ,
				"[[[7,[6,4]],[3,[1,3]]],[[[5,5],1],9]]" ,
				"[[6,[[7,3],[3,2]]],[[[3,8],[5,7]],4]]" ,
				"[[[[5,4],[7,7]],8],[[8,3],8]]" ,
				"[[9,3],[[9,9],[6,[4,9]]]]" ,
				"[[2,[[7,7],7]],[[5,8],[[9,3],[0,2]]]]" ,
				"[[[[5,2],5],[8,[3,7]]],[[5,[7,5]],[4,4]]]")
				.map(SnailFishSum::create)
				.toList();

		long biggestValue= 0;
		for (int i = 0; i < snailFishSums.size(); i++) {
			for (int j = 0; j < snailFishSums.size(); j++) {
				if (i != j){
					SnailFishSum res = new SnailFishSum(snailFishSums.get(i)).add(new SnailFishSum(snailFishSums.get(j)));
					if (res.getMagnitude()> biggestValue){
						biggestValue= res.getMagnitude();

					}
				}
			}
		}
		assertThat(biggestValue).isEqualTo(3993);
	}
}