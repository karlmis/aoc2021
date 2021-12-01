package be.durvenproeven.aoc2021;

import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LineResolver {
	public static int group(String fileName, Function<List<String>, Integer> function) {
		try {
			URL resourceUri = LineResolver.class.getClassLoader().getResource(fileName);

			Path path = Paths.get(resourceUri.toURI());
			List<String> stringList = Files.lines(path)
					.collect(toList());
			int valid = 0;
			List<String> input = new ArrayList<>();
			for (String s : stringList) {
				if (StringUtils.isBlank(s)) {
					if (!input.isEmpty()) {
						valid+= function.apply(input);
					}
					input = new ArrayList<>();

				} else {
					input.add(s);
				}

			}
			if (!input.isEmpty()) {
				valid+= function.apply(input);
			}
			return valid;
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}
	//separated by empty line
	public static <T> List<T> groupToList(String fileName, Function<List<String>, T> function) {
		ArrayList<T> res = new ArrayList<>();
		try {
			URL resourceUri = LineResolver.class.getClassLoader().getResource(fileName);

			Path path = Paths.get(resourceUri.toURI());
			List<String> stringList = Files.lines(path)
					.collect(toList());
			int valid = 0;
			List<String> input = new ArrayList<>();
			for (String s : stringList) {
				if (StringUtils.isBlank(s)) {
					if (!input.isEmpty()) {
						res.add(function.apply(input));
					}
					input = new ArrayList<>();

				} else {
					input.add(s);
				}

			}
			if (!input.isEmpty()) {
				res.add(function.apply(input));
			}
			return res;
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

	public static Stream<String> getStringStreamOfFile(String fileName)  {
		try {
			URL resourceUri = LineResolver.class.getClassLoader().getResource(fileName);

			Path path = Paths.get(resourceUri.toURI());
			return Files.lines(path);
		} catch (URISyntaxException | IOException e) {
			e.printStackTrace();
			throw new IllegalArgumentException(e);
		}
	}

}
