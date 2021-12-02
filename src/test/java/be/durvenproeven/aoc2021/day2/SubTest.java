package be.durvenproeven.aoc2021.day2;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SubTest {

	@Test
	void move_Forward() {
		Sub sub = new Sub();

		sub.move("forward 1");

		assertThat(sub.getDepth()).isZero();
		assertThat(sub.getxPosition()).isEqualTo(1);

	}

	@Test
	void move_Down() {
		Sub sub = new Sub();
		sub.move("down 3");

		assertThat(sub.getDepth()).isEqualTo(3);
		assertThat(sub.getxPosition()).isZero();

	}

	@Test
	void move_Up() {
		Sub sub = new Sub();

		sub.move("down 3");
		sub.move("up 2");

		assertThat(sub.getDepth()).isEqualTo(1);
		assertThat(sub.getxPosition()).isZero();
	}

	@Test
	void move_GivenSimpleInput() {
		Sub sub = new Sub();

		Stream.of("forward 5",
				"down 5",
				"forward 8",
				"up 3",
				"down 8",
				"forward 2"
		).forEach(sub::move);
		assertThat(sub.getxPosition() * sub.getDepth()).isEqualTo(150);
	}

	@Test
	void move_GivenInput() {

		Sub sub = new Sub();
		LineResolver.getStringStreamOfFile("day2.txt")
				.forEach(sub::move);
		assertThat(sub.getxPosition() * sub.getDepth()).isEqualTo(2147104);
	}

	@Test
	void moveWithAim_Forward() {
		Sub sub = new Sub();

		sub.moveWithAim("down 3");
		sub.moveWithAim("forward 1");

		assertThat(sub.getAim()).isEqualTo(3);
		assertThat(sub.getxPosition()).isEqualTo(1);
		assertThat(sub.getDepth()).isEqualTo(1*3);
	}

	@Test
	void moveWithAim_Down() {
		Sub sub = new Sub();
		sub.moveWithAim("down 3");

		assertThat(sub.getAim()).isEqualTo(3);
		assertThat(sub.getxPosition()).isZero();
		assertThat(sub.getDepth()).isZero();

	}

	@Test
	void moveWithAim_Up() {
		Sub sub = new Sub();

		sub.moveWithAim("down 3");
		sub.moveWithAim("up 2");

		assertThat(sub.getAim()).isEqualTo(1);
		assertThat(sub.getxPosition()).isZero();
		assertThat(sub.getDepth()).isZero();

	}

	@Test
	void moveWithAim_GivenSimpleInput() {
		Sub sub = new Sub();

		Stream.of("forward 5",
				"down 5",
				"forward 8",
				"up 3",
				"down 8",
				"forward 2"
		).forEach(sub::moveWithAim);
		assertThat(sub.getxPosition() * sub.getDepth()).isEqualTo(900);
	}

	@Test
	void moveWithAim_GivenInput() {

		Sub sub = new Sub();
		LineResolver.getStringStreamOfFile("day2.txt")
				.forEach(sub::moveWithAim);
		assertThat(sub.getxPosition() * sub.getDepth()).isEqualTo(2044620088);
	}

}