package be.durvenproeven.aoc2021.day7;

import be.durvenproeven.aoc2021.day7.CrabSwarm;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CrabSwarmTest {

	@Test
	void getDistance() {
		CrabSwarm crabSwarm = new CrabSwarm(List.of(16, 1, 2, 0, 4, 2, 7, 1, 2, 14));

		assertThat(crabSwarm.getDistance(2)).isEqualTo(37);
		assertThat(crabSwarm.getDistance(1)).isEqualTo(41);
		assertThat(crabSwarm.getDistance(3)).isEqualTo(39);
	}
}