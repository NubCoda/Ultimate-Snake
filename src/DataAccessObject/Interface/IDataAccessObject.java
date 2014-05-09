package DataAccessObject.Interface;

import java.util.Vector;

import Properties.Player;

public interface IDataAccessObject {
	public void createConnection();
	public void createPlayer(Player player);
	public Vector<Player> getPlayers();
}
