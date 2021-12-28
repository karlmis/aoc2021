package be.durvenproeven.aoc2021.day22;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CubeWithLights {
	private Cube cube;
	private List<CubeWithLights> cubesOtherLightStatus= new ArrayList<>();

	public CubeWithLights(Cube cube) {
		this.cube = cube;
	}

	public long getLights(){
		return cube.getSize() - cubesOtherLightStatus.stream()
				.mapToLong(CubeWithLights::getLights)
				.sum();
	}

	public List<Cube> addSameLight(Cube other){
		Optional<Cube> intersection = cube.intersection(other);
		if (intersection.isEmpty()){
			return List.of(other);
		}
		cubesOtherLightStatus.forEach(c -> c.addReverseLight(intersection.get()));
		return other.difference(cube);
	}

	public void addReverseLight(Cube other){
		Optional<Cube> intersection = cube.intersection(other);
		if (intersection.isEmpty()){
			return ;
		}
		//eerst overlapping
		List<Cube> overlappings= List.of(intersection.get());
		for (CubeWithLights otherLightStatus : cubesOtherLightStatus) {
			List<Cube> newOverlappings= new ArrayList<>();
			for (Cube overlapping : overlappings) {
				newOverlappings.addAll(otherLightStatus.addSameLight(overlapping));
			}
			overlappings= newOverlappings;

		}
		overlappings.stream()
				.map(CubeWithLights::new)
				.forEach(cubesOtherLightStatus::add);

	}
}
