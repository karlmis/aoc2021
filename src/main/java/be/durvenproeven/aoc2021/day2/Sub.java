package be.durvenproeven.aoc2021.day2;

public class Sub {
	private int depth = 0;
	private int xPosition = 0;
	private int aim = 0;

	public Sub() {
	}

	public void move(String input) {
		String[] s = input.split(" ");
		String direction = s[0];
		int nr = Integer.parseInt(s[1]);

		if (direction.equals("forward")) {
			this.xPosition += nr;
			return;
		}
		if (direction.equals("down")) {
			depth += nr;
			return;
		}
		if (direction.equals("up")) {
			depth -= nr;
			return;
		}
		throw new UnsupportedOperationException();
	}

	public void moveWithAim(String input) {
		String[] s = input.split(" ");
		String direction = s[0];
		int nr = Integer.parseInt(s[1]);
		if (direction.equals("forward")) {
			this.xPosition += nr;
			depth += nr * aim;
			return;
		}
		if (direction.equals("down")) {
			aim += nr;
			return;
		}
		if (direction.equals("up")) {
			aim -= nr;
			return;
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
