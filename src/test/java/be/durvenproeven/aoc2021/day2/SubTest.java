package be.durvenproeven.aoc2021.day2;

import be.durvenproeven.aoc2021.LineResolver;
import org.junit.jupiter.api.Test;

import java.util.function.BinaryOperator;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SubTest {

	private static final BinaryOperator<Sub> NO_COMBINER = (s1, S2) -> null;

	@Test
	void move_Forward() {
		assertThat(new Sub().move("forward 1"))
				.returns(0, Sub::getDepth)
				.returns(1, Sub::getXPosition);
	}

	@Test
	void move_Down() {
		assertThat(new Sub().move("down 3"))
				.returns(3, Sub::getDepth)
				.returns(0, Sub::getXPosition);
	}

	@Test
	void move_Up() {

		assertThat(new Sub().move("down 3").move("up 2"))
				.returns(1, Sub::getDepth)
				.returns(0, Sub::getXPosition);
	}

	@Test
	void move_WrongInput() {
		Sub sub = new Sub();

		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				sub.move("backward 1"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				sub.move("forward 1 1"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				sub.move("forward"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				sub.move("forward A"));
	}

	@Test
	void move_GivenSimpleInput() {
		Sub sub = Stream.of("forward 5",
				"down 5",
				"forward 8",
				"up 3",
				"down 8",
				"forward 2"
		).reduce(new Sub(), Sub::move, NO_COMBINER);

		assertThat(sub.getXPosition() * sub.getDepth()).isEqualTo(150);
	}

	@Test
	void move_GivenInput() {
		Sub sub = LineResolver.getStringStreamOfFile("day2.txt")
				.reduce(new Sub(), Sub::move, NO_COMBINER);
		assertThat(sub.getXPosition() * sub.getDepth()).isEqualTo(2147104);
	}

	@SuppressWarnings("PointlessArithmeticExpression")
	@Test
	void moveWithAim_Forward() {
		assertThat(new Sub().moveWithAim("down 3").moveWithAim("forward 1"))
				.returns(3, Sub::getAim)
				.returns(1, Sub::getXPosition)
				.returns(1*3, Sub::getDepth);
	}

	@Test
	void moveWithAim_Down() {
		assertThat(new Sub().moveWithAim("down 3"))
				.returns(3, Sub::getAim)
				.returns(0, Sub::getXPosition)
				.returns(0, Sub::getDepth);
	}

	@Test
	void moveWithAim_Up() {
		assertThat(new Sub().moveWithAim("down 3").moveWithAim("up 2"))
				.returns(1, Sub::getAim)
				.returns(0, Sub::getXPosition)
				.returns(0, Sub::getDepth);
	}

	@Test
	void moveWithAim_WrongInput() {
		assertThatExceptionOfType(UnsupportedOperationException.class).isThrownBy(() ->
				new Sub().moveWithAim("backward 1"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Sub().moveWithAim("forward 1 1"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Sub().moveWithAim("forward"));
		assertThatIllegalArgumentException().isThrownBy(() ->
				new Sub().moveWithAim("forward A"));
	}

	@Test
	void moveWithAim_GivenSimpleInput() {
		Sub sub = Stream.of("forward 5",
				"down 5",
				"forward 8",
				"up 3",
				"down 8",
				"forward 2"
		).reduce(new Sub(), Sub::moveWithAim, NO_COMBINER);
		assertThat(sub.getXPosition() * sub.getDepth()).isEqualTo(900);
	}

	@Test
	void moveWithAim_GivenInput() {
		Sub sub = LineResolver.getStringStreamOfFile("day2.txt")
				.reduce(new Sub(), Sub::moveWithAim, NO_COMBINER);
		assertThat(sub.getXPosition() * sub.getDepth()).isEqualTo(2044620088);
	}

}