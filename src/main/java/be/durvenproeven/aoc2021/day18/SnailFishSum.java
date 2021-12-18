package be.durvenproeven.aoc2021.day18;

import org.apache.commons.lang.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class SnailFishSum {

	private int leftValue;
	private SnailFishSum leftSum;

	private int rightValue;
	private SnailFishSum rightSum;

	private SnailFishSum(int leftValue, SnailFishSum leftSum, int rightValue, SnailFishSum rightSum) {
		this.leftValue = leftValue;
		this.leftSum = leftSum;
		this.rightValue = rightValue;
		this.rightSum = rightSum;
	}

	public SnailFishSum(SnailFishSum other) {
		this(
				other.leftValue,
				Optional.ofNullable(other.leftSum).map(SnailFishSum::new).orElse(null),
				other.rightValue,
				Optional.ofNullable(other.rightSum).map(SnailFishSum::new).orElse(null)
		);
	}

	public static SnailFishSum create(int leftValue, int rightNr) {
		return new SnailFishSum(leftValue, null, rightNr, null);
	}

	public static SnailFishSum create(int leftValue, SnailFishSum rightSum) {
		return new SnailFishSum(leftValue, null, 0, rightSum);
	}

	public static SnailFishSum create(SnailFishSum leftSum, int rightNr) {
		return new SnailFishSum(0, leftSum, rightNr, null);
	}

	public static SnailFishSum create(SnailFishSum leftSum, SnailFishSum rightSum) {
		return new SnailFishSum(0, leftSum, 0, rightSum);
	}

	public static SnailFishSum create(String s) {
		if (StringUtils.countMatches(s, "[") == 1) {
			List<Integer> nrs = Arrays.stream(StringUtils.substringBetween(s, "[", "]").trim().split(","))
					.map(Integer::parseInt)
					.toList();
			return create(nrs.get(0), nrs.get(1));
		}
		String innerString = s.substring(1, s.length() - 1);
		int indexCommaSeparator = getIndexAfterClosingInner(innerString);

		String firstPart = innerString.substring(0, indexCommaSeparator);
		String secondPart = innerString.substring(indexCommaSeparator + 1);

		if (firstPart.contains("[")) {
			SnailFishSum first = create(firstPart);
			if (secondPart.contains("[")) {
				return create(first, create(secondPart));
			} else {
				return create(first, Integer.parseInt(secondPart));
			}
		} else {
			int first = Integer.parseInt(firstPart);
			if (secondPart.contains("[")) {
				return create(first, create(secondPart));
			} else {
				return create(first, Integer.parseInt(secondPart));
			}

		}

	}

	private static int getIndexAfterClosingInner(String s) {
		int openingBrackets = 0;
		for (int i = 0; i < s.length(); i++) {
			String nextPart = s.substring(i, i + 1);
			if ("]".equals(nextPart)) {
				openingBrackets--;
			}
			if ("[".equals(nextPart)) {
				openingBrackets++;
			}
			if (",".equals(nextPart) && openingBrackets == 0) {
				return i;
			}
		}
		return -1;
	}

	SnailFishSum add(SnailFishSum second) {
		SnailFishSum snailFishSum = create(this, second);
		snailFishSum.reduceCompletely();
		return snailFishSum;
	}

	void reduce() {
		Optional<ToAdd> toAdd = this.reducePairs(4);
		if (toAdd.isEmpty()) {
			this.reduceBigNr();
		}

	}

	void reduceCompletely() {
		System.out.println(this.toPrettyString(0));
		while (true) {
			Optional<ToAdd> pairReduced = reducePairs(4);
			if (pairReduced.isEmpty()) {
				Optional<ToAdd> bigNrREduction = reduceBigNr();
				if (bigNrREduction.isEmpty()) {
					return;
				}
			}
		}
	}

	private void addToFirst(ToAdd toAdd) {
		if (leftSum == null) {
			leftValue += toAdd.rightValue;
		} else {
			leftSum.addToFirst(toAdd);
		}
	}

	private void addToLast(ToAdd toAdd) {
		if (rightSum == null) {
			rightValue += toAdd.leftValue;
		} else {
			rightSum.addToLast(toAdd);
		}
	}

	Optional<ToAdd> reducePairs(int level) {
		if (level == 1) {
			if (leftSum != null) {
				ToAdd toAddToHigher = new ToAdd(leftSum.leftValue, leftSum.rightValue);
				leftSum = null;
				leftValue = 0;
				if (rightSum != null) {
					rightSum.addToFirst(toAddToHigher);
				} else {
					rightValue += toAddToHigher.rightValue();
				}
				return Optional.of(toAddToHigher.withoutRight());
			}/* else {
				Optional<ToAdd> leftNrExceeded = changeLeftExceeded();
				if (leftNrExceeded.isPresent()) {
					return leftNrExceeded;
				}
			}*/
			if (rightSum != null) {
				ToAdd toAddToHigher = new ToAdd(rightSum.leftValue, rightSum.rightValue);
				leftValue += rightSum.leftValue;
				rightSum = null;
				rightValue = 0;
				return Optional.of(toAddToHigher.withoutLeft());
			} /*else {
				Optional<ToAdd> rightNrExceeded = changeRightExceeded();
				if (rightNrExceeded.isPresent()) {
					return rightNrExceeded;
				}
			}*/
			return Optional.empty();
		}
//		Optional<ToAdd> leftNrExceeded = changeLeftExceeded();
//		if (leftNrExceeded.isPresent()) {
//			return leftNrExceeded;
//		}
		Optional<ToAdd> firstReduced = Optional.ofNullable(leftSum)
				.flatMap(s -> s.reducePairs(level - 1));
		if (firstReduced.isPresent()) {
			if (rightSum == null) {
				rightValue += firstReduced.get().rightValue;
			} else {
				rightSum.addToFirst(firstReduced.get());
			}
			return firstReduced.map(ToAdd::withoutRight);
		}

//		Optional<ToAdd> rightNrExceeded = changeRightExceeded();
//		if (rightNrExceeded.isPresent()) {
//			return rightNrExceeded;
//		}
		Optional<ToAdd> rightReduced = Optional.ofNullable(rightSum)
				.flatMap(s -> s.reducePairs(level - 1));
		if (rightReduced.isPresent()) {
			if (leftSum == null) {
				leftValue += rightReduced.get().leftValue();
			} else {
				leftSum.addToLast(rightReduced.get());
			}
			return rightReduced.map(ToAdd::withoutLeft);
		}
		return rightReduced;
	}

	Optional<ToAdd> reduceBigNr() {
		if (leftSum == null) {
			Optional<ToAdd> leftNrExceeded = changeLeftExceeded();
			if (leftNrExceeded.isPresent()) {
				return leftNrExceeded;
			}
		} else {
			Optional<ToAdd> toAdd = leftSum.reduceBigNr();
			if (toAdd.isPresent()) {
				return toAdd;
			}
		}
		if (rightSum == null) {
			Optional<ToAdd> rightNrExceeded = changeRightExceeded();
			if (rightNrExceeded.isPresent()) {
				return rightNrExceeded;
			}
		} else {
			Optional<ToAdd> toAdd = rightSum.reduceBigNr();
			if (toAdd.isPresent()) {
				return toAdd;
			}
		}
		return Optional.empty();
	}

	private Optional<ToAdd> changeLeftExceeded() {
		if (leftSum == null && leftValue > 9) {
			leftSum = SnailFishSum.create(leftValue / 2, leftValue - (leftValue / 2));
			leftValue = 0;
			return Optional.of(new ToAdd(0, 0));
		}
		return Optional.empty();
	}

	private Optional<ToAdd> changeRightExceeded() {
		if (rightSum == null && rightValue > 9) {
			rightSum = SnailFishSum.create(rightValue / 2, rightValue - (rightValue / 2));
			rightValue = 0;
			return Optional.of(new ToAdd(0, 0));
		}
		return Optional.empty();
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SnailFishSum that = (SnailFishSum) o;
		return leftValue == that.leftValue && rightValue == that.rightValue && Objects.equals(leftSum, that.leftSum) && Objects.equals(rightSum, that.rightSum);
	}

	@Override
	public int hashCode() {
		return Objects.hash(leftSum, rightValue, rightSum);
	}

	@Override
	public String toString() {
		return "[" +
				Optional.ofNullable(leftSum).map(Object::toString).orElse("" + leftValue)
				+ "," +
				Optional.ofNullable(rightSum).map(Object::toString).orElse("" + rightValue)
				+ "]";
	}

	public String toPrettyString(int nr) {
		if (leftSum == null && rightSum == null) {
			return StringUtils.rightPad("", nr) + "[" + leftValue + "," + rightValue + "]";
		}
		return new StringBuilder(StringUtils.rightPad("", nr)).append("[").append("\n")
				.append(printOut(nr + 1, leftSum).orElse(StringUtils.rightPad("", nr + 1) + leftValue)).append(",\n")
				.append(printOut(nr + 1, rightSum).orElse(StringUtils.rightPad("", nr + 1) + rightValue)).append("\n")
				.append(StringUtils.rightPad("", nr)).append("]")
				.toString();
	}

	public long getMagnitude() {
		long sum = 0;
		sum += 3 * Optional.ofNullable(leftSum).map(SnailFishSum::getMagnitude).orElse((long) leftValue);
		sum += 2 * Optional.ofNullable(rightSum).map(SnailFishSum::getMagnitude).orElse((long) rightValue);
		return sum;
	}


	private Optional<String> printOut(int nr, SnailFishSum sum) {
		return Optional.ofNullable(sum).map(ls -> ls.toPrettyString(nr));
	}

	private record ToAdd(int leftValue, int rightValue) {
		ToAdd withoutLeft() {
			return new ToAdd(0, rightValue);
		}

		ToAdd withoutRight() {
			return new ToAdd(leftValue, 0);
		}

		boolean hasLeft() {
			return 0 != leftValue;
		}

		boolean hasRight() {
			return 0 != rightValue;
		}

		boolean isDone() {
			return !hasLeft() && !hasRight();
		}

	}
}
