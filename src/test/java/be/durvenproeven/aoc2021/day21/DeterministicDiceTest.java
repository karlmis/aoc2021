package be.durvenproeven.aoc2021.day21;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DeterministicDiceTest {

	@Test
	void getNextRolls() {
		DeterministicDice deterministicDice = new DeterministicDice(5);

		assertThat(deterministicDice.getNextRolls(3)).isEqualTo(1+2+3);
		assertThat(deterministicDice.getNextRolls(3)).isEqualTo(4+5+1);
		assertThat(deterministicDice.getNextRolls(3)).isEqualTo(2+3+4);

		assertThat(deterministicDice.getNrOfTimesRolled()).isEqualTo(9);
	}
}