package be.durvenproeven.aoc2021;

public class MathHelper {
	public static long lcm(long number1, long number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		long absNumber1 = Math.abs(number1);
		long absNumber2 = Math.abs(number2);
		long absHigherNumber = Math.max(absNumber1, absNumber2);
		long absLowerNumber = Math.min(absNumber1, absNumber2);
		long lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}

	public static int lcm(int number1, int number2) {
		if (number1 == 0 || number2 == 0) {
			return 0;
		}
		int absNumber1 = Math.abs(number1);
		int absNumber2 = Math.abs(number2);
		int absHigherNumber = Math.max(absNumber1, absNumber2);
		int absLowerNumber = Math.min(absNumber1, absNumber2);
		int lcm = absHigherNumber;
		while (lcm % absLowerNumber != 0) {
			lcm += absHigherNumber;
		}
		return lcm;
	}
}
