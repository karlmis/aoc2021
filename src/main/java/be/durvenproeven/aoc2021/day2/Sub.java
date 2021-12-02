package be.durvenproeven.aoc2021.day2;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.BiFunction;

import static com.google.common.base.Preconditions.checkArgument;

public class Sub {
	private int depth = 0;
	private int xPosition = 0;
	private int aim = 0;

	public Sub() {
	}

	private Sub(int depth, int xPosition) {
		this(depth, xPosition, 0);
	}

	private Sub(int depth, int xPosition, int aim) {
		this.depth = depth;
		this.xPosition = xPosition;
		this.aim = aim;
	}

	private enum Mover {
		FORWARD((sub, nr) -> new Sub(sub.depth, sub.xPosition + nr)),
		DOWN((sub, nr) -> new Sub(sub.depth + nr, sub.xPosition)),
		UP((sub, nr) -> new Sub(sub.depth - nr, sub.xPosition));

		Mover(BiFunction<Sub, Integer, Sub> mover) {
			this.mover = mover;
		}

		private final BiFunction<Sub, Integer, Sub> mover;

		Sub Move(Sub sub, int nr) {
			return mover.apply(sub, nr);
		}

		static Optional<Mover> getMover(String name) {
			return Arrays.stream(values()).
					filter(s -> s.name().equalsIgnoreCase(name))
					.findFirst();
		}
	}
	private enum MoverWithAim {
		FORWARD((sub, nr) -> new Sub(sub.depth + nr * sub.aim, sub.xPosition + nr, sub.aim)),
		DOWN((sub, nr) -> new Sub(sub.depth, sub.xPosition, sub.aim + nr)),
		UP((sub, nr) ->new Sub(sub.depth, sub.xPosition, sub.aim - nr) );

		MoverWithAim(BiFunction<Sub, Integer, Sub> mover) {
			this.mover = mover;
		}

		private final BiFunction<Sub, Integer, Sub> mover;

		Sub move(Sub sub, int nr) {
			return mover.apply(sub, nr);
		}

		static Optional<MoverWithAim> getMover(String name) {
			return Arrays.stream(values()).
					filter(s -> s.name().equalsIgnoreCase(name))
					.findFirst();
		}
	}

	public Sub moveWithAim(String input) {
		String[] s = input.split(" ");
		checkArgument(s.length == 2);

		String direction = s[0];
		int nr = Integer.parseInt(s[1]);

		return MoverWithAim.getMover(direction)
				.map(d -> d.move(this, nr))
				.orElseThrow(UnsupportedOperationException::new);
	}

	public Sub move(String input) {
		String[] s = input.split(" ");
		checkArgument(s.length == 2);

		String direction = s[0];
		int nr = Integer.parseInt(s[1]);

		return Mover.getMover(direction)
				.map(d -> d.Move(this, nr))
				.orElseThrow(UnsupportedOperationException::new);
	}

	public int getDepth() {
		return depth;
	}

	public int getXPosition() {
		return xPosition;
	}

	public int getAim() {
		return aim;
	}
}
