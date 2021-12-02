package be.durvenproeven.aoc2021.day2;

import static com.google.common.base.Preconditions.checkArgument;

public class Sub {
	private int depth = 0;
	private int xPosition = 0;
	private int aim = 0;

	public Sub() {
	}

	private Sub(int depth, int xPosition) {
		this.depth = depth;
		this.xPosition = xPosition;
	}

	public Sub(int depth, int xPosition, int aim) {
		this.depth = depth;
		this.xPosition = xPosition;
		this.aim = aim;
	}

	public Sub move(String input) {
		String[] s = input.split(" ");
		checkArgument(s.length==2);

		String direction = s[0];
		int nr = Integer.parseInt(s[1]);

		if (direction.equals("forward")) {
			return new Sub(depth, xPosition+nr);
		}
		if (direction.equals("down")) {
			return new Sub(depth+nr, xPosition);
		}
		if (direction.equals("up")) {
			return new Sub(depth-nr, xPosition);
		}
		throw new UnsupportedOperationException();
	}

	public Sub moveWithAim(String input) {
		String[] s = input.split(" ");
		checkArgument(s.length==2);

		String direction = s[0];
		int nr = Integer.parseInt(s[1]);

		if (direction.equals("forward")) {
			return new Sub(depth+nr * aim, xPosition+nr, aim);
		}
		if (direction.equals("down")) {
			return new Sub(depth, xPosition, aim+nr);
		}
		if (direction.equals("up")) {
			return new Sub(depth, xPosition, aim - nr);
		}
		throw new UnsupportedOperationException();
	}

	public int getDepth() {
		return depth;
	}

	public int getxPosition() {
		return xPosition;
	}

	public int getAim() {
		return aim;
	}
}
