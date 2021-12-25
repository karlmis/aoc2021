package be.durvenproeven.aoc2021.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SeaCucumber {
	private final int maxY;
	private final int maxX;
	private List<List<Type>> cucumbers;

	public SeaCucumber(List<List<Type>> cucumbers) {
		this.cucumbers = cucumbers;
		maxY = cucumbers.size();
		maxX = cucumbers.get(0).size();
	}

	public static SeaCucumber fromStrings(List<String> input) {
		return new SeaCucumber(input.stream()
				.map(SeaCucumber::toCucumbers)
				.toList());
	}

	private static List<Type> toCucumbers(String s) {
		return s.chars().mapToObj(SeaCucumber::toTypeOrNull).toList();
	}

	private static Type toTypeOrNull(int i) {
		if (i == 'v') {
			return Type.SOUTH;
		}
		if (i == '>') {
			return Type.EAST;
		}
		return null;
	}

	public SeaCucumber move() {
		return moveEast()
				.moveSouth()
				;
	}

	private SeaCucumber moveEast() {
		List<List<Type>> newCucumbers = IntStream.range(0, cucumbers.size())
				.mapToObj(this::moveRowEast)
				.toList();

		return new SeaCucumber(newCucumbers);
	}

	private SeaCucumber moveSouth() {
		List<List<Type>> newCu = IntStream.range(0, cucumbers.size())
				.mapToObj(ign -> createListWithNullsOfSize(maxX))
				.collect(Collectors.toCollection(ArrayList::new));

		IntStream.range(0, maxX)
				.forEach(columnIndex -> moveSouthColumn(columnIndex, newCu));

		return new SeaCucumber(newCu);
	}

	private List<Type> moveRowEast(int rowIndex) {
		List<Type> res = createListWithNullsOfSize(maxX);
		for (int i = maxX - 1; i >= 0; i--) {
			Type typeAtLocation = getType(i, rowIndex);
			if (typeAtLocation == Type.EAST && getType(i + 1, rowIndex) == null) {
				setType(i + 1, Type.EAST, res);
				setType(i, null, res);
			} else if (typeAtLocation != null) {
				setType(i, typeAtLocation, res);
			}
		}
		return res;
	}

	private ArrayList<Type> createListWithNullsOfSize(int maxX) {
		return IntStream.range(0, maxX)
				.mapToObj(ign -> (Type) null)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	private void moveSouthColumn(int columnIndex, List<List<Type>> newCu) {
		for (int i = maxY - 1; i >= 0; i--) {
			Type typeAtLocation = getType(columnIndex, i);
			if (typeAtLocation == Type.SOUTH && getType(columnIndex, i + 1) == null) {
				setType(columnIndex, i + 1, Type.SOUTH, newCu);
				setType(columnIndex, i, null, newCu);
			} else if (typeAtLocation!= null){
				setType(columnIndex, i, typeAtLocation, newCu);
			}
		}
	}

	private Type getType(int column, int rowIndex) {
		return cucumbers.get(rowIndex % maxY).get(column % maxX);
	}

	private Type setType(int column, Type element, List<Type> newRow) {
		return newRow.set(column % maxX, element);
	}

	private Type setType(int column, int row, Type element, List<List<Type>> newRow) {
		return newRow.get(row % maxY).set(column % maxX, element);
	}


	private enum MoveStatus {
		MOVE, STAY, NOTHING
	}

	public boolean canMove() {
		throw new UnsupportedOperationException("implement me!");
	}

	public static int moveAsLongAsYouCan(SeaCucumber seaCucumber){
		SeaCucumber previous= seaCucumber;
		int counter=0;
		while(true) {
			counter++;
			SeaCucumber nextCu = previous.move();
			if (nextCu.equals(previous)){
				return counter;
			}
			previous= nextCu;
		}


	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		SeaCucumber that = (SeaCucumber) o;
		return Objects.equals(cucumbers, that.cucumbers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(cucumbers);
	}

	enum Type {
		SOUTH("v"), EAST(">");

		private String param;

		Type(String param) {
			this.param = param;
		}

		public String getParam() {
			return param;
		}
	}

	@Override
	public String toString() {
		return "\n" + cucumbers.stream()
				.map(s -> s.stream().map(t -> Optional.ofNullable(t).map(Type::getParam).orElse(".")).collect(Collectors.joining()))
				.collect(Collectors.joining("\n"));
	}
}
