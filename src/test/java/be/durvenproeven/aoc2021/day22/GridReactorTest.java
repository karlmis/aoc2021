package be.durvenproeven.aoc2021.day22;

import be.durvenproeven.aoc2021.LineResolver;
import be.durvenproeven.aoc2021.day19.Coordinates;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GridReactorTest {

	@Test
	void firstExample() {
		GridReactor gridReactor = new GridReactor(50);

		gridReactor.turnOn(new Coordinates(10,10,10), new Coordinates(12,12,12));
		gridReactor.turnOn(new Coordinates(11,11,11), new Coordinates(13,13,13));
		gridReactor.turnOff(new Coordinates(9,9,9), new Coordinates(11,11,11));
		gridReactor.turnOn(new Coordinates(10,10,10), new Coordinates(10,10,10));

		assertThat(gridReactor.getNrOfCubesOn()).isEqualTo(39);

	}

	@Test
	void secondExample() {
		GridReactor gridReactor = new GridReactor(50);
		Stream.of("on x=-20..26,y=-36..17,z=-47..7" ,
				"on x=-20..33,y=-21..23,z=-26..28" ,
				"on x=-22..28,y=-29..23,z=-38..16" ,
				"on x=-46..7,y=-6..46,z=-50..-1" ,
				"on x=-49..1,y=-3..46,z=-24..28" ,
				"on x=2..47,y=-22..22,z=-23..27" ,
				"on x=-27..23,y=-28..26,z=-21..29" ,
				"on x=-39..5,y=-6..47,z=-3..44" ,
				"on x=-30..21,y=-8..43,z=-13..34" ,
				"on x=-22..26,y=-27..20,z=-29..19" ,
				"off x=-48..-32,y=26..41,z=-47..-37" ,
				"on x=-12..35,y=6..50,z=-50..-2" ,
				"off x=-48..-32,y=-32..-16,z=-15..-5" ,
				"on x=-18..26,y=-33..15,z=-7..46" ,
				"off x=-40..-22,y=-38..-28,z=23..41" ,
				"on x=-16..35,y=-41..10,z=-47..6" ,
				"off x=-32..-23,y=11..30,z=-14..3" ,
				"on x=-49..-5,y=-3..45,z=-29..18" ,
				"off x=18..30,y=-20..-8,z=-3..13" ,
				"on x=-41..9,y=-7..43,z=-33..15" ,
				"on x=-54112..-39298,y=-85059..-49293,z=-27449..7877",
				"on x=967..23432,y=45373..81175,z=27513..53682"
		)
				.map(this::getOperation)
				.forEach(c -> c.accept(gridReactor));

		assertThat(gridReactor.getNrOfCubesOn()).isEqualTo(590784);

	}

	@Test
	void actualTest() {
		GridReactor gridReactor = new GridReactor(50);
		LineResolver.getStringStreamOfFile("day22GridReactor.txt")
				.map(this::getOperation)
				.forEach(c -> c.accept(gridReactor));

		assertThat(gridReactor.getNrOfCubesOn()).isEqualTo(553201);

	}

	public Consumer<GridReactor> getOperation(String s){
		String coordinatesPart = StringUtils.substringAfter(s, " ");
		String[] split = coordinatesPart.split(",");
		List<Integer> firstNumberList = Arrays.stream(split)
				.map(s1 -> StringUtils.substringBetween(s1, "=", ".."))
				.map(Integer::parseInt)
				.toList();
		Coordinates firstCoordinates = new Coordinates(firstNumberList.get(0), firstNumberList.get(1), firstNumberList.get(2));
		List<Integer> secondNumberList = Arrays.stream(split)
				.map(s1 -> StringUtils.substringAfter(s1,  ".."))
				.map(Integer::parseInt)
				.toList();
		Coordinates secondCoordinates = new Coordinates(secondNumberList.get(0), secondNumberList.get(1), secondNumberList.get(2));

		if (s.startsWith("on")){
			return gridReactor -> gridReactor.turnOn(firstCoordinates, secondCoordinates);
		}
		return gridReactor -> gridReactor.turnOff(firstCoordinates, secondCoordinates);
	}


}