package be.durvenproeven.aoc2021.day12;

import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static be.durvenproeven.aoc2021.CollectionUtils.createListWith;

public class CavesPath {
	private final String from;
	private final String end;

	private List<ConnectionWithDirection> connections= new ArrayList<>();
	private List<String> smallCaves= new ArrayList<>();

	public CavesPath(String from, String end, CavesConnection connection) {
		this.from = from;
		this.end = end;
		if (connection.getFirst().equals(from)){
			connections.add(new ConnectionWithDirection(connection, false));
		}else if (connection.getSecond().equals(from)){
			connections.add(new ConnectionWithDirection(connection, true));
		}else {
			throw new IllegalArgumentException();
		}
		if (StringUtils.isAllLowerCase(connections.get(0).getTo())){
			smallCaves.add(connections.get(0).getTo());
		}
	}

	private CavesPath(String from, String end, List<ConnectionWithDirection> connections, List<String> smallCaves) {
		this.from = from;
		this.end = end;
		this.connections = connections;
		this.smallCaves = smallCaves;
	}

	public Optional<CavesPath> addConnection(CavesConnection connection){
		if (!connection.contains(getEndPoint().getTo())){
			return Optional.empty();
		}
		if (connection.contains(from)){//niet terug naar start
			return Optional.empty();
		}
		ConnectionWithDirection connectionWithDirection =
				new ConnectionWithDirection(connection, connection.getSecond().equals(getEndPoint().getTo()));
		if (StringUtils.isAllLowerCase(connectionWithDirection.getTo())){
			if (smallCaves.contains(connectionWithDirection.getTo())){
				return Optional.empty();
			}else{
				return Optional.of(
						new CavesPath(from, end,
								createListWith(connections, connectionWithDirection),
								createListWith(smallCaves, connectionWithDirection.getTo())
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

	boolean isFinished(){
		ConnectionWithDirection cavesConnection = getEndPoint();
		return cavesConnection.getTo().equals(end);
	}

	private ConnectionWithDirection getEndPoint() {
		return connections.get(connections.size() - 1);
	}

	public List<String> getConnectionStrings(){
		List<String> res= new ArrayList<>();
		res.add(from);
		connections.stream()
				.map(ConnectionWithDirection::getTo)
				.forEach(res::add);
		return res;
	}

	private static class ConnectionWithDirection{
		private CavesConnection connection;
		private boolean reversed;

		public ConnectionWithDirection(CavesConnection connection, boolean reversed) {
			this.connection = connection;
			this.reversed = reversed;
		}

		String getFrom(){
			if (reversed){
				return connection.getSecond();
			}
			return connection.getFirst();
		}

		String getTo(){
			if (reversed){
				return connection.getFirst();
			}
			return connection.getSecond();
		}
	}
}
