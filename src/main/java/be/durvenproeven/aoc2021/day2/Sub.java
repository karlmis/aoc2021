package be.durvenproeven.aoc2021.day2;

public class Sub {
	private int depth=0;
	private int xPosition=0;
	private int aim =0;

	public Sub() {
	}

	public void move(String input){
		String[] s = input.split(" ");
		if (s[0].equals("forward")){
			xPosition+= Integer.parseInt(s[1]);
			return;
		}
		if (s[0].equals("down")){
			depth+= Integer.parseInt(s[1]);
			return;
		}
		if (s[0].equals("up")){
			depth-= Integer.parseInt(s[1]);
			return;
		}
		throw new UnsupportedOperationException();
	}

	public void moveWithAim(String input){
		String[] s = input.split(" ");
		if (s[0].equals("forward")){
			int nr = Integer.parseInt(s[1]);
			this.xPosition += nr;
			depth+= nr *aim;
			return;
		}
		if (s[0].equals("down")){
			aim += Integer.parseInt(s[1]);
			return;
		}
		if (s[0].equals("up")){
			aim -= Integer.parseInt(s[1]);
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
