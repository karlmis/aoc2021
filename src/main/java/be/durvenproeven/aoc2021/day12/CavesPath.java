package be.durvenproeven.aoc2021.day12;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiPredicate;

import static be.durvenproeven.aoc2021.CollectionUtils.createListWith;

public class CavesPath {
	private final String from;
	private final String end;

	private List<ConnectionWithDirection> connections = new ArrayList<>();
	private final SmallCaves smallCaves;

	public CavesPath(String from, String end, CavesConnection connection) {
		this.from = from;
		this.end = end;
		if (connection.getFirst().equals(from)) {
			connections.add(new ConnectionWithDirection(connection, false));
		} else if (connection.getSecond().equals(from)) {
			connections.add(new ConnectionWithDirection(connection, true));
		} else {
			throw new IllegalArgumentException();
		}

		smallCaves = createSmallCaves(connections.get(0).getTo());

	}

	private SmallCaves createSmallCaves(String toCave) {
		if (StringUtils.isAllLowerCase(toCave)) {
			return new SmallCaves(toCave);
		}
		return new SmallCaves();
	}

	private CavesPath(String from, String end, List<ConnectionWithDirection> connections, SmallCaves smallCaves) {
		this.from = from;
		this.end = end;
		this.connections = connections;
		this.smallCaves = smallCaves;
	}

	public Optional<CavesPath> addConnection(CavesConnection connection, BiPredicate<SmallCaves, String> notAllowedPredicate) {
		if (!connection.contains(getEndPoint().getTo())) {
			return Optional.empty();
		}
		if (connection.contains(from)) {//niet terug naar start
			return Optional.empty();
		}
		ConnectionWithDirection connectionWithDirection =
				new ConnectionWithDirection(connection, connection.getSecond().equals(getEndPoint().getTo()));
		if (StringUtils.isAllLowerCase(connectionWithDirection.getTo())) {
			if (notAllowedPredicate.test(smallCaves, connectionWithDirection.getTo())) {
				return Optional.empty();
			} else {
				return Optional.of(
						new CavesPath(from, end,
								createListWith(connections, connectionWithDirection),
								smallCaves.add(connectionWithDirection.getTo())
						));
			}
		} else {
			return Optional.of(
					new CavesPath(from, end,
							createListWith(connections, connectionWithDirection),
							smallCaves
					));
		}
	}

	public Optional<CavesPath> addConnectionExtended(CavesConnection connection) {
		if (!connection.contains(getEndPoint().getTo())) {
			return Optional.empty();
		}
		if (connection.contains(from)) {//niet terug naar start
			return Optional.empty();
		}
		BiPredicate<SmallCaves, String> notAllowedPredicate= (sc, s)-> sc.getNrOfOccurences(s)==2 ||
				(sc.getNrOfOccurences(s)==1 && smallCaves.getMaximumNrOfOccurences() == 2);
		ConnectionWithDirection connectionWithDirection =
				new ConnectionWithDirection(connection, connection.getSecond().equals(getEndPoint().getTo()));
		if (StringUtils.isAllLowerCase(connectionWithDirection.getTo())) {
			Integer nrOfPasses = smallCaves.getNrOfOccurences(connectionWithDirection.getTo());
			if (notAllowedPredicate.test(smallCaves, connectionWithDirection.getTo())){
					//nrOfPasses == 2 || nrOfPasses == 1 && smallCaves.getMaximumNrOfOccurences() == 2) {
				return Optional.empty();
			}
			return Optional.of(
					new CavesPath(from, end,
							createListWith(connections, connectionWithDirection),
							smallCaves.add(connectionWithDirection.getTo())
					));
		} else {
			return Optional.of(
					new CavesPath(from, end,
							createListWith(connections, connectionWithDirection),
							smallCaves
					));
		}
	}

	boolean isFinished() {
		ConnectionWithDirection cavesConnection = getEndPoint();
		return cavesConnection.getTo().equals(end);
	}

	private ConnectionWithDirection getEndPoint() {
		return connections.get(connections.size() - 1);
	}

	public List<String> getConnectionStrings() {
		List<String> res = new ArrayList<>();
		res.add(from);
		connections.stream()
				.map(ConnectionWithDirection::getTo)
				.forEach(res::add);
		return res;
	}

	private static class ConnectionWithDirection {
		private CavesConnection connection;
		private boolean reversed;

		public ConnectionWithDirection(CavesConnection connection, boolean reversed) {
			this.connection = connection;
			this.reversed = reversed;
		}

		String getFrom() {
			if (reversed) {
				return connection.getSecond();
			}
			return connection.getFirst();
		}

		String getTo() {
			if (reversed) {
				return connection.getFirst();
			}
			return connection.getSecond();
		}
	}
}
