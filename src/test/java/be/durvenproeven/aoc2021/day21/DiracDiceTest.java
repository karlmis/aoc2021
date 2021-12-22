package be.durvenproeven.aoc2021.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DiracDiceTest {

	@Test
	void roll() {
		DiracDice diracDice = new DiracDice(4, 8, 10);
		DeterministicDice dice = new DeterministicDice(100);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(10,0);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(10,3);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(14,3);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(14,9);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(20,9);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(20,16);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(26,16);

		assertThat(diracDice.roll(dice, 1000)).isFalse();
		assertThat(diracDice.getScores()).containsExactly(26,22);

	}

	@Test
	void rollTillWinner() {
		DiracDice diracDice = new DiracDice(4, 8, 10);
		DeterministicDice dice = new DeterministicDice(100);

		assertThat(diracDice.rollTillWinner(dice, 1000)).isEqualTo(745);
		assertThat(dice.getNrOfTimesRolled()).isEqualTo(993);
	}

	@Test
	void rollTillWinner_RealInput() {
		DiracDice diracDice = new DiracDice(7, 1, 10);
		DeterministicDice dice = new DeterministicDice(100);


		assertThat(diracDice.rollTillWinner(dice, 1000)).isEqualTo(795);
		System.out.println(795* dice.getNrOfTimesRolled());
		assertThat(dice.getNrOfTimesRolled()).isEqualTo(861);
	}
}