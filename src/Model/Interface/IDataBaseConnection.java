package Model.Interface;

import java.util.Vector;

import Properties.Player;

public interface IDataBaseConnection {
	public void createConnection();
	public void createPlayer(String playerName);
	public Vector<Player> getPlayers();
}
